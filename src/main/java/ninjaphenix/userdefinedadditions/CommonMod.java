package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ModInitializer;

public class CommonMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        TEMP temp = TEMP.getInstance();

        temp.onInitialize();
    }
}
