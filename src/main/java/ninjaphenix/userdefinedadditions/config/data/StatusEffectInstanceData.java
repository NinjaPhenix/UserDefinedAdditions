package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.CommonEntry;

public final class StatusEffectInstanceData
{
    private final Identifier effect;
    private final Integer length;
    private final Integer power;
    private final Boolean visible;
    private final Float chance;

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

    public static JsonObject serialize(StatusEffectInstanceData data, Marshaller marshaller)
    {
        JsonObject object = new JsonObject();
        object.put("effect", marshaller.serialize(data.effect));
        object.put("length", marshaller.serialize(data.length));
        if (data.power != null) object.put("power", marshaller.serialize(data.power));
        if (data.visible != null) object.put("visible", marshaller.serialize(data.visible));
        if (data.chance != null) object.put("chance", marshaller.serialize(data.chance));
        return object;
    }

    public StatusEffectInstance asMCObject()
    {
        if (!Registry.STATUS_EFFECT.containsId(effect))
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create status effect, effect {} does not exist..", CommonEntry.MOD_ID, effect);
            return null;
        }
        if (length == null || length < 0)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create status effect, length is not defined or less than 0.", CommonEntry.MOD_ID);
            return null;
        }
        int pow = (power == null) ? 1 : power;
        boolean vis = (visible == null) ? true : visible;
        return new StatusEffectInstance(Registry.STATUS_EFFECT.get(effect), length, pow, false, vis);
    }

    public Float getChance() { return chance; }
}
