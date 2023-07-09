package net.digitalpear.newworld.init;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.digitalpear.newworld.Newworld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class NWBoatTypes {
    public static TerraformBoatType makeBoat(String name, ItemConvertible planks, Item boat, Item chestBoat){
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, new Identifier(Newworld.MOD_ID, name),
                new TerraformBoatType.Builder()
                        .planks(planks.asItem())
                        .item(boat)
                        .chestItem(chestBoat)
                        .build());
    }


    public static final TerraformBoatType FIR = makeBoat("fir", NWBlocks.FIR_PLANKS, NWItems.FIR_BOAT, NWItems.FIR_CHEST_BOAT);


    public static void init() {
    }
}
