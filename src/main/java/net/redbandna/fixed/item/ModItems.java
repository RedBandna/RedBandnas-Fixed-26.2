package net.redbandna.fixed.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.custom.PaintBrushItem;
import net.redbandna.fixed.item.custom.TrowelItem;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {

    public static final Item VOID_ESSENCE = registerItem("void_essence", Item::new);
    public static final Item PURE_VOID_ESSENCE = registerItem("pure_void_essence", Item::new);
    public static final Item VOID_AMETHYST_SHARD = registerItem("void_amethyst_shard", Item::new);
    public static final Item VOID_COPPER_INGOT = registerItem("void_copper_ingot", Item::new);
    public static final Item VOID_DIAMOND = registerItem("void_diamond", Item::new);
    public static final Item VOID_EMERALD = registerItem("void_emerald", Item::new);
    public static final Item VOID_GOLD_INGOT = registerItem("void_gold_ingot", Item::new);
    public static final Item VOID_IRON_INGOT = registerItem("void_iron_ingot", Item::new);
    public static final Item VOID_LAPIS_LAZULI = registerItem("void_lapis_lazuli", Item::new);
    public static final Item VOID_REDSTONE = registerItem("void_redstone", Item::new);

    public static final Item Trowel = registerItem("trowel", properties -> new TrowelItem(properties.durability(320)));

    public static final ColorCollection<Item> PAINT_BRUSH = ColorCollection.registerItems(ColorCollection.prefixWithColor(ColorCollection.create("paint_brush")), (name, color) -> registerDyedItem(name, color, PaintBrushItem::new));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name)))));
    }

    private static Item registerDyedItem(String name, DyeColor color, BiFunction<Item.Properties, DyeColor, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name))), color));
    }

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static void registerModItems() {
        RedBandnaSFixed.LOGGER.info("Registering Mod Items for " + RedBandnaSFixed.MOD_ID);
    }
}
