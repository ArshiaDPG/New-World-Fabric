package net.digitalpear.newworld;

import net.digitalpear.newworld.common.worldgen.biome.NWOverworldBiomes;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.NWStructures;
import net.digitalpear.newworld.init.NWUtil;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewWorld implements ModInitializer {

    public static String getId(String id){
        return MOD_ID + ":" + id;
    }
    public static final String MOD_ID = "newworld";
    public static final String MOD_NAME = "New World";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        NWBlocks.init();
        NWItems.init();
        NWOverworldBiomes.init();
        NWUtil.init();
        NWStructures.init();

        LOGGER.info(MOD_NAME + " has initialized.");
    }

}
