package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ninjaphenix.userdefinedadditions.api.ContentAdder;
import ninjaphenix.userdefinedadditions.api.LoadOrderManager;
import ninjaphenix.userdefinedadditions.api.ReaderManager;

import java.util.List;

public class CommonMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ReaderManager readerManager = ReaderManager.getInstance();
        LoadOrderManager loadOrderManager = LoadOrderManager.getInstance();

        ContentAdder own = new UDA_ContentAdder(); // Ensure our base content adder gets loaded first.
        own.addReaders(readerManager);
        own.addLoadOrderValues(loadOrderManager);

        List<ContentAdder> adders = FabricLoader.getInstance().getEntrypoints("uda_plugin", ContentAdder.class);
        for (ContentAdder adder : adders)
        {
            adder.addReaders(readerManager);
            adder.addLoadOrderValues(loadOrderManager);
        }

        ContentLoader.getInstance().onInitialize();
    }
}
