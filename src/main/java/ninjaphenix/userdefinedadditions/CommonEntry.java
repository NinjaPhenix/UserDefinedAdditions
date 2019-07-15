package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.config.Config;
import ninjaphenix.userdefinedadditions.config.data.GroupData;
import ninjaphenix.userdefinedadditions.config.data.ItemData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
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
        Map<Identifier, ItemGroup> itemGroups = validate_and_registerItemGroups();
        validate_and_registerItems(itemGroups);
    }

    private Map<Identifier, ItemGroup> validate_and_registerItemGroups()
    {
        Map<Identifier, ItemGroup> groups = new HashMap<>();
        // add vanillas with modified identifiers
        groups.put(new Identifier("brewing"), ItemGroup.BREWING);
        groups.put(new Identifier("tools"), ItemGroup.TOOLS);
        groups.put(new Identifier("combat"), ItemGroup.COMBAT);
        groups.put(new Identifier("decorations"), ItemGroup.DECORATIONS);
        groups.put(new Identifier("building_blocks"), ItemGroup.BUILDING_BLOCKS);
        groups.put(new Identifier("transportation"), ItemGroup.TRANSPORTATION);
        groups.put(new Identifier("misc"), ItemGroup.MISC);
        groups.put(new Identifier("materials"), ItemGroup.MATERIALS);
        groups.put(new Identifier("food"), ItemGroup.FOOD);
        groups.put(new Identifier("redstone"), ItemGroup.REDSTONE);

        GroupData[] data = Config.INSTANCE.getItemGroups();
        for (GroupData groupData : data)
        {
            Identifier identifier;

            try
            {
                if (groupData.identifier.contains(":")) identifier = new Identifier(groupData.identifier);
                else identifier = Config.INSTANCE.getId(groupData.identifier);
            }
            catch (Exception e)
            {
                LOGGER.error(
                        "[{}] Failed to create item group with identifier \"{}\" as the identifier could not be created. Please ensure that the string only consists of lower case letters, underscores and a single :",
                        MOD_ID, groupData.identifier);
                continue;
            }
            Identifier groupIcon;
            try
            {
                if (groupData.icon.contains(":")) groupIcon = new Identifier(groupData.icon);
                else groupIcon = Config.INSTANCE.getId(groupData.icon);
            }
            catch (Exception e)
            {
                LOGGER.error(
                        "[{}] Failed to create item group icon with identifier \"{}\" as the identifier could not be created. Please ensure that the string only consists of lower case letters, underscores and a single :",
                        MOD_ID, groupData.identifier);
                continue;
            }
            ItemGroup group = FabricItemGroupBuilder.create(identifier).icon(() -> new ItemStack(Registry.ITEM.get(groupIcon))).build();
            groups.put(identifier, group);
        }
        return groups;
    }

    private void validate_and_registerItems(Map<Identifier, ItemGroup> itemGroups)
    {
        ItemData[] items = Config.INSTANCE.getItems();
        for (ItemData item : items)
        {
            Registry.register(Registry.ITEM, item.identifier, item.asMCObject());
        }
    }
}
