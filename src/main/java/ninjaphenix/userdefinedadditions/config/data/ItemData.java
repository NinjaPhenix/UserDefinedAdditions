package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.item.Item;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import ninjaphenix.userdefinedadditions.CommonEntry;
import ninjaphenix.userdefinedadditions.CustomItem;
import ninjaphenix.userdefinedadditions.config.Config;

public final class ItemData
{
    public String identifier;
    public String text_formatting;
    public FoodComponentData food_component;
    public Integer max_stack;
    public String item_group;

    private ItemData(String identifier, String text_formatting, FoodComponentData food_component, Integer max_stack, String item_group)
    {
        this.identifier = identifier;
        this.text_formatting = text_formatting;
        this.food_component = food_component;
        this.max_stack = max_stack;
        this.item_group = item_group;
    }

    public static ItemData parse(JsonObject object)
    {
        String identifier = object.get(String.class, "identifier");
        String text_formatting = object.get(String.class, "text_formatting");
        FoodComponentData food_component = object.get(FoodComponentData.class, "food_component");
        Integer max_stack = object.get(Integer.class, "max_stack");
        String item_group = object.get(String.class, "item_group");
        return new ItemData(identifier, text_formatting, food_component, max_stack, item_group);
    }

    public static JsonElement serialize(ItemData item, Marshaller marshaller)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("identifier", marshaller.serialize(item.identifier));
        if (item.max_stack != null) jsonObject.put("max_stack", marshaller.serialize(item.max_stack));
        if (item.text_formatting != null) jsonObject.put("text_formatting", marshaller.serialize(item.text_formatting));
        if (item.food_component != null) jsonObject.put("food_component", marshaller.serialize(item.food_component));
        if (item.item_group != null) jsonObject.put("item_group", marshaller.serialize(item.item_group));
        return jsonObject;
    }

    public Pair<Identifier, Item> asMCObject()
    {
        Identifier itemID;
        Formatting textFormatting;
        try
        {
            if (identifier.contains(":")) itemID = new Identifier(identifier);
            else itemID = Config.INSTANCE.getId(identifier);
        }
        catch (Exception e)
        {
            CommonEntry.LOGGER.error(
                    "[{}] Failed to create item with identifier \"{}\" as the identifier could not be created. Please ensure that the string only consists of lower case letters, underscores and a single :",
                    CommonEntry.MOD_ID, identifier);
            return null;
        }
        textFormatting = Formatting.byName(text_formatting);
        Item.Settings settings = new Item.Settings();
        if (food_component != null) settings.food(food_component.asMCObject());
        if (max_stack != null) settings.maxCount(max_stack);

        Identifier group = null;
        try
        {
            if (item_group.contains(":")) group = new Identifier(item_group);
            else group = Config.INSTANCE.getId(item_group);
        }
        catch (Exception e)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create itemgroup for item {} with group {}", CommonEntry.MOD_ID, identifier, item_group);
        }
        if (CommonEntry.itemGroups.containsKey(group)) settings.group(CommonEntry.itemGroups.get(group));

        return new Pair<>(itemID, new CustomItem(settings, textFormatting == null ? Formatting.WHITE : textFormatting));
    }
}
