package net.redbandna.fixed.worldgen.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModFeatures {
    public static final Feature<VoidRuptureConfig> VOID_RUPTURE = Registry.register(BuiltInRegistries.FEATURE,
            RedBandnaSFixed.id("void_rupture"), new VoidRuptureFeature(VoidRuptureConfig.CODEC));

    public static void registerFeatures() {
        RedBandnaSFixed.LOGGER.info("Registering Features for " + RedBandnaSFixed.MOD_ID);
    }
}
