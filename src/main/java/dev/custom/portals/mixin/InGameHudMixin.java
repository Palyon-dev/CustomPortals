package dev.custom.portals.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import dev.custom.portals.CustomPortals;
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
    private void renderPortalOverlay(float f, CallbackInfo ci, Sprite sprite) {
        int color = ((EntityMixinAccess)this.client.player).getPortalColor();
        if(color != 0) {
            Block spriteModel;
            switch(color) {
                case 29: spriteModel = CustomPortals.BLACK_PORTAL;
                break;
                case 25: spriteModel = CustomPortals.BLUE_PORTAL;
                break;
                case 26: spriteModel = CustomPortals.BROWN_PORTAL;
                break;
                case 23: spriteModel = CustomPortals.CYAN_PORTAL;
                break;
                case 21: spriteModel = CustomPortals.GRAY_PORTAL;
                break;
                case 27: spriteModel = CustomPortals.GREEN_PORTAL;
                break;
                case 17: spriteModel = CustomPortals.LIGHT_BLUE_PORTAL;
                break;
                case 22: spriteModel = CustomPortals.LIGHT_GRAY_PORTAL;
                break;
                case 19: spriteModel = CustomPortals.LIME_PORTAL;
                break;
                case 16: spriteModel = CustomPortals.MAGENTA_PORTAL;
                break;
                case 15: spriteModel = CustomPortals.ORANGE_PORTAL;
                break;
                case 20: spriteModel = CustomPortals.PINK_PORTAL;
                break;
                case 24: spriteModel = CustomPortals.PURPLE_PORTAL;
                break;
                case 28: spriteModel = CustomPortals.RED_PORTAL;
                break;
                case 8: spriteModel = CustomPortals.WHITE_PORTAL;
                break;
                case 18: spriteModel = CustomPortals.YELLOW_PORTAL;
                break;
                default: spriteModel = Blocks.NETHER_PORTAL;
            }
            sprite = this.client.getBlockRenderManager().getModels().getSprite(spriteModel.getDefaultState());
            float g = sprite.getMinU();
            float h = sprite.getMinV();
            float i = sprite.getMaxU();
            float j = sprite.getMaxV();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE);
            bufferBuilder.vertex(0.0D, (double)this.scaledHeight, -90.0D).texture(g, j).next();
            bufferBuilder.vertex((double)this.scaledWidth, (double)this.scaledHeight, -90.0D).texture(i, j).next();
            bufferBuilder.vertex((double)this.scaledWidth, 0.0D, -90.0D).texture(i, h).next();
            bufferBuilder.vertex(0.0D, 0.0D, -90.0D).texture(g, h).next();
            tessellator.draw();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
