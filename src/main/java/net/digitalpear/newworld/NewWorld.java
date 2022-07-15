package net.digitalpear.newworld;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.NWUtil;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

public class NewWorld implements ModInitializer {

    public static String getId(String id){
        return MOD_ID + ":" + id;
    }
    public static final String MOD_ID = "newworld";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        NWBlocks.init();
        NWItems.init();
        NWBiomes.init();
        NWUtil.init();
    }

}
