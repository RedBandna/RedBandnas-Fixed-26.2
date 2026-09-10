package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_ORE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.PURE_VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.END_RELAY))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_TRAPDOOR));
        for (Block button : ModBlocks.VOID_BUTTONS)
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.getRK(button));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_ORE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.PURE_VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.END_RELAY))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_TRAPDOOR));
        for (Block button : ModBlocks.VOID_BUTTONS)
            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.getRK(button));

        tag(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_ORE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.PURE_VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_TRAPDOOR));
        for (Block button : ModBlocks.VOID_BUTTONS)
            tag(BlockTags.DRAGON_IMMUNE).add(ModBlocks.getRK(button));

        tag(BlockTags.STAIRS).add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_STAIRS));
        tag(BlockTags.SLABS).add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_SLAB));
        tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE));
        tag(BlockTags.DOORS).add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_DOOR));
        tag(BlockTags.TRAPDOORS).add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_TRAPDOOR));

        for (Block button : ModBlocks.VOID_BUTTONS)
            tag(ModTags.Blocks.VOID_BUTTONS).add(ModBlocks.getRK(button));
        tag(BlockTags.BUTTONS)
                .addOptionalTag(ModTags.Blocks.VOID_BUTTONS);

        tag(ModTags.Blocks.INCORRECT_FOR_FLINT_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL);
        tag(ModTags.Blocks.CORRECT_FOR_ROSE_QUARTZ_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_ROSE_QUARTZ_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    }
}
