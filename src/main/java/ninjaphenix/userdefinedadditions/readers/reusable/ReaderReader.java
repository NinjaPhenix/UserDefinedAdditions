package ninjaphenix.userdefinedadditions.readers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.function.Supplier;

public class ReaderReader implements Reader<ReaderReader.Data, JsonObject>
{
    private static final ReaderReader INSTANCE = new ReaderReader();

    public static Reader<Data, JsonObject> getInstance() { return INSTANCE; }

    @Override
    public Data read(JsonObject object)
    {
        if (object.containsKey("type") && object.containsKey("data"))
        {
            Marshaller marshaller = object.getMarshaller();
            final Identifier type = marshaller.marshall(Identifier.class, object.get("type"));
            final Integer version = marshaller.marshall(Integer.class, object.get("version"));
            final JsonObject data = object.getObject("data");
            return new Data(type, version, data);
        }
        return null;
    }

    public static class Data implements Supplier<JsonObject>
    {
        private final Identifier type;
        private final Integer version;
        private final JsonObject data;

        private Data(Identifier type, Integer version, JsonObject data)
        {
            this.type = type;
            this.version = version;
            this.data = data;
        }

        public Identifier getType() { return type; }

        public Integer getVersion() { return version == null ? 0 : version; }

        @Override
        public JsonObject get() { return data; }
    }
}