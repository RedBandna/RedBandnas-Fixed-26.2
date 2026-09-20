package net.redbandna.fixed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class TriggeredButtonBlock extends ButtonBlock {
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public TriggeredButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TRIGGERED, false));
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (state.getValue(POWERED))
            this.checkPressed(state, level, pos);
        else
            this.press(state, level, pos, null);
    }

    @Override
    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        if (level instanceof ServerLevel serverLevel) {
            boolean signal = serverLevel.hasNeighborSignal(pos);
            if (signal != state.getValue(TRIGGERED)) {
                BlockState newState = state;
                serverLevel.setBlock(pos, newState.setValue(TRIGGERED, signal), 3);
                if (!state.getValue(TRIGGERED) && !state.getValue(POWERED)) {
                    level.scheduleTick(pos, this, 4);
                }
            }
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (oldState.getBlock() != state.getBlock() && level instanceof ServerLevel serverLevel &&serverLevel.hasNeighborSignal(pos))
                serverLevel.setBlock(pos, state.setValue(TRIGGERED, true), 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
        builder.add(FACING);
        builder.add(POWERED);
        builder.add(FACE);
    }
}
