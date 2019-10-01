package ninjaphenix.userdefinedadditions.api.readers.interfaces;

import blue.endless.jankson.JsonObject;

public interface Reader<T>
{
    T read(JsonObject object);
}
