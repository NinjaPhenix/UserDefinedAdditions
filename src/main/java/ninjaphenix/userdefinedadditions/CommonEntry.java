package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.config.Config;
import ninjaphenix.userdefinedadditions.config.data.Item;
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
            Identifier identifier;
            try
            {
                if (item.identifier.contains(":")) identifier = new Identifier(item.identifier);
                else identifier = instance.getId(item.identifier);
            }
            catch (Exception e)
            {
                LOGGER.error(
                        "[{}] Failed to create item with identifier \"{}\" as the identifier could not be created. Please ensure that the string only consists of lower case letters, underscores and a single :",
                        MOD_ID, item.identifier);
                continue;
            }
            Integer maxStackSize = item.max_stack;
            if (maxStackSize == null || maxStackSize < 1 || maxStackSize > 64)
            {
                LOGGER.warn("[{}] Item \"{}\" had an invalid stack size, using default of 64.", MOD_ID, identifier);
                maxStackSize = 64;
            }
            Formatting fontColor = Formatting.byName(item.font_color);
            if (fontColor == null)
            {
                LOGGER.warn("[{}] Item \"{}\" had an invalid font color, using default of WHITE.", MOD_ID, identifier);
                fontColor = Formatting.WHITE;
            }
            ItemGroup itemGroup;
            switch (item.item_group)
            {
                case "BREWING":
                    itemGroup = ItemGroup.BREWING;
                    break;
                case "TOOLS":
                    itemGroup = ItemGroup.TOOLS;
                    break;
                case "COMBAT":
                    itemGroup = ItemGroup.COMBAT;
                    break;
                case "DECORATIONS":
                    itemGroup = ItemGroup.DECORATIONS;
                    break;
                case "BUILDING_BLOCKS":
                    itemGroup = ItemGroup.BUILDING_BLOCKS;
                    break;
                case "MISC":
                    itemGroup = ItemGroup.MISC;
                case "TRANSPORTATION":
                    itemGroup = ItemGroup.TRANSPORTATION;
                    break;
                case "FOOD":
                    itemGroup = ItemGroup.FOOD;
                    break;
                case "REDSTONE":
                    itemGroup = ItemGroup.REDSTONE;
                    break;
                default:
                    LOGGER.warn("[{}] Item \"{}\" had an invalid item group, using default of MISC.", MOD_ID, identifier);
                    itemGroup = ItemGroup.MISC;
            }
            FoodComponent.Builder foodComponent = new FoodComponent.Builder();

        }
    }
}
