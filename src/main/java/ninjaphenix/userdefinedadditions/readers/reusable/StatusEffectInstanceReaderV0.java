package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.Optional;
import java.util.function.Supplier;

public class StatusEffectInstanceReaderV0 implements Reader<StatusEffectInstanceReaderV0.Data, StatusEffectInstance>
{
    private static final Integer default_length = 5;
    private static final Integer default_power = 1;
    private static final Boolean default_visible = Boolean.TRUE;
    private static final Float default_chance = 1.0f;
    private static final StatusEffectInstanceReaderV0 INSTANCE = new StatusEffectInstanceReaderV0();

    public static Reader<Data, StatusEffectInstance> getInstance() { return INSTANCE; }

    @Override
    public StatusEffectInstanceReaderV0.Data read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Identifier effect = marshaller.marshall(Identifier.class, object.get("effect"));
        final Integer length = marshaller.marshall(Integer.class, object.get("length"));
        final Integer power = marshaller.marshall(Integer.class, object.get("power"));
        final Boolean visible = marshaller.marshall(Boolean.class, object.get("visible"));
        final Float chance = marshaller.marshall(Float.class, object.get("change"));
        return new Data(effect, length, power, visible, chance);
    }

    public static class Data implements Supplier<StatusEffectInstance>
    {
        private final Identifier effect;
        private final Integer length, power;
        private final Boolean visible;

        private final Float chance;

        private Data(Identifier effect, Integer length, Integer power, Boolean visible, Float chance)
        {
            this.effect = effect;
            this.length = length;
            this.power = power;
            this.visible = visible;
            this.chance = chance;
        }

        /**
         * Note: whilst this is mostly a complete object the float chance to get an effect isn't represented here. If some values are not defined the defaults
         * defined above will be used.
         *
         * @see Data#getChance()
         */
        @Override
        public StatusEffectInstance get()
        {
            if (this.effect != null)
            {
                final Optional<StatusEffect> effect = Registry.STATUS_EFFECT.getOrEmpty(this.effect);
                if (effect.isPresent())
                {
                    final int length = this.length == null ? default_length : this.length;
                    final int power = this.power == null ? default_power : this.power;
                    final boolean visible = this.visible == null ? default_visible : this.visible;
                    return new StatusEffectInstance(effect.get(), length, power, false, visible, visible);
                }
            }
            return null;
        }

        /**
         * @return The chance for this status effect to apply. Will return 1.0f if chance is not defined.
         */
        public Float getChance() { return chance == null ? default_chance : chance; }

    }
}