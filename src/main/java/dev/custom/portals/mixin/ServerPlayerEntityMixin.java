package dev.custom.portals.mixin;

import java.util.Iterator;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.custom.portals.util.EntityMixinAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.biome.source.BiomeAccess;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow
    private boolean inTeleportationState;
    @Shadow
    public ServerPlayNetworkHandler networkHandler;
    @Final
    @Shadow
    public ServerPlayerInteractionManager interactionManager;
    @Final
    @Shadow
    public MinecraftServer server;
    @Shadow
    private float syncedHealth;
    @Shadow
    private int syncedExperience;
    @Shadow
    private int syncedFoodLevel;

    @Shadow
    public abstract ServerWorld getServerWorld();
    @Shadow
    protected abstract void worldChanged(ServerWorld origin);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    /* This is necessary to avoid unwanted side effects from using the normal moveToWorld(),
     * such as how moving from the end to the overworld causes the credits to play and
     * resets the player's position to the world spawn. Most of this is simply copied from
     * vanilla code.
     */
    @Inject(method = "moveToWorld", at = @At("HEAD"), cancellable = true)
    public void moveToWorld(ServerWorld destination, CallbackInfoReturnable<Entity> ci) {
        this.inTeleportationState = true;
        ServerWorld serverWorld = this.getServerWorld();
        if (((EntityMixinAccess)this).isInCustomPortal()) {
            WorldProperties worldProperties = destination.getLevelProperties();
            this.networkHandler.sendPacket(new PlayerRespawnS2CPacket(destination.getDimension(), destination.getRegistryKey(), BiomeAccess.hashSeed(destination.getSeed()), this.interactionManager.getGameMode(), this.interactionManager.method_30119(), destination.isDebugWorld(), destination.isFlat(), true));
            this.networkHandler.sendPacket(new DifficultyS2CPacket(worldProperties.getDifficulty(), worldProperties.isDifficultyLocked()));
            PlayerManager playerManager = this.server.getPlayerManager();
            playerManager.sendCommandTree((ServerPlayerEntity)(Object)this);
            serverWorld.removePlayer((ServerPlayerEntity)(Object)this);
            this.removed = false;
            TeleportTarget teleportTarget = this.getTeleportTarget(destination);
            if (teleportTarget != null) {
                serverWorld.getProfiler().push("moving");
                serverWorld.getProfiler().pop();
                serverWorld.getProfiler().push("placing");
                this.setWorld(destination);
                destination.onPlayerChangeDimension((ServerPlayerEntity)(Object)this);
                this.setRotation(teleportTarget.yaw, teleportTarget.pitch);
                this.refreshPositionAfterTeleport(teleportTarget.position.x, teleportTarget.position.y, teleportTarget.position.z);
                serverWorld.getProfiler().pop();
                this.worldChanged(serverWorld);
                this.interactionManager.setWorld(destination);
                this.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(this.abilities));
                playerManager.sendWorldInfo((ServerPlayerEntity)(Object)this, destination);
                playerManager.sendPlayerStatus((ServerPlayerEntity)(Object)this);
                Iterator<StatusEffectInstance> var7 = this.getStatusEffects().iterator();

                while(var7.hasNext()) {
                    StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var7.next();
                    this.networkHandler.sendPacket(new EntityStatusEffectS2CPacket(this.getEntityId(), statusEffectInstance));
                }

                this.networkHandler.sendPacket(new WorldEventS2CPacket(1032, BlockPos.ORIGIN, 0, false));
                this.syncedExperience = -1;
                this.syncedHealth = -1.0F;
                this.syncedFoodLevel = -1;
            }
            ci.setReturnValue((ServerPlayerEntity)(Object)this);
        }
    }
}
