package dev.custom.portals.mixin;

import dev.custom.portals.util.PortalHelper;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.util.EntityMixinAccess;

import org.spongepowered.asm.mixin.Final;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.block.Block;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void renderPortalOverlay(DrawContext drawContext, float f, CallbackInfo ci) {
        int color = ((EntityMixinAccess)this.client.player).getPortalColor();
        if(color != 0 && !((EntityMixinAccess)this.client.player).isInNetherPortal()) {
            Block spriteModel = PortalHelper.getPortalBlockFromColorId(color);
            if (f < 1.0F) {
                f *= f;
                f *= f;
                f = f * 0.8F + 0.2F;
            }

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            drawContext.setShaderColor(1.0F, 1.0F, 1.0F, f);
            Sprite sprite = this.client.getBlockRenderManager().getModels().getModelParticleSprite(spriteModel.getDefaultState().with(PortalBlock.LIT, true));
            drawContext.drawSprite(0, 0, -90, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), sprite);
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
