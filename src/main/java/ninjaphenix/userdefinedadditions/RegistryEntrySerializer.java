package ninjaphenix.userdefinedadditions;

import net.minecraft.util.Identifier;

public interface RegistryEntrySerializer<T, R> extends Serializer<T>
{
    R register(Identifier id);

    R asMCObject();
}
