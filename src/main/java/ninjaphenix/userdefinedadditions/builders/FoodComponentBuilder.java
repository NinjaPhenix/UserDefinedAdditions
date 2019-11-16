package ninjaphenix.userdefinedadditions.builders;

import net.minecraft.item.FoodComponent;
import ninjaphenix.userdefinedadditions.api.Builder;

public class FoodComponentBuilder implements Builder<FoodComponent>
{
    private final FoodComponent.Builder builder = new FoodComponent.Builder();

    @Override
    public FoodComponent build()
    {
        return builder.build();
    }
}
