package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.config.Config;
import ninjaphenix.userdefinedadditions.config.data.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class CommonEntry implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "userdefinedadditions";

    @Override
    public void onInitialize()
    {
        LOGGER.info("[{}] Mod Initialized.", MOD_ID);
        Config.initialize();
        LOGGER.info("[{}] Config Initialized.", MOD_ID);
        validate_and_registerItems();
    }

    private void validate_and_registerItems()
    {
        // Lets create a local copy because we might be fixing a lot of identifiers.
        Config instance = Config.INSTANCE;
        Item[] items = instance.getItems();

        for (Item item : items)
        {
            // Check if all data is valid

            // fix mod identifiers

            // if invalid log error and don't add item to list
        }
    }
}
