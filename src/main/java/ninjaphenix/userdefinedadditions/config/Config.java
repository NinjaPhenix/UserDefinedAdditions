package ninjaphenix.userdefinedadditions.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.impl.Marshaller;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.config.data.FoodComponent;
import ninjaphenix.userdefinedadditions.config.data.Item;
import ninjaphenix.userdefinedadditions.config.data.StatusEffectInstance;

import java.nio.file.Path;

public class Config
{
    public static Config INSTANCE;

    @Comment("\nThe default mod id for items defined without a mod id\ne.g. \"silver_coin\" instead of \"uda:silver_coin\"")
    private final String default_modid = "uda";

    @Comment("\nFor help with defining custom items see: (link to wiki page)\nA list of dummy items identifiers which can be used in crafting, economies, or as food.")
    private final Item[] dummy_items = new Item[]{};

    public static void initialize()
    {
        Marshaller marshaller = ConfigManager.getMarshaller();
        marshaller.registerSerializer(Identifier.class, JsonPrimitive::new);
        marshaller.register(Identifier.class, (it) -> (it instanceof String) ? new Identifier((String) it) : new Identifier(it.toString()));
        marshaller.registerTypeAdapter(StatusEffectInstance.class, StatusEffectInstance::parse);
        marshaller.registerTypeAdapter(FoodComponent.class, FoodComponent::parse);
        marshaller.registerTypeAdapter(Item.class, Item::parse);
        marshaller.registerSerializer(Item.class, Item::serialize);
        Path configDirectory = FabricLoader.getInstance().getConfigDirectory().toPath();
        INSTANCE = ConfigManager.loadConfig(Config.class, configDirectory.resolve("UserDefinedAdditions.cfg").toFile());
    }

    public Identifier getId(String path) { return new Identifier(default_modid, path); }

    public Item[] getItems() { return dummy_items; }
}