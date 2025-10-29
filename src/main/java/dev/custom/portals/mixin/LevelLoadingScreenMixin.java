package dev.custom.portals.mixin;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.util.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.LevelLoadingScreen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.world.ClientChunkLoadProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(LevelLoadingScreen.class)
public abstract class LevelLoadingScreenMixin extends Screen {
    @Shadow
    private LevelLoadingScreen.WorldEntryReason reason;
    @Shadow
    private ClientChunkLoadProgress chunkLoadProgress;

    @Unique
    private boolean packetSent = false;

    public LevelLoadingScreenMixin(ClientChunkLoadProgress clientChunkLoadProgress, LevelLoadingScreen.WorldEntryReason worldEntryReason) {
        super(NarratorManager.EMPTY);
        this.chunkLoadProgress = clientChunkLoadProgress;
        this.reason = worldEntryReason;
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
