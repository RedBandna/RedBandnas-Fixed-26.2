package net.redbandna.fixed.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.custom.ModArmorMaterials;
import net.redbandna.fixed.item.custom.PaintBrushItem;
import net.redbandna.fixed.item.custom.TrowelItem;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {

    public static final Item VOID_ESSENCE = registerItem("void_essence", Item::new);
    public static final Item PURE_VOID_ESSENCE = registerItem("pure_void_essence", Item::new);
    public static final Item VOID_AMETHYST_SHARD = registerItem("void_amethyst_shard", Item::new);
    public static final Item VOID_COAL = registerItem("void_coal", Item::new);
    public static final Item VOID_COPPER_INGOT = registerItem("void_copper_ingot", Item::new);
    public static final Item VOID_DIAMOND = registerItem("void_diamond", Item::new);
    public static final Item VOID_EMERALD = registerItem("void_emerald", Item::new);
    public static final Item VOID_GOLD_INGOT = registerItem("void_gold_ingot", Item::new);
    public static final Item VOID_IRON_INGOT = registerItem("void_iron_ingot", Item::new);
    public static final Item VOID_LAPIS_LAZULI = registerItem("void_lapis_lazuli", Item::new);
    public static final Item VOID_NETHERITE_SCRAP = registerItem("void_netherite_scrap", Item::new);
    public static final Item VOID_QUARTZ = registerItem("void_quartz", Item::new);
    public static final Item VOID_REDSTONE = registerItem("void_redstone", Item::new);
    public static final List<Item> VOID_MINERALS = List.of(VOID_AMETHYST_SHARD, VOID_COAL, VOID_COPPER_INGOT, VOID_DIAMOND, VOID_EMERALD,
            VOID_GOLD_INGOT, VOID_IRON_INGOT, VOID_LAPIS_LAZULI, VOID_NETHERITE_SCRAP, VOID_QUARTZ, VOID_REDSTONE);

    public static final Item Trowel = registerItem("trowel", properties -> new TrowelItem(properties.durability(320)));

    public static final ColorCollection<Item> PAINT_BRUSH = ColorCollection.registerItems(ColorCollection.prefixWithColor(ColorCollection.create("paint_brush")), (name, color) -> registerDyedItem(name, color, PaintBrushItem::new));

    public static final Item FLINT_SWORD = registerItem("flint_sword",
            properties -> new Item(properties.sword(ModToolMaterials.FLINT, 3, -2.4f)));
    public static final Item FLINT_PICKAXE = registerItem("flint_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.FLINT, 1, -2.8f)));
    public static final Item FLINT_SHOVEL = registerItem("flint_shovel",
            properties -> new ShovelItem(ModToolMaterials.FLINT, 1.5f, -3.0f, properties));
    public static final Item FLINT_AXE = registerItem("flint_axe",
            properties -> new AxeItem(ModToolMaterials.FLINT, 6, -3.2f, properties));
    public static final Item FLINT_HOE = registerItem("flint_hoe",
            properties -> new HoeItem(ModToolMaterials.FLINT, 0, -3.0f, properties));
    public static final Item FLINT_SPEAR = registerItem("flint_spear",
            properties -> new Item(properties.spear(ModToolMaterials.FLINT, 0.65f, 0.7f, 0.75f,
                    5.0f, 14.0f, 10.0f, 5.1f, 15.0f, 4.6f)));

    public static final Item ROSE_QUARTZ_SWORD = registerItem("rose_quartz_sword",
            properties -> new Item(properties.sword(ModToolMaterials.ROSE_QUARTZ, 3, -2.4f)));
    public static final Item ROSE_QUARTZ_PICKAXE = registerItem("rose_quartz_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.ROSE_QUARTZ, 1, -2.8f)));
    public static final Item ROSE_QUARTZ_SHOVEL = registerItem("rose_quartz_shovel",
            properties -> new ShovelItem(ModToolMaterials.ROSE_QUARTZ, 1.5f, -3.0f, properties));
    public static final Item ROSE_QUARTZ_AXE = registerItem("rose_quartz_axe",
            properties -> new AxeItem(ModToolMaterials.ROSE_QUARTZ, 6, -3.2f, properties));
    public static final Item ROSE_QUARTZ_HOE = registerItem("rose_quartz_hoe",
            properties -> new HoeItem(ModToolMaterials.ROSE_QUARTZ, 0, -3.0f, properties));
    public static final Item ROSE_QUARTZ_SPEAR = registerItem("rose_quartz_spear",
            properties -> new Item(properties.spear(ModToolMaterials.ROSE_QUARTZ, 1.05F, 1.075F, 0.5F,
                    3.0F, 10.0F, 6.5F, 5.1F, 10.0F, 4.6F)));
    public static final Item ROSE_QUARTZ_HELMET = registerItem("rose_quartz_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ROSE_QUARTZ, ArmorType.HELMET)));
    public static final Item ROSE_QUARTZ_CHESTPLATE = registerItem("rose_quartz_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ROSE_QUARTZ, ArmorType.CHESTPLATE)));
    public static final Item ROSE_QUARTZ_LEGGINGS = registerItem("rose_quartz_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ROSE_QUARTZ, ArmorType.LEGGINGS)));
    public static final Item ROSE_QUARTZ_BOOTS = registerItem("rose_quartz_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ROSE_QUARTZ, ArmorType.BOOTS)));
    public static final Item ROSE_QUARTZ_HORSE_ARMOR = registerItem("rose_quartz_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.ROSE_QUARTZ)));

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
