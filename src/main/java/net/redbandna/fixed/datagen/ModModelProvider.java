package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.item.custom.PaintBrushItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.VOID_ESSENCE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.VOID_ESSENCE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.PURE_VOID_ESSENCE_BLOCK);

        blockModelGenerators.createTrivialBlock(ModBlocks.END_RELAY, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.VOID_ESSENCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURE_VOID_ESSENCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_AMETHYST_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_COPPER_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_DIAMOND, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_EMERALD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_GOLD_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_IRON_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_LAPIS_LAZULI, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.VOID_REDSTONE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.Trowel, ModelTemplates.FLAT_HANDHELD_ITEM);
        ModItems.PAINT_BRUSH.forEach(paint_brush -> {
            itemModelGenerators.generateFlatItem(paint_brush, ModelTemplates.FLAT_HANDHELD_ITEM);
        });
    }
}
