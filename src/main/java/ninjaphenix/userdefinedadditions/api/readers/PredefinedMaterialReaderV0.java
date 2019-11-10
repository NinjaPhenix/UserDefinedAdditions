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
        materialMap.put(new Identifier("air"), Material.AIR);
        materialMap.put(new Identifier("structure_void"), Material.STRUCTURE_VOID);
        materialMap.put(new Identifier("portal"), Material.PORTAL);
        materialMap.put(new Identifier("carpet"), Material.CARPET);
        materialMap.put(new Identifier("plant"), Material.PLANT);
        materialMap.put(new Identifier("underwater_plant"), Material.UNDERWATER_PLANT);
        materialMap.put(new Identifier("replaceable_plant"), Material.REPLACEABLE_PLANT);
        materialMap.put(new Identifier("seagrass"), Material.SEAGRASS);
        materialMap.put(new Identifier("water"), Material.WATER);
        materialMap.put(new Identifier("bubble_column"), Material.BUBBLE_COLUMN);
        materialMap.put(new Identifier("lava"), Material.LAVA);
        materialMap.put(new Identifier("snow"), Material.SNOW);
        materialMap.put(new Identifier("fire"), Material.FIRE);
        materialMap.put(new Identifier("part"), Material.PART);
        materialMap.put(new Identifier("cobweb"), Material.COBWEB);
        materialMap.put(new Identifier("redstone_lamp"), Material.REDSTONE_LAMP);
        materialMap.put(new Identifier("clay"), Material.CLAY);
        materialMap.put(new Identifier("earth"), Material.EARTH);
        materialMap.put(new Identifier("organic"), Material.ORGANIC);
        materialMap.put(new Identifier("packed_ice"), Material.PACKED_ICE);
        materialMap.put(new Identifier("sand"), Material.SAND);
        materialMap.put(new Identifier("sponge"), Material.SPONGE);
        materialMap.put(new Identifier("shulker_box"), Material.SHULKER_BOX);
        materialMap.put(new Identifier("wood"), Material.WOOD);
        materialMap.put(new Identifier("bamboo_sapling"), Material.BAMBOO_SAPLING);
        materialMap.put(new Identifier("bamboo"), Material.BAMBOO);
        materialMap.put(new Identifier("wool"), Material.WOOL);
        materialMap.put(new Identifier("tnt"), Material.TNT);
        materialMap.put(new Identifier("leaves"), Material.LEAVES);
        materialMap.put(new Identifier("glass"), Material.GLASS);
        materialMap.put(new Identifier("ice"), Material.ICE);
        materialMap.put(new Identifier("cactus"), Material.CACTUS);
        materialMap.put(new Identifier("stone"), Material.STONE);
        materialMap.put(new Identifier("metal"), Material.METAL);
        materialMap.put(new Identifier("snow_block"), Material.SNOW_BLOCK);
        materialMap.put(new Identifier("anvil"), Material.ANVIL);
        materialMap.put(new Identifier("barrier"), Material.BARRIER);
        materialMap.put(new Identifier("piston"), Material.PISTON);
        materialMap.put(new Identifier("unused_plant"), Material.UNUSED_PLANT);
        materialMap.put(new Identifier("pumpkin"), Material.PUMPKIN);
        materialMap.put(new Identifier("egg"), Material.EGG);
        materialMap.put(new Identifier("cake"), Material.CAKE);
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