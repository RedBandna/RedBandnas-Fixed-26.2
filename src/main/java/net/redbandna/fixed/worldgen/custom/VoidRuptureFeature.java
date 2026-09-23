package net.redbandna.fixed.worldgen.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.redbandna.fixed.RedBandnaSFixed;

public class VoidRuptureFeature extends Feature<VoidRuptureConfig> {
    public VoidRuptureFeature(Codec<VoidRuptureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<VoidRuptureConfig> context) {

        WorldGenLevel level = context.level();
        VoidRuptureConfig config = context.config();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        int scale = config.scale();
        int dx = random.nextInt(-scale, scale);
        int dy = random.nextInt(-scale, scale);
        int dz = random.nextInt(-scale, scale);

        BlockState inner = config.inner().getState(level, random, origin);
        BlockState outer = config.outer().getState(level, random, origin);
        BlockState replace = config.replace().getState(level, random, origin);
        float oreChance = config.oreChance();

        boolean generated = false;

        int length = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));
        RedBandnaSFixed.LOGGER.info(dx + ", " + dy + ", " + dz + ", " + length);
        for (int i = 0; i < length; ++i) {
            int x = origin.getX() + dx * i / length;
            int y = origin.getY() + dy * i / length;
            int z = origin.getZ() + dz * i / length;

            int radius = (i - i * i / length);
            RedBandnaSFixed.LOGGER.info(radius + "");

            for (int a = -radius; a < radius; ++a) {
                int h = (int) Math.sqrt(radius * radius - a * a);
                for (int b = -h; b <= h; ++b) {
                    BlockPos pos;
                    if (length == Math.abs(dx))       pos = new BlockPos(x, y + a, z + b);
                    else if (length == Math.abs(dy))  pos = new BlockPos(x + b, y, z + a);
                    else                    pos = new BlockPos(x + a, y + b, z);

                    if (pos.getY() >= 0 && pos.getY() <= 256) {
                        level.setBlock(pos, inner, 3);

                        if (random.nextFloat() < oreChance) {
                            for (Direction dir : Direction.values()) {
                                BlockPos orePos = pos.relative(dir);

                                if (orePos.getY() >= 0 && orePos.getY() <= 256 && level.getBlockState(orePos).is(replace.getBlock())) {
                                    level.setBlock(orePos, outer, 3);
                                }
                            }
                        }
                        generated = true;
                    }
                }
            }
        }

        return generated;
    }
}
