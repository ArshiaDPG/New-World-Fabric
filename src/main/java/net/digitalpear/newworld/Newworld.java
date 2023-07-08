package net.digitalpear.newworld;

import net.digitalpear.newworld.common.worldgen.NWFeature;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWData;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.NWStructures;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Newworld implements ModInitializer {
    public static final String MOD_ID = "newworld";
    public static final String MOD_NAME = "New World";
    public static final Logger LOGGER = LogManager.getLogger();

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        NWBlocks.init();
        NWItems.init();
        NWBiomes.init();
        NWData.init();
        NWStructures.init();
        NWFeature.init();
        NWConfiguredFeatures.init();
        NWPlacedFeatures.init();

        LOGGER.info(MOD_NAME + " has initialized.");
    }
}
