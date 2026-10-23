package net.redbandna.fixed.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.redbandna.fixed.item.ModItems;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ModLootTableModifiers {

    public record forgedEquipmentLootEntry(String tables, List<Item> items, List<Item> templates) {}

    public static List<Item> defaultTemplates = List.of(
            ModItems.SHORT_FORGING_TEMPLATE,
            ModItems.DULL_FORGING_TEMPLATE,
            ModItems.BRITTLE_FORGING_TEMPLATE,
            ModItems.IMPRECISE_FORGING_TEMPLATE,
            ModItems.PRECISE_FORGING_TEMPLATE,
            ModItems.DURABLE_FORGING_TEMPLATE,
            ModItems.LUCKY_FORGING_TEMPLATE
    );

    public static List<forgedEquipmentLootEntry> forgedEquipmentMap = List.of(
            new forgedEquipmentLootEntry("abandoned_mineshaft", List.of(Items.IRON_PICKAXE, Items.IRON_SWORD), List.of(ModItems.DWARF_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("bastion", List.of(
                    Items.GOLDEN_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SWORD, Items.GOLDEN_SPEAR, ModItems.GOLDEN_BRACERS,
                    Items.DIAMOND_AXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SWORD, Items.DIAMOND_SPEAR, ModItems.DIAMOND_BRACERS,
                    Items.IRON_AXE, Items.IRON_PICKAXE, Items.IRON_SWORD, Items.IRON_SPEAR, ModItems.IRON_BRACERS,
                    ModItems.ROSE_QUARTZ_AXE, ModItems.ROSE_QUARTZ_PICKAXE, ModItems.ROSE_QUARTZ_SWORD, ModItems.ROSE_QUARTZ_SPEAR, ModItems.ROSE_QUARTZ_BRACERS
            ), List.of(ModItems.UNDERESTIMATED_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("buried_treasure", List.of(Items.IRON_SPEAR, Items.STONE_SWORD, Items.LEATHER_HELMET), List.of(ModItems.LUCKY_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("ancient_city", List.of(Items.DIAMOND_PICKAXE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_HOE), List.of(ModItems.CATALYTIC_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("desert_pyramid", List.of(Items.IRON_SPEAR, Items.IRON_SWORD, Items.BOW), List.of(ModItems.SYPHONING_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("end_city_treasure", List.of(
                    Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_HELMET,
                    Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_CHESTPLATE, Items.IRON_HELMET
            ), List.of(ModItems.DWARF_FORGING_TEMPLATE, ModItems.LIGHT_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("jungle_temple", List.of(Items.BOW, Items.IRON_SWORD), List.of(ModItems.POISONING_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("pillager_outpost", List.of(Items.CROSSBOW, Items.STONE_SWORD), List.of(ModItems.SYPHONING_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("shipwreck", List.of(Items.FISHING_ROD, Items.LEATHER_BOOTS), List.of(ModItems.LUCKY_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("dungeon", List.of(
                    Items.IRON_PICKAXE, Items.IRON_SWORD,
                    Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS
            ), List.of(ModItems.GIANT_FORGING_TEMPLATE, ModItems.HEAVY_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("stronghold", List.of(
                    Items.IRON_PICKAXE, Items.IRON_SWORD, Items.IRON_SPEAR
            ), List.of(ModItems.UNDERESTIMATED_FORGING_TEMPLATE, ModItems.LONG_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("underwater_ruin", List.of(Items.FISHING_ROD, Items.LEATHER_LEGGINGS, Items.STONE_SPEAR), List.of(ModItems.LUCKY_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("mansion", List.of(Items.IRON_PICKAXE, Items.IRON_SWORD), List.of(ModItems.HEAVY_FORGING_TEMPLATE, ModItems.SWIFT_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("ruined_portal", List.of(
                    Items.GOLDEN_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SWORD, Items.GOLDEN_SPEAR, Items.CROSSBOW,
                    Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, ModItems.GOLDEN_BRACERS
            ), List.of(ModItems.SYPHONING_FORGING_TEMPLATE, ModItems.POISONING_FORGING_TEMPLATE)),
            new forgedEquipmentLootEntry("trial_chambers_intersection", List.of(
                    Items.COPPER_AXE, Items.COPPER_PICKAXE, Items.COPPER_SWORD, Items.COPPER_SPEAR,
                    Items.COPPER_HELMET, Items.COPPER_CHESTPLATE, Items.COPPER_LEGGINGS, Items.COPPER_BOOTS,  ModItems.COPPER_BRACERS,
                    Items.IRON_AXE, Items.IRON_PICKAXE, Items.IRON_SWORD, Items.IRON_SPEAR,
                    Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS,  ModItems.IRON_BRACERS
            ), List.of(ModItems.HEAVY_FORGING_TEMPLATE, ModItems.SWIFT_FORGING_TEMPLATE, ModItems.LONG_FORGING_TEMPLATE))
    );

    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder, LootTableSource source, HolderLookup.Provider provider) {
        if (key.identifier().getPath().startsWith("chests/")) {
            forgedEquipmentMap.forEach(entry -> {
                if (key.identifier().getPath().contains(entry.tables)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .when(LootItemRandomChanceCondition.randomChance(0.1f));
                    for (Item item : entry.items()) {
                        poolBuilder.add(LootItem.lootTableItem(item).setWeight(5).apply(ForgedFunction.builder(defaultTemplates)));
                        poolBuilder.add(LootItem.lootTableItem(item).apply(ForgedFunction.builder(entry.templates)));
                    }
                    builder.pool(poolBuilder.build());
                }
            });
        }
    }

    public static @Nullable LootTable replaceLootTables(ResourceKey<LootTable> key, LootTable lootTable, LootTableSource source, HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace("entities/evoker")))
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.EMERALD)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider, UniformGenerator.between(0.0F, 1.0F))))
                            .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModItems.CORRUPT_SOUL)))
                    .build();

        return lootTable;
    }
}
