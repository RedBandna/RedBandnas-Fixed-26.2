package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
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
                .add(ModBlocks.getRK(ModBlocks.END_RELAY));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_ORE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.PURE_VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.END_RELAY));

        tag(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_ORE))
                .add(ModBlocks.getRK(ModBlocks.VOID_ESSENCE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.PURE_VOID_ESSENCE_BLOCK));

    }
}
