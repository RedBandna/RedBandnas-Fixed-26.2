package net.redbandna.fixed;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.resources.Identifier;

import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.command.ForgeCommand;
import net.redbandna.fixed.command.ForgeTemplateArgument;
import net.redbandna.fixed.creativemodetab.ModCreativeModeTabs;
import net.redbandna.fixed.attribute.ModAttributes;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.effect.ModEffects;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.item.forging.ModForgePatterns;
import net.redbandna.fixed.menu.ModMenuTypes;
import net.redbandna.fixed.worldgen.custom.ModFeatures;
import net.redbandna.fixed.worldgen.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedBandnaSFixed implements ModInitializer {
	public static final String MOD_ID = "rbfixed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModCreativeModeTabs.registerCreativeModeTabs();
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModAttributes.registerAttributes();
		ModDataComponents.registerDataComponents();
		ModEffects.registerEffects();
		ModForgePatterns.registerForgePatterns();
		ModBlockEntities.registerBlockEntities();
		ModMenuTypes.registerMenuTypes();
		ModFeatures.registerFeatures();
		ModWorldGeneration.generateWorldGen();

		ArgumentTypeRegistry.registerArgumentType(id("forge_template"), ForgeTemplateArgument.class, SingletonArgumentInfo.contextFree(ForgeTemplateArgument::new));
		CommandRegistrationCallback.EVENT.register((dispatcher, context, environment) -> {
			ForgeCommand.register(dispatcher, context);
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
