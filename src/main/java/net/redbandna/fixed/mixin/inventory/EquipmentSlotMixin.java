package net.redbandna.fixed.mixin.inventory;

import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.*;

@Mixin(EquipmentSlot.class)
public enum EquipmentSlotMixin {
    RBFIXED_BELT(EquipmentSlot.Type.RBFIXED_ACCESSORY, 0, 1, MixinIntrinsics.currentEnumOrdinal(), "belt"),
    RBFIXED_ARMS(EquipmentSlot.Type.RBFIXED_ACCESSORY, 1, 1, MixinIntrinsics.currentEnumOrdinal(), "arms"),
    RBFIXED_BACK(EquipmentSlot.Type.RBFIXED_ACCESSORY, 2, 1, MixinIntrinsics.currentEnumOrdinal(), "back"),
    RBFIXED_NECK(EquipmentSlot.Type.RBFIXED_ACCESSORY, 3, 1, MixinIntrinsics.currentEnumOrdinal(), "neck");

    @Shadow
    EquipmentSlotMixin(final EquipmentSlot.Type type, final int index, final int countLimit, final int id, final String name) {}
}
