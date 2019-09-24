package ninjaphenix.userdefinedadditions.objects.internal;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.Serializer;

import java.util.Set;

public class TypeAndDataSerializer implements Serializer<TypeAndDataSerializer, JsonObject>
{
    private final Identifier type;
    private final JsonObject data;

    private TypeAndDataSerializer(Identifier type, JsonObject data) { this.type = type; this.data = data; }

    @Override
    public TypeAndDataSerializer read(JsonObject object)
    {
        final Set<String> keySet = object.keySet();
        if (keySet.contains("type") && keySet.contains("data"))
        {

        }
        return null;
    }

    @Override
    public JsonObject write(TypeAndDataSerializer object, Marshaller marshaller)
    {
        final JsonObject rv = new JsonObject();
        rv.put("type", marshaller.serialize(type));
        rv.put("data", data);
        return rv;
    }

    @Override
    public JsonObject asRealObject()
    {
        return data;
    }
}
