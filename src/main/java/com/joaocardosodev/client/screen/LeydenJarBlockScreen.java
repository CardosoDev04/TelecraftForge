package com.joaocardosodev.client.screen;


import com.joaocardosodev.telecraft.Telecraft;
import com.joaocardosodev.telecraft.blockentity.LeydenJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class LeydenJarBlockScreen extends Screen {


    public static final Component TITLE = Component.translatable("gui."+ Telecraft.MODID + ".leyden_jar_block_screen");
    public static final Component EXAMPLE_BUTTON_TEXT = Component.translatable("gui." + Telecraft.MODID + ".leyden_jar_block_screen");

    private static final ResourceLocation BACKGROUND_TEXTURE =
            new ResourceLocation(Telecraft.MODID, "textures/gui/leyden_jar_background.png");

    private final BlockPos position;
    private final int imageWidth, imageHeight;

    private LeydenJarBlockEntity blockEntity;
    private int leftPos,topPos;

    private Button button;

    public LeydenJarBlockScreen(BlockPos pos) {
        super(TITLE);
        this.position = pos;
        this.imageWidth  = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();


        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        Level level = this.minecraft.level;

        if(level == null) return;
        BlockEntity be = level.getBlockEntity(this.position);

        if(be instanceof LeydenJarBlockEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            return;
        }

        this.button = addRenderableWidget(
                Button.builder(
                        EXAMPLE_BUTTON_TEXT,
                        this::handleExampleButton
                )
                        .bounds(this.leftPos + 8, this.topPos + 20,20,20)
                        .tooltip(Tooltip.create(EXAMPLE_BUTTON_TEXT))
                        .build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0,0,this.imageWidth,this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.drawString(this.font, TITLE,this.leftPos + 8, this.topPos + 8, 0x404040,false);

        graphics.drawString(this.font,"Seconds existed: %d".formatted(this.blockEntity.getSecondsExisted()),this.leftPos + 40, this.topPos + 20, 0xFF0000,false);
    }

    private void handleExampleButton(Button btn) {

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
