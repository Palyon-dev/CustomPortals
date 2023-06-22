package dev.custom.portals.mixin;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.stat.StatHandler;
import org.jetbrains.annotations.Nullable;
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
    private MinecraftClient client;
    @Shadow
    public float prevNauseaIntensity;
    @Shadow
    public float nauseaIntensity;

    public ClientPlayerEntityMixin(MinecraftClient minecraftClient, ClientWorld clientWorld, ClientPlayNetworkHandler clientPlayNetworkHandler, StatHandler statHandler, ClientRecipeBook clientRecipeBook, boolean bl, boolean bl2) {
        super(clientWorld, clientPlayNetworkHandler.getProfile());
    }

    @Inject(method = "updateNausea", at = @At("HEAD"), cancellable = true)
    private void updateNausea(CallbackInfo ci) {
        //if (((EntityMixinAccess)this).isInCustomPortal()) System.out.println("In custom portal.");
        //if (((EntityMixinAccess)this).getDestPortal() != null) System.out.println("Dest portal found.");
        if(((EntityMixinAccess)this).isInCustomPortal() && ((EntityMixinAccess)this).getDestPortal() != null) {
            //System.out.println("(2) Nausea Intensity is " + this.nauseaIntensity);
            this.prevNauseaIntensity = this.nauseaIntensity;
            if (!(this.client.currentScreen == null || this.client.currentScreen.shouldPause() || this.client.currentScreen instanceof DeathScreen || this.client.currentScreen instanceof DownloadingTerrainScreen)) {
                if (this.client.currentScreen instanceof HandledScreen) {
                    this.closeHandledScreen();
                }

                this.client.setScreen((Screen)null);
            }

            if (this.nauseaIntensity == 0.0F) {
                this.client.getSoundManager().play(PositionedSoundInstance.ambient(SoundEvents.BLOCK_PORTAL_TRIGGER, this.random.nextFloat() * 0.4F + 0.8F, 0.25F));
            }

            this.nauseaIntensity += 0.0125F;
            if (this.nauseaIntensity >= 1.0F) {
                this.nauseaIntensity = 1.0F;
            }
            ((EntityMixinAccess)this).notInCustomPortal();
            ci.cancel();
        }
        /*if(this.prevNauseaIntensity == 0.0F && ((EntityMixinAccess)this).getPortalColor() != 0)
            ((EntityMixinAccess)this).setPortalColor(0);*/
    }
}
