package ninjaphenix.userdefinedadditions.objects.internal;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import ninjaphenix.userdefinedadditions.Serializer;

public class StatusEffectInstanceDataV0 implements Serializer<StatusEffectInstanceDataV0, StatusEffectInstance>
{
    private Float chance;

    @Override
    public StatusEffectInstanceDataV0 read(JsonObject object)
    {
        return null;
    }

    @Override
    public JsonObject write(StatusEffectInstanceDataV0 object, Marshaller marshaller)
    {
        return null;
    }

    /**
     * Note: whilst this is mostly a complete object the float chance to get an effect isn't represented here.
     *
     * @see StatusEffectInstanceDataV0#getChance()
     */
    @Override
    public StatusEffectInstance asRealObject()
    {
        return null;
    }

    /**
     * @return The chance for this status effect to apply.
     */
    public Float getChance() { return chance; }
}
