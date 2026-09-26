package net.redbandna.fixed.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.item.forging.ForgePattern;

import java.util.List;

public class ForgeTemplateItem extends Item {
    public final List<ForgePattern> patterns;
    public ForgeTemplateItem(Properties properties, ForgePattern pattern) {
        this(properties, List.of(pattern));
    }
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
}
