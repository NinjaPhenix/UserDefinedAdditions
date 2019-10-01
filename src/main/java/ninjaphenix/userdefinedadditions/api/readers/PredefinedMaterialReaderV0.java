package ninjaphenix.userdefinedadditions.api.readers;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.impl.Marshaller;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.api.readers.interfaces.Reader;

import java.util.HashMap;
import java.util.Map;

public final class PredefinedMaterialReaderV0 implements Reader<Material>
{
    private static final Map<Identifier, Material> materialMap;
    private static final PredefinedMaterialReaderV0 INSTANCE = new PredefinedMaterialReaderV0();

    static
    {
        materialMap = new HashMap<>();
        materialMap.put(new Identifier("minecraft", "air"), Material.AIR);
        materialMap.put(new Identifier("minecraft", "structure_void"), Material.STRUCTURE_VOID);
        materialMap.put(new Identifier("minecraft", "portal"), Material.PORTAL);
        materialMap.put(new Identifier("minecraft", "carpet"), Material.CARPET);
        materialMap.put(new Identifier("minecraft", "plant"), Material.PLANT);
        materialMap.put(new Identifier("minecraft", "underwater_plant"), Material.UNDERWATER_PLANT);
        materialMap.put(new Identifier("minecraft", "replaceable_plant"), Material.REPLACEABLE_PLANT);
        materialMap.put(new Identifier("minecraft", "seagrass"), Material.SEAGRASS);
        materialMap.put(new Identifier("minecraft", "water"), Material.WATER);
        materialMap.put(new Identifier("minecraft", "bubble_column"), Material.BUBBLE_COLUMN);
        materialMap.put(new Identifier("minecraft", "lava"), Material.LAVA);
        materialMap.put(new Identifier("minecraft", "snow"), Material.SNOW);
        materialMap.put(new Identifier("minecraft", "fire"), Material.FIRE);
        materialMap.put(new Identifier("minecraft", "part"), Material.PART);
        materialMap.put(new Identifier("minecraft", "cobweb"), Material.COBWEB);
        materialMap.put(new Identifier("minecraft", "redstone_lamp"), Material.REDSTONE_LAMP);
        materialMap.put(new Identifier("minecraft", "clay"), Material.CLAY);
        materialMap.put(new Identifier("minecraft", "earth"), Material.EARTH);
        materialMap.put(new Identifier("minecraft", "organic"), Material.ORGANIC);
        materialMap.put(new Identifier("minecraft", "packed_ice"), Material.PACKED_ICE);
        materialMap.put(new Identifier("minecraft", "sand"), Material.SAND);
        materialMap.put(new Identifier("minecraft", "sponge"), Material.SPONGE);
        materialMap.put(new Identifier("minecraft", "shulker_box"), Material.SHULKER_BOX);
        materialMap.put(new Identifier("minecraft", "wood"), Material.WOOD);
        materialMap.put(new Identifier("minecraft", "bamboo_sapling"), Material.BAMBOO_SAPLING);
        materialMap.put(new Identifier("minecraft", "bamboo"), Material.BAMBOO);
        materialMap.put(new Identifier("minecraft", "wool"), Material.WOOL);
        materialMap.put(new Identifier("minecraft", "tnt"), Material.TNT);
        materialMap.put(new Identifier("minecraft", "leaves"), Material.LEAVES);
        materialMap.put(new Identifier("minecraft", "glass"), Material.GLASS);
        materialMap.put(new Identifier("minecraft", "ice"), Material.ICE);
        materialMap.put(new Identifier("minecraft", "cactus"), Material.CACTUS);
        materialMap.put(new Identifier("minecraft", "stone"), Material.STONE);
        materialMap.put(new Identifier("minecraft", "metal"), Material.METAL);
        materialMap.put(new Identifier("minecraft", "snow_block"), Material.SNOW_BLOCK);
        materialMap.put(new Identifier("minecraft", "anvil"), Material.ANVIL);
        materialMap.put(new Identifier("minecraft", "barrier"), Material.BARRIER);
        materialMap.put(new Identifier("minecraft", "piston"), Material.PISTON);
        materialMap.put(new Identifier("minecraft", "unused_plant"), Material.UNUSED_PLANT);
        materialMap.put(new Identifier("minecraft", "pumpkin"), Material.PUMPKIN);
        materialMap.put(new Identifier("minecraft", "egg"), Material.EGG);
        materialMap.put(new Identifier("minecraft", "cake"), Material.CAKE);
    }

    public static Reader<Material> getInstance() { return INSTANCE; }

    @Override
    public Material read(JsonObject object)
    {
        Marshaller marshaller = object.getMarshaller();
        final Identifier identifier = marshaller.marshall(Identifier.class, object.get("id"));
        if (identifier != null && materialMap.containsKey(identifier)) return materialMap.get(identifier);
        return null;
    }
}