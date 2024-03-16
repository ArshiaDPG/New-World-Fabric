package net.digitalpear.newworld;

import net.digitalpear.newworld.common.worldgen.NWFeature;
import net.digitalpear.newworld.init.*;
import net.digitalpear.newworld.init.data.NWData;
import net.digitalpear.newworld.init.data.NWStats;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
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
        NWBlockEntityTypes.init();
        NWBoatTypes.init();
        NWItems.init();
        NWBiomes.init();
        NWData.init();
        NWStructures.init();
        NWFeature.init();
        NWConfiguredFeatures.init();
        NWPlacedFeatures.init();
        NWStats.init();

        LOGGER.info(MOD_NAME + " has initialized.");
    }
}
