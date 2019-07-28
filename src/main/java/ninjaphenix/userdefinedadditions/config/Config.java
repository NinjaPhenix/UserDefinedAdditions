package ninjaphenix.userdefinedadditions.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.impl.Marshaller;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.config.data.FoodComponentData;
import ninjaphenix.userdefinedadditions.config.data.GroupData;
import ninjaphenix.userdefinedadditions.config.data.ItemData;
import ninjaphenix.userdefinedadditions.config.data.StatusEffectInstanceData;

import java.nio.file.Path;

public class Config
{
    public static Config INSTANCE;

    @Comment("\nThe default mod id for items defined without a mod id\ne.g. \"silver_coin\" instead of \"uda:silver_coin\"")
    private final String default_modid = "uda";

    @Comment("\nFor help with defining custom items see: (link to wiki page)\nA list of items identifiers which can be used in crafting, economies, or as food.")
    private final ItemData[] items = new ItemData[]{};

    @Comment("\nDefine custom creative tabs to assign items into")
    private final GroupData[] item_groups = new GroupData[]{};

    public static void initialize()
    {
        Marshaller marshaller = ConfigManager.getMarshaller();
        marshaller.registerSerializer(Identifier.class, JsonPrimitive::new);
        marshaller.register(Identifier.class, (it) -> (it instanceof String) ? new Identifier((String) it) : new Identifier(it.toString()));
        marshaller.registerTypeAdapter(GroupData.class, GroupData::parse);
        marshaller.registerTypeAdapter(StatusEffectInstanceData.class, StatusEffectInstanceData::parse);
        marshaller.registerTypeAdapter(FoodComponentData.class, FoodComponentData::parse);
        marshaller.registerTypeAdapter(ItemData.class, ItemData::parse);
        marshaller.registerSerializer(StatusEffectInstanceData.class, StatusEffectInstanceData::serialize);
        marshaller.registerSerializer(FoodComponentData.class, FoodComponentData::serialize);
        marshaller.registerSerializer(ItemData.class, ItemData::serialize);
        Path configDirectory = FabricLoader.getInstance().getConfigDirectory().toPath();
        INSTANCE = ConfigManager.loadConfig(Config.class, configDirectory.resolve("UserDefinedAdditions.cfg").toFile());
    }

    public Identifier getId(String path) { return new Identifier(default_modid, path); }

    public ItemData[] getItems() { return items; }

    public GroupData[] getItemGroups() { return item_groups; }
}