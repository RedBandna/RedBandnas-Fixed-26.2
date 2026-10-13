package net.redbandna.fixed.mixin.inventory;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.redbandna.fixed.RedBandnaSFixed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {

    @Shadow
    private static CreativeModeTab selectedTab;

    @ModifyArg(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"), index = 1)
    public Identifier replaceBackground(Identifier texture) {
        if (selectedTab.getType() == CreativeModeTab.Type.INVENTORY)
            return RedBandnaSFixed.id("textures/gui/inventory/tab_inventory.png");
        return texture;
    }

    @ModifyArgs(method = "selectTab", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/CreativeModeInventoryScreen$SlotWrapper;<init>(Lnet/minecraft/world/inventory/Slot;III)V"))
    public void moveSlots(Args args, @Local(name = "i") int i) {
        if (i == 45) {
            args.set(2, 127);
            args.set(3, 20);
        }
        if (i >= 46 && i < 50) {
            args.set(2, 9 + ((i - 46) / 2) * 24);
            args.set(3, 8 + ((i - 46) % 2) * 24);
        }
    }
}
