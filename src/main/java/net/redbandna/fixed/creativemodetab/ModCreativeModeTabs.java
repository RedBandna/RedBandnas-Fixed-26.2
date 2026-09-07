package net.redbandna.fixed.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.item.ModItems;

public class ModCreativeModeTabs {

    public static final CreativeModeTab END_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "end_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PURE_VOID_ESSENCE))
                    .title(Component.translatable("creativemodetab.rbfixed.end_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.VOID_ESSENCE);
                        output.accept(ModItems.PURE_VOID_ESSENCE);
                        output.accept(ModItems.VOID_AMETHYST_SHARD);
                        output.accept(ModItems.VOID_COPPER_INGOT);
                        output.accept(ModItems.VOID_DIAMOND);
                        output.accept(ModItems.VOID_EMERALD);
                        output.accept(ModItems.VOID_GOLD_INGOT);
                        output.accept(ModItems.VOID_IRON_INGOT);
                        output.accept(ModItems.VOID_LAPIS_LAZULI);
                        output.accept(ModItems.VOID_REDSTONE);
                    }).build());

    public static final CreativeModeTab END_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "end_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.VOID_ESSENCE_BLOCK))
                    .title(Component.translatable("creativemodetab.rbfixed.end_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.VOID_ESSENCE_ORE);
                        output.accept(ModBlocks.VOID_ESSENCE_BLOCK);
                        output.accept(ModBlocks.PURE_VOID_ESSENCE_BLOCK);
                    }).build());

    public static void registerModCreativeModeTabs() {
        RedBandnaSFixed.LOGGER.info("Registering Creative Mode Tabs for " + RedBandnaSFixed.MOD_ID);
    }
}
