package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.HashMap;
import java.util.Map;

// Todo: allow modders to add their own food components to this.
public final class PredefinedFoodComponentReaderV0 implements Reader<FoodComponent>
{
    private static final Map<Identifier, FoodComponent> foodMap;
    private static final PredefinedFoodComponentReaderV0 INSTANCE = new PredefinedFoodComponentReaderV0();

    static
    {
        foodMap = new HashMap<>();
        foodMap.put(new Identifier("minecraft", "apple"), FoodComponents.APPLE);
        foodMap.put(new Identifier("minecraft", "baked_potato"), FoodComponents.BAKED_POTATO);
        foodMap.put(new Identifier("minecraft", "beef"), FoodComponents.BEEF);
        foodMap.put(new Identifier("minecraft", "beetroot"), FoodComponents.BEETROOT);
        foodMap.put(new Identifier("minecraft", "beetroot_soup"), FoodComponents.BEETROOT_SOUP);
        foodMap.put(new Identifier("minecraft", "bread"), FoodComponents.BREAD);
        foodMap.put(new Identifier("minecraft", "carrot"), FoodComponents.CARROT);
        foodMap.put(new Identifier("minecraft", "chicken"), FoodComponents.CHICKEN);
        foodMap.put(new Identifier("minecraft", "chorus_fruit"), FoodComponents.CHORUS_FRUIT);
        foodMap.put(new Identifier("minecraft", "cod"), FoodComponents.COD);
        foodMap.put(new Identifier("minecraft", "cooked_beef"), FoodComponents.COOKED_BEEF);
        foodMap.put(new Identifier("minecraft", "cooked_chicken"), FoodComponents.COOKED_CHICKEN);
        foodMap.put(new Identifier("minecraft", "cooked_cod"), FoodComponents.COOKED_COD);
        foodMap.put(new Identifier("minecraft", "cooked_mutton"), FoodComponents.COOKED_MUTTON);
        foodMap.put(new Identifier("minecraft", "cooked_porkchop"), FoodComponents.COOKED_PORKCHOP);
        foodMap.put(new Identifier("minecraft", "cooked_rabbit"), FoodComponents.COOKED_RABBIT);
        foodMap.put(new Identifier("minecraft", "cooked_salmon"), FoodComponents.COOKED_SALMON);
        foodMap.put(new Identifier("minecraft", "cookie"), FoodComponents.COOKIE);
        foodMap.put(new Identifier("minecraft", "dried_kelp"), FoodComponents.DRIED_KELP);
        foodMap.put(new Identifier("minecraft", "enchanted_golden_apple"), FoodComponents.ENCHANTED_GOLDEN_APPLE);
        foodMap.put(new Identifier("minecraft", "golden_apple"), FoodComponents.GOLDEN_APPLE);
        foodMap.put(new Identifier("minecraft", "golden_carrot"), FoodComponents.GOLDEN_CARROT);
        foodMap.put(new Identifier("minecraft", "melon_slice"), FoodComponents.MELON_SLICE);
        foodMap.put(new Identifier("minecraft", "mushroom_stew"), FoodComponents.MUSHROOM_STEW);
        foodMap.put(new Identifier("minecraft", "mutton"), FoodComponents.MUTTON);
        foodMap.put(new Identifier("minecraft", "poisonous_potato"), FoodComponents.POISONOUS_POTATO);
        foodMap.put(new Identifier("minecraft", "porkchop"), FoodComponents.PORKCHOP);
        foodMap.put(new Identifier("minecraft", "potato"), FoodComponents.POTATO);
        foodMap.put(new Identifier("minecraft", "pufferfish"), FoodComponents.PUFFERFISH);
        foodMap.put(new Identifier("minecraft", "pumpkin_pie"), FoodComponents.PUMPKIN_PIE);
        foodMap.put(new Identifier("minecraft", "rabbit"), FoodComponents.RABBIT);
        foodMap.put(new Identifier("minecraft", "rabbit_stew"), FoodComponents.RABBIT_STEW);
        foodMap.put(new Identifier("minecraft", "rotton_flesh"), FoodComponents.ROTTEN_FLESH);
        foodMap.put(new Identifier("minecraft", "salmon"), FoodComponents.SALMON);
        foodMap.put(new Identifier("minecraft", "spider_eye"), FoodComponents.SPIDER_EYE);
        foodMap.put(new Identifier("minecraft", "suspicious_stew"), FoodComponents.SUSPICIOUS_STEW);
        foodMap.put(new Identifier("minecraft", "sweet_strawberries"), FoodComponents.SWEET_BERRIES);
        foodMap.put(new Identifier("minecraft", "tropical_fish"), FoodComponents.TROPICAL_FISH);
    }

    public static Reader<FoodComponent> getInstance() { return INSTANCE; }

    @Override
    public FoodComponent read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Identifier identifier = marshaller.marshall(Identifier.class, object.get("id"));
        if (identifier != null && foodMap.containsKey(identifier)) return foodMap.get(identifier);
        return null;
    }
}