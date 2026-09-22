package net.redbandna.fixed.menu.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.redbandna.fixed.block.entity.custom.NetherForgeBlockEntity;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.menu.ModeMenuTypes;
import net.redbandna.fixed.tags.ModTags;

public class NetherForgeMenu extends AbstractContainerMenu {
    private final Container inventory;
    public final NetherForgeBlockEntity blockEntity;
    private final ContainerData data;

    public NetherForgeMenu(int containerId, Inventory inventory, BlockPos pos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public NetherForgeMenu(int containerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModeMenuTypes.NETHER_FORGE_MENU, containerId);
        blockEntity = (NetherForgeBlockEntity) entity;
        this.data = data;
        this.inventory = blockEntity;

        addSlot(new Slot(this.inventory, 0, 80, 17) {
            @Override
            public boolean mayPickup(Player player) {
                return !isCrafting();
            }
        });
        addSlot(new Slot(this.inventory, 1, 80, 53) {
            @Override
            public boolean mayPlace(final ItemStack itemStack) {
                return itemStack.is(ModTags.Items.FORGE_FUEL_ITEMS);
            }
        });
        addStandardInventorySlots(inventory, 8, 84);
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public float getLitProgress() {
        return Mth.clamp((float) this.data.get(0) / this.data.get(1), 0.0F, 1.0F);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();

            clicked = stack.copy();
            if (slotIndex == 0 && stack.get(ModDataComponents.MALLEABLE) != null) {
                if (!this.moveItemStackTo(stack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex != 1 && slotIndex != 0) {
                if (NetherForgeBlockEntity.isForgeable(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (stack.is(ModTags.Items.FORGE_FUEL_ITEMS)) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 2 && slotIndex < 29) {
                    if (!this.moveItemStackTo(stack, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 29 && slotIndex < 38 && !this.moveItemStackTo(stack, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }
}
