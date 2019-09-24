package ninjaphenix.userdefinedadditions.serializers;

import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.serializers.interfaces.Serializer;

import java.util.function.Supplier;

public class SerializerManager
{
    // just a test method trying to get generics to work
    public static <T> void registerSerializerType(Identifier id, Class<T> return_type, Serializer<? extends Supplier<T>, T> serializer)
    {
        System.out.println(id + ": " + return_type.getCanonicalName() + ", " + serializer.getClass().getCanonicalName());
    }
}
