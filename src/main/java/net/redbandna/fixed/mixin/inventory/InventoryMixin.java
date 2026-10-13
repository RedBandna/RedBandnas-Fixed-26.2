package net.redbandna.fixed.mixin.inventory;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow @Final
    public static Int2ObjectMap<EquipmentSlot> EQUIPMENT_SLOT_MAPPING;

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void modifyEquipment(CallbackInfo ci) {
        EQUIPMENT_SLOT_MAPPING.put(EquipmentSlot.RBFIXED_BELT.getIndex(43), EquipmentSlot.RBFIXED_BELT);
        EQUIPMENT_SLOT_MAPPING.put(EquipmentSlot.RBFIXED_ARMS.getIndex(43), EquipmentSlot.RBFIXED_ARMS);
        EQUIPMENT_SLOT_MAPPING.put(EquipmentSlot.RBFIXED_BACK.getIndex(43), EquipmentSlot.RBFIXED_BACK);
        EQUIPMENT_SLOT_MAPPING.put(EquipmentSlot.RBFIXED_NECK.getIndex(43), EquipmentSlot.RBFIXED_NECK);
    }
}
