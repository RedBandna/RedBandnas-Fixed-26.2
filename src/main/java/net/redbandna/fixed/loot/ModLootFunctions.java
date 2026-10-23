package net.redbandna.fixed.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModLootFunctions {
    public static final MapCodec<ForgedFunction> FORGED_FUNCTION = Registry.register(
            BuiltInRegistries.LOOT_FUNCTION_TYPE, RedBandnaSFixed.id("forged_function"), ForgedFunction.MAP_CODEC);

    public static void registerFunctions() {
        RedBandnaSFixed.LOGGER.info("Registering Loot Functions for " + RedBandnaSFixed.MOD_ID);
    }
}
