package ninjaphenix.userdefinedadditions.readers.main;

import blue.endless.jankson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.api.readers.ReaderReader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.RegisterableReader;

public class BlockReaderV0 implements RegisterableReader<Block>
{
    private static final BlockReaderV0 INSTANCE = new BlockReaderV0();

    public static RegisterableReader<Block> getInstance() { return INSTANCE; }

    @Override
    public Block read(JsonObject object, Identifier identifier)
    {
        final JsonObject blockSettingsObj = object.getObject("block_settings");
        final JsonObject itemSettingsObj = object.getObject("item_settings");
        if (blockSettingsObj == null) throw new RuntimeException("Block is missing a block_settings key.");
        if (itemSettingsObj == null) throw new RuntimeException("Block is missing a item_settings key.");
        ReaderReader.ReaderData data = ReaderReader.getInstance().read(blockSettingsObj);
        final Reader<Block.Settings> blockReader = ReaderManager.getInstance().get(data.getType(), data.getVersion());
        final Block.Settings blockSettings = blockReader.read(data.getData());
        if (blockSettings == null) throw new RuntimeException("Block settings key is invalid.");
        data = ReaderReader.getInstance().read(itemSettingsObj);
        final Reader<Item.Settings> itemReader = ReaderManager.getInstance().get(data.getType(), data.getVersion());
        final Item.Settings itemSettings = itemReader.read(data.getData());
        if (itemSettings == null) throw new RuntimeException("Item settings key is invalid.");
        Block block = Registry.register(Registry.BLOCK, identifier, new Block(blockSettings));
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, itemSettings));
        return block;
    }

    @Override
    public Block read(JsonObject object) { throw new UnsupportedOperationException("Reader#read method not supported for block readers."); }
}