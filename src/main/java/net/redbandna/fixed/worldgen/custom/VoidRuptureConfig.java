package net.redbandna.fixed.worldgen.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record VoidRuptureConfig(
        BlockStateProvider inner,
        BlockStateProvider outer,
        BlockStateProvider replace,
        int scale,
        float oreChance
) implements FeatureConfiguration {
    public static final Codec<VoidRuptureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("inner").forGetter(VoidRuptureConfig::inner),
            BlockStateProvider.CODEC.fieldOf("outer").forGetter(VoidRuptureConfig::outer),
            BlockStateProvider.CODEC.fieldOf("replace").forGetter(VoidRuptureConfig::replace),
            Codec.INT.fieldOf("scale").forGetter(VoidRuptureConfig::scale),
            Codec.FLOAT.fieldOf("ore_chance").forGetter(VoidRuptureConfig::oreChance)
    ).apply(instance, VoidRuptureConfig::new));
}
