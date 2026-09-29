package net.redbandna.fixed.item.forging;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.attribute.ModAttributes;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.effect.ModEffects;

import java.util.List;

public class ModForgePatterns {

    public static ForgePattern SHORT_TOOL = new ForgePattern.Attributes(0, "Short", ItemTags.MINING_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SHORT_WEAPON = new ForgePattern.Attributes(0, "Short", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SHORT_TRIDENT = new ForgePattern.Attributes(0, "Short", ItemTags.TRIDENT_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern DULL_TOOL = new ForgePattern.Attributes(0, "Dull", ItemTags.MINING_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_BREAK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern DULL_WEAPON = new ForgePattern.Attributes(0, "Dull", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_BREAK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern DULL_TRIDENT = new ForgePattern.Attributes(0, "Dull", ItemTags.TRIDENT_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_BREAK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern BRITTLE = new ForgePattern.Data<>(0, "Brittle", ItemTags.DURABILITY_ENCHANTABLE, DataComponents.MAX_DAMAGE, current -> current / 2);

    public static ForgePattern IMPRECISE_BOW = new ForgePattern.Attributes(2, "Imprecise", ItemTags.BOW_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(ModAttributes.ARROW_SPREAD, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern IMPRECISE_CROSSBOW = new ForgePattern.Attributes(2, "Imprecise", ItemTags.CROSSBOW_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(ModAttributes.ARROW_SPREAD, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));

    public static ForgePattern PRECISE_BOW = new ForgePattern.Attributes(6, "Precise", ItemTags.BOW_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(ModAttributes.ARROW_SPREAD, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern PRECISE_CROSSBOW = new ForgePattern.Attributes(6, "Precise", ItemTags.CROSSBOW_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(ModAttributes.ARROW_SPREAD, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));

    public static ForgePattern HEAVY_WEAPON = new ForgePattern.Attributes(7, "Heavy", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_SPEED, -0.5, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_DAMAGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern HEAVY_ARMOR = new ForgePattern.Attributes(7, "Heavy", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.MOVEMENT_SPEED, -0.01, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.ARMOR, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern LIGHT_WEAPON = new ForgePattern.Attributes(7, "Light", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_SPEED, 0.5, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern LIGHT_ARMOR = new ForgePattern.Attributes(7, "Light", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.MOVEMENT_SPEED, 0.01, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.ARMOR, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern GIANT = new ForgePattern.Attributes(7, "Giant's", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.SCALE, 0.3, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 0.2, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 0.2, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.MAX_HEALTH, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern DWARF = new ForgePattern.Attributes(9, "Dwarf's", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.SCALE, -0.1, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ForgePattern.ModifierTemplate(Attributes.MINING_EFFICIENCY, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern DURABLE = new ForgePattern.Data<>(0, "Durable", ItemTags.DURABILITY_ENCHANTABLE, DataComponents.MAX_DAMAGE, current -> (int) (current * 1.4));

    public static ForgePattern SWIFT_TOOL = new ForgePattern.Attributes(18, "Swift", ItemTags.MINING_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.MINING_EFFICIENCY, 25.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SWIFT_WEAPON = new ForgePattern.Attributes(18, "Swift", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.ATTACK_SPEED, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SWIFT_ARMOR = new ForgePattern.Attributes(18, "Swift", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.MOVEMENT_SPEED, 0.03, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern LONG_TOOL = new ForgePattern.Attributes(15, "Long", ItemTags.MINING_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern LONG_WEAPON = new ForgePattern.Attributes(15, "Long", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern LONG_TRIDENT = new ForgePattern.Attributes(15, "Long", ItemTags.TRIDENT_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ForgePattern.ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern LUCKY_ROD = new ForgePattern.Attributes(15, "Lucky", ItemTags.FISHING_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.LUCK, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern LUCKY_ARMOR = new ForgePattern.Attributes(15, "Lucky", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ForgePattern.ModifierTemplate(Attributes.LUCK, 0.5, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern SYPHONING_WEAPON = new ForgePattern.Effects(20, "Syphoning", ItemTags.WEAPON_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(ModEffects.SYPHONED, 200)));
    public static ForgePattern SYPHONING_BOW = new ForgePattern.Effects(20, "Syphoning", ItemTags.BOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(ModEffects.SYPHONED, 200)));
    public static ForgePattern SYPHONING_CROSSBOW = new ForgePattern.Effects(20, "Syphoning", ItemTags.CROSSBOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(ModEffects.SYPHONED, 200)));
    public static ForgePattern SYPHONING_TRIDENT = new ForgePattern.Effects(20, "Syphoning", ItemTags.TRIDENT_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(ModEffects.SYPHONED, 200)));

    public static ForgePattern POISONING_WEAPON = new ForgePattern.Effects(20, "Poisoning", ItemTags.WEAPON_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.POISON, 200)));
    public static ForgePattern POISONING_BOW = new ForgePattern.Effects(20, "Poisoning", ItemTags.BOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.POISON, 200)));
    public static ForgePattern POISONING_CROSSBOW = new ForgePattern.Effects(20, "Poisoning", ItemTags.CROSSBOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.POISON, 200)));
    public static ForgePattern POISONING_TRIDENT = new ForgePattern.Effects(20, "Poisoning", ItemTags.TRIDENT_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.POISON, 200)));

    public static ForgePattern WITHERING_WEAPON = new ForgePattern.Effects(20, "Withering", ItemTags.WEAPON_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.WITHER, 200)));
    public static ForgePattern WITHERING_BOW = new ForgePattern.Effects(20, "Withering", ItemTags.BOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.WITHER, 200)));
    public static ForgePattern WITHERING_CROSSBOW = new ForgePattern.Effects(20, "Withering", ItemTags.CROSSBOW_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.WITHER, 200)));
    public static ForgePattern WITHERING_TRIDENT = new ForgePattern.Effects(20, "Withering", ItemTags.TRIDENT_ENCHANTABLE, List.of(new SuspiciousStewEffects.Entry(MobEffects.WITHER, 200)));

    public static ForgePattern CATALYTIC = new ForgePattern.Data<>(50, "Catalytic", ItemTags.FOOT_ARMOR_ENCHANTABLE, ModDataComponents.CATALYTIC, c -> Unit.INSTANCE);

    public static ForgePattern GILDED_WEAPON = new ForgePattern.Data<>(50, "Gilded", ItemTags.WEAPON_ENCHANTABLE, ModDataComponents.GILDED, c -> Unit.INSTANCE);
    public static ForgePattern GILDED_BOW = new ForgePattern.Data<>(50, "Gilded", ItemTags.BOW_ENCHANTABLE, ModDataComponents.GILDED, c -> Unit.INSTANCE);
    public static ForgePattern GILDED_CROSSBOW = new ForgePattern.Data<>(50, "Gilded", ItemTags.CROSSBOW_ENCHANTABLE, ModDataComponents.GILDED, c -> Unit.INSTANCE);
    public static ForgePattern GILDED_TRIDENT = new ForgePattern.Data<>(50, "Gilded", ItemTags.TRIDENT_ENCHANTABLE, ModDataComponents.GILDED, c -> Unit.INSTANCE);

    public static ForgePattern UNDERESTIMATED_WEAPON = new ForgePattern.Data<>(50, "Underestimated", ItemTags.WEAPON_ENCHANTABLE, ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, c -> List.of());
    public static ForgePattern UNDERESTIMATED_BOW = new ForgePattern.Data<>(50, "Underestimated", ItemTags.BOW_ENCHANTABLE, ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, c -> List.of());
    public static ForgePattern UNDERESTIMATED_CROSSBOW = new ForgePattern.Data<>(50, "Underestimated", ItemTags.CROSSBOW_ENCHANTABLE, ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, c -> List.of());
    public static ForgePattern UNDERESTIMATED_TRIDENT = new ForgePattern.Data<>(50, "Underestimated", ItemTags.TRIDENT_ENCHANTABLE, ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, c -> List.of());

    public static ForgePattern STORMING_WEAPON = new ForgePattern.Data<>(35, "Storming", ItemTags.WEAPON_ENCHANTABLE, ModDataComponents.SUMMONS_THUNDER, c -> Unit.INSTANCE);
    public static ForgePattern STORMING_BOW = new ForgePattern.Data<>(35, "Storming", ItemTags.BOW_ENCHANTABLE, ModDataComponents.SUMMONS_THUNDER, c -> Unit.INSTANCE);
    public static ForgePattern STORMING_CROSSBOW = new ForgePattern.Data<>(35, "Storming", ItemTags.CROSSBOW_ENCHANTABLE, ModDataComponents.SUMMONS_THUNDER, c -> Unit.INSTANCE);
    public static ForgePattern STORMING_TRIDENT = new ForgePattern.Data<>(35, "Storming", ItemTags.TRIDENT_ENCHANTABLE, ModDataComponents.SUMMONS_THUNDER, c -> Unit.INSTANCE);

    public static void registerForgePatterns() {
        RedBandnaSFixed.LOGGER.info("Registering Forge Patterns for " + RedBandnaSFixed.MOD_ID);
    }
}
