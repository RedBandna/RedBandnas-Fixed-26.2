package net.redbandna.fixed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;


public class PlayerPressurePlateBlock extends PressurePlateBlock {
    public PlayerPressurePlateBlock(BlockSetType type, Properties properties) {
        super(type, properties);
    }

    @Override
    protected int getSignalStrength(Level level, BlockPos pos) {
        return level.getEntitiesOfClass(Player.class, TOUCH_AABB.move(pos)).isEmpty() ? 0 : 15;
    }
}
