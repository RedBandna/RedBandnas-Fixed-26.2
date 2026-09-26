package net.redbandna.fixed.item.forging;

import com.llamalad7.mixinextras.lib.apache.commons.StringUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.entity.custom.NetherForgeBlockEntity;
import net.redbandna.fixed.data.ModAttributes;

import java.util.ArrayList;
import java.util.List;

public class ForgePattern {

    public static ForgePattern SHORT_TOOL = new ForgePattern(0, "Short", ItemTags.MINING_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SHORT_WEAPON = new ForgePattern(0, "Short", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern DULL_TOOL = new ForgePattern(0, "Dull", ItemTags.MINING_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_BREAK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern DULL_WEAPON = new ForgePattern(0, "Dull", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_BREAK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern BRITTLE = new ForgePattern(0, "Brittle", ItemTags.DURABILITY_ENCHANTABLE, null) {
        @Override public ItemStack apply(ItemStack stack) {
            stack.set(DataComponents.MAX_DAMAGE, stack.get(DataComponents.MAX_DAMAGE) / 2);
            return applyPrefix(stack);
        }
    };

    public static ForgePattern IMPRECISE_BOW = new ForgePattern(0, "Imprecise", ItemTags.BOW_ENCHANTABLE, List.of(
            new ModifierTemplate(ModAttributes.ARROW_SPREAD, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern IMPRECISE_CROSSBOW = new ForgePattern(0, "Imprecise", ItemTags.CROSSBOW_ENCHANTABLE, List.of(
            new ModifierTemplate(ModAttributes.ARROW_SPREAD, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));

    public static ForgePattern PRECISE_BOW = new ForgePattern(7, "Precise", ItemTags.BOW_ENCHANTABLE, List.of(
            new ModifierTemplate(ModAttributes.ARROW_SPREAD, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern PRECISE_CROSSBOW = new ForgePattern(7, "Precise", ItemTags.CROSSBOW_ENCHANTABLE, List.of(
            new ModifierTemplate(ModAttributes.ARROW_SPREAD, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));

    public static ForgePattern HEAVY_WEAPON = new ForgePattern(7, "Heavy", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.ATTACK_SPEED, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ATTACK_DAMAGE, 1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern HEAVY_ARMOR = new ForgePattern(7, "Heavy", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.MOVEMENT_SPEED, -0.02, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.ARMOR, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern LIGHT_WEAPON = new ForgePattern(7, "Light", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.ATTACK_SPEED, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern LIGHT_ARMOR = new ForgePattern(7, "Light", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.MOVEMENT_SPEED, 0.01, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.ARMOR, -1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern GIANT = new ForgePattern(7, "Giant's", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.SCALE, 0.3, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 0.2, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 0.2, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.MAX_HEALTH, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern DWARF = new ForgePattern(7, "Dwarf's", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.SCALE, -0.1, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR),
            new ModifierTemplate(Attributes.MINING_EFFICIENCY, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern DURABLE = new ForgePattern(7, "Durable", ItemTags.DURABILITY_ENCHANTABLE, null) {
        @Override public ItemStack apply(ItemStack stack) {
            stack.set(DataComponents.MAX_DAMAGE, (int) (stack.get(DataComponents.MAX_DAMAGE) * 1.3));
            return applyPrefix(stack);
        }
    };

    public static ForgePattern SWIFT_TOOL = new ForgePattern(15, "Swift", ItemTags.MINING_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.MINING_EFFICIENCY, 25.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SWIFT_WEAPON = new ForgePattern(15, "Swift", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.ATTACK_SPEED, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern SWIFT_ARMOR = new ForgePattern(15, "Swift", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.MOVEMENT_SPEED, 0.03, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

    public static ForgePattern LONG_TOOL = new ForgePattern(15, "Long", ItemTags.MINING_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));
    public static ForgePattern LONG_WEAPON = new ForgePattern(15, "Long", ItemTags.WEAPON_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
            new ModifierTemplate(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)));

    public static ForgePattern LUCKY_ROD = new ForgePattern(15, "Lucky", ItemTags.FISHING_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.LUCK, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.HAND)));
    public static ForgePattern LUCKY_ARMOR = new ForgePattern(15, "Lucky", ItemTags.ARMOR_ENCHANTABLE, List.of(
            new ModifierTemplate(Attributes.LUCK, 0.5, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)));

//    DataComponents.SUSPICIOUS_STEW_EFFECTS (add mixin to apply affects on arrow creation)

    public static ItemAttributeModifiers.Entry createModifier(Holder<Attribute> attribute, String name, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup slotGroup) {
        return new ItemAttributeModifiers.Entry(attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name), amount, operation), slotGroup);
    }

    public final int cost;
    public final String prefix;
    public final TagKey<Item> applicable;
    public final List<ModifierTemplate> modifiers;

    public ForgePattern(int cost, String prefix, TagKey<Item> applicable, List<ModifierTemplate> modifiers) {
        this.cost = cost;
        this.prefix = prefix;
        this.applicable = applicable;
        this.modifiers = modifiers;
    }

    public ItemStack apply(ItemStack stack) {
        if (modifiers != null) {
            ItemAttributeModifiers stackModifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            for (ModifierTemplate entry : modifiers)
                stackModifiers = stackModifiers.withModifierAdded(entry.attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID,
                        (stack.getHoverName().getString() + prefix + "ForgingPattern").replaceAll("[^a-z0-9/._-]", "")), entry.amount, entry.operation), entry.slotGroup);

            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, stackModifiers);
        }
        return applyPrefix(stack);
    }

    public ItemStack applyPrefix(ItemStack stack) {
        stack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(prefix + " " + StringUtils.removeStart(stack.getHoverName().getString(), NetherForgeBlockEntity.prefix)));
        return stack;
    }
    
    public static class ModifierTemplate {
        Holder<Attribute> attribute;
        double amount;
        AttributeModifier.Operation operation;
        EquipmentSlotGroup slotGroup;
        public ModifierTemplate(Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup slotGroup) {
            this.attribute = attribute;
            this.amount = amount;
            this.operation = operation;
            this.slotGroup = slotGroup;
        }
    }
}
