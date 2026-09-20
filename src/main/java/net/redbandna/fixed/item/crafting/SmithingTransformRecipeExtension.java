package net.redbandna.fixed.item.crafting;

import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

import java.util.Optional;

public interface SmithingTransformRecipeExtension {
    public Recipe.CommonInfo getCommonInfo();
    public void setAdditionCount(int count);
    public int getAdditionCount();
    public ItemStackTemplate getResult();
    public static SmithingTransformRecipe withCount(Recipe.CommonInfo commonInfo, Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, int additionCount, ItemStackTemplate result) {
        SmithingTransformRecipe out = new SmithingTransformRecipe(commonInfo, template, base, addition, result);
        ((SmithingTransformRecipeExtension) out).setAdditionCount(additionCount);
        return out;
    }
}
