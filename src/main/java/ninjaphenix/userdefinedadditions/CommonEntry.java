package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import ninjaphenix.userdefinedadditions.config.Config;
import ninjaphenix.userdefinedadditions.config.data.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

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
        validateItems();
    }

    private void validateItems()
    {
        // Lets create a local copy because we might be fixing a lot of identifiers.
        Config instance = Config.INSTANCE;
        Item[] items = instance.getItems();
        ArrayList<Item> validItems = new ArrayList<>();

        // Check if all data is valid

        // fix mod identifiers

        // if invalid log error and don't add item to list

        this.registerItems(validItems.toArray(new Item[]{}));
    }

    private void registerItems(Item[] items)
    {
        for (Item item : items)
        {
            // Create Item.Settings
                // Create Food Component
                    // Create Status Effect Instances
            // Register Item
        }
    }
}
