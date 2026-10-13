package net.redbandna.fixed.command;

import com.llamalad7.mixinextras.lib.apache.commons.StringUtils;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

public class ForgeTemplateArgument implements ArgumentType<ForgeTemplateItem> {

    public static ForgeTemplateItem getTemplate(final CommandContext<?> context, final String name) {
        return context.getArgument(name, ForgeTemplateItem.class);
    }

    @Override
    public ForgeTemplateItem parse(StringReader reader) throws NoSuchElementException {
        int start = reader.getCursor();
        while (reader.canRead() && Identifier.isAllowedInIdentifier(reader.peek())) reader.skip();

        return (ForgeTemplateItem) BuiltInRegistries.ITEM.get(Identifier.parse(RedBandnaSFixed.MOD_ID + ':' + reader.getString().substring(start, reader.getCursor()) + "_forging_template")).get().value();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof ForgeTemplateItem)
                builder.suggest(StringUtils.removeEnd(StringUtils.removeStart(BuiltInRegistries.ITEM.getKey(item).toString(), RedBandnaSFixed.MOD_ID + ':'), "_forging_template"));
        });
        return builder.buildFuture();
    }
}
