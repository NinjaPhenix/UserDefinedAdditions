package ninjaphenix.userdefinedadditions;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import ninjaphenix.userdefinedadditions.api.LoadOrderManager;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.api.readers.ReaderReader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.RegisterableReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ContentLoader
{
    public static Map<Identifier, ItemGroup> itemGroups = new HashMap<>();
    private static ContentLoader instance;
    private final String MOD_ID = "userdefinedadditions";
    private final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDirectory().toPath().resolve(MOD_ID);
    private final Jankson jankson;

    private ContentLoader()
    {
        Jankson.Builder builder = new Jankson.Builder();
        builder.registerPrimitiveTypeAdapter(Identifier.class, (it) -> new Identifier(it.toString()));
        builder.registerPrimitiveTypeAdapter(Rarity.class, (it) -> {
            switch (it.toString().toUpperCase())
            {
                case "COMMON":
                    return Rarity.COMMON;
                case "RARE":
                    return Rarity.RARE;
                case "UNCOMMON":
                    return Rarity.UNCOMMON;
                case "EPIC":
                    return Rarity.EPIC;
                default:
                    return null;
            }
        });
        // todo add api for others to expand on json content system. Including adding their own deserializers
        jankson = builder.build();
        instance = this;
    }

    public static ContentLoader getInstance()
    {
        if (instance == null) instance = new ContentLoader();
        return instance;
    }

    private void initialize(Path path)
    {
        if (Files.isDirectory(path))
        {
            final String mod_id = path.getFileName().toString();
            Iterator<String> folders = LoadOrderManager.getInstance().iterator();
            folders.forEachRemaining((name) ->
            {
                Path sub = path.resolve(name);
                if (mkDirSafely(sub))
                {
                    try
                    {
                        Files.walk(sub, 1).forEach(file ->
                        {
                            String filename = file.getFileName().toString();
                            if (!Files.isDirectory(file) && filename.endsWith(".json"))
                            {
                                System.out.println("Attempting to load: " + file);
                                final Identifier id = new Identifier(mod_id, filename.substring(0, filename.length() - 5));
                                try
                                {
                                    LOGGER.info("Reading: {}", id);
                                    JsonObject object = jankson.load(Files.newInputStream(file, StandardOpenOption.READ));
                                    ReaderReader.ReaderData d = ReaderReader.getInstance().read(object);
                                    LOGGER.info("    wants to be read by: {}, v={}", d.getType(), d.getVersion());
                                    Reader<?> serializer = ReaderManager.getInstance().get(d.getType(), d.getVersion());
                                    if (serializer == null)
                                    {
                                        LOGGER.info("       read: reader not found");
                                    }
                                    else
                                    {
                                        if (serializer instanceof RegisterableReader)
                                        {
                                            LOGGER.info("        read: {}", ((RegisterableReader<?>) serializer).read(d.getData(), id));
                                        }
                                        else
                                        {
                                            LOGGER.info("        read: {}", serializer.read(d.getData()));
                                        }
                                    }
                                }
                                catch (IOException e)
                                {
                                    LOGGER.warn("Skipping {} due to file read error.", id);
                                }
                                catch (SyntaxError e)
                                {
                                    LOGGER.warn("Skipping {} due to invalid json detected.", id);
                                    LOGGER.throwing(e);
                                }
                                catch (RuntimeException e)
                                {
                                    LOGGER.warn("Skipping {} due to wrong contents.", id);
                                    LOGGER.throwing(e);
                                }
                            }
                        });
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else LOGGER.error("Skipping content defined under {}/{}", mod_id, name);
            });
        }
    }

    void onInitialize()
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
                LOGGER.error("Failed to list sub directories of {}.", CONFIG_DIR);
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
                LOGGER.throwing(e);
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
