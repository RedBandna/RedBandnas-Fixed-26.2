package net.redbandna.fixed.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;
import java.util.Optional;

public class PaintBrushItem extends BrushItem {

    public final DyeColor dyeColor;
    private static final List<ColorCollection<Block>> blockCollections = List.of(
            Blocks.BED, Blocks.WOOL, Blocks.STAINED_GLASS, Blocks.DYED_TERRACOTTA, Blocks.STAINED_GLASS_PANE, Blocks.CARPET, Blocks.DYED_SHULKER_BOX, Blocks.GLAZED_TERRACOTTA, Blocks.CONCRETE, Blocks.CONCRETE_POWDER, Blocks.DYED_CANDLE);

    public static Optional<ColorCollection<Block>> getColorCollection(Block block) {
        if (block.equals(Blocks.TERRACOTTA)) return Optional.of(Blocks.DYED_TERRACOTTA);
        if (block.equals(Blocks.GLASS)) return Optional.of(Blocks.STAINED_GLASS);
        if (block.equals(Blocks.GLASS_PANE)) return Optional.of(Blocks.STAINED_GLASS_PANE);
        if (block.equals(Blocks.SHULKER_BOX)) return Optional.of(Blocks.DYED_SHULKER_BOX);
        if (block.equals(Blocks.CANDLE)) return Optional.of(Blocks.DYED_CANDLE);
        return blockCollections.stream().filter(blocks -> blocks.asList().contains(block)).findFirst();
    }

    public PaintBrushItem(Properties properties, DyeColor color) {
        super(properties.durability(64));
        this.dyeColor = color;
    }

    @Override
    public void onUseTick(final Level level, final LivingEntity livingEntity, final ItemStack itemStack, final int ticksRemaining) {
        if (ticksRemaining >= 0 && livingEntity instanceof Player player) {

            HitResult hitResult = this.calculateHitResult(player);
            if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {

                BlockPos pos = blockHitResult.getBlockPos();
                Optional<ColorCollection<Block>> collection = getColorCollection(level.getBlockState(pos).getBlock());
                if (collection.isPresent()) {

                    if ((this.getUseDuration(itemStack, livingEntity) - ticksRemaining + 1) % 10 == 5) {

                        level.playSound(player, pos, SoundEvents.BRUSH_GENERIC, SoundSource.BLOCKS);

                        if (level instanceof ServerLevel serverLevel) {

                            BlockState blockState = serverLevel.getBlockState(pos);
                            Block dyed_block = collection.get().pick(dyeColor);
                            if (blockState.getBlock().equals(dyed_block)) {
                                return;
                            }

                            if(collection.get().equals(Blocks.BED)) {
                                BlockPos headPos;
                                BlockPos footPos;
                                if (blockState.getValue(BlockStateProperties.BED_PART).equals(BedPart.FOOT))
                                    headPos = (footPos = pos).relative(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING), 1);
                                else footPos = (headPos = pos).relative(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING), -1);

                                serverLevel.setBlock(headPos, dyed_block.withPropertiesOf(blockState.setValue(BlockStateProperties.BED_PART, BedPart.HEAD)), 51);
                                serverLevel.setBlock(footPos, dyed_block.withPropertiesOf(blockState.setValue(BlockStateProperties.BED_PART, BedPart.FOOT)), 3);
                                dyed_block.withPropertiesOf(blockState.setValue(BlockStateProperties.BED_PART, BedPart.HEAD)).updateNeighbourShapes(serverLevel, headPos, 3);

                            }

                            else if (collection.get().equals(Blocks.DYED_SHULKER_BOX)) {

                                CompoundTag nbt = (serverLevel.getBlockEntity(pos)).saveWithoutMetadata(serverLevel.registryAccess());
                                serverLevel.setBlock(pos, collection.get().pick(dyeColor).withPropertiesOf(blockState), 3);
                                (serverLevel.getBlockEntity(pos)).loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, serverLevel.registryAccess(), nbt));

                            }

                            else {
                                serverLevel.setBlock(pos, collection.get().pick(dyeColor).withPropertiesOf(blockState), 3);
                            }

                            EquipmentSlot equippedHand = itemStack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                            itemStack.hurtAndBreak(1, player, equippedHand);
                        }
                    }
                    return;
                }
            }
        }
        livingEntity.releaseUsingItem();
    }

    private HitResult calculateHitResult(final Player player) {
        return ProjectileUtil.getHitResultOnViewVector(player, EntitySelector.CAN_BE_PICKED, player.blockInteractionRange());
    }
}
