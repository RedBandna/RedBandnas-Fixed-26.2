package net.redbandna.fixed.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.redbandna.fixed.RedBandnaSFixed;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<Boolean> FORGED = register("forged",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));


    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        RedBandnaSFixed.LOGGER.info("Registering Data Components for " + RedBandnaSFixed.MOD_ID);
    }
}
