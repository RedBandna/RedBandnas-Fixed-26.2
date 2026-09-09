package net.redbandna.fixed.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.ModBlocks;
import net.redbandna.fixed.block.entity.custom.EndRelayBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<EndRelayBlockEntity> END_RELAY_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "end_relay_be"),
                    FabricBlockEntityTypeBuilder.create(EndRelayBlockEntity::new, ModBlocks.END_RELAY).build());

    public static void registerBlockEntities() {
        RedBandnaSFixed.LOGGER.info("Registering ModBlockEntities for " + RedBandnaSFixed.MOD_ID);
    }
}
