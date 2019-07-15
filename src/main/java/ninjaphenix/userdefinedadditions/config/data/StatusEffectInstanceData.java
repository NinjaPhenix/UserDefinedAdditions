package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.CommonEntry;

public class StatusEffectInstanceData
{
    public Identifier effect;
    public Integer length;
    public Integer power;
    public Boolean visible;
    public Float chance;

    private StatusEffectInstanceData(Identifier effect, Integer length, Integer power, Boolean visible, Float chance)
    {
        this.effect = effect;
        this.length = length;
        this.power = power;
        this.visible = visible;
        this.chance = chance;
    }

    public static StatusEffectInstanceData parse(JsonObject object)
    {
        Identifier effect = object.get(Identifier.class, "effect");
        Integer length = object.get(Integer.class, "length");
        Integer power = object.get(Integer.class, "power");
        Boolean visible = object.get(Boolean.class, "visible");
        Float chance = object.get(Float.class, "chance");
        return new StatusEffectInstanceData(effect, length, power, visible, chance);
    }

    public StatusEffectInstance asMCObject()
    {
        if(!Registry.STATUS_EFFECT.containsId(effect))
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create status effect, effect {} does not exist..", CommonEntry.MOD_ID, effect);
            return null;
        }
        if(length == null || length < 0)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create status effect, length is not defined or less than 0.", CommonEntry.MOD_ID);
            return null;
        }
        int pow = (power == null) ? 1 : power;
        boolean vis = (visible == null) ? true : visible;
        return new StatusEffectInstance(Registry.STATUS_EFFECT.get(effect), length, pow, false, vis);
    }
}
