package ninjaphenix.userdefinedadditions.serializers.main;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.serializers.interfaces.RegistrableSupplier;
import ninjaphenix.userdefinedadditions.serializers.interfaces.Serializer;

public class ItemSerializerV0 implements Serializer<ItemSerializerV0.Data, Item>
{
    private static final ItemSerializerV0 INSTANCE = new ItemSerializerV0();

    public static Serializer<Data, Item> getInstance() { return INSTANCE; }

    @Override
    public Data read(JsonObject object)
    {
        return null;
    }

    @Override
    public JsonObject write(Data object, Marshaller marshaller)
    {
        return null;
    }

    public static class Data implements RegistrableSupplier<Item>
    {
        @Override
        public Item register(Identifier id)
        {
            return null;
        }

        @Override
        public Item get()
        {
            return null;
        }
    }
}