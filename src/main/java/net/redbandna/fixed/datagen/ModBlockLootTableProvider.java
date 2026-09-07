package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        dropSelf(ModBlocks.VOID_ESSENCE_BLOCK);
        dropSelf(ModBlocks.PURE_VOID_ESSENCE_BLOCK);

        add(ModBlocks.VOID_ESSENCE_ORE, createOreDrop(ModBlocks.VOID_ESSENCE_ORE, ModItems.VOID_ESSENCE));

    }
}
