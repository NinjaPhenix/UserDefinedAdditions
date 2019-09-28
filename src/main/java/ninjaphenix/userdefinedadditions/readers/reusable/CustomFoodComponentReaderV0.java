package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.function.Supplier;

public class CustomFoodComponentReaderV0 implements Reader<CustomFoodComponentReaderV0.Data, FoodComponent>
{
    private static final CustomFoodComponentReaderV0 INSTANCE = new CustomFoodComponentReaderV0();

    public static Reader<Data, FoodComponent> getInstance() { return INSTANCE; }

    @Override
    public CustomFoodComponentReaderV0.Data read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Integer hunger = marshaller.marshall(Integer.class, object.get("hunger"));
        final Float saturation = marshaller.marshall(Float.class, object.get("saturation"));
        final Boolean meat = marshaller.marshall(Boolean.class, object.get("meat"));
        final Boolean alwaysEdible = marshaller.marshall(Boolean.class, object.get("alwaysEdible"));
        final Boolean snack = marshaller.marshall(Boolean.class, object.get("snack"));
        final StatusEffectInstanceReaderV0.Data[] statusEffects = marshaller
                .marshall(StatusEffectInstanceReaderV0.Data[].class, object.get("statusEffects"));
        return new Data(hunger, saturation, meat, alwaysEdible, snack, statusEffects);
    }

    public static class Data implements Supplier<FoodComponent>
    {
        private final Integer hunger;
        private final Float saturation;
        private final Boolean meat, alwaysEdible, snack;
        private final StatusEffectInstanceReaderV0.Data[] statusEffects;

        private Data(Integer hunger, Float saturation, Boolean meat, Boolean alwaysEdible, Boolean snack,
                StatusEffectInstanceReaderV0.Data[] statusEffects)
        {
            this.hunger = hunger;
            this.saturation = saturation;
            this.meat = meat;
            this.alwaysEdible = alwaysEdible;
            this.snack = snack;
            this.statusEffects = statusEffects;
        }

        @Override
        public FoodComponent get()
        {
            FoodComponent.Builder builder = new FoodComponent.Builder();
            if (hunger == null || saturation == null) return null;
            builder.hunger(hunger);
            builder.saturationModifier(saturation);
            if (meat != null && meat) builder.meat();
            if (alwaysEdible != null && alwaysEdible) builder.alwaysEdible();
            if (snack != null && snack) builder.snack();
            for (StatusEffectInstanceReaderV0.Data data : statusEffects)
            {
                StatusEffectInstance instance = data.get();
                if (instance != null) builder.statusEffect(instance, data.getChance());
            }
            return builder.build();
        }
    }
}