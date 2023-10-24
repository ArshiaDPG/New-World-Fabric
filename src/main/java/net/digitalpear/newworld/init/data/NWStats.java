package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.Newworld;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class NWStats {

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Newworld.id(id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    public static final Identifier TOMBSTONE_ACTIVATION = register("tombstone_activation", StatFormatter.DEFAULT);


    public static void init(){}


}
