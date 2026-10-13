package net.redbandna.fixed.mixin.inventory;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.inventory.AccessorySlot;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {
    protected InventoryMenuMixin(MenuType<?> menuType, int containerId) { super(menuType, containerId); }
    @Unique
    private static final Identifier EMPTY_ACCESSORY_SLOT_NECK = RedBandnaSFixed.id("container/slot/neck");
    @Unique
    private static final Identifier EMPTY_ACCESSORY_SLOT_BACk = RedBandnaSFixed.id("container/slot/back");
    @Unique
    private static final Identifier EMPTY_ACCESSORY_SLOT_ARMS = RedBandnaSFixed.id("container/slot/arms");
    @Unique
    private static final Identifier EMPTY_ACCESSORY_SLOT_BELT = RedBandnaSFixed.id("container/slot/belt");


    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/InventoryMenu$1;<init>(Lnet/minecraft/world/inventory/InventoryMenu;Lnet/minecraft/world/Container;IIILnet/minecraft/world/entity/player/Player;)V"))
    public void shiftOffHand(Args args) {
        args.set(3, 95);
    }
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    public void addAccessorySlots(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
        addSlot(new AccessorySlot(inventory, owner, EquipmentSlot.RBFIXED_NECK, 46, 77, 8, EMPTY_ACCESSORY_SLOT_NECK));
        addSlot(new AccessorySlot(inventory, owner, EquipmentSlot.RBFIXED_BACK, 45, 77, 26, EMPTY_ACCESSORY_SLOT_BACk));
        addSlot(new AccessorySlot(inventory, owner, EquipmentSlot.RBFIXED_ARMS, 44, 77, 44, EMPTY_ACCESSORY_SLOT_ARMS));
        addSlot(new AccessorySlot(inventory, owner, EquipmentSlot.RBFIXED_BELT, 43, 77, 62, EMPTY_ACCESSORY_SLOT_BELT));
    }

    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Player;getEquipmentSlotForItem(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/EquipmentSlot;"), cancellable = true)
    public void moveAccessory(Player player, int slotIndex, CallbackInfoReturnable<ItemStack> cir, @Local(name = "stack") ItemStack stack, @Local(name = "eqSlot") EquipmentSlot eqSlot) {
        if (slotIndex >= 46 && slotIndex < 50) {
            if (!moveItemStackTo(stack, 9, 45, false)) {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        } else if (eqSlot.getType() == EquipmentSlot.Type.RBFIXED_ACCESSORY && !slots.get(49 - eqSlot.getIndex()).hasItem()) {
            if (!moveItemStackTo(stack, 49 - eqSlot.getIndex(), 50 - eqSlot.getIndex(), false)) {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        }
    }
}
