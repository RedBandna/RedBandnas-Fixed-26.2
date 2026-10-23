package net.redbandna.fixed.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;
import net.redbandna.fixed.item.forging.ForgePattern;

import java.util.List;
import java.util.stream.Stream;

public class ForgedFunction extends LootItemConditionalFunction {

    public static final MapCodec<ForgedFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> commonFields(i).and(Item.CODEC.listOf().fieldOf("patterns").forGetter(f -> f.patterns)).apply(i, ForgedFunction::new));
    private final List<Holder<Item>> patterns;

    protected ForgedFunction(List<LootItemCondition> predicates, List<Holder<Item>> patterns) {
        super(predicates);
        this.patterns = patterns;
    }

    @Override
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    protected ItemStack run(ItemStack item, LootContext context) {
        List<ForgePattern> applicable = patterns.stream().flatMap(h -> ((ForgeTemplateItem) h.value()).isApplicable(item) ?
                Stream.of(((ForgeTemplateItem) h.value()).getPattern(item)) : Stream.empty()).toList();
        applicable.get(context.getRandom().nextInt(applicable.size())).apply(item);
        return item;
    }

    public static LootItemConditionalFunction.Builder<?> builder(List<Item> value) {
        return simpleBuilder((conditions) -> new ForgedFunction(conditions, value.stream().map(Holder::direct).toList()));
    }
}
