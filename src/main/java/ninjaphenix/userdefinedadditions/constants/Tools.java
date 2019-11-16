package ninjaphenix.userdefinedadditions.constants;

import net.minecraft.util.Identifier;

public class Tools
{
    public static final Tools INSTANCE = new Tools();
    // <editor-fold desc="Tool Types">
    public final String PICKAXE = "fabric:pickaxes";
    public final String AXE = "fabric:axes";
    public final String SHOVEL = "fabric:shovels";
    public final String HOE = "fabric:hoes";
    public final String SWORD = "fabric:swords";
    // </editor-fold>
    // <editor-fold desc="Tool Tiers">
    public final int WOOD_TIER = 0;
    public final int STONE_TIER = 1;
    public final int IRON_TIER = 2;
    // </editor-fold>
    public final int DIAMOND_TIER = 3;
    //<editor-fold desc="Pickaxes">
    public final Tool WOOD_PICKAXE = Tool.of(PICKAXE, WOOD_TIER);
    public final Tool STONE_PICKAXE = Tool.of(PICKAXE, STONE_TIER);
    public final Tool IRON_PICKAXE = Tool.of(PICKAXE, IRON_TIER);
    public final Tool DIAMOND_PICKAXE = Tool.of(PICKAXE, DIAMOND_TIER);
    //</editor-fold>
    //<editor-fold desc="Swords">
    public final Tool WOOD_SWORD = Tool.of(SWORD, WOOD_TIER);
    public final Tool STONE_SWORD = Tool.of(SWORD, STONE_TIER);
    public final Tool IRON_SWORD = Tool.of(SWORD, IRON_TIER);
    public final Tool DIAMOND_SWORD = Tool.of(SWORD, DIAMOND_TIER);
    //</editor-fold>
    //<editor-fold desc="Shovels">
    public final Tool WOOD_SHOVEL = Tool.of(SHOVEL, WOOD_TIER);
    public final Tool STONE_SHOVEL = Tool.of(SHOVEL, STONE_TIER);
    public final Tool IRON_SHOVEL = Tool.of(SHOVEL, IRON_TIER);
    public final Tool DIAMOND_SHOVEL = Tool.of(SHOVEL, DIAMOND_TIER);
    //</editor-fold>
    //<editor-fold desc="Hoes">
    public final Tool WOOD_HOE = Tool.of(HOE, WOOD_TIER);
    public final Tool STONE_HOE = Tool.of(HOE, STONE_TIER);
    public final Tool IRON_HOE = Tool.of(HOE, IRON_TIER);
    public final Tool DIAMOND_HOE = Tool.of(HOE, DIAMOND_TIER);
    //</editor-fold>
    //<editor-fold desc="Axes">
    public final Tool WOOD_AXE = Tool.of(AXE, WOOD_TIER);
    public final Tool STONE_AXE = Tool.of(AXE, STONE_TIER);
    public final Tool IRON_AXE = Tool.of(AXE, IRON_TIER);
    //</editor-fold>
    public final Tool DIAMOND_AXE = Tool.of(AXE, DIAMOND_TIER);

    private Tools() {}

    public Tool of(String type, int miningLevel) { return Tool.of(type, miningLevel); }

    public static class Tool
    {
        public String ToolType;
        public int MiningLevel;

        private Tool(Identifier type, int level)
        {
            ToolType = type.toString(); // ensures the tool has a valid identifier
            MiningLevel = level;
        }

        private static Tool of(String type, int level)
        {
            Identifier iType = Identifier.tryParse(type);
            if (iType == null) throw new IllegalArgumentException("type must be a valid identifier.");
            return new Tool(iType, level);
        }
    }
}
