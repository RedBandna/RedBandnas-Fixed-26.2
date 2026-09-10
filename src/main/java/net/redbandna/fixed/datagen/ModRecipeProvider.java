package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.item.custom.PaintBrushItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> RBFIXED_SMELTABLES = List.of(ModBlocks.VOID_ESSENCE_ORE);

                oreSmelting(RBFIXED_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.VOID_ESSENCE, 0.3f, 200, "void_essence");
                oreBlasting(RBFIXED_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.VOID_ESSENCE, 0.3f, 100, "void_essence");

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.VOID_ESSENCE, RecipeCategory.MISC, ModItems.PURE_VOID_ESSENCE, "pure_essence_packing", "pure_void_essence", "pure_essence_unpacking", "void_essence");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.PURE_VOID_ESSENCE, RecipeCategory.MISC, ModBlocks.VOID_ESSENCE_BLOCK, "essence_block_packing", "void_essence_block", "essence_block_unpacking", "pure_void_essence");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModBlocks.VOID_ESSENCE_BLOCK, RecipeCategory.MISC, ModBlocks.PURE_VOID_ESSENCE_BLOCK, "pure_essence_block_packing", "pure_void_essence_block", "pure_essence_block_unpacking", "void_essence_block");

                shaped(RecipeCategory.TRANSPORTATION, ModBlocks.END_RELAY)
                        .pattern("OEO")
                        .pattern("ALA")
                        .pattern("OLO")
                        .define('O', Blocks.OBSIDIAN)
                        .define('E', Items.ENDER_EYE)
                        .define('A', ModItems.VOID_AMETHYST_SHARD)
                        .define('L', ModItems.VOID_LAPIS_LAZULI)
                        .unlockedBy(getHasName(ModItems.VOID_LAPIS_LAZULI), has(ModItems.VOID_LAPIS_LAZULI))
                        .save((output));

                stairBuilder(ModBlocks.VOID_ESSENCE_STAIRS, Ingredient.of(ModBlocks.VOID_ESSENCE_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.VOID_ESSENCE_BLOCK), has(ModBlocks.VOID_ESSENCE_BLOCK))
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.VOID_ESSENCE_SLAB, ModBlocks.VOID_ESSENCE_BLOCK);
                pressurePlate(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE, ModBlocks.VOID_ESSENCE_BLOCK);

                doorBuilder(ModBlocks.VOID_ESSENCE_DOOR, Ingredient.of(ModItems.PURE_VOID_ESSENCE));
                trapdoorBuilder(ModBlocks.VOID_ESSENCE_TRAPDOOR, Ingredient.of(ModItems.PURE_VOID_ESSENCE));

                buildVoidButtonRecipe(ModBlocks.VOID_AMETHYST_BUTTON, ModItems.VOID_AMETHYST_SHARD, output);
                buildVoidButtonRecipe(ModBlocks.VOID_COAL_BUTTON, ModItems.VOID_COAL, output);
                buildVoidButtonRecipe(ModBlocks.VOID_COPPER_BUTTON, ModItems.VOID_COPPER_INGOT, output);
                buildVoidButtonRecipe(ModBlocks.VOID_DIAMOND_BUTTON, ModItems.VOID_DIAMOND, output);
                buildVoidButtonRecipe(ModBlocks.VOID_EMERALD_BUTTON, ModItems.VOID_EMERALD, output);
                buildVoidButtonRecipe(ModBlocks.VOID_GOLD_BUTTON, ModItems.VOID_GOLD_INGOT, output);
                buildVoidButtonRecipe(ModBlocks.VOID_IRON_BUTTON, ModItems.VOID_IRON_INGOT, output);
                buildVoidButtonRecipe(ModBlocks.VOID_LAPIS_BUTTON, ModItems.VOID_LAPIS_LAZULI, output);
                buildVoidButtonRecipe(ModBlocks.VOID_NETHERITE_BUTTON, ModItems.VOID_NETHERITE_SCRAP, output);
                buildVoidButtonRecipe(ModBlocks.VOID_QUARTZ_BUTTON, ModItems.VOID_QUARTZ, output);
                buildVoidButtonRecipe(ModBlocks.VOID_REDSTONE_BUTTON, ModItems.VOID_REDSTONE, output);

                buildVoidMineralRecipe(ModItems.VOID_AMETHYST_SHARD, Items.AMETHYST_SHARD, output);
                buildVoidMineralRecipe(ModItems.VOID_COAL, Items.COPPER_INGOT, output);
                buildVoidMineralRecipe(ModItems.VOID_COPPER_INGOT, Items.COPPER_INGOT, output);
                buildVoidMineralRecipe(ModItems.VOID_DIAMOND, Items.DIAMOND, output);
                buildVoidMineralRecipe(ModItems.VOID_EMERALD, Items.EMERALD, output);
                buildVoidMineralRecipe(ModItems.VOID_GOLD_INGOT, Items.GOLD_INGOT, output);
                buildVoidMineralRecipe(ModItems.VOID_IRON_INGOT, Items.IRON_INGOT, output);
                buildVoidMineralRecipe(ModItems.VOID_LAPIS_LAZULI, Items.LAPIS_LAZULI, output);
                buildVoidMineralRecipe(ModItems.VOID_NETHERITE_SCRAP, Items.REDSTONE, output);
                buildVoidMineralRecipe(ModItems.VOID_QUARTZ, Items.REDSTONE, output);
                buildVoidMineralRecipe(ModItems.VOID_REDSTONE, Items.REDSTONE, output);

                shaped(RecipeCategory.TOOLS, ModItems.Trowel)
                        .pattern(" C")
                        .pattern("CS")
                        .pattern(" S")
                        .define('C', Blocks.COBBLESTONE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                        .save(output);

                ModItems.PAINT_BRUSH.forEach(paint_brush -> {
                    shapeless(RecipeCategory.TOOLS, paint_brush)
                            .requires(Items.DYE.pick(((PaintBrushItem)paint_brush).dyeColor), 8)
                            .requires(Items.BRUSH)
                            .unlockedBy(getHasName(Items.BRUSH), has(Items.BRUSH))
                            .group("paint_brushes")
                            .save(output);
                });

                shaped(RecipeCategory.COMBAT, ModItems.FLINT_SWORD)
                        .pattern("F")
                        .pattern("F")
                        .pattern("S")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);
                shaped(RecipeCategory.TOOLS, ModItems.FLINT_PICKAXE)
                        .pattern("FFF")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);
                shaped(RecipeCategory.TOOLS, ModItems.FLINT_SHOVEL)
                        .pattern("F")
                        .pattern("S")
                        .pattern("S")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);
                shaped(RecipeCategory.TOOLS, ModItems.FLINT_AXE)
                        .pattern("SF")
                        .pattern("S ")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);
                shaped(RecipeCategory.TOOLS, ModItems.FLINT_HOE)
                        .pattern("FF")
                        .pattern("S ")
                        .pattern("S ")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);
                shaped(RecipeCategory.COMBAT, ModItems.FLINT_SPEAR)
                        .pattern("  F")
                        .pattern(" S ")
                        .pattern("S  ")
                        .define('F', Items.FLINT).define('S', Items.STICK).unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(output);


            }

            public void buildVoidButtonRecipe(ItemLike button, ItemLike mineral, RecipeOutput output) {
                buttonBuilder(button, Ingredient.of(mineral)).unlockedBy(getHasName(mineral), has(mineral))
                        .group("void_buttons").save(output);
            }

            public void buildVoidMineralRecipe(ItemLike void_mineral, ItemLike mineral, RecipeOutput output) {
                shapeless(RecipeCategory.MISC, void_mineral)
                        .requires(ModItems.PURE_VOID_ESSENCE, 4)
                        .requires(mineral, 4)
                        .unlockedBy(getHasName(ModItems.PURE_VOID_ESSENCE), has(ModItems.PURE_VOID_ESSENCE))
                        .group("void_minerals")
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "rbfixed recipes";
    }
}
