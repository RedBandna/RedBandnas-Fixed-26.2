package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.item.crafting.SmithingTransformRecipeBuilderExtension;
import net.redbandna.fixed.item.custom.PaintBrushItem;

import java.util.List;
import java.util.Map;
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

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.VOID_ESSENCE, RecipeCategory.MISC, ModItems.PURE_VOID_ESSENCE, "pure_essence_packing",
                        "pure_void_essence", "pure_essence_unpacking", "void_essence");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.PURE_VOID_ESSENCE, RecipeCategory.MISC, ModBlocks.VOID_ESSENCE_BLOCK, "essence_block_packing",
                        "void_essence_block", "essence_block_unpacking", "pure_void_essence");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModBlocks.VOID_ESSENCE_BLOCK, RecipeCategory.MISC, ModBlocks.PURE_VOID_ESSENCE_BLOCK, "pure_essence_block_packing",
                        "pure_void_essence_block", "pure_essence_block_unpacking", "void_essence_block");

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

                List<Map.Entry<String, String>> Upgrades = List.of(
                        Map.entry("rbfixed:flint", "stone"), Map.entry("wooden", "stone"), Map.entry("wooden", "copper"), Map.entry("stone", "copper"), Map.entry("stone", "iron"),
                        Map.entry("leather", "chainmail"), Map.entry("copper", "chainmail"), Map.entry("chainmail", "iron"),
                        Map.entry("copper", "golden"), Map.entry("iron", "golden"), Map.entry("iron", "diamond"), Map.entry("golden", "rbfixed:rose_quartz"), Map.entry("diamond", "netherite"), Map.entry("rbfixed:rose_quartz", "netherite"));
                Map<String, Item> Materials = Map.ofEntries(
                        Map.entry("stone", Items.COBBLESTONE), Map.entry("copper", Items.COPPER_INGOT), Map.entry("chainmail", Items.IRON_CHAIN), Map.entry("iron", Items.IRON_INGOT), Map.entry("golden", Items.GOLD_INGOT),
                        Map.entry("diamond", Items.DIAMOND), Map.entry("rbfixed:rose_quartz", Items.QUARTZ), Map.entry("netherite", Items.NETHERITE_INGOT)
                );

                Upgrades.forEach((Map.Entry<String, String> entry) -> {
                    String base = entry.getKey();
                    String out = entry.getValue();
                    Ingredient templateIngredient = Ingredient.of(BuiltInRegistries.ITEM.get(Identifier.parse(((out.contains(":") || out.equals("netherite")) ? "" : "rbfixed:") + out + "_upgrade_smithing_template")).get().value());
                    Item materialItem = Materials.get(out);
                    int bonus = materialItem.equals(Items.QUARTZ) ? 48 : 0;

                    if (!base.equals("chainmail") && !out.equals("chainmail")) {
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "sword"), materialItem, 2 + bonus, parseTieredItem(out, "sword"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "pickaxe"), materialItem, 3 + bonus, parseTieredItem(out, "pickaxe"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "shovel"), materialItem, 1 + bonus, parseTieredItem(out, "shovel"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "axe"), materialItem, 3 + bonus, parseTieredItem(out, "axe"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "hoe"), materialItem, 2 + bonus, parseTieredItem(out, "hoe"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "spear"), materialItem, 1 + bonus, parseTieredItem(out, "spear"));
                    }
                    if (List.of("leather", "copper", "chainmail", "iron", "golden", "diamond", "rbfixed:rose_quartz").contains(base)) {
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "helmet"), materialItem, 5 + bonus, parseTieredItem(out, "helmet"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "chestplate"), materialItem, 8 + bonus, parseTieredItem(out, "chestplate"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "leggings"), materialItem, 7 + bonus, parseTieredItem(out, "leggings"));
                        createSmithingUpgradeRecipe(templateIngredient, parseTieredItem(base, "boots"), materialItem, 4 + bonus, parseTieredItem(out, "boots"));
                    }
                });

            }

            public Item parseTieredItem (String tier, String type) {
                return BuiltInRegistries.ITEM.get(Identifier.parse(tier + "_" + type)).get().value();
            }

            public void createSmithingUpgradeRecipe (Ingredient templateIngredient, Item baseItem, Item materialItem, int materialCount, Item outItem) {
                ((SmithingTransformRecipeBuilderExtension) SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(baseItem), Ingredient.of(materialItem), RecipeCategory.MISC, outItem)).count(materialCount)
                        .unlocks(getHasName(materialItem), has(materialItem)).save(output, BuiltInRegistries.ITEM.getKey(outItem).getPath() + "_from_" + BuiltInRegistries.ITEM.getKey(baseItem).getPath() + "_smithing");
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
