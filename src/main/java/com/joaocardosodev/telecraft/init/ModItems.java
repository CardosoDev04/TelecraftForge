package com.joaocardosodev.telecraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.joaocardosodev.telecraft.Telecraft.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ModCreativeTabs.addToTab(ITEMS.register("example_item", () ->
            new Item(new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = ModCreativeTabs.addToTab(ITEMS.register("example_block", () ->
            new BlockItem(ModBlocks.EXAMPLE_BLOCK.get(),new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
            )));

    public static final RegistryObject<BlockItem> EXAMPLE_ORE_BLOCK_ITEM = ModCreativeTabs.addToTab(ITEMS.register("example_ore", () ->
            new BlockItem(ModBlocks.EXAMPLE_ORE.get(), new Item.Properties())
            ));
}
