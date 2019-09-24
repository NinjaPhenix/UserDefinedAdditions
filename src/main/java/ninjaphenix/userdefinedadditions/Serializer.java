package ninjaphenix.userdefinedadditions;

import blue.endless.jankson.JsonObject;

public interface Serializer
{
    JsonObject read(JsonObject object);
}
