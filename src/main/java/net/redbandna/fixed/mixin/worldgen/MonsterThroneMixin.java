package net.redbandna.fixed.mixin.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import net.redbandna.fixed.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherFortressPieces.MonsterThrone.class)
public abstract class MonsterThroneMixin extends NetherFortressPieces.NetherBridgePiece {

    protected MonsterThroneMixin(StructurePieceType type, int genDepth, BoundingBox boundingBox) { super(type, genDepth, boundingBox); }

    @Inject(method = "postProcess", at = @At(value = "TAIL"))
    public void addCorruptedBeacon(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox chunkBB, ChunkPos chunkPos, BlockPos referencePos, CallbackInfo ci) {
        BlockPos pos = getWorldPos(3, 4, 5);
        if (chunkBB.isInside(pos)) {
            level.setBlock(pos, ModBlocks.CORRUPTED_BEACON.defaultBlockState(), 2);
        }
    }
}
