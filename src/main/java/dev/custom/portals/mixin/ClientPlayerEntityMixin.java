package dev.custom.portals.mixin;

import com.mojang.authlib.GameProfile;

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
    public float lastNauseaStrength;
    @Shadow
    public float nextNauseaStrength;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "updateNausea", at = @At("HEAD"), cancellable = true)
    private void updateNausea(CallbackInfo ci) {
        if(((EntityMixinAccess)this).isInCustomPortal() && ((EntityMixinAccess)this).getDestPortal() != null) {
            this.lastNauseaStrength = this.nextNauseaStrength;
            if (this.client.currentScreen != null && !this.client.currentScreen.isPauseScreen()) {
                if (this.client.currentScreen instanceof HandledScreen) {
                    this.closeHandledScreen();
                }

                this.client.openScreen((Screen)null);
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
