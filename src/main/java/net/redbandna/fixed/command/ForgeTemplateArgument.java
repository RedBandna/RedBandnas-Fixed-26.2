package net.redbandna.fixed.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;

import java.util.concurrent.CompletableFuture;

public class ForgeTemplateArgument implements ArgumentType<ForgeTemplateItem> {

    public static ForgeTemplateItem getTemplate(final CommandContext<?> context, final String name) {
        return context.getArgument(name, ForgeTemplateItem.class);
    }

    @Override
    public ForgeTemplateItem parse(StringReader reader) throws CommandSyntaxException {
        return (ForgeTemplateItem) BuiltInRegistries.ITEM.get(Identifier.read(reader)).get().value();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {

        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof ForgeTemplateItem) builder.suggest(BuiltInRegistries.ITEM.getKey(item).toString());
        });
        return builder.buildFuture();
    }
}
