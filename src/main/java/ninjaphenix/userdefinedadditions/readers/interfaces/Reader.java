package ninjaphenix.userdefinedadditions.readers.interfaces;

import blue.endless.jankson.JsonObject;

import java.util.function.Supplier;

public interface Reader<T extends Supplier<R>, R>
{
    T read(JsonObject object);
}
