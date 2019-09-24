package ninjaphenix.userdefinedadditions.objects;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.RegistryEntrySerializer;

public class ItemDataV0 implements RegistryEntrySerializer<ItemDataV0, Item>
{
    @Override
    public ItemDataV0 read(JsonObject object)
    {
        return null;
    }

    @Override
    public JsonObject write(ItemDataV0 object, Marshaller marshaller)
    {
        return null;
    }

    @Override
    public Item register(Identifier id)
    {
        return Registry.register(Registry.ITEM, id, asRealObject());
    }

    @Override
    public Item asRealObject()
    {
        return null;
    }
}