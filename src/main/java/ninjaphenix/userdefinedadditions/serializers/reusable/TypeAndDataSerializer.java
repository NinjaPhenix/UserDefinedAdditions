package ninjaphenix.userdefinedadditions.serializers.reusable;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.serializers.interfaces.Serializer;

import java.util.function.Supplier;

public class TypeAndDataSerializer implements Serializer<TypeAndDataSerializer.Data, JsonObject>
{
    private static final TypeAndDataSerializer INSTANCE = new TypeAndDataSerializer();

    public static Serializer<Data, JsonObject> getInstance() { return INSTANCE; }

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

    @Override
    public JsonObject write(Data object, Marshaller marshaller)
    {
        final JsonObject rv = new JsonObject();
        rv.put("type", marshaller.serialize(object.type));
        rv.put("version", marshaller.serialize(object.version));
        rv.put("data", object.data);
        return rv;
    }

    class Data implements Supplier<JsonObject>
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

        // todo Change this a bit more to allow for different versions of parsers.
        @Override
        public JsonObject get() { return data; }
    }
}