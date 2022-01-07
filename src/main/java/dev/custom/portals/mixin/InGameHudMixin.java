package dev.custom.portals.mixin;

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

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexFormat;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    @Inject(method = "renderPortalOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/Sprite;getMinU()F"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void renderPortalOverlay(float nauseaStrength, CallbackInfo ci, Sprite sprite) {
        int color = ((EntityMixinAccess)this.client.player).getPortalColor();
        if(color != 0) {
            Block spriteModel;
            switch(color) {
                case 29: spriteModel = CPBlocks.BLACK_PORTAL;
                break;
                case 25: spriteModel = CPBlocks.BLUE_PORTAL;
                break;
                case 26: spriteModel = CPBlocks.BROWN_PORTAL;
                break;
                case 23: spriteModel = CPBlocks.CYAN_PORTAL;
                break;
                case 21: spriteModel = CPBlocks.GRAY_PORTAL;
                break;
                case 27: spriteModel = CPBlocks.GREEN_PORTAL;
                break;
                case 17: spriteModel = CPBlocks.LIGHT_BLUE_PORTAL;
                break;
                case 22: spriteModel = CPBlocks.LIGHT_GRAY_PORTAL;
                break;
                case 19: spriteModel = CPBlocks.LIME_PORTAL;
                break;
                case 16: spriteModel = CPBlocks.MAGENTA_PORTAL;
                break;
                case 15: spriteModel = CPBlocks.ORANGE_PORTAL;
                break;
                case 20: spriteModel = CPBlocks.PINK_PORTAL;
                break;
                case 24: spriteModel = CPBlocks.PURPLE_PORTAL;
                break;
                case 28: spriteModel = CPBlocks.RED_PORTAL;
                break;
                case 8: spriteModel = CPBlocks.WHITE_PORTAL;
                break;
                case 18: spriteModel = CPBlocks.YELLOW_PORTAL;
                break;
                default: spriteModel = Blocks.NETHER_PORTAL;
            }
            sprite = this.client.getBlockRenderManager().getModels().getModelParticleSprite(spriteModel.getDefaultState().with(PortalBlock.LIT, true));
            float f = sprite.getMinU();
            float g = sprite.getMinV();
            float h = sprite.getMaxU();
            float i = sprite.getMaxV();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
            bufferBuilder.vertex(0.0D, (double)this.scaledHeight, -90.0D).texture(f, i).next();
            bufferBuilder.vertex((double)this.scaledWidth, (double)this.scaledHeight, -90.0D).texture(h, i).next();
            bufferBuilder.vertex((double)this.scaledWidth, 0.0D, -90.0D).texture(h, g).next();
            bufferBuilder.vertex(0.0D, 0.0D, -90.0D).texture(f, g).next();
            tessellator.draw();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
