package com.joaocardosodev.telecraft.init;

import com.joaocardosodev.telecraft.Telecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.joaocardosodev.telecraft.Telecraft.MODID;
import static com.joaocardosodev.telecraft.init.ModTabs.CREATIVE_MODE_TABS;


@Mod.EventBusSubscriber(modid= MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    public static final List<Supplier<? extends ItemLike>> TELECRAFT_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> TELECRAFT_TAB = CREATIVE_MODE_TABS.register("telecraft_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.telecraft_tab"))
                    .icon(ModItems.EXAMPLE_BLOCK_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams,output) -> {
                        TELECRAFT_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build()
            );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        TELECRAFT_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
