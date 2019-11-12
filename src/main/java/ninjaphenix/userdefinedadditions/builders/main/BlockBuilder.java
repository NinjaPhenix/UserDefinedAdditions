package ninjaphenix.userdefinedadditions.builders.main;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.api.RegisteredBuilder;

//TODO: sounds, color, random tick?
public class BlockBuilder implements RegisteredBuilder<Block>
{
    private FabricBlockSettings settings;
    private ItemBuilder item = null;

    //TODO: empty ctor and require a material call first?
    public BlockBuilder(Material material) {
        this.settings = FabricBlockSettings.of(material);
    }

    public BlockBuilder(Block block) {
        this.settings = FabricBlockSettings.copy(block);
    }

    public BlockBuilder hardness(float hardness) {
        settings.hardness(hardness);
        return this;
    }

    public BlockBuilder resistance(float resistance) {
        settings.resistance(resistance);
        return this;
    }

    public BlockBuilder strength(float hardness, float resistance) {
        settings.strength(hardness, resistance);
        return this;
    }

    public BlockBuilder breakByTool(String tool) {
        //TODO: better solution than just hardcoding to mining tools?
        return this;
    }

    public BlockBuilder breakByTool(String tool, int miningLevel) {
        //TODO: better solution than just hardcoding to mining tools?
        return this;
    }

    public BlockBuilder lightLevel(int level) {
        settings.lightLevel(level);
        return this;
    }

    public BlockBuilder slipperiness(float slipperiness) {
        settings.slipperiness(slipperiness);
        return this;
    }

    public BlockBuilder breakByHand() {
        settings.breakByHand(true);
        return this;
    }

    public BlockBuilder breakInstantly() {
        settings.breakInstantly();
        return this;
    }

    public BlockBuilder dropsNothing() {
        settings.dropsNothing();
        return this;
    }

    public BlockBuilder dropsLike(String block) {
        settings.dropsLike(Registry.BLOCK.get(new Identifier(block)));
        return this;
    }

    public BlockBuilder drops(String table) {
        settings.drops(new Identifier(table));
        return this;
    }

    public BlockBuilder noCollision() {
        settings.noCollision();
        return this;
    }

    public BlockBuilder dynamicBounds() {
        settings.dynamicBounds();
        return this;
    }

    public BlockBuilder item(ItemBuilder builder) {
        item = builder;
        return this;
    }

    @Override
    public Block register(String id)
    {
        return null;
    }
}
