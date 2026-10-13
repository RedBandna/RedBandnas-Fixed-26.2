package net.redbandna.fixed.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.block.entity.custom.CorruptedBeaconBlockEntity;
import org.jspecify.annotations.Nullable;

public class CorruptedBeaconBlock extends BaseEntityBlock implements BeaconBeamBlock {
    public static final MapCodec<CorruptedBeaconBlock> CODEC = simpleCodec(CorruptedBeaconBlock::new);

    public CorruptedBeaconBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<CorruptedBeaconBlock> codec() {
        return CODEC;
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.YELLOW;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new CorruptedBeaconBlockEntity(worldPosition, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ModBlockEntities.CORRUPTED_BEACON_BE,
                (level1, pos, state, entity) -> entity.tick((ServerLevel) level1, pos));
    }
}
