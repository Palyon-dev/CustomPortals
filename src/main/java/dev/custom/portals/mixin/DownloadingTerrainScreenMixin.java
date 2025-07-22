package dev.custom.portals.mixin;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.util.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(DownloadingTerrainScreen.class)
public abstract class DownloadingTerrainScreenMixin extends Screen {

    @Shadow
    private final BooleanSupplier shouldClose;
    @Shadow
    private final DownloadingTerrainScreen.WorldEntryReason worldEntryReason;
    @Shadow
    private final long loadStartTime;

    @Unique
    private boolean packetSent = false;

    public DownloadingTerrainScreenMixin(BooleanSupplier booleanSupplier, DownloadingTerrainScreen.WorldEntryReason worldEntryReason) {
        super(NarratorManager.EMPTY);
        this.shouldClose = booleanSupplier;
        this.worldEntryReason = worldEntryReason;
        this.loadStartTime = System.currentTimeMillis();
    }

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    public void renderBackground(DrawContext drawContext, int i, int j, float f, CallbackInfo ci) {
        if (ClientUtil.transitionBackgroundSpriteModel != null) {
            ClientUtil.isTransitioning = true;
            if (!packetSent) {
                ClientPlayNetworking.send(new ScreenTransitionPayload(true));
                packetSent = true;
            }
            drawContext.drawSpriteStretched(RenderPipelines.GUI_OPAQUE_TEX_BG, this.client.getBlockRenderManager().getModels().getModelParticleSprite(ClientUtil.transitionBackgroundSpriteModel.getDefaultState().with(PortalBlock.LIT, true)), 0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight());
            ci.cancel();
        }
    }
    @Inject(method = "close", at = @At("HEAD"))
    public void close(CallbackInfo ci) {
        ClientUtil.isTransitioning = false;
        ClientPlayNetworking.send(new ScreenTransitionPayload(false));
        ClientUtil.transitionBackgroundSpriteModel = null;
        packetSent = false;
    }
}
