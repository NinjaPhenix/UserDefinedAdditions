package ninjaphenix.userdefinedadditions.builders.main;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.Builder;

public class FoodComponentBuilder implements Builder<FoodComponent>
{
    private FoodComponent.Builder builder = new FoodComponent.Builder();

    FoodComponentBuilder hunger(int hunger)
    {
        builder.hunger(hunger);
        return this;
    }

    FoodComponentBuilder saturation(float saturation)
    {
        builder.saturationModifier(saturation);
        return this;
    }

    FoodComponentBuilder effect(String effect, int duration, int amplifier, float chance)
    {
        StatusEffect parsed = Registry.STATUS_EFFECT.get(new Identifier(effect));
        StatusEffectInstance instance = new StatusEffectInstance(parsed, duration, amplifier);
        builder.statusEffect(instance, chance);
        return this;
    }

    FoodComponentBuilder alwaysEdible()
    {
        builder.alwaysEdible();
        return this;
    }

    FoodComponentBuilder meat()
    {
        builder.meat();
        return this;
    }

    FoodComponentBuilder snack()
    {
        builder.snack();
        return this;
    }

    @Override
    public FoodComponent build()
    {
        return builder.build();
    }
}
