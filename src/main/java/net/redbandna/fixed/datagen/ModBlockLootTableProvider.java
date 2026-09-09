package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        add(ModBlocks.VOID_ESSENCE_ORE, createOreDrop(ModBlocks.VOID_ESSENCE_ORE, ModItems.VOID_ESSENCE));
        dropSelf(ModBlocks.VOID_ESSENCE_BLOCK);
        dropSelf(ModBlocks.PURE_VOID_ESSENCE_BLOCK);
        dropSelf(ModBlocks.END_RELAY);
        dropSelf(ModBlocks.VOID_ESSENCE_STAIRS);
        add(ModBlocks.VOID_ESSENCE_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE);
        add(ModBlocks.VOID_ESSENCE_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.VOID_ESSENCE_TRAPDOOR);
        for (Block button : ModBlocks.VOID_BUTTONS)
            dropSelf(button);


    }
}
