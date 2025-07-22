package dev.custom.portals.mixin;

import java.util.Iterator;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.PlayerPosition;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.custom.portals.util.EntityMixinAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow
    private boolean inTeleportationState;
    @Shadow
    public ServerPlayNetworkHandler networkHandler;
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
    public abstract ServerWorld getWorld();
    @Shadow
    protected abstract void worldChanged(ServerWorld serverWorld);
    @Shadow
    public abstract void setServerWorld(ServerWorld world);
    @Shadow
    public abstract CommonPlayerSpawnInfo createCommonPlayerSpawnInfo(ServerWorld serverWorld);

    public ServerPlayerEntityMixin(MinecraftServer minecraftServer, ServerWorld serverWorld, GameProfile gameProfile) {
        super(serverWorld, gameProfile);
    }

    /* This is necessary to avoid unwanted side effects from using the normal teleportTo(),
     * such as how moving from the end to the overworld causes the credits to play and
     * resets the player's position to the world spawn. Most of this is simply copied from
     * vanilla code.
     */
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true)
    public void telportTo(TeleportTarget teleportTarget, CallbackInfoReturnable<Entity> cir) {
        if (this.isRemoved())
            cir.setReturnValue(null);
        ServerWorld serverWorld = teleportTarget.world();
        ServerWorld serverWorld2 = this.getWorld();
        RegistryKey<World> registryKey = serverWorld2.getRegistryKey();
        if (((EntityMixinAccess)this).isInCustomPortal()) {
            ServerPlayerEntity thisPlayer = (ServerPlayerEntity)(Object)this;
            this.inTeleportationState = true;
            WorldProperties worldProperties = serverWorld.getLevelProperties();
            this.networkHandler.sendPacket(new PlayerRespawnS2CPacket(this.createCommonPlayerSpawnInfo(serverWorld), (byte)3));
            this.networkHandler.sendPacket(new DifficultyS2CPacket(worldProperties.getDifficulty(), worldProperties.isDifficultyLocked()));
            PlayerManager playerManager = this.server.getPlayerManager();
            playerManager.sendCommandTree(thisPlayer);
            serverWorld2.removePlayer(thisPlayer, RemovalReason.CHANGED_DIMENSION);
            this.unsetRemoved();
            Profiler profiler = Profilers.get();
            profiler.push("moving");
            profiler.pop();
            profiler.push("placing");
            this.setServerWorld(serverWorld);
            this.networkHandler.requestTeleport(PlayerPosition.fromTeleportTarget(teleportTarget), teleportTarget.comp_3183());
            this.networkHandler.syncWithPlayerPosition();
            serverWorld.onDimensionChanged(thisPlayer);
            profiler.pop();
            this.worldChanged(serverWorld2);
            this.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(this.getAbilities()));
            playerManager.sendWorldInfo(thisPlayer, serverWorld);
            playerManager.sendPlayerStatus(thisPlayer);
            playerManager.sendStatusEffects(thisPlayer);
            // Apparently this line means "play the teleport sound effect." Minecraft try to have readable code challenge (impossible)
            this.networkHandler.sendPacket(new WorldEventS2CPacket(1032, BlockPos.ORIGIN, 0, false));
            this.syncedExperience = -1;
            this.syncedHealth = -1.0F;
            this.syncedFoodLevel = -1;
            cir.setReturnValue(thisPlayer);
        }
    }
}
