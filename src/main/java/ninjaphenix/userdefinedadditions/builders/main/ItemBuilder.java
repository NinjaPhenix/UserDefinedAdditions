package ninjaphenix.userdefinedadditions.builders.main;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.ContentLoader;
import ninjaphenix.userdefinedadditions.api.RegisteredBuilder;

public class ItemBuilder implements RegisteredBuilder<Item>
{
    private Item.Settings settings = new Item.Settings();

    public ItemBuilder maxCount(int maxCount)
    {
        settings.maxCount(maxCount);
        return this;
    }

    public ItemBuilder maxDamage(int maxDamage)
    {
        settings.maxDamage(maxDamage);
        return this;
    }

    public ItemBuilder remainder(String remainderId)
    {
        settings.recipeRemainder(Registry.ITEM.get(new Identifier(remainderId)));
        return this;
    }

    public ItemBuilder rarity(String rarity)
    {
        Rarity parsed = Rarity.COMMON;
        switch(rarity.toUpperCase()) {
            case "COMMON":
                parsed = Rarity.COMMON;
                break;
            case "RARE":
                parsed = Rarity.RARE;
                break;
            case "UNCOMMON":
                parsed = Rarity.UNCOMMON;
                break;
            case "EPIC":
                parsed = Rarity.EPIC;
        }
        settings.rarity(parsed);
        return this;
    }

    public ItemBuilder group(String group)
    {
        settings.group(ContentLoader.itemGroups.get(new Identifier(group)));
        return this;
    }

    public ItemBuilder group(ItemGroup group)
    {
        settings.group(group);
        return this;
    }

    public ItemBuilder foodOf(String of)
    {
        //TODO: handle if not food
        Item item = Registry.ITEM.get(new Identifier(of));
        if (item.isFood()) {
            settings.food(item.getFoodComponent());
        }
        return this;
    }

    public ItemBuilder food(FoodComponent food)
    {
        settings.food(food);
        return this;
    }

    public ItemBuilder food(FoodComponentBuilder builder)
    {
        settings.food(builder.build());
        return this;
    }

    @Override
    public Item register(String id)
    {
        Identifier parsed = new Identifier(id);
        return Registry.register(Registry.ITEM, parsed, new Item(settings));
    }

    public Item blockItem(Block block) {
        return new BlockItem(block, settings);
    }
}
