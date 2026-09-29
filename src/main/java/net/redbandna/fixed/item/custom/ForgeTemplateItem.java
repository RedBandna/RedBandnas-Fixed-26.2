package net.redbandna.fixed.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.redbandna.fixed.item.forging.ForgePattern;

import java.util.List;
import java.util.function.Consumer;

public class ForgeTemplateItem extends Item {
    public final List<ForgePattern> patterns;

    public ForgeTemplateItem(Properties properties, List<ForgePattern> patterns) {
        super(properties);
        this.patterns = patterns;
    }

    public boolean isApplicable(ItemStack item) {
        return patterns.stream().anyMatch(pattern -> item.is(pattern.applicable));
    }

    public ForgePattern getPattern(ItemStack item) {
        return patterns.stream().filter(pattern -> item.is(pattern.applicable)).findFirst().get();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.translatable("item.forging_template").withColor(TextColor.GRAY));
        builder.accept(CommonComponents.EMPTY);
        builder.accept(Component.translatable("item.forging_template.applies_to").withColor(TextColor.GRAY));
        patterns.forEach(pattern -> {
            builder.accept(CommonComponents.space().append(Component.translatable("item.forging_template." + pattern.applicable.location().getPath().replace('/', '.')).withColor(TextColor.BLUE)));
        });
    }
}
