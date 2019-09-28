package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ninjaphenix.userdefinedadditions.api.ContentAdder;
import ninjaphenix.userdefinedadditions.api.ReaderManager;

import java.util.List;

public class CommonMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        List<ContentAdder> adders = FabricLoader.getInstance().getEntrypoints("uda_plugin", ContentAdder.class);

        adders.forEach((adder) -> adder.addReaders(ReaderManager.getInstance()));

        TEMP.getInstance().onInitialize();
    }
}
