package com.joaocardosodev.telecraft.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.joaocardosodev.telecraft.Telecraft.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> RAW_TIN = ModCreativeTabs.addToTab(ITEMS.register("raw_tin", () ->
            new Item(new Item.Properties())));

    public static final RegistryObject<Item> TIN_INGOT = ModCreativeTabs.addToTab(ITEMS.register("tin_ingot", () ->
            new Item(new Item.Properties())));

    public static final RegistryObject<BlockItem> LEYDEN_JAR_BLOCK_ITEM = ModCreativeTabs.addToTab(ITEMS.register("leyden_jar", () ->
            new BlockItem(ModBlocks.LEYDEN_JAR.get(),new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
            )));

    public static final RegistryObject<BlockItem> TIN_ORE_BLOCK_ITEM = ModCreativeTabs.addToTab(ITEMS.register("tin_ore", () ->
            new BlockItem(ModBlocks.TIN_ORE.get(), new Item.Properties())
            ));
}
