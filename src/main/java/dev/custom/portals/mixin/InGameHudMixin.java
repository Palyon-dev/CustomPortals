package dev.custom.portals.mixin;

import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.registry.CPBlocks;
import dev.custom.portals.util.EntityMixinAccess;

import org.spongepowered.asm.mixin.Final;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexFormat;

import javax.sound.sampled.Port;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void renderPortalOverlay(DrawContext drawContext, float f, CallbackInfo ci) {
        int color = ((EntityMixinAccess)this.client.player).getPortalColor();
        if(color != 0) {
            Block spriteModel = switch (color) {
                case 29 -> CPBlocks.BLACK_PORTAL;
                case 25 -> CPBlocks.BLUE_PORTAL;
                case 26 -> CPBlocks.BROWN_PORTAL;
                case 23 -> CPBlocks.CYAN_PORTAL;
                case 21 -> CPBlocks.GRAY_PORTAL;
                case 27 -> CPBlocks.GREEN_PORTAL;
                case 17 -> CPBlocks.LIGHT_BLUE_PORTAL;
                case 22 -> CPBlocks.LIGHT_GRAY_PORTAL;
                case 19 -> CPBlocks.LIME_PORTAL;
                case 16 -> CPBlocks.MAGENTA_PORTAL;
                case 15 -> CPBlocks.ORANGE_PORTAL;
                case 20 -> CPBlocks.PINK_PORTAL;
                case 24 -> CPBlocks.PURPLE_PORTAL;
                case 28 -> CPBlocks.RED_PORTAL;
                case 8 -> CPBlocks.WHITE_PORTAL;
                case 18 -> CPBlocks.YELLOW_PORTAL;
                default -> Blocks.NETHER_PORTAL;
            };
            if (f < 1.0F) {
                f *= f;
                f *= f;
                f = f * 0.8F + 0.2F;
            }

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            drawContext.setShaderColor(1.0F, 1.0F, 1.0F, f);
            Sprite sprite = this.client.getBlockRenderManager().getModels().getModelParticleSprite(spriteModel.getDefaultState().with(PortalBlock.LIT, true));
            drawContext.drawSprite(0, 0, -90, this.scaledWidth, this.scaledHeight, sprite);
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
