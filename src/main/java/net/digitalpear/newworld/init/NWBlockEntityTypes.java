package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class NWBlockEntityTypes {

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> blockEntityFactory, Block... blocks){
        return (BlockEntityType<T>) Registry.register(Registries.BLOCK_ENTITY_TYPE, Newworld.id(name), FabricBlockEntityTypeBuilder.create(blockEntityFactory, blocks).build());
    }

    public static final BlockEntityType<TombstoneBlockEntity> TOMBSTONE = register("tombstone", TombstoneBlockEntity::new, NWBlocks.TOMBSTONE);
    public static void init(){}
}
