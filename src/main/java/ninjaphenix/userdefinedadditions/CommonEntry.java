package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        registerItems();
    }

    private void registerItems()
    {
        Item item = new CustomItem(new Item.Settings(), Formatting.DARK_PURPLE);
        Registry.register(Registry.ITEM, "uda:test_item", item);
    }
}
