package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;

public class FoodComponent
{
    private int hunger;
    private float saturation;
    private boolean meat;
    private boolean always_edible;
    private boolean snack;
    private StatusEffectInstance[] status_effects;

    public FoodComponent(int hunger, float saturation, boolean meat, boolean always_edible, boolean snack, StatusEffectInstance[] status_effects)
    {
        this.hunger = hunger;
        this.saturation = saturation;
        this.meat = meat;
        this.always_edible = always_edible;
        this.snack = snack;
        this.status_effects = status_effects;
    }

    public static FoodComponent parse(JsonObject object)
    {
        Integer hunger = object.get(Integer.class, "hunger");
        Float saturation = object.get(Float.class, "saturation");
        Boolean meat = object.get(Boolean.class, "is_meat");
        Boolean always_edible = object.get(Boolean.class, "is_always_edible");
        Boolean snack = object.get(Boolean.class, "is_snack");
        StatusEffectInstance[] status_effects = object.get(StatusEffectInstance[].class, "status_effects");
        return new FoodComponent(hunger, saturation, meat, always_edible, snack, status_effects);
    }
}
