package ninjaphenix.userdefinedadditions.objects.internal;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.Serializer;

public class StatusEffectInstanceDataV0 implements Serializer<StatusEffectInstanceDataV0, StatusEffectInstance>
{
    private static final Integer default_length = 5;
    private static final Integer default_power = 1;
    private static final Boolean default_visible = Boolean.TRUE;
    private static final Float default_chance = 1F;
    private final Identifier effect;
    private final Integer length;
    private final Integer power;
    private final Boolean visible;
    private final Float chance;

    private StatusEffectInstanceDataV0(Identifier effect, Integer length, Integer power, Boolean visible, Float chance)
    {
        this.effect = effect;
        this.length = length;
        this.power = power;
        this.visible = visible;
        this.chance = chance;
    }

    @Override
    public StatusEffectInstanceDataV0 read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        if (object.containsKey("effect"))
        {
            final Identifier effect = marshaller.marshall(Identifier.class, object.get("effect"));
            final Integer length = marshaller.marshall(Integer.class, object.get("length"));
            final Integer power = marshaller.marshall(Integer.class, object.get("power"));
            final Boolean visible = marshaller.marshall(Boolean.class, object.get("visible"));
            final Float chance = marshaller.marshall(Float.class, object.get("change"));
            return new StatusEffectInstanceDataV0(effect, length, power, visible, chance);
        }
        return null;
    }

    @Override
    public JsonObject write(StatusEffectInstanceDataV0 object, Marshaller marshaller)
    {
        final JsonObject rv = new JsonObject();
        rv.put("effect", marshaller.serialize(effect));
        if (length != null) rv.put("length", marshaller.serialize(length));
        if (power != null) rv.put("power", marshaller.serialize(power));
        if (visible != null) rv.put("visible", marshaller.serialize(visible));
        if (chance != null) rv.put("chance", marshaller.serialize(chance));
        return rv;
    }

    /**
     * Note: whilst this is mostly a complete object the float chance to get an effect isn't represented here. If some values are not defined the defaults
     * defined above will be used.
     *
     * @see StatusEffectInstanceDataV0#getChance()
     */
    @Override
    public StatusEffectInstance asRealObject()
    {
        final int length = this.length == null ? default_length : this.length;
        final int power = this.power == null ? default_power : this.power;
        final boolean visible = this.visible == null ? default_visible : this.visible;
        return new StatusEffectInstance(Registry.STATUS_EFFECT.get(effect), length, power, false, visible, visible);
    }

    /**
     * @return The chance for this status effect to apply. Will return 1.0f if chance is not defined.
     */
    public Float getChance() { return chance == null ? default_chance : chance; }
}
