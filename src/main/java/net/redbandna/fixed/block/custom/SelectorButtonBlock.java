package net.redbandna.fixed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class SelectorButtonBlock extends ButtonBlock {
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public SelectorButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TRIGGERED, false));
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (state.getValue(TRIGGERED))
            level.setBlock(pos, state.setValue(TRIGGERED, false), 3);
        else {
            if (state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, false), 3);
                updateNeighbours(state, level, pos);
                playSound(null, level, pos, false);
                level.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
            }

            level.setBlock(pos, state.setValue(POWERED, false).setValue(TRIGGERED, true), 3);
            propogate(level, pos);
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlock(pos, state.setValue(POWERED, true).setValue(TRIGGERED, true), 3);
        updateNeighbours(state, level, pos);
        propogate(level, pos);
        level.scheduleTick(pos, this, 1);
        playSound(player, level, pos, true);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    private void updateNeighbours(final BlockState state, final Level level, final BlockPos pos) {
        Direction front = getConnectedDirection(state).getOpposite();
        Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(level, front, front.getAxis().isHorizontal() ? Direction.UP : state.getValue(FACING));
        level.updateNeighborsAt(pos, this, orientation);
        level.updateNeighborsAt(pos.relative(front), this, orientation);
    }

    private void propogate(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighbor = pos.relative(direction);
            BlockState state = level.getBlockState(neighbor);

            if (state.is(this) && !state.getValue(TRIGGERED)) {
                level.scheduleTick(neighbor, this, 1);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
        builder.add(FACING);
        builder.add(POWERED);
        builder.add(FACE);
    }
}
