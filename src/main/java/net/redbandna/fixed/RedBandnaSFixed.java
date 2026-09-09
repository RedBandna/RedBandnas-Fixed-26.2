package net.redbandna.fixed;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.creativemodetab.ModCreativeModeTabs;
import net.redbandna.fixed.item.ModItems;
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
		ModBlockEntities.registerBlockEntities();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
