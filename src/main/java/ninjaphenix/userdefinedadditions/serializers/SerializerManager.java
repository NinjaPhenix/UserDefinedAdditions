package ninjaphenix.userdefinedadditions.serializers;

import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.serializers.interfaces.Serializer;

import java.util.function.Supplier;

public class SerializerManager
{
    private static final SerializerManager INSTANCE = new SerializerManager();

    public static SerializerManager getInstance() { return INSTANCE; }

    public <T> void registerSerializerType(Identifier id, int version, Class<T> return_type, Serializer<? extends Supplier<T>, T> serializer)
    {

    }

    public <T> Serializer<? extends Supplier<T>, T> getSerializer(Identifier id, int version)
    {
        return null;
    }
}
