package net.redbandna.fixed.inventory;

import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class AccessorySlot extends Slot {
    private final LivingEntity owner;
    private final EquipmentSlot slot;
    private final Identifier emptyIcon;
    
    public AccessorySlot(Container inventory, LivingEntity owner, EquipmentSlot slot, int slotIndex, int x, int y, Identifier emptyIcon) {
        super(inventory, slotIndex, x, y);
        this.owner = owner;
        this.slot = slot;
        this.emptyIcon = emptyIcon;
    }

    @Override
    public void setByPlayer(final ItemStack itemStack, final ItemStack previous) {
        owner.onEquipItem(slot, previous, itemStack);
        super.setByPlayer(itemStack, previous);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPlace(final ItemStack itemStack) {
        return owner.isEquippableInSlot(itemStack, slot);
    }

    @Override
    public boolean isActive() {
        return owner.canUseSlot(slot);
    }

    @Override
    public boolean mayPickup(final Player player) {
        ItemStack itemStack = getItem();
        return (itemStack.isEmpty() || player.isCreative() || !EnchantmentHelper.has(itemStack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) && super.mayPickup(player);
    }

    @Override
    public Identifier getNoItemIcon() {
        return emptyIcon;
    }
}
