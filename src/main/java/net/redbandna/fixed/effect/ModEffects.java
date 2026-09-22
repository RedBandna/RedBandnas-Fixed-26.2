package net.redbandna.fixed.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModEffects {
    public static final Holder<MobEffect> SYPHONED = registerMobEffect("syphoned",
            new SyphonedEffect(MobEffectCategory.HARMFUL, 0X583012));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        RedBandnaSFixed.LOGGER.info("Registering Effects for " + RedBandnaSFixed.MOD_ID);
    }
}
