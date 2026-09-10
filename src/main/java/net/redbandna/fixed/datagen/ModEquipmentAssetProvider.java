package net.redbandna.fixed.datagen;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.custom.ModArmorMaterials;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(ModArmorMaterials.ROSE_QUARTZ_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "rose_quartz"))
                .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY,
                        new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "rose_quartz"))).build());
    }

    @Override
    public CompletableFuture<?> run(final CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @Override
    public String getName() {
        return "RedBandna's Fixed Equipment Asset Definition";
    }
}
