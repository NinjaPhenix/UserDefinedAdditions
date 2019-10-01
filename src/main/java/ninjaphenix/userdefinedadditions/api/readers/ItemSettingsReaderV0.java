package ninjaphenix.userdefinedadditions.api.readers;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.ContentLoader;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;

import java.util.Optional;

public class ItemSettingsReaderV0 implements Reader<Item.Settings>
{
    private static final ItemSettingsReaderV0 INSTANCE = new ItemSettingsReaderV0();

    public static Reader<Item.Settings> getInstance() { return INSTANCE; }

    @Override
    public Item.Settings read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Integer count = marshaller.marshall(Integer.class, object.get("max_count"));
        final Integer damage = marshaller.marshall(Integer.class, object.get("max_damage"));
        final Identifier group = marshaller.marshall(Identifier.class, object.get("group"));
        final Identifier recipeRemainder = marshaller.marshall(Identifier.class, object.get("recipe_remainder"));
        final Rarity rarity = marshaller.marshall(Rarity.class, object.get("rarity"));
        final JsonObject foodComponentObject = object.getObject("food_component");
        FoodComponent component = null;
        if (foodComponentObject != null)
        {
            ReaderReader.ReaderData data = ReaderReader.getInstance().read(foodComponentObject);
            component = (FoodComponent) ReaderManager.getInstance().get(data.getType(), data.getVersion()).read(data.getData());
        }
        Item.Settings settings = new Item.Settings();
        settings.food(component);
        if (rarity != null) settings.rarity(rarity);
        if (damage != null) settings.maxDamage(damage);
        if (count != null) settings.maxCount(count);
        if (recipeRemainder != null)
        {
            Optional<Item> remainder = Registry.ITEM.getOrEmpty(recipeRemainder);
            remainder.ifPresent(settings::recipeRemainder);
        }
        if (group != null && ContentLoader.itemGroups.containsKey(group)) settings.group(ContentLoader.itemGroups.get(group));
        return settings;
    }
}