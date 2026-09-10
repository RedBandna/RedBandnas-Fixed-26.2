package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        for (Item mineral : ModItems.VOID_MINERALS)
            tag(ModTags.Items.VOID_MINERALS).add(ModItems.getRK(mineral));

        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.FLINT_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.FLINT_PICKAXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.FLINT_SHOVEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.FLINT_AXE));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.FLINT_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.FLINT_SPEAR));

        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.ROSE_QUARTZ_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.ROSE_QUARTZ_PICKAXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.ROSE_QUARTZ_SHOVEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.ROSE_QUARTZ_AXE));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.ROSE_QUARTZ_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.ROSE_QUARTZ_SPEAR));

        tag(ItemTags.HEAD_ARMOR).add(ModItems.getRK(ModItems.ROSE_QUARTZ_HELMET));
        tag(ItemTags.CHEST_ARMOR).add(ModItems.getRK(ModItems.ROSE_QUARTZ_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR).add(ModItems.getRK(ModItems.ROSE_QUARTZ_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR).add(ModItems.getRK(ModItems.ROSE_QUARTZ_BOOTS));
    }
}
