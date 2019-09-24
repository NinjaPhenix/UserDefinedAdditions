package ninjaphenix.userdefinedadditions;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;

public interface Serializer<T>
{
    T read(JsonObject object);

    void write(T object, Marshaller marshaller);
}
