package ninjaphenix.userdefinedadditions.serializers.interfaces;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;

import java.util.function.Supplier;

public interface Serializer<T extends Supplier<R>, R>
{
    T read(JsonObject object);

    JsonObject write(T object, Marshaller marshaller);
}
