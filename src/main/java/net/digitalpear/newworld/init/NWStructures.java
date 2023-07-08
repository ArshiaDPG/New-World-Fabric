package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.structures.BuriedBunkerFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class NWStructures {
    /**
     /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     */
    public static StructureType<BuriedBunkerFeature> BURIED_BUNKER = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(Newworld.MOD_ID, "buried_bunker"), () -> BuriedBunkerFeature.CODEC);

    public static void init() {
        // The generation step for when to generate the structure. there are 10 stages you can pick from!
        // This surface structure stage places the structure before plants and ores are generated.
    }
}