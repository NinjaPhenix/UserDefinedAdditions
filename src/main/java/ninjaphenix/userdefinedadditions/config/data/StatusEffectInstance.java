package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;
import net.minecraft.util.Identifier;

public class StatusEffectInstance
{
    private Identifier effect;
    private int length;
    private int power;
    private boolean visible;

    public StatusEffectInstance(Identifier effect, int length, int power, boolean visible)
    {
        this.effect = effect;
        this.length = length;
        this.power = power;
        this.visible = visible;
    }

    public static StatusEffectInstance parse(JsonObject object)
    {
        Identifier effect = object.get(Identifier.class, "effect");
        Integer length = object.get(Integer.class, "length");
        Integer power = object.get(Integer.class, "power");
        Boolean visible = object.get(Boolean.class, "visible");
        return new StatusEffectInstance(effect, length, power, visible);
    }
}
