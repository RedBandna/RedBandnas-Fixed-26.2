package net.redbandna.fixed;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.creativemodetab.ModCreativeModeTabs;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.effect.ModEffects;
import net.redbandna.fixed.item.ModItems;
import net.redbandna.fixed.menu.ModeMenuTypes;
import net.redbandna.fixed.worldgen.ModFeatures;
import net.redbandna.fixed.worldgen.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedBandnaSFixed implements ModInitializer {
	public static final String MOD_ID = "rbfixed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponents.registerDataComponents();
		ModEffects.registerEffects();
		ModBlockEntities.registerBlockEntities();
		ModeMenuTypes.registerModMenuTypes();
		ModFeatures.registerModFeatures();
		ModWorldGeneration.generateModWorldGen();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
