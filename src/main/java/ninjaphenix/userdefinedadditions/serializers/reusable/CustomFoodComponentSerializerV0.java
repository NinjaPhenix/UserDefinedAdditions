package ninjaphenix.userdefinedadditions.serializers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import ninjaphenix.userdefinedadditions.serializers.interfaces.Serializer;

import java.util.function.Supplier;

public class CustomFoodComponentSerializerV0 implements Serializer<CustomFoodComponentSerializerV0.Data, FoodComponent>
{
    private static final CustomFoodComponentSerializerV0 INSTANCE = new CustomFoodComponentSerializerV0();

    public static Serializer<Data, FoodComponent> getInstance() { return INSTANCE; }

    @Override
    public CustomFoodComponentSerializerV0.Data read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Integer hunger = marshaller.marshall(Integer.class, object.get("hunger"));
        final Float saturation = marshaller.marshall(Float.class, object.get("saturation"));
        final Boolean meat = marshaller.marshall(Boolean.class, object.get("meat"));
        final Boolean alwaysEdible = marshaller.marshall(Boolean.class, object.get("alwaysEdible"));
        final Boolean snack = marshaller.marshall(Boolean.class, object.get("snack"));
        final StatusEffectInstanceSerializerV0.Data[] statusEffects = marshaller
                .marshall(StatusEffectInstanceSerializerV0.Data[].class, object.get("statusEffects"));
        return new Data(hunger, saturation, meat, alwaysEdible, snack, statusEffects);
    }

    @Override
    public JsonObject write(CustomFoodComponentSerializerV0.Data object, Marshaller marshaller)
    {
        final JsonObject rv = new JsonObject();
        if (object.hunger != null) rv.put("hunger", marshaller.serialize(object.hunger));
        if (object.saturation != null) rv.put("saturation", marshaller.serialize(object.saturation));
        if (object.meat != null) rv.put("meat", marshaller.serialize(object.meat));
        if (object.alwaysEdible != null) rv.put("alwaysEdible", marshaller.serialize(object.alwaysEdible));
        if (object.snack != null) rv.put("snack", marshaller.serialize(object.snack));
        if (object.statusEffects != null) rv.put("statusEffects", marshaller.serialize(object.statusEffects));
        return rv;
    }

    static class Data implements Supplier<FoodComponent>
    {
        private final Integer hunger;
        private final Float saturation;
        private final Boolean meat, alwaysEdible, snack;
        private final StatusEffectInstanceSerializerV0.Data[] statusEffects;

        private Data(Integer hunger, Float saturation, Boolean meat, Boolean alwaysEdible, Boolean snack,
                StatusEffectInstanceSerializerV0.Data[] statusEffects)
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
            for (StatusEffectInstanceSerializerV0.Data data : statusEffects)
            {
                StatusEffectInstance instance = data.get();
                if (instance != null) builder.statusEffect(instance, data.getChance());
            }
            return builder.build();
        }
    }
}