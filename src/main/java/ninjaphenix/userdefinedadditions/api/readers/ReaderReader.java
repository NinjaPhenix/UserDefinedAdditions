package ninjaphenix.userdefinedadditions.api.readers;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;

public class ReaderReader implements Reader<ReaderReader.ReaderData>
{
    private static final ReaderReader INSTANCE = new ReaderReader();

    public static Reader<ReaderData> getInstance() { return INSTANCE; }

    @Override
    public ReaderData read(JsonObject object)
    {
        if (object.containsKey("type") && object.containsKey("data"))
        {
            Marshaller marshaller = object.getMarshaller();
            final Identifier type = marshaller.marshall(Identifier.class, object.get("type"));
            final Integer version = marshaller.marshall(Integer.class, object.get("version"));
            final JsonObject data = object.getObject("data");
            return new ReaderData(type, version, data);
        }
        return null;
    }

    public static class ReaderData
    {
        private final Identifier type;
        private final Integer version;
        private final JsonObject data;

        ReaderData(Identifier type, Integer version, JsonObject data)
        {
            this.type = type;
            this.version = version;
            this.data = data;
        }

        public Identifier getType() { return type; }

        public Integer getVersion() { return version; }

        public JsonObject getData() { return data; }
    }
}