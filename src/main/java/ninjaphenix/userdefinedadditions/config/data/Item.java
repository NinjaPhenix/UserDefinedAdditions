package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;

public class Item
{
    public String identifier;
    public String font_color;
    public FoodComponent food_component;
    public Integer max_stack;
    public String item_group;

    private Item(String identifier, String font_color, FoodComponent food_component, Integer max_stack, String item_group)
    {
        this.identifier = identifier;
        this.font_color = font_color;
        this.food_component = food_component;
        this.max_stack = max_stack;
        this.item_group = item_group;
    }

    public static Item parse(JsonObject object)
    {
        String identifier = object.get(String.class, "identifier");
        String font_color = object.get(String.class, "font_color");
        FoodComponent foodComponent = object.get(FoodComponent.class, "food_component");
        Integer max_stack = object.get(Integer.class, "max_stack");
        String item_group = object.get(String.class, "item_group");
        return new Item(identifier, font_color, foodComponent, max_stack, item_group);
    }

    public static JsonElement serialize(Item item, Marshaller marshaller)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("identifier", marshaller.serialize(item.identifier));
        jsonObject.put("max_stack", marshaller.serialize(item.max_stack));
        if (item.font_color != null) jsonObject.put("font_color", marshaller.serialize(item.font_color));
        if (item.food_component != null) jsonObject.put("food_component", marshaller.serialize(item.food_component));
        if (item.item_group != null) jsonObject.put("item_group", marshaller.serialize(item.item_group));
        return jsonObject;
    }
}
