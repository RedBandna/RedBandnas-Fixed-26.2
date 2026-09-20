package net.redbandna.fixed.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.crafting.SmithingTransformRecipeBuilderExtension;
import net.redbandna.fixed.item.crafting.SmithingTransformRecipeExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingTransformRecipeBuilder.class)
public class SmithingTransformRecipeBuilderMixin implements SmithingTransformRecipeBuilderExtension {

    @Unique
    private int additionCount = 1;

    @Unique
    public SmithingTransformRecipeBuilder count(int count) {
        this.additionCount = count;
        return (SmithingTransformRecipeBuilder) (Object) this;
    }

    @Inject(method = "save(Lnet/minecraft/data/recipes/RecipeOutput;Lnet/minecraft/resources/ResourceKey;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/data/recipes/RecipeOutput;accept(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;)V"))
    public void saveCount(RecipeOutput output, ResourceKey<Recipe<?>> id, CallbackInfo ci, @Local SmithingTransformRecipe recipe) {
        ((SmithingTransformRecipeExtension) recipe).setAdditionCount(additionCount);
    }
}
