package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.FoodComponent;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

public class CustomFoodComponentReaderV0 implements Reader<FoodComponent>
{
    private static final CustomFoodComponentReaderV0 INSTANCE = new CustomFoodComponentReaderV0();

    public static Reader<FoodComponent> getInstance() { return INSTANCE; }

    @Override
    public FoodComponent read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Integer hunger = marshaller.marshall(Integer.class, object.get("hunger"));
        final Float saturation = marshaller.marshall(Float.class, object.get("saturation"));
        final Boolean meat = marshaller.marshall(Boolean.class, object.get("meat"));
        final Boolean alwaysEdible = marshaller.marshall(Boolean.class, object.get("alwaysEdible"));
        final Boolean snack = marshaller.marshall(Boolean.class, object.get("snack"));
        //final StatusEffectInstanceReaderV0.Data[] statusEffects = marshaller
        //        .marshall(StatusEffectInstanceReaderV0.Data[].class, object.get("statusEffects"));

        FoodComponent.Builder builder = new FoodComponent.Builder();
        if (hunger == null || saturation == null) return null;
        builder.hunger(hunger);
        builder.saturationModifier(saturation);
        if (Boolean.TRUE.equals(meat)) builder.meat();
        if (Boolean.TRUE.equals(alwaysEdible)) builder.alwaysEdible();
        if (Boolean.TRUE.equals(snack)) builder.snack();
        //for (Pair<StatusEffectInstance, Float> data : statusEffects)
        //{
        //    if (instance != null) builder.statusEffect(instance, data.getChance());
        //}
        return builder.build();
    }
}