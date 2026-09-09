package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.VOID_ESSENCE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.PURE_VOID_ESSENCE_BLOCK);

        blockModelGenerators.createTrivialBlock(ModBlocks.END_RELAY, TexturedModel.CUBE_TOP_BOTTOM);

        blockModelGenerators.family(ModBlocks.VOID_ESSENCE_BLOCK)
                        .stairs(ModBlocks.VOID_ESSENCE_STAIRS)
                        .slab(ModBlocks.VOID_ESSENCE_SLAB)
                        .pressurePlate(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE);

        blockModelGenerators.createDoor(ModBlocks.VOID_ESSENCE_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.VOID_ESSENCE_TRAPDOOR);

        for (Block button : ModBlocks.VOID_BUTTONS) {
            TextureMapping textureMapping = new TextureMapping().put(TextureSlot.TEXTURE, new Material(
                    Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "block/" + BuiltInRegistries.BLOCK.getKey(button).getPath())));

            MultiVariant normal = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON.create(button, textureMapping, blockModelGenerators.modelOutput));
            MultiVariant pressed = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON_PRESSED.create(button, textureMapping, blockModelGenerators.modelOutput));
            Identifier inventory = ModelTemplates.BUTTON_INVENTORY.create(button, textureMapping, blockModelGenerators.modelOutput);

            blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(button, normal, pressed));
            blockModelGenerators.registerSimpleItemModel(button.asItem(), inventory);
        }

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

        itemModelGenerators.generateFlatItem(ModItems.VOID_ESSENCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURE_VOID_ESSENCE, ModelTemplates.FLAT_ITEM);
        for (Item mineral : ModItems.VOID_MINERALS)
            itemModelGenerators.generateFlatItem(mineral, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.Trowel, ModelTemplates.FLAT_HANDHELD_ITEM);
        ModItems.PAINT_BRUSH.forEach(paint_brush -> {
            itemModelGenerators.generateFlatItem(paint_brush, ModelTemplates.FLAT_HANDHELD_ITEM);
        });
    }
}
