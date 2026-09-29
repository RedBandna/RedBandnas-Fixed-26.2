package net.redbandna.fixed.item.forging;

import com.llamalad7.mixinextras.lib.apache.commons.StringUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.entity.custom.NetherForgeBlockEntity;
import net.redbandna.fixed.data.ModDataComponents;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public abstract class ForgePattern {

    public final int cost;
    public final String prefix;
    public final TagKey<Item> applicable;

    public ForgePattern(int cost, String prefix, TagKey<Item> applicable) {
        this.cost = cost;
        this.prefix = prefix;
        this.applicable = applicable;
    }

    public ItemStack apply(ItemStack stack) {
        stack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(prefix + " " + StringUtils.removeStart(stack.getHoverName().getString(), NetherForgeBlockEntity.prefix)));
        return stack;
    }

    public static class Data<T> extends ForgePattern {

        public final DataComponentType<T> type;
        public final UnaryOperator<T> setter;

        public Data(int cost, String prefix, TagKey<Item> applicable, DataComponentType<T> type, UnaryOperator<T> setter) {
            super(cost, prefix, applicable);
            this.type = type;
            this.setter = setter;
        }

        @Override
        public ItemStack apply(ItemStack stack) {
            stack.update(type, null, setter);
            return super.apply(stack);
        }
    }

    public static class Effects extends ForgePattern {

        public final List<SuspiciousStewEffects.Entry> effects;

        public Effects(int cost, String prefix, TagKey<Item> applicable, List<SuspiciousStewEffects.Entry> effects) {
            super(cost, prefix, applicable);
            this.effects = effects;
        }

        @Override
        public ItemStack apply(ItemStack stack) {
            stack.update(ModDataComponents.FORGED_EFFECTS, new SuspiciousStewEffects(List.of()), current ->
                    new SuspiciousStewEffects(Stream.concat(current.effects().stream(), effects.stream()).toList()));

            return super.apply(stack);
        }
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

    public static class Attributes extends ForgePattern {

        public final List<ModifierTemplate> modifiers;

        public Attributes(int cost, String prefix, TagKey<Item> applicable, List<ModifierTemplate> modifiers) {
            super(cost, prefix, applicable);
            this.modifiers = modifiers;
        }

        @Override
        public ItemStack apply(ItemStack stack) {
            stack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, current ->
                    new ItemAttributeModifiers(Stream.concat(current.modifiers().stream(), modifiers.stream().map(entry ->
                            new ItemAttributeModifiers.Entry(entry.attribute, new AttributeModifier(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID,
                                    (stack.getHoverName().getString() + prefix + "ForgingPattern").replaceAll("[^a-z0-9/._-]", "")), entry.amount, entry.operation), entry.slotGroup))).toList()));
            return super.apply(stack);
        }
    }
}
