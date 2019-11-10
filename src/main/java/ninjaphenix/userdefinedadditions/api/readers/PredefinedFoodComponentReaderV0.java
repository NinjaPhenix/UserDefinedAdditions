package ninjaphenix.userdefinedadditions.api.readers;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;

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
        foodMap.put(new Identifier("apple"), FoodComponents.APPLE);
        foodMap.put(new Identifier("baked_potato"), FoodComponents.BAKED_POTATO);
        foodMap.put(new Identifier("beef"), FoodComponents.BEEF);
        foodMap.put(new Identifier("beetroot"), FoodComponents.BEETROOT);
        foodMap.put(new Identifier("beetroot_soup"), FoodComponents.BEETROOT_SOUP);
        foodMap.put(new Identifier("bread"), FoodComponents.BREAD);
        foodMap.put(new Identifier("carrot"), FoodComponents.CARROT);
        foodMap.put(new Identifier("chicken"), FoodComponents.CHICKEN);
        foodMap.put(new Identifier("chorus_fruit"), FoodComponents.CHORUS_FRUIT);
        foodMap.put(new Identifier("cod"), FoodComponents.COD);
        foodMap.put(new Identifier("cooked_beef"), FoodComponents.COOKED_BEEF);
        foodMap.put(new Identifier("cooked_chicken"), FoodComponents.COOKED_CHICKEN);
        foodMap.put(new Identifier("cooked_cod"), FoodComponents.COOKED_COD);
        foodMap.put(new Identifier("cooked_mutton"), FoodComponents.COOKED_MUTTON);
        foodMap.put(new Identifier("cooked_porkchop"), FoodComponents.COOKED_PORKCHOP);
        foodMap.put(new Identifier("cooked_rabbit"), FoodComponents.COOKED_RABBIT);
        foodMap.put(new Identifier("cooked_salmon"), FoodComponents.COOKED_SALMON);
        foodMap.put(new Identifier("cookie"), FoodComponents.COOKIE);
        foodMap.put(new Identifier("dried_kelp"), FoodComponents.DRIED_KELP);
        foodMap.put(new Identifier("enchanted_golden_apple"), FoodComponents.ENCHANTED_GOLDEN_APPLE);
        foodMap.put(new Identifier("golden_apple"), FoodComponents.GOLDEN_APPLE);
        foodMap.put(new Identifier("golden_carrot"), FoodComponents.GOLDEN_CARROT);
        foodMap.put(new Identifier("melon_slice"), FoodComponents.MELON_SLICE);
        foodMap.put(new Identifier("mushroom_stew"), FoodComponents.MUSHROOM_STEW);
        foodMap.put(new Identifier("mutton"), FoodComponents.MUTTON);
        foodMap.put(new Identifier("poisonous_potato"), FoodComponents.POISONOUS_POTATO);
        foodMap.put(new Identifier("porkchop"), FoodComponents.PORKCHOP);
        foodMap.put(new Identifier("potato"), FoodComponents.POTATO);
        foodMap.put(new Identifier("pufferfish"), FoodComponents.PUFFERFISH);
        foodMap.put(new Identifier("pumpkin_pie"), FoodComponents.PUMPKIN_PIE);
        foodMap.put(new Identifier("rabbit"), FoodComponents.RABBIT);
        foodMap.put(new Identifier("rabbit_stew"), FoodComponents.RABBIT_STEW);
        foodMap.put(new Identifier("rotton_flesh"), FoodComponents.ROTTEN_FLESH);
        foodMap.put(new Identifier("salmon"), FoodComponents.SALMON);
        foodMap.put(new Identifier("spider_eye"), FoodComponents.SPIDER_EYE);
        foodMap.put(new Identifier("suspicious_stew"), FoodComponents.SUSPICIOUS_STEW);
        foodMap.put(new Identifier("sweet_strawberries"), FoodComponents.SWEET_BERRIES);
        foodMap.put(new Identifier("tropical_fish"), FoodComponents.TROPICAL_FISH);
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