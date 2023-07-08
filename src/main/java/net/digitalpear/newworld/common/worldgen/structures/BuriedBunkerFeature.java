package net.digitalpear.newworld.common.worldgen.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.newworld.init.NWStructures;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class BuriedBunkerFeature extends Structure {

    public static final Codec<BuriedBunkerFeature> CODEC = RecordCodecBuilder.<BuriedBunkerFeature>mapCodec(instance ->
            instance.group(BuriedBunkerFeature.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, BuriedBunkerFeature::new)).codec();


    private final RegistryEntry<StructurePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Type> projectStartToHeightmap;
    private final int maxDistanceFromCenter;


    public BuriedBunkerFeature(Structure.Config config,
                         RegistryEntry<StructurePool> startPool,
                         Optional<Identifier> startJigsawName,
                         int size,
                         HeightProvider startHeight,
                         Optional<Heightmap.Type> projectStartToHeightmap,
                         int maxDistanceFromCenter)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }


    private static boolean extraSpawningChecks(Structure.Context context) {
        // Grabs the chunk position we are at
        ChunkPos chunkpos = context.chunkPos();

        // Checks to make sure our structure does not spawn above land that's higher than y = 150
        // to demonstrate how this method is good for checking extra conditions for spawning
        return context.chunkGenerator().getHeightInGround(
                chunkpos.getStartX(),
                chunkpos.getStartZ(),
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
        Optional<StructurePosition> structurePiecesGenerator = StructurePoolBasedGenerator.generate(context,
                this.startPool,
                this.startJigsawName,
                this.size,
                blockpos.down(6), // Where to spawn the structure.
                false,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter);

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> getType() {
        return NWStructures.BURIED_BUNKER;
    }
}
