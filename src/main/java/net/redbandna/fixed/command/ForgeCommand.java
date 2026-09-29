package net.redbandna.fixed.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;

import java.util.Collection;

public final class ForgeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("forge")
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.argument("targets", EntityArgument.entities())
                        .then(Commands.argument("forge_template", new ForgeTemplateArgument())
                                .executes(c -> forge(
                                        c.getSource(), EntityArgument.getEntities(c, "targets"), ForgeTemplateArgument.getTemplate(c, "forge_template"))))));
    }

    private static int forge(CommandSourceStack source, Collection<? extends Entity> targets, ForgeTemplateItem templateItem) throws CommandSyntaxException {

        int success = 0;
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity target) {
                ItemStack item = target.getMainHandItem();
                if (!item.isEmpty() && templateItem.isApplicable(item)) {
                    templateItem.getPattern(item).apply(item);
                    success++;
                }
            }
        }
        if (success == 0)
            throw new SimpleCommandExceptionType(Component.translatable("commands.forge.failed")).create();

        if (targets.size() == 1) {
            source.sendSuccess(() -> Component.translatable("commands.forge.success.single").append(targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendSuccess(() -> Component.translatable("commands.forge.success"), true);
        }

        return success;
    }
}
