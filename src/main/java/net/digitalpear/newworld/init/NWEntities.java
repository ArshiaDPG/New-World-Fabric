package net.digitalpear.newworld.init;

import gg.moonflower.pollen.api.PollenRegistries;
import gg.moonflower.pollen.api.entity.PollinatedBoatType;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.digitalpear.newworld.NewWorld;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class NWEntities {

    public static final PollinatedRegistry<PollinatedBoatType> BOATS = PollinatedRegistry.create(PollenRegistries.BOAT_TYPE_REGISTRY, NewWorld.MOD_ID);

    public static final Supplier<PollinatedBoatType> FIR_BOAT = makeBoat("fir");


    public static Supplier<PollinatedBoatType> makeBoat(String name){
        return BOATS.register(name + "_boat", () -> new PollinatedBoatType(new Identifier(NewWorld.MOD_ID, "textures/entity/boat/"+ name +".png")));
    }
}
