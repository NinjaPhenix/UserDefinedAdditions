package ninjaphenix.userdefinedadditions;

import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.api.ContentAdder;
import ninjaphenix.userdefinedadditions.api.ReaderManager;
import ninjaphenix.userdefinedadditions.readers.main.ItemGroupReaderV0;
import ninjaphenix.userdefinedadditions.readers.main.ItemReaderV0;
import ninjaphenix.userdefinedadditions.readers.reusable.CustomFoodComponentReaderV0;
import ninjaphenix.userdefinedadditions.readers.reusable.ItemSettingsReaderV0;
import ninjaphenix.userdefinedadditions.readers.reusable.PredefinedFoodComponentReaderV0;

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
    }
}
