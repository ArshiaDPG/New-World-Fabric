package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.Newworld;
import net.minecraft.util.Identifier;

public class NWLootTables {
    public static final Identifier BUNKER_BARREL = makeChestLootTable("bunker_barrel");
    public static final Identifier BUNKER_CACHE = makeChestLootTable("bunker_cache");


    public static Identifier makeChestLootTable(String name){
        return Newworld.id(name).withPrefixedPath("chests/");
    }
}
