package net.digitalpear.newworld.common.datagens.loot;

import net.digitalpear.newworld.init.data.NWLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class NWChestLootTableProvider extends SimpleFabricLootTableProvider {

    public NWChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }



    @Override
    public void accept(RegistryWrapper.WrapperLookup registryLookup, BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        exporter.accept(NWLootTables.BUNKER_BARREL, LootTable.builder()
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(3, 10))
                        .with(ItemEntry.builder(Items.PAPER).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                        .with(ItemEntry.builder(Items.COBWEB).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 12.0F))))
                        .with(ItemEntry.builder(Items.MELON_SEEDS).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                        .with(ItemEntry.builder(Items.STICK).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                        .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                        .build()));

        exporter.accept(NWLootTables.BUNKER_CACHE, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.DRIED_KELP_BLOCK)))
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(5, 7))
                        .with(ItemEntry.builder(Items.DRIED_KELP).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                        .with(ItemEntry.builder(Items.IRON_NUGGET).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))
                        .with(ItemEntry.builder(Items.GUNPOWDER).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.EMERALD).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                        .build()));
    }
}
