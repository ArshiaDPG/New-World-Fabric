package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class NWBlockEntityTypes {
    public static final BlockEntityType<TombstoneBlockEntity> TOMBSTONE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Newworld.id("tombstone"),
            FabricBlockEntityTypeBuilder.create(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE).build()
    );

    public static void init(){}
}
