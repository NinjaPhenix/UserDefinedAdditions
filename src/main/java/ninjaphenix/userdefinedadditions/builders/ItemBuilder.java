package ninjaphenix.userdefinedadditions.builders;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.RegistrableBuilder;

public class ItemBuilder implements RegistrableBuilder<Item>
{
    private final Item.Settings settings = new Item.Settings();

    @Override
    public Item build() { return new Item(settings); }

    @Override
    public Item register(String id) { return Registry.register(Registry.ITEM, id, build()); }
}
