package dev.custom.portals.mixin;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.util.PortalHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    public DownloadingTerrainScreenMixin(BooleanSupplier booleanSupplier, DownloadingTerrainScreen.WorldEntryReason worldEntryReason) {
        super(NarratorManager.EMPTY);
        this.shouldClose = booleanSupplier;
        this.worldEntryReason = worldEntryReason;
        this.loadStartTime = System.currentTimeMillis();
    }

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    public void renderBackground(DrawContext drawContext, int i, int j, float f, CallbackInfo ci) {
        if (PortalHelper.transitionBackgroundSpriteModel != null) {
            PortalHelper.isTransitioning = true;
            drawContext.drawSprite(0, 0, -90, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), this.client.getBlockRenderManager().getModels().getModelParticleSprite(PortalHelper.transitionBackgroundSpriteModel.getDefaultState().with(PortalBlock.LIT, true)));
            ci.cancel();
        }
    }
    @Inject(method = "close", at = @At("HEAD"))
    public void close(CallbackInfo ci) {
        PortalHelper.isTransitioning = false;
        PortalHelper.transitionBackgroundSpriteModel = null;
    }
}
