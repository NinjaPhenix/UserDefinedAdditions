package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.config.Config;
import ninjaphenix.userdefinedadditions.config.data.GroupData;
import ninjaphenix.userdefinedadditions.config.data.ItemData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class CommonEntry implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "userdefinedadditions";
    public static Map<Identifier, ItemGroup> itemGroups = new HashMap<>();

    @Override
    public void onInitialize()
    {
        LOGGER.info("[{}] Mod Initialized.", MOD_ID);
        Config.initialize();
        LOGGER.info("[{}] Config Initialized.", MOD_ID);
        validate_and_registerItemGroups();
        validate_and_registerItems();
    }

    private void validate_and_registerItemGroups()
    {
        itemGroups.put(new Identifier("brewing"), ItemGroup.BREWING);
        itemGroups.put(new Identifier("tools"), ItemGroup.TOOLS);
        itemGroups.put(new Identifier("combat"), ItemGroup.COMBAT);
        itemGroups.put(new Identifier("decorations"), ItemGroup.DECORATIONS);
        itemGroups.put(new Identifier("building_blocks"), ItemGroup.BUILDING_BLOCKS);
        itemGroups.put(new Identifier("transportation"), ItemGroup.TRANSPORTATION);
        itemGroups.put(new Identifier("misc"), ItemGroup.MISC);
        itemGroups.put(new Identifier("materials"), ItemGroup.MATERIALS);
        itemGroups.put(new Identifier("food"), ItemGroup.FOOD);
        itemGroups.put(new Identifier("redstone"), ItemGroup.REDSTONE);

        GroupData[] data = Config.INSTANCE.getItemGroups();
        for (GroupData groupData : data)
        {
            Pair<Identifier, ItemGroup> pair = groupData.asMCObject();
            if (pair != null) itemGroups.put(pair.getLeft(), pair.getRight());
        }
    }

    private void validate_and_registerItems()
    {
        ItemData[] items = Config.INSTANCE.getItems();
        for (ItemData itemData : items)
        {
            Pair<Identifier, Item> pair = itemData.asMCObject();
            if (pair != null) Registry.register(Registry.ITEM, pair.getLeft(), pair.getRight());
        }
    }
}
