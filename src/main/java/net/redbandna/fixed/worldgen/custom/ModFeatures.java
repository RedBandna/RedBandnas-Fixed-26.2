package net.redbandna.fixed.worldgen.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModFeatures {
    public static final Feature<VoidRuptureConfig> VOID_RUPTURE = Registry.register(BuiltInRegistries.FEATURE,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "void_rupture"), new VoidRuptureFeature(VoidRuptureConfig.CODEC));

    public static void registerModFeatures() {
        RedBandnaSFixed.LOGGER.info("Registering Features for " + RedBandnaSFixed.MOD_ID);
    }
}
