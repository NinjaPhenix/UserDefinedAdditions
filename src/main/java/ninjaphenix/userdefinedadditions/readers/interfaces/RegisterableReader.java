package ninjaphenix.userdefinedadditions.readers.interfaces;

import blue.endless.jankson.JsonObject;
import net.minecraft.util.Identifier;

public interface RegisterableReader<T> extends Reader<T>
{
    T read(JsonObject object, Identifier identifier);
}
