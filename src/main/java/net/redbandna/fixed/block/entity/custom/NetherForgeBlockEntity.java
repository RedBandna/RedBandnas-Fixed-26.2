package net.redbandna.fixed.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.redbandna.fixed.block.custom.NetherForgeBlock;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.menu.custom.NetherForgeMenu;
import net.redbandna.fixed.tags.ModTags;

public class NetherForgeBlockEntity extends BaseContainerBlockEntity implements ExtendedMenuProvider<BlockPos>, StackedContentsCompatible {
    protected static final int SLOT_ITEM = 0;
    protected static final int SLOT_FUEL = 1;
    public static String prefix = "Malleable ";
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private int litTimeRemaining;
    private int litTotalTime;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(final int dataId) {
            return switch (dataId) {
                case 0 -> NetherForgeBlockEntity.this.litTimeRemaining;
                case 1 -> NetherForgeBlockEntity.this.litTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(final int dataId, final int value) {
            switch (dataId) {
                case 0: NetherForgeBlockEntity.this.litTimeRemaining = value;
                case 1: NetherForgeBlockEntity.this.litTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public NetherForgeBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.NETHER_FORGE_BE, worldPosition, blockState);
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        litTimeRemaining = input.getShortOr("lit_time_remaining", (short)0);
        litTotalTime = input.getShortOr("lit_total_time", (short)0);
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        output.putShort("lit_time_remaining", (short)litTimeRemaining);
        output.putShort("lit_total_time", (short)litTotalTime);
        ContainerHelper.saveAllItems(output, items);
    }

    public void tick(final ServerLevel level, final BlockPos pos, BlockState state) {

        ItemStack item = items.get(0);
        ItemStack fuel = items.get(1);

        if (litTimeRemaining > 0) {
            if (!isForgeable(item))
                litTimeRemaining = 0;
            else if (--litTimeRemaining <= 0) {
                item.set(ModDataComponents.FORGED, false);
                item.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(prefix + item.getHoverName().getString()));
                items.set(0, item);
                level.setBlock(pos, state.setValue(NetherForgeBlock.LIT, false), 3);
                setChanged(level, pos, state);
            }
        } else if (!fuel.isEmpty() && fuel.is(ModTags.Items.FORGE_FUEL_ITEMS) && isForgeable(item)) {
            litTotalTime = litTimeRemaining = getCookingTime(item, fuel);
            Item fuelItem = fuel.getItem();
            fuel.shrink(1);
            if (fuel.isEmpty()) {
                ItemStackTemplate remainder = fuelItem.getCraftingRemainder();
                items.set(1, remainder != null ? remainder.create() : ItemStack.EMPTY);
            }
            level.setBlock(pos, state.setValue(NetherForgeBlock.LIT, true), 3);
            setChanged(level, pos, state);
        }
    }

    private static int getCookingTime(ItemStack item, ItemStack fuel) {
        return 100;
    }

    public static boolean isForgeable(ItemStack item) {
        return !item.isEmpty() && (item.get(ModDataComponents.FORGED) == null) && item.is(ItemTags.DURABILITY_ENCHANTABLE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.rbfixed.nether_forge");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new NetherForgeMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public void fillStackedContents(final StackedItemContents contents) {
        for (ItemStack itemStack : this.items) {
            contents.accountStack(itemStack);
        }
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }
}
