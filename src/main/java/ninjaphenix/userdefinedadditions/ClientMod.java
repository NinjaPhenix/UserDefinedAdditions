package ninjaphenix.userdefinedadditions;

import net.fabricmc.api.ClientModInitializer;

public final class ClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
    }
}


/*

{
    "type": "minecraft:item",
    "version": 0,
    "data": {
        "food_component": {
            "type": "minecraft:predefined_food_component",
            "version": 0,
            "data": {
                "id": "minecraft:cooked_chicken"
            }
        },
        "settings": {
            "type": "minecraft:item_settings",
            "version": 0,
            "data": {
                "max_count": 16,
                "group": "minecraft:brewing",
                "rarity": "epic"
            }
        }
    }
}

 */