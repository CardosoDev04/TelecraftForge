package com.joaocardosodev.telecraft.init;

import com.joaocardosodev.telecraft.block.TestBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
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
                    .isViewBlocking((state, world, pos) -> false)
    ));

    public static final RegistryObject<DropExperienceBlock> TIN_ORE = BLOCKS.register("tin_ore", () -> new DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)
                    .mapColor(MapColor.COLOR_GRAY),
                    UniformInt.of(1, 3)
    ));
}
