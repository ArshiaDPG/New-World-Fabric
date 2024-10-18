package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class NWEntityTypes {
    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Newworld.id(id));
    }
    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type){
        return register(keyOf(name), type);
    }
    public static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> name, EntityType.Builder<T> type){
        return Registry.register(Registries.ENTITY_TYPE, name, type.build(name));
    }
    private static EntityType.EntityFactory<BoatEntity> boatFactory(Item item) {
        return (entityType, world) -> new BoatEntity(entityType, world, () -> item);
    }
    private static EntityType.EntityFactory<ChestBoatEntity> chestBoatFactory(Item item) {
        return (entityType, world) -> new ChestBoatEntity(entityType, world, () -> item);
    }

//    public static final EntityType<BoatEntity> FIR_BOAT = register("fir_boat", EntityType.Builder.create(boatFactory(NWItems.FIR_BOAT), SpawnGroup.MISC).dropsNothing().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));
//    public static final EntityType<ChestBoatEntity> FIR_CHEST_BOAT = register("fir_chest_boat", EntityType.Builder.create(chestBoatFactory(NWItems.FIR_CHEST_BOAT), SpawnGroup.MISC).dropsNothing().dimensions(1.375F, 0.5625F).eyeHeight(0.5625F).maxTrackingRange(10));

    public static void init() {
    }
}
