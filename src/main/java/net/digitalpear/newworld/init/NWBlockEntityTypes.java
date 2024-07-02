package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class NWBlockEntityTypes {

    public static <T extends BlockEntity> BlockEntityType register(String name, BlockEntityType<T> builder){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Newworld.id(name), builder);
    }

    public static final BlockEntityType<TombstoneBlockEntity> TOMBSTONE = register("tombstone", BlockEntityType.Builder.create(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE).build());
    public static void init(){}
}
