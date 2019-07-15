package ninjaphenix.userdefinedadditions.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.impl.Marshaller;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

public class Config
{
    public static Config INSTANCE;

    @Comment("\nThe default mod id for items defined without a mod id\ne.g. \"silver_coin\" instead of \"uda:silver_coin\"")
    private final String default_modid = "uda";

    @Comment("\nFor help with defining custom items see: (link to wiki page)\nA list of dummy items identifiers which can be used in crafting, economies, or as food.")
    private final ItemAndSettings[] dummy_items = new ItemAndSettings[]{};

    public static void initialize()
    {
        Marshaller marshaller = ConfigManager.getMarshaller();
        marshaller.registerSerializer(Identifier.class, JsonPrimitive::new);
        marshaller.register(Identifier.class, (it) -> (it instanceof String) ? new Identifier((String) it) : new Identifier(it.toString()));
        marshaller.registerTypeAdapter(StatusEffectInstance.class, (it) ->
        {
            Identifier effect = it.get(Identifier.class, "effect");
            Integer length = it.get(Integer.class, "length");
            Integer power = it.get(Integer.class, "power");
            Boolean visible = it.get(Boolean.class, "visible");
            return new StatusEffectInstance(effect, length, power, visible);
        });

        marshaller.registerTypeAdapter(FoodComponent.class, (it) ->
        {
            Integer hunger = it.get(Integer.class, "hunger");
            Float saturation = it.get(Float.class, "saturation");
            Boolean meat = it.get(Boolean.class, "is_meat");
            Boolean always_edible = it.get(Boolean.class, "is_always_edible");
            Boolean snack = it.get(Boolean.class, "is_snack");
            StatusEffectInstance[] status_effects = it.get(StatusEffectInstance[].class, "status_effects");
            return new FoodComponent(hunger, saturation, meat, always_edible, snack, status_effects);
        });

        marshaller.registerTypeAdapter(ItemAndSettings.class, (it) ->
        {
            String identifier = it.get(String.class, "identifier");
            String font_color = it.get(String.class, "font_color");
            FoodComponent foodComponent = it.get(FoodComponent.class, "food_component");
            Integer max_stack = it.get(Integer.class, "max_stack");
            String item_group = it.get(String.class, "item_group");
            return new ItemAndSettings(identifier, font_color, foodComponent, max_stack, item_group);
        });
        Path configDirectory = FabricLoader.getInstance().getConfigDirectory().toPath();
        INSTANCE = ConfigManager.loadConfig(Config.class, configDirectory.resolve("UserDefinedAdditions.cfg").toFile());
    }

    // Classes below are for config loading / saving.
    private static class ItemAndSettings
    {
        private String identifier;
        private String font_color;
        private FoodComponent food_component;
        private int max_stack;
        private String item_group;

        public ItemAndSettings(String identifier, String font_color, FoodComponent food_component, int max_stack, String item_group)
        {
            this.identifier = identifier;
            this.font_color = font_color;
            this.food_component = food_component;
            this.max_stack = max_stack;
            this.item_group = item_group;
        }
    }

    private static class FoodComponent
    {
        private int hunger;
        private float saturation;
        private boolean meat;
        private boolean always_edible;
        private boolean snack;
        private StatusEffectInstance[] status_effects;

        public FoodComponent(int hunger, float saturation, boolean meat, boolean always_edible, boolean snack, StatusEffectInstance[] status_effects)
        {
            this.hunger = hunger;
            this.saturation = saturation;
            this.meat = meat;
            this.always_edible = always_edible;
            this.snack = snack;
            this.status_effects = status_effects;
        }
    }

    private static class StatusEffectInstance
    {
        private Identifier effect;
        private int length;
        private int power;
        private boolean visible;

        public StatusEffectInstance(Identifier effect, int length, int power, boolean visible)
        {
            this.effect = effect;
            this.length = length;
            this.power = power;
            this.visible = visible;
        }
    }
}