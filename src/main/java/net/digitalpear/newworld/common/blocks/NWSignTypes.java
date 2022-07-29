package net.digitalpear.newworld.common.blocks;

import net.digitalpear.newworld.mixin.SignTypeAccessor;
import net.minecraft.util.SignType;

public class NWSignTypes {
    public static final SignType FIR =
            SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("fir"));
}