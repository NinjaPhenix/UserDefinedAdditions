package ninjaphenix.userdefinedadditions.readers.main;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.RegisterableReader;

import java.util.Optional;

public class ItemGroupReaderV0 implements RegisterableReader<ItemGroup>
{
    private static final ItemGroupReaderV0 INSTANCE = new ItemGroupReaderV0();

    public static RegisterableReader<ItemGroup> getInstance() { return INSTANCE; }

    @Override
    public ItemGroup read(JsonObject object, Identifier identifier)
    {
        Marshaller marshaller = object.getMarshaller();
        final Identifier icon = marshaller.marshall(Identifier.class, object.get("icon"));
        final String texture = marshaller.marshall(String.class, object.get("texture"));
        FabricItemGroupBuilder builder = FabricItemGroupBuilder.create(identifier);
        if (icon != null)
        {
            Optional<Item> item = Registry.ITEM.getOrEmpty(icon);
            item.ifPresent(value -> builder.icon(() -> new ItemStack(value)));
        }
        ItemGroup group = builder.build();
        if (texture != null) group.setTexture(texture);
        return group;
    }

    @Override
    public ItemGroup read(JsonObject object) { throw new UnsupportedOperationException("Reader#read method not supported for item group readers."); }
}
