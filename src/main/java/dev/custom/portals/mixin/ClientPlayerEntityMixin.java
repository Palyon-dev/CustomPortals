package dev.custom.portals.mixin;

import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.stat.StatHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundEvents;

import dev.custom.portals.util.EntityMixinAccess;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Shadow
    @Final
    protected MinecraftClient client;
    @Shadow
    public float lastNauseaStrength;
    @Shadow
    public float nextNauseaStrength;

    public ClientPlayerEntityMixin(MinecraftClient minecraftClient, ClientWorld clientWorld, ClientPlayNetworkHandler clientPlayNetworkHandler, StatHandler statHandler, ClientRecipeBook clientRecipeBook, boolean bl, boolean bl2) {
        super(clientWorld, clientPlayNetworkHandler.getProfile());
    }

    @Inject(method = "updateNausea", at = @At("HEAD"), cancellable = true)
    private void updateNausea(CallbackInfo ci) {
        if(((EntityMixinAccess)this).isInCustomPortal() && ((EntityMixinAccess)this).getDestPortal() != null) {
            this.lastNauseaStrength = this.nextNauseaStrength;
            if (!(this.client.currentScreen == null || this.client.currentScreen.shouldPause() || this.client.currentScreen instanceof DeathScreen || this.client.currentScreen instanceof DownloadingTerrainScreen)) {
                if (this.client.currentScreen instanceof HandledScreen) {
                    this.closeHandledScreen();
                }

                this.client.setScreen((Screen)null);
            }

            if (this.nextNauseaStrength == 0.0F) {
                this.client.getSoundManager().play(PositionedSoundInstance.ambient(SoundEvents.BLOCK_PORTAL_TRIGGER, this.random.nextFloat() * 0.4F + 0.8F, 0.25F));
            }

            this.nextNauseaStrength += 0.0125F;
            if (this.nextNauseaStrength >= 1.0F) {
                this.nextNauseaStrength = 1.0F;
            }
            ((EntityMixinAccess)this).notInCustomPortal();
            ci.cancel();
        }
        if(this.lastNauseaStrength == 0.0F && ((EntityMixinAccess)this).getPortalColor() != 0)
            ((EntityMixinAccess)this).setPortalColor(0);
    }
}
