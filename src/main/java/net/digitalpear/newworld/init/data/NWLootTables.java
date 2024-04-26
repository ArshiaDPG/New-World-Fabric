package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.Newworld;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class NWLootTables {
    public static final RegistryKey<LootTable> BUNKER_BARREL = makeChestLootTable("bunker_barrel");
    public static final RegistryKey<LootTable> BUNKER_CACHE = makeChestLootTable("bunker_cache");


    public static RegistryKey<LootTable> makeChestLootTable(String name){
        return makeLootTable("chests/" + name);
    }
    public static RegistryKey<LootTable> makeLootTable(String name){
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Newworld.id(name));
    }
}
