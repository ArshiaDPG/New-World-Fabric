package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.entities.automaton.AutomatonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class NWEntityTypes {

    public static final EntityType<AutomatonEntity> AUTOMATON = Registry.register(
            Registries.ENTITY_TYPE,
            Newworld.id("automaton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AutomatonEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );


    public static void init(){
        FabricDefaultAttributeRegistry.register(AUTOMATON, AutomatonEntity.createAutomatonAttributes());
    }
}
