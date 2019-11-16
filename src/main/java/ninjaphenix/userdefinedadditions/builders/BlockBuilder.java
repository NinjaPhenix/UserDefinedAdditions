package ninjaphenix.userdefinedadditions.builders;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.RegistrableBuilder;

public class BlockBuilder implements RegistrableBuilder<Block>
{

    @Override
    public Block register(String id)
    {
        return Registry.register(Registry.BLOCK, id, build());
    }

    @Override
    public Block build()
    {
        return null;
    }
}
