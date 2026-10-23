package net.redbandna.fixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
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

        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.FLINT_PICKAXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.FLINT_SHOVEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.FLINT_AXE));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.FLINT_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.FLINT_SPEAR));
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.FLINT_SWORD));

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

        tag(ModTags.Items.FLINT_REPAIR).add(ItemIds.FLINT);
        tag(ModTags.Items.ROSE_QUARTZ_REPAIR).add(ModItems.getRK(ModItems.VOID_QUARTZ));
        tag(ItemTags.REPAIRS_NETHERITE_ARMOR).remove(ItemIds.NETHERITE_INGOT).add(ItemIds.NETHERITE_SCRAP);
        tag(ItemTags.NETHERITE_TOOL_MATERIALS).remove(ItemIds.NETHERITE_INGOT).add(ItemIds.NETHERITE_SCRAP);

        tag(ModTags.Items.BRACER_ENCHANTABLE)
                .add(ModItems.getRK(ModItems.LEATHER_BRACERS))
                .add(ModItems.getRK(ModItems.COPPER_BRACERS))
                .add(ModItems.getRK(ModItems.CHAINMAIL_BRACERS))
                .add(ModItems.getRK(ModItems.IRON_BRACERS))
                .add(ModItems.getRK(ModItems.GOLDEN_BRACERS))
                .add(ModItems.getRK(ModItems.DIAMOND_BRACERS))
                .add(ModItems.getRK(ModItems.ROSE_QUARTZ_BRACERS))
                .add(ModItems.getRK(ModItems.NETHERITE_BRACERS));

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModTags.Items.BRACER_ENCHANTABLE);
        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .addTag(ModTags.Items.BRACER_ENCHANTABLE);
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(ModTags.Items.BRACER_ENCHANTABLE);

        tag(ModTags.Items.FORGE_FUEL_ITEMS)
                .add(ItemIds.LAVA_BUCKET)
                .add(ItemIds.BLAZE_ROD)
                .add(BlockItemIds.BONE_BLOCK.item());
    }
}
