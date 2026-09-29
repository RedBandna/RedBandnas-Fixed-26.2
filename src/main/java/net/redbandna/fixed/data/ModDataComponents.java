package net.redbandna.fixed.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.redbandna.fixed.RedBandnaSFixed;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<Boolean> FORGED = register("forged",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    public static final DataComponentType<SuspiciousStewEffects> FORGED_EFFECTS = register("forged_effects",
            builder -> builder.persistent(SuspiciousStewEffects.CODEC).networkSynchronized(SuspiciousStewEffects.STREAM_CODEC).cacheEncoding());
    public static final DataComponentType<Unit> CATALYTIC = register("catalytic",
            builder -> builder.persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC));
    public static final DataComponentType<Unit> GILDED = register("gilded",
            builder -> builder.persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC));
    public static final DataComponentType<List<String>> FIRST_HIT_BONUS_EXCLUDES = register("first_hit_bonus",
            builder -> builder.persistent(Codec.STRING.listOf()).networkSynchronized(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())));
    public static final DataComponentType<Unit> SUMMONS_THUNDER = register("summons_thunder",
            builder -> builder.persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC));

    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, RedBandnaSFixed.id(name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        RedBandnaSFixed.LOGGER.info("Registering Data Components for " + RedBandnaSFixed.MOD_ID);
    }
}
