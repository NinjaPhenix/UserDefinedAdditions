package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.Optional;

public class StatusEffectInstanceReaderV0 implements Reader<Pair<StatusEffectInstance, Float>>
{
    private static final Integer default_length = 5;
    private static final Integer default_power = 1;
    private static final Boolean default_visible = Boolean.TRUE;
    private static final Float default_chance = 1.0f;
    private static final StatusEffectInstanceReaderV0 INSTANCE = new StatusEffectInstanceReaderV0();

    public static Reader<Pair<StatusEffectInstance, Float>> getInstance() { return INSTANCE; }

    @Override
    public Pair<StatusEffectInstance, Float> read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Identifier effect = marshaller.marshall(Identifier.class, object.get("effect"));
        Integer length = marshaller.marshall(Integer.class, object.get("length"));
        Integer power = marshaller.marshall(Integer.class, object.get("power"));
        Boolean visible = marshaller.marshall(Boolean.class, object.get("visible"));
        Float chance = marshaller.marshall(Float.class, object.get("change"));
        final Optional<StatusEffect> optEffect = Registry.STATUS_EFFECT.getOrEmpty(effect);
        if (optEffect.isPresent())
        {
            length = length == null ? default_length : length;
            power = power == null ? default_power : power;
            visible = visible == null ? default_visible : visible;
            chance = chance == null ? default_chance : chance;
            return new Pair<>(new StatusEffectInstance(optEffect.get(), length, power, false, visible, visible), chance);
        }
        return null;
    }

}