package net.redbandna.fixed.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.redbandna.fixed.block.entity.custom.EndRelayBlockEntity;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class EndRelayBlock extends BaseEntityBlock {

    private static List<ResourceKey<Level>> AllowedDimensions = List.of(Level.END);
    private static MapCodec<EndRelayBlock> CODEC = simpleCodec(EndRelayBlock::new);

    public EndRelayBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EndRelayBlockEntity endRelayBlockEntity) {
            level.removeBlock(pos, false);

            if (!AllowedDimensions.contains(level.dimension())) {
                ExplosionDamageCalculator damageCalculator = new ExplosionDamageCalculator() {
                    {
                        Objects.requireNonNull(EndRelayBlock.this);
                    }

                    boolean anyWaterNeighbors = Direction.Plane.HORIZONTAL.stream().map(pos::relative).anyMatch((neighborPos) -> isWaterThatWouldFlow(neighborPos, level));
                    final boolean inWater = anyWaterNeighbors || level.getFluidState(pos.above()).is(FluidTags.WATER);
                    public Optional<Float> getBlockExplosionResistance(final Explosion explosion, final BlockGetter level, final BlockPos testPos, final BlockState block, final FluidState fluid) {
                        return testPos.equals(pos) && inWater ? Optional.of(Blocks.WATER.getExplosionResistance()) : super.getBlockExplosionResistance(explosion, level, testPos, block, fluid);
                    }
                };
                level.explode(null, level.damageSources().badRespawnPointExplosion(Vec3.atCenterOf(pos)), damageCalculator, Vec3.atCenterOf(pos), 5.0F, true, Level.ExplosionInteraction.BLOCK);
                return InteractionResult.SUCCESS;
            }

            if (itemStack.getItem().equals(Items.COMPASS)) {
                LodestoneTracker tracker = itemStack.get(DataComponents.LODESTONE_TRACKER);

                if (tracker != null && tracker.target().isPresent() && AllowedDimensions.contains(tracker.target().get().dimension())) {
                    endRelayBlockEntity.destination = tracker.target().get();
                    endRelayBlockEntity.setChanged();
                    return InteractionResult.SUCCESS;
                }
            } else if (itemStack.getItem().equals(Items.ENDER_PEARL) && !player.getCooldowns().isOnCooldown(itemStack)) {

                if (endRelayBlockEntity.destination != null && AllowedDimensions.contains(endRelayBlockEntity.destination.dimension())) {
                    BlockPos target = endRelayBlockEntity.destination.pos().above();
                    if (!level.isClientSide()) {
                        ServerLevel targetLevel = level.getServer().getLevel(endRelayBlockEntity.destination.dimension());
                        if (DismountHelper.canDismountTo(targetLevel, Vec3.atCenterOf(target), player, Pose.STANDING)) {
                            player.teleportTo(targetLevel, target.getX() + 0.5, target.getY(), target.getZ() + 0.5, Set.of(), 0, 0, true);

                            if (!player.isCreative()) itemStack.shrink(1);
                            player.getCooldowns().addCooldown(player.getCooldowns().getCooldownGroup(itemStack), 20);

                            level.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS);
                            level.playSound(null, target, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS);
                        }
                        else level.playSound(null, pos, SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.BLOCKS);

                        return InteractionResult.SUCCESS;
                    }
                }

                level.playSound(player, pos, SoundEvents.EMPTY, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        return  InteractionResult.PASS;
    }

    private static boolean isWaterThatWouldFlow(final BlockPos pos, final Level level) {
        FluidState fluid = level.getFluidState(pos);
        if (!fluid.is(FluidTags.WATER)) {
            return false;
        } else if (fluid.isSource()) {
            return true;
        } else {
            float amount = (float)fluid.getAmount();
            if (amount < 2.0F) {
                return false;
            } else {
                FluidState fluidBelow = level.getFluidState(pos.below());
                return !fluidBelow.is(FluidTags.WATER);
            }
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new EndRelayBlockEntity(worldPosition, blockState);
    }

}
