package ninjaphenix.userdefinedadditions;

import net.minecraft.util.Identifier;

public interface RegistryEntrySerializer<T, R> extends Serializer<T, R>
{
    R register(Identifier id);
}
