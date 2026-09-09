package net.redbandna.fixed.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.tags.ModTags;

public class ModCreativeModeTabs {

    public static final CreativeModeTab END_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "end_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PURE_VOID_ESSENCE))
                    .title(Component.translatable("creativemodetab.rbfixed.end_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.VOID_ESSENCE);
                        output.accept(ModItems.PURE_VOID_ESSENCE);
                        for (Item mineral : ModItems.VOID_MINERALS)
                            output.accept(mineral);
                    }).build());

    public static final CreativeModeTab END_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "end_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.VOID_ESSENCE_BLOCK))
                    .title(Component.translatable("creativemodetab.rbfixed.end_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.VOID_ESSENCE_ORE);
                        output.accept(ModBlocks.VOID_ESSENCE_BLOCK);
                        output.accept(ModBlocks.PURE_VOID_ESSENCE_BLOCK);
                        output.accept(ModBlocks.END_RELAY);
                        output.accept(ModBlocks.VOID_ESSENCE_STAIRS);
                        output.accept(ModBlocks.VOID_ESSENCE_SLAB);
                        output.accept(ModBlocks.VOID_ESSENCE_PRESSURE_PLATE);
                        output.accept(ModBlocks.VOID_ESSENCE_DOOR);
                        output.accept(ModBlocks.VOID_ESSENCE_TRAPDOOR);
                        for (Block button : ModBlocks.VOID_BUTTONS)
                            output.accept(button);

                    }).build());

    public static final CreativeModeTab MISC_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "mod_misc"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Trowel))
                    .title(Component.translatable("creativemodetab.rbfixed.mod_misc"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.Trowel);
                        ModItems.PAINT_BRUSH.forEach(output::accept);
                    }).build());

    public static void registerModCreativeModeTabs() {
        RedBandnaSFixed.LOGGER.info("Registering Creative Mode Tabs for " + RedBandnaSFixed.MOD_ID);
    }
}
