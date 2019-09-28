package ninjaphenix.userdefinedadditions.readers.main;

import blue.endless.jankson.JsonObject;
import net.minecraft.item.Item;
import ninjaphenix.userdefinedadditions.readers.ReaderManager;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;
import ninjaphenix.userdefinedadditions.readers.reusable.ReaderReader;

import java.util.function.Supplier;

public class ItemReaderV0 implements Reader<ItemReaderV0.Data, Item>
{
    private static final ItemReaderV0 INSTANCE = new ItemReaderV0();

    public static Reader<Data, Item> getInstance() { return INSTANCE; }

    @Override
    public Data read(JsonObject object)
    {
        final JsonObject settingsObj = object.getObject("settings");
        if (settingsObj == null) throw new RuntimeException("Item is missing a settings key.");
        final ReaderReader.Data data = ReaderReader.getInstance().read(settingsObj);
        final Reader<?, Item.Settings> serializer = ReaderManager.getInstance().getSerializer(data.getType(), data.getVersion());
        final Item.Settings settings = serializer.read(data.get()).get();
        return new Data(settings);
    }

    public static class Data implements Supplier<Item>
    {
        private final Item.Settings itemSettings;

        private Data(Item.Settings settings)
        {
            this.itemSettings = settings;
        }

        @Override
        public Item get() { return new Item(itemSettings); }
    }
}

/*

{
    "type": "minecraft:item",
    "version": 0,
    "data": {
        "settings": {
            "type": "minecraft:item_settings",
            "version": 0,
            "data": {
                "max_count": 16,
                "group": "minecraft:brewing",
                "rarity": "epic"
                "food_component": {
                    "type": "minecraft:predefined_food_component",
                    "version": 0,
                    "data": {
                        "id": "minecraft:cooked_chicken"
                }
            }
        }
    }
}

 */