package dev.custom.portals.mixin;

import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
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

    @Inject(method = "renderPortalOverlay",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;client:Lnet/minecraft/client/MinecraftClient;", opcode = Opcodes.GETFIELD),
            locals = LocalCapture.NO_CAPTURE,
            cancellable = true
    )
    private void renderPortalOverlay(MatrixStack matrices, float nauseaStrength, CallbackInfo ci) {
        int color = (this.client.player != null) ? ((EntityMixinAccess)this.client.player).getPortalColor() : 0;
        if (color != 0) {
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
                case 8  -> CPBlocks.WHITE_PORTAL;
                case 18 -> CPBlocks.YELLOW_PORTAL;
                default -> Blocks.NETHER_PORTAL;
            };
            Sprite sprite = this.client.getBlockRenderManager().getModels().getModelParticleSprite(spriteModel.getDefaultState().with(PortalBlock.LIT, true));
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
