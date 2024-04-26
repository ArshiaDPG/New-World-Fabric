package net.digitalpear.newworld.common.worldgen.structures;

import com.mojang.serialization.MapCodec;
import net.digitalpear.newworld.init.NWStructureTypes;
import net.digitalpear.newworld.init.worldgen.structures.NWStructurePools;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.alias.StructurePoolAliasLookup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class BuriedBunkerFeature extends Structure {

    public static final MapCodec<BuriedBunkerFeature> CODEC = createCodec(BuriedBunkerFeature::new);

    public BuriedBunkerFeature(Structure.Config config)
    {
        super(config);
    }


    private static boolean extraSpawningChecks(Structure.Context context) {
        // Grabs the chunk position we are at
        ChunkPos chunkpos = context.chunkPos();
        Random random = context.random();

        // Checks to make sure our structure does not spawn above land that's higher than y = 150
        // to demonstrate how this method is good for checking extra conditions for spawning
        return context.chunkGenerator().getHeightInGround(
                chunkpos.getStartX() + random.nextInt(16),
                chunkpos.getStartZ() + random.nextInt(16),
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                context.world(),
                context.noiseConfig()) < 150;
    }


    @Override
    protected Optional<StructurePosition> getStructurePosition(Context context) {
        if (!BuriedBunkerFeature.extraSpawningChecks(context)) {
            return Optional.empty();
        }
        BlockPos blockpos = context.chunkPos().getCenterAtY(0);
        RegistryEntryLookup<StructurePool> poolCollector = context.dynamicRegistryManager().getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL);

        Optional<StructurePosition> structurePiecesGenerator = StructurePoolBasedGenerator.generate(context,
                poolCollector.getOrThrow(NWStructurePools.BURIED_BUNKER),
                Optional.empty(),
                1,
                blockpos.down(6), // Where to spawn the structure.
                false,
                Optional.of(Heightmap.Type.WORLD_SURFACE_WG),
                80,
                StructurePoolAliasLookup.EMPTY);

        return structurePiecesGenerator;
    }


    @Override
    public StructureType<?> getType() {
        return NWStructureTypes.BURIED_BUNKER;
    }
}
