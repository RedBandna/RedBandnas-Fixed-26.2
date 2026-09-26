package net.redbandna.fixed.menu.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;
import net.redbandna.fixed.item.forging.ForgePattern;
import net.redbandna.fixed.menu.ModMenuTypes;

public class VoidAnvilMenu extends ItemCombinerMenu {
    public static final int MAX_NAME_LENGTH = 50;

    public VoidAnvilMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public VoidAnvilMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access) {
        super(ModMenuTypes.VOID_ANVIL_MENU, containerId, inventory, access, createInputSlotDefinitions());
    }

    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, stack -> stack.is(ItemTags.DURABILITY_ENCHANTABLE))
                .withSlot(1, 76, 47, stack -> stack.getItem() instanceof ForgeTemplateItem)
                .withResultSlot(2, 134, 47)
                .build();
    }

    @Override
    protected boolean isValidBlock(final BlockState state) {
        return state.is(ModBlocks.VOID_ANVIL);
    }

    @Override
    protected boolean mayPickup(final Player player, final boolean hasItem) {
        return (player.hasInfiniteMaterials() || player.experienceLevel >= getPattern().cost);
    }

    @Override
    protected void onTake(final Player player, final ItemStack carried) {

        if (!player.hasInfiniteMaterials()) {
            player.giveExperienceLevels(-getPattern().cost);
        }

        inputSlots.setItem(0, ItemStack.EMPTY);
        inputSlots.getItem(1).shrink(1);

        access.execute((level, pos) -> {
            BlockState state = level.getBlockState(pos);
            if (!player.hasInfiniteMaterials() && state.is(ModBlocks.VOID_ANVIL) && player.getRandom().nextFloat() < 0.12F) {
                level.setBlock(pos, AnvilBlock.damage(state), 2);
                level.levelEvent(1030, pos, 0);
            } else {
                level.levelEvent(1030, pos, 0);
            }
        });
    }

    @Override
    public void createResult() {
        ItemStack input = inputSlots.getItem(0);
        if (!inputSlots.getItem(1).isEmpty() && input.getComponents().has(ModDataComponents.FORGED) && !input.get(ModDataComponents.FORGED) && isApplicable()) {

            ItemStack result = getPattern().apply(input.copy());
            result.set(ModDataComponents.FORGED, true);
            resultSlots.setItem(0, result);
        } else {
            resultSlots.setItem(0, ItemStack.EMPTY);
        }
        broadcastChanges();
    }

    public boolean setItemName(final String name) {
        String itemName = StringUtil.filterText(name);
        ItemStack result = resultSlots.getItem(0);
        if (!result.isEmpty() && !StringUtil.isBlank(itemName) && itemName.length() <= 50) {
            result.set(DataComponents.CUSTOM_NAME, Component.literal(itemName));
            broadcastChanges();
            return true;
        }
        return false;
    }

    public boolean isApplicable() {
        return ((ForgeTemplateItem) inputSlots.getItem(1).getItem()).isApplicable(inputSlots.getItem(0));
    }

    public ForgePattern getPattern() {
        return ((ForgeTemplateItem) inputSlots.getItem(1).getItem()).getPattern(inputSlots.getItem(0));
    }
}
