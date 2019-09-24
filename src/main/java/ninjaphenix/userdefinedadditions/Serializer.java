package ninjaphenix.userdefinedadditions;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.util.Identifier;

public interface Serializer<T, R>
{
    T read(JsonObject object);

    void write(T object, Marshaller marshaller);

    R register(Identifier id);

    R asMCObject();
}
