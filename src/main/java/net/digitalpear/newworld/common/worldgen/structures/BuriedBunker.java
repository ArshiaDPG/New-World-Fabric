package net.digitalpear.newworld.common.worldgen.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.newworld.NewWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.PostPlacementProcessor;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.apache.logging.log4j.Level;

import java.util.Optional;

public class BuriedBunker extends StructureFeature<StructurePoolFeatureConfig> {


    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(StructurePoolFeatureConfig::getStartPool),
                            Codec.intRange(0, 30).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
                    )
                    .apply(instance, StructurePoolFeatureConfig::new)
    );

    public BuriedBunker() {
        super(CODEC, BuriedBunker::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }
    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        return true;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        if (!BuriedBunker.isFeatureChunk(context)) {
            return Optional.empty();
        }


        BlockPos blockpos = context.chunkPos().getCenterAtY(0);
        blockpos = blockpos.down(4);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockpos.down(4), true, true);

        if(structurePiecesGenerator.isPresent()) {
            NewWorld.LOGGER.log(Level.DEBUG, "Rundown House at {}", blockpos);
        }

        return structurePiecesGenerator;
    }
}