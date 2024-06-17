package com.joaocardosodev.telecraft.init;

import com.joaocardosodev.telecraft.block.TestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.joaocardosodev.telecraft.Telecraft.MODID;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new TestBlock(
            (BlockBehaviour.Properties.copy(Blocks.GLASS))
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(2f)
                    .instrument(NoteBlockInstrument.BELL)
                    .lightLevel(state -> 10)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isViewBlocking((state,world,pos) -> false)
    ));
}
