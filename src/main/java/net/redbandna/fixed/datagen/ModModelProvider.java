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
import net.minecraft.world.level.block.Blocks;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.item.custom.ModArmorMaterials;

import java.util.function.Consumer;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.PURE_VOID_AIR, Blocks.AIR);

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
                    RedBandnaSFixed.id("block/" + BuiltInRegistries.BLOCK.getKey(button).getPath())));

            MultiVariant normal = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON.create(button, textureMapping, blockModelGenerators.modelOutput));
            MultiVariant pressed = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON_PRESSED.create(button, textureMapping, blockModelGenerators.modelOutput));
            Identifier inventory = ModelTemplates.BUTTON_INVENTORY.create(button, textureMapping, blockModelGenerators.modelOutput);

            blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(button, normal, pressed));
            blockModelGenerators.registerSimpleItemModel(button.asItem(), inventory);
        }

        blockModelGenerators.createFurnace(ModBlocks.NETHER_FORGE, TexturedModel.ORIENTABLE_ONLY_TOP);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

        Consumer<Item> flat = item -> { itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_ITEM); };
        Consumer<Item> handheld = item -> { itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM); };

        flat.accept(ModItems.VOID_ESSENCE);
        flat.accept(ModItems.PURE_VOID_ESSENCE);
        ModItems.VOID_MINERALS.forEach(flat);
        flat.accept(ModItems.CORRUPT_SOUL);

        handheld.accept(ModItems.Trowel);
        ModItems.PAINT_BRUSH.forEach(handheld);

        handheld.accept(ModItems.FLINT_SWORD);
        handheld.accept(ModItems.FLINT_PICKAXE);
        handheld.accept(ModItems.FLINT_SHOVEL);
        handheld.accept(ModItems.FLINT_AXE);
        handheld.accept(ModItems.FLINT_HOE);
        itemModelGenerators.generateSpear(ModItems.FLINT_SPEAR);

        handheld.accept(ModItems.ROSE_QUARTZ_SWORD);
        handheld.accept(ModItems.ROSE_QUARTZ_PICKAXE);
        handheld.accept(ModItems.ROSE_QUARTZ_SHOVEL);
        handheld.accept(ModItems.ROSE_QUARTZ_AXE);
        handheld.accept(ModItems.ROSE_QUARTZ_HOE);
        itemModelGenerators.generateSpear(ModItems.ROSE_QUARTZ_SPEAR);
        itemModelGenerators.generateTrimmableItem(ModItems.ROSE_QUARTZ_HELMET, ModArmorMaterials.ROSE_QUARTZ_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ROSE_QUARTZ_CHESTPLATE, ModArmorMaterials.ROSE_QUARTZ_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ROSE_QUARTZ_LEGGINGS, ModArmorMaterials.ROSE_QUARTZ_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.ROSE_QUARTZ_BOOTS, ModArmorMaterials.ROSE_QUARTZ_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        handheld.accept(ModItems.ROSE_QUARTZ_HORSE_ARMOR);

        flat.accept(ModItems.LEATHER_BRACERS);
        flat.accept(ModItems.COPPER_BRACERS);
        flat.accept(ModItems.CHAINMAIL_BRACERS);
        flat.accept(ModItems.IRON_BRACERS);
        flat.accept(ModItems.GOLDEN_BRACERS);
        flat.accept(ModItems.DIAMOND_BRACERS);
        flat.accept(ModItems.ROSE_QUARTZ_BRACERS);
        flat.accept(ModItems.NETHERITE_BRACERS);

        ModItems.PENDANTS.forEach(flat);

        flat.accept(ModItems.STONE_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.CHAINMAIL_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.IRON_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE);
        flat.accept(ModItems.ROSE_QUARTZ_UPGRADE_SMITHING_TEMPLATE);

        flat.accept(ModItems.SHORT_FORGING_TEMPLATE);
        flat.accept(ModItems.DULL_FORGING_TEMPLATE);
        flat.accept(ModItems.BRITTLE_FORGING_TEMPLATE);
        flat.accept(ModItems.IMPRECISE_FORGING_TEMPLATE);
        flat.accept(ModItems.PRECISE_FORGING_TEMPLATE);
        flat.accept(ModItems.HEAVY_FORGING_TEMPLATE);
        flat.accept(ModItems.LIGHT_FORGING_TEMPLATE);
        flat.accept(ModItems.GIANT_FORGING_TEMPLATE);
        flat.accept(ModItems.DWARF_FORGING_TEMPLATE);
        flat.accept(ModItems.DURABLE_FORGING_TEMPLATE);
        flat.accept(ModItems.SWIFT_FORGING_TEMPLATE);
        flat.accept(ModItems.LONG_FORGING_TEMPLATE);
        flat.accept(ModItems.LUCKY_FORGING_TEMPLATE);
        flat.accept(ModItems.SYPHONING_FORGING_TEMPLATE);
        flat.accept(ModItems.POISONING_FORGING_TEMPLATE);
        flat.accept(ModItems.WITHERING_FORGING_TEMPLATE);
        flat.accept(ModItems.CATALYTIC_FORGING_TEMPLATE);
        flat.accept(ModItems.UNDERESTIMATED_FORGING_TEMPLATE);
    }
}
