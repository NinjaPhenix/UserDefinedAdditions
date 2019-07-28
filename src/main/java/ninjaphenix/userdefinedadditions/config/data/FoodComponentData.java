package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import ninjaphenix.userdefinedadditions.CommonEntry;

public class FoodComponentData
{
    public Integer hunger;
    public Float saturation;
    public Boolean is_meat;
    public Boolean is_always_edible;
    public Boolean is_snack;
    public StatusEffectInstanceData[] status_effects;

    private FoodComponentData(int hunger, float saturation, boolean is_meat, boolean is_always_edible, boolean is_snack,
            StatusEffectInstanceData[] status_effects)
    {
        this.hunger = hunger;
        this.saturation = saturation;
        this.is_meat = is_meat;
        this.is_always_edible = is_always_edible;
        this.is_snack = is_snack;
        this.status_effects = status_effects;
    }

    public static FoodComponentData parse(JsonObject object)
    {
        System.out.println("Parsing food component:");
        Integer hunger = object.get(Integer.class, "hunger");
        System.out.println("Hunger="+hunger);
        Float saturation = object.get(Float.class, "saturation");
        System.out.println("Saturation="+saturation);
        Boolean is_meat = object.get(Boolean.class, "is_meat");
        System.out.println("Meat? "+is_meat);
        Boolean is_always_edible = object.get(Boolean.class, "is_always_edible");
        System.out.println("Always edible? "+is_always_edible);
        Boolean is_snack = object.get(Boolean.class, "is_snack");
        System.out.println("Snack? "+is_snack);
        StatusEffectInstanceData[] status_effects = object.get(StatusEffectInstanceData[].class, "status_effects");
        System.out.println("Status Effects="+status_effects);
        FoodComponentData rv = new FoodComponentData(hunger, saturation, is_meat, is_always_edible, is_snack, status_effects);
        System.out.println("Returning "+rv);
        return rv;
    }

    public FoodComponent asMCObject()
    {
        if (hunger == null || hunger < 0)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create food component, hunger is not defined or less than 0.", CommonEntry.MOD_ID);
            return null;
        }
        if (saturation == null || saturation < 0)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create food component, saturation is not defined or less than 0.", CommonEntry.MOD_ID);
            return null;
        }
        FoodComponent.Builder builder = new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation);
        if (is_meat) builder.meat();
        if (is_always_edible) builder.alwaysEdible();
        if (is_snack) builder.snack();
        if (status_effects != null)
        {
            for (StatusEffectInstanceData status_effect : status_effects)
            {
                StatusEffectInstance inst = status_effect.asMCObject();
                Float chance = status_effect.chance;
                if (chance == null || chance < 0 || chance > 1) chance = 1.0F;
                if (inst != null) builder.statusEffect(inst, chance);
            }
        }
        return builder.build();
    }

    public static JsonElement serialize(FoodComponentData foodComponentData, Marshaller marshaller)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("hunger", marshaller.serialize(foodComponentData.hunger));
        jsonObject.put("saturation", marshaller.serialize(foodComponentData.saturation));
        if (foodComponentData.is_meat != null) jsonObject.put("is_meat", marshaller.serialize(foodComponentData.is_meat));
        if (foodComponentData.is_always_edible != null) jsonObject.put("is_always_edible", marshaller.serialize(foodComponentData.is_always_edible));
        if (foodComponentData.is_snack != null) jsonObject.put("is_snack", marshaller.serialize(foodComponentData.is_snack));
        if (foodComponentData.status_effects != null) jsonObject.put("status_effects", marshaller.serialize(foodComponentData.status_effects));
        return jsonObject;
    }
}
