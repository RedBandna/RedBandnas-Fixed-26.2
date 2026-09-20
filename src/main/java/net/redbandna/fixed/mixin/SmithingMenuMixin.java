package net.redbandna.fixed.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.redbandna.fixed.item.crafting.SmithingTransformRecipeExtension;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(SmithingMenu.class)
public abstract class SmithingMenuMixin extends ItemCombinerMenu {
    public SmithingMenuMixin(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, ContainerLevelAccess access, ItemCombinerMenuSlotDefinition itemInputSlots) {
        super(menuType, containerId, inventory, access, itemInputSlots);
    }

    @Shadow
    protected abstract SmithingRecipeInput createRecipeInput();

    @Inject(method = "onTake", at = @At(value = "HEAD"))
    protected void shrinkCount(Player player, ItemStack carried, CallbackInfo ci) {
        if (player instanceof LocalPlayer) return;

        SmithingRecipe recipe = player.level().recipeAccess().getSynchronizedRecipes().getFirstMatch(RecipeType.SMITHING, createRecipeInput(), player.level()).get().value();
        if (recipe instanceof SmithingTransformRecipeExtension transformRecipe) {
            ItemStack stack = this.inputSlots.getItem(2);
            if (!stack.isEmpty()) {
                stack.shrink(transformRecipe.getAdditionCount() - 1);
                this.inputSlots.setItem(2, stack);
            }
        }
    }
}
