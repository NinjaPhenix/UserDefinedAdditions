package ninjaphenix.userdefinedadditions;

import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.api.ContentAdder;
import ninjaphenix.userdefinedadditions.api.LoadOrderManager;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.api.readers.*;
import ninjaphenix.userdefinedadditions.readers.main.BlockReaderV0;
import ninjaphenix.userdefinedadditions.readers.main.ItemGroupReaderV0;
import ninjaphenix.userdefinedadditions.readers.main.ItemReaderV0;

public class UDA_ContentAdder implements ContentAdder
{
    @Override
    public void addReaders(ReaderManager manager)
    {
        manager.add(new Identifier("item"), 0, ItemReaderV0.getInstance());
        manager.add(new Identifier("item_settings"), 0, ItemSettingsReaderV0.getInstance());
        manager.add(new Identifier("predefined_food_component"), 0, PredefinedFoodComponentReaderV0.getInstance());
        manager.add(new Identifier("food_component"), 0, CustomFoodComponentReaderV0.getInstance());
        manager.add(new Identifier("item_group"), 0, ItemGroupReaderV0.getInstance());
        manager.add(new Identifier("block"), 0, BlockReaderV0.getInstance());
        manager.add(new Identifier("block_settings"), 0, BlockSettingsReaderV0.getInstance());
        manager.add(new Identifier("predefined_material"), 0, PredefinedMaterialReaderV0.getInstance());
    }

    @Override
    public void addLoadOrderValues(LoadOrderManager manager)
    {
        manager.addEnd("item_groups");
        manager.addEnd("items");
        manager.addEnd("blocks");
    }
}
