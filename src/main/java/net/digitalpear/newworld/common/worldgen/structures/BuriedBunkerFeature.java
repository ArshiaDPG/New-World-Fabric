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

public class BuriedBunkerFeature extends StructureFeature<StructurePoolFeatureConfig> {


    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(StructurePoolFeatureConfig::getStartPool),
                            Codec.intRange(0, 30).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
                    )
                    .apply(instance, StructurePoolFeatureConfig::new)
    );

    public BuriedBunkerFeature() {
        super(CODEC, BuriedBunkerFeature::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }
    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
//        BlockPos place = context.chunkPos().getCenterAtY(0).down(4);
//        int width = 5;
//        int height = 5;
//        for (int x = place.getX() - width; x < place.getX() + width; x++){
//            for (int z = place.getZ() - width; z < place.getZ() + width; z++){
//                for (int y = place.getY() - height; y < place.getY() + height; y++){
//
//                }
//            }
//        }
        return true;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        if (!BuriedBunkerFeature.isFeatureChunk(context)) {
            return Optional.empty();
        }


        BlockPos blockpos = context.chunkPos().getCenterAtY(0);
        blockpos = blockpos.down(6);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockpos, true, true);

        if(structurePiecesGenerator.isPresent()) {
            NewWorld.LOGGER.log(Level.DEBUG, "Buried Bunker at {}", blockpos);
        }

        return structurePiecesGenerator;
    }
}