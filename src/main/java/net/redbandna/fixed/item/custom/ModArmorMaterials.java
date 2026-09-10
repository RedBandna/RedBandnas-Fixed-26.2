package net.redbandna.fixed.item.custom;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.tags.ModTags;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> ROSE_QUARTZ_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "rose_quartz"));

    public static final ArmorMaterial ROSE_QUARTZ = new ArmorMaterial(238,
            ArmorMaterials.makeDefense(2, 4, 6, 2, 10),
            20, SoundEvents.ARMOR_EQUIP_DIAMOND, 0, 0, ModTags.Items.ROSE_QUARTZ_REPAIR, ROSE_QUARTZ_KEY);

}
