package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import ninjaphenix.userdefinedadditions.readers.ReaderManager;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.function.Supplier;

public class ItemSettingsReaderV0 implements Reader<ItemSettingsReaderV0.Data, Item.Settings>
{
    private static final ItemSettingsReaderV0 INSTANCE = new ItemSettingsReaderV0();

    public static Reader<Data, Item.Settings> getInstance() { return INSTANCE; }

    @Override
    public ItemSettingsReaderV0.Data read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Integer count = marshaller.marshall(Integer.class, object.get("max_count"));
        final Identifier group = marshaller.marshall(Identifier.class, object.get("group"));
        final Rarity rarity = marshaller.marshall(Rarity.class, object.get("rarity"));
        final JsonObject foodComponentObject = object.getObject("food_component");
        FoodComponent component = null;
        if (foodComponentObject != null)
        {
            ReaderReader.Data data = ReaderReader.getInstance().read(foodComponentObject);
            component = (FoodComponent) ReaderManager.getInstance().getSerializer(data.getType(), data.getVersion()).read(foodComponentObject).get();
        }

        return new Data(group, count, rarity, component);
    }

    public static class Data implements Supplier<Item.Settings>
    {

        private final FoodComponent component;
        private final Identifier group;
        private final Integer count;
        private final Rarity rarity;

        private Data(Identifier group, Integer count, Rarity rarity, FoodComponent component)
        {
            this.group = group;
            this.count = count;
            this.rarity = rarity;
            this.component = component;
        }

        @Override
        public Item.Settings get()
        {
            Item.Settings settings = new Item.Settings();
            settings.food(component);
            settings.rarity(rarity);
            if (count == null || count > 64 || count < 0) settings.maxCount(64);
            else settings.maxCount(count);
            return settings;
        }
    }
}