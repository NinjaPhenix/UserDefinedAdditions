package ninjaphenix.userdefinedadditions.api.readers;

import net.minecraft.block.Material;

import java.util.HashMap;
import java.util.Map;

public final class PredefinedMaterialReaderV0
{
    private static final Map<String, Material> materialMap;

    static
    {
        materialMap = new HashMap<>();
        materialMap.put("air", Material.AIR);
        materialMap.put("structure_void", Material.STRUCTURE_VOID);
        materialMap.put("portal", Material.PORTAL);
        materialMap.put("carpet", Material.CARPET);
        materialMap.put("plant", Material.PLANT);
        materialMap.put("underwater_plant", Material.UNDERWATER_PLANT);
        materialMap.put("replaceable_plant", Material.REPLACEABLE_PLANT);
        materialMap.put("seagrass", Material.SEAGRASS);
        materialMap.put("water", Material.WATER);
        materialMap.put("bubble_column", Material.BUBBLE_COLUMN);
        materialMap.put("lava", Material.LAVA);
        materialMap.put("snow", Material.SNOW);
        materialMap.put("fire", Material.FIRE);
        materialMap.put("part", Material.PART);
        materialMap.put("cobweb", Material.COBWEB);
        materialMap.put("redstone_lamp", Material.REDSTONE_LAMP);
        materialMap.put("clay", Material.CLAY);
        materialMap.put("earth", Material.EARTH);
        materialMap.put("organic", Material.ORGANIC);
        materialMap.put("packed_ice", Material.PACKED_ICE);
        materialMap.put("sand", Material.SAND);
        materialMap.put("sponge", Material.SPONGE);
        materialMap.put("shulker_box", Material.SHULKER_BOX);
        materialMap.put("wood", Material.WOOD);
        materialMap.put("bamboo_sapling", Material.BAMBOO_SAPLING);
        materialMap.put("bamboo", Material.BAMBOO);
        materialMap.put("wool", Material.WOOL);
        materialMap.put("tnt", Material.TNT);
        materialMap.put("leaves", Material.LEAVES);
        materialMap.put("glass", Material.GLASS);
        materialMap.put("ice", Material.ICE);
        materialMap.put("cactus", Material.CACTUS);
        materialMap.put("stone", Material.STONE);
        materialMap.put("metal", Material.METAL);
        materialMap.put("snow_block", Material.SNOW_BLOCK);
        materialMap.put("anvil", Material.ANVIL);
        materialMap.put("barrier", Material.BARRIER);
        materialMap.put("piston", Material.PISTON);
        materialMap.put("unused_plant", Material.UNUSED_PLANT);
        materialMap.put("pumpkin", Material.PUMPKIN);
        materialMap.put("egg", Material.EGG);
        materialMap.put("cake", Material.CAKE);
    }


    public Material get(String name) {
        return materialMap.getOrDefault(name.toLowerCase(), Material.AIR);
    }
}