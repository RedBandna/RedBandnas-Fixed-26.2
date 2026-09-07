package net.redbandna.fixed.item.custom;


import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.ArrayList;
import java.util.function.Predicate;

public class TrowelItem extends BlockItem{
    private Block using;
    private double rand = Math.random();

    public TrowelItem(final Item.Properties properties) {
        super(Blocks.COBBLESTONE, properties);
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        BundleContents contents = context.getPlayer().getOffhandItem().get(DataComponents.BUNDLE_CONTENTS);
        if (contents == null || contents.isEmpty()) {
            return InteractionResult.PASS;
        }
        ArrayList<ItemStack> items = new ArrayList<>(contents.itemCopyStream().toList());
        ItemStack stack = items.get((int) (rand * contents.size()));
        if (context.getPlayer() instanceof ServerPlayer) {
            rand = Math.random();
        }
        if (stack.getItem() instanceof BlockItem blockItem) {
            this.using = blockItem.getBlock();
        }
        else {
            return InteractionResult.FAIL;
        }

        InteractionResult placeResult = this.place(new BlockPlaceContext(context), stack);
        if (placeResult.equals(InteractionResult.SUCCESS)) {
            context.getPlayer().getOffhandItem().set(DataComponents.BUNDLE_CONTENTS, new BundleContents(
                    items.stream().filter(Predicate.not(ItemStack::isEmpty)).map(ItemStackTemplate::fromStack).toList()));
        }

        return !placeResult.consumesAction() && context.getItemInHand().has(DataComponents.CONSUMABLE) ? super.use(context.getLevel(), context.getPlayer(), context.getHand()) : placeResult;
    }

    public InteractionResult place(final BlockPlaceContext placeContext, ItemStack itemStack) {
        if (!this.getBlock().isEnabled(placeContext.getLevel().enabledFeatures())) {
            return InteractionResult.FAIL;
        } else if (!placeContext.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            BlockPlaceContext updatedPlaceContext = this.updatePlacementContext(placeContext);
            if (updatedPlaceContext == null) {
                return InteractionResult.FAIL;
            } else {
                BlockState placementState = this.getPlacementState(updatedPlaceContext);
                if (placementState == null) {
                    return InteractionResult.FAIL;
                } else if (!this.placeBlock(updatedPlaceContext, placementState)) {
                    return InteractionResult.FAIL;
                } else {
                    BlockPos pos = updatedPlaceContext.getClickedPos();
                    Level level = updatedPlaceContext.getLevel();
                    Player player = updatedPlaceContext.getPlayer();
                    BlockState placedState = level.getBlockState(pos);
                    if (placedState.is(placementState.getBlock())) {
                        placedState = this.updateBlockStateFromTag(pos, level, itemStack, placedState);
                        this.updateCustomBlockEntityTag(pos, level, player, itemStack, placedState);
                        updateBlockEntityComponents(level, pos, itemStack);
                        placedState.getBlock().setPlacedBy(level, pos, placedState, player, itemStack);
                        if (player instanceof ServerPlayer serverPlayer) {
                            ServerLevel serverLevel = (ServerLevel) level;
                            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, pos, itemStack);
                            updatedPlaceContext.getItemInHand().hurtAndBreak(1, serverLevel, serverPlayer, item -> {
                                player.broadcastToPlayer(serverPlayer);
                            });
                        }
                    }

                    SoundType soundType = placedState.getSoundType();
                    level.playSound(player, pos, this.getPlaceSound(placedState), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
                    level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, placedState));
                    itemStack.consume(1, player);
                    return InteractionResult.SUCCESS;
                }
            }
        }
    }

    private static void updateBlockEntityComponents(final Level level, final BlockPos pos, final ItemStack itemStack) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity != null) {
            entity.applyComponentsFromItemStack(itemStack);
            entity.setChanged();
        }

    }

    private BlockState updateBlockStateFromTag(final BlockPos pos, final Level level, final ItemStack itemStack, final BlockState placedState) {
        BlockItemStateProperties blockState = (BlockItemStateProperties)itemStack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        if (blockState.isEmpty()) {
            return placedState;
        } else {
            BlockState modifiedState = blockState.apply(placedState);
            if (modifiedState != placedState) {
                level.setBlock(pos, modifiedState, 2);
            }

            return modifiedState;
        }
    }
    @Override
    public Block getBlock() {
        return this.using;
    }

}
