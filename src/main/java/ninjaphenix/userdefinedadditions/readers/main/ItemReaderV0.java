package ninjaphenix.userdefinedadditions.readers.main;

import blue.endless.jankson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.api.readers.ReaderReader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.RegisterableReader;

public class ItemReaderV0 implements RegisterableReader<Item>
{
    private static final ItemReaderV0 INSTANCE = new ItemReaderV0();

    public static RegisterableReader<Item> getInstance() { return INSTANCE; }

    @Override
    public Item read(JsonObject object, Identifier identifier)
    {
        return Registry.register(Registry.ITEM, identifier, read(object));
    }

    @Override
    public Item read(JsonObject object)
    {
        final JsonObject settingsObj = object.getObject("settings");
        if (settingsObj == null) throw new RuntimeException("Item is missing a settings key.");
        final ReaderReader.ReaderData data = ReaderReader.getInstance().read(settingsObj);
        final Reader<Item.Settings> serializer = ReaderManager.getInstance().get(data.getType(), data.getVersion());
        final Item.Settings settings = serializer.read(data.getData());
        return new Item(settings);
    }
}