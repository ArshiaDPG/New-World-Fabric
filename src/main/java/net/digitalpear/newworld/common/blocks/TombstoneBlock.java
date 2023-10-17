package net.digitalpear.newworld.common.blocks;

import com.google.common.collect.ImmutableMap;
import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TombstoneBlock extends BlockWithEntity implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;

    protected static final Map<Direction, VoxelShape> directionToShapeMap = ImmutableMap.of(
            Direction.UP, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Direction.DOWN, Block.createCuboidShape(0.0, 7.0, 0.0, 16.0, 16.0, 16.0),
            Direction.SOUTH, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 9.0),
            Direction.NORTH, Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 16.0),
            Direction.EAST, Block.createCuboidShape(0.0, 0.0, 0.0, 9.0, 16.0, 16.0),
            Direction.WEST, Block.createCuboidShape(7.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    );
    public TombstoneBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(FACING, Direction.UP));
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return directionToShapeMap.get(state.get(FACING));
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TombstoneBlockEntity) {
            ((TombstoneBlockEntity)blockEntity).tick();
        }

    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        FluidState fluidState = world.getFluidState(blockPos);

        return super.getPlacementState(ctx).with(WATERLOGGED, fluidState.isOf(Fluids.WATER)).with(FACING, ctx.getSide());
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TombstoneBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
