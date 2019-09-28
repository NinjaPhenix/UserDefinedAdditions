package ninjaphenix.userdefinedadditions.readers.interfaces;

import blue.endless.jankson.JsonObject;

public interface Reader<T>
{
    T read(JsonObject object);
}
