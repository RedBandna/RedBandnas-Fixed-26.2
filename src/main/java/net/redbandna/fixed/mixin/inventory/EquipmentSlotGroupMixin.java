package net.redbandna.fixed.mixin.inventory;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinIntrinsics;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(EquipmentSlotGroup.class)
public enum EquipmentSlotGroupMixin {
    RBFIXED_BELT(MixinIntrinsics.currentEnumOrdinal(), "belt", EquipmentSlot.RBFIXED_BELT),
    RBFIXED_ARMS(MixinIntrinsics.currentEnumOrdinal(), "arms", EquipmentSlot.RBFIXED_ARMS),
    RBFIXED_BACK(MixinIntrinsics.currentEnumOrdinal(), "back", EquipmentSlot.RBFIXED_BACK),
    RBFIXED_NECK(MixinIntrinsics.currentEnumOrdinal(), "neck", EquipmentSlot.RBFIXED_NECK),
    RBFIXED_ACCESSORIES(MixinIntrinsics.currentEnumOrdinal(), "accessories", slot -> slot.getType() == EquipmentSlot.Type.RBFIXED_ACCESSORY);

    @Shadow
    EquipmentSlotGroupMixin(final int id, final String key, final Predicate<EquipmentSlot> predicate) {}
    @Shadow
    EquipmentSlotGroupMixin(final int id, final String key, final EquipmentSlot slot) {}

    @Inject(method = "bySlot", at = @At(value = "HEAD"), cancellable = true)
    private static void returnAccessorySlot(EquipmentSlot slot, CallbackInfoReturnable<EquipmentSlotGroup> cir) {
        EquipmentSlotGroup out = switch(slot) {
            case RBFIXED_BELT -> EquipmentSlotGroup.RBFIXED_BELT;
            case RBFIXED_ARMS -> EquipmentSlotGroup.RBFIXED_ARMS;
            case RBFIXED_BACK -> EquipmentSlotGroup.RBFIXED_BACK;
            case RBFIXED_NECK -> EquipmentSlotGroup.RBFIXED_NECK;
            default -> null;
        };
        if (out != null) cir.setReturnValue(out);
    }
}
