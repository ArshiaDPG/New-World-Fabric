package net.digitalpear.newworld.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SproutsBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class MossSproutsBlock extends SproutsBlock {
    public MossSproutsBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
    }
}
