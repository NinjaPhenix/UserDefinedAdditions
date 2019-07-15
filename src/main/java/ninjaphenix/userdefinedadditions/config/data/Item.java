package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;

public class Item
{
    public String identifier;
    public String font_color;
    public FoodComponent food_component;
    public int max_stack;
    public String item_group;

    private Item(String identifier, String font_color, FoodComponent food_component, int max_stack, String item_group)
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
}
