package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;

public class FoodComponent
{
    public int hunger;
    public float saturation;
    public boolean is_meat;
    public boolean is_always_edible;
    public boolean is_snack;
    public StatusEffectInstance[] status_effects;

    private FoodComponent(int hunger, float saturation, boolean is_meat, boolean is_always_edible, boolean is_snack, StatusEffectInstance[] status_effects)
    {
        this.hunger = hunger;
        this.saturation = saturation;
        this.is_meat = is_meat;
        this.is_always_edible = is_always_edible;
        this.is_snack = is_snack;
        this.status_effects = status_effects;
    }

    public static FoodComponent parse(JsonObject object)
    {
        Integer hunger = object.get(Integer.class, "hunger");
        Float saturation = object.get(Float.class, "saturation");
        Boolean is_meat = object.get(Boolean.class, "is_meat");
        Boolean is_always_edible = object.get(Boolean.class, "is_always_edible");
        Boolean is_snack = object.get(Boolean.class, "is_snack");
        StatusEffectInstance[] status_effects = object.get(StatusEffectInstance[].class, "status_effects");
        return new FoodComponent(hunger, saturation, is_meat, is_always_edible, is_snack, status_effects);
    }
}
