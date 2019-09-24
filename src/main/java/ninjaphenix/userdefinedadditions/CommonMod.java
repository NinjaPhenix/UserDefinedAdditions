package ninjaphenix.userdefinedadditions;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class CommonMod implements ModInitializer
{
    public static final String MOD_ID = "userdefinedadditions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDirectory().toPath().resolve(MOD_ID);
    public static Map<Identifier, ItemGroup> itemGroups = new HashMap<>();

    public void initialize(Path path) throws IOException
    {
        LOGGER.info("Folder name: " + path.getFileName());
        if (Files.isDirectory(path))
        {
            final String mod_id = path.getFileName().toString();
            Path sub = path.resolve("item_groups");
            if (dirExists(sub))
            {
                Iterator<Path> paths = Files.list(sub).iterator();
                while (paths.hasNext())
                {
                    final Path p = paths.next();
                    final String namespace = p.getFileName().toString();
                    final Identifier id = new Identifier(mod_id, namespace);
                    try
                    {
                        JsonObject object = Jankson.builder().build().load(Files.newInputStream(p, StandardOpenOption.READ));
                    }
                    catch (SyntaxError e)
                    {
                        LOGGER.warn("Skipping {} due to invalid json detected.", id);
                        e.printStackTrace();
                    }
                }
            }
            sub = path.resolve("items");
            if (dirExists(sub))
            {
                Iterator<Path> paths = Files.list(sub).iterator();
            }
            sub = path.resolve("blocks");
            if (dirExists(sub))
            {
                Iterator<Path> paths = Files.list(sub).iterator();
            }
        }
    }

    @Override
    public void onInitialize()
    {
        if (mkDirSafely(CONFIG_DIR))
        {
            try
            {
                Iterator<Path> paths = Files.list(CONFIG_DIR).iterator();
                while (paths.hasNext()) initialize(paths.next());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
        Returns true if directory exists, false otherwise.
     */
    private boolean mkDirSafely(Path dir)
    {
        if (!Files.exists(dir))
        {
            try
            {
                Files.createDirectories(dir);
            }
            catch (IOException e)
            {
                LOGGER.error("[{}] Failed to create directory: {}", MOD_ID, dir);
                e.printStackTrace();
                return false;
            }
        }
        return Files.isDirectory(dir);
    }

    private boolean dirExists(Path dir)
    {
        return Files.exists(dir) && Files.isDirectory(dir);
    }
}
