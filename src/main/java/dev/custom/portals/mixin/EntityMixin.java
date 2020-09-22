package dev.custom.portals.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.custom.portals.util.EntityMixinAccess;
import dev.custom.portals.data.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityMixinAccess {
    
    @Unique
    private int portalColor;
    @Unique
    private boolean inCustomPortal;
    @Unique
    private Portal destPortal;
    @Unique
    private int customPortalTime;

    @Shadow
    public World world;
    @Shadow
    protected BlockPos lastNetherPortalPosition;
    @Shadow
    public float yaw;

    @Shadow
    public abstract boolean method_30230(); // returns true if netherPortalCooldown > 0, false otherwise
    @Shadow
    public abstract void method_30229(); // sets netherPortalCooldown to value returned by getDefaultNetherPortalCooldown()
    @Shadow
    public abstract int getMaxNetherPortalTime();
    @Shadow
    protected abstract void tickNetherPortalCooldown();
    @Shadow
    public abstract void teleport(double destX, double destY, double destZ);
    @Shadow
    public abstract boolean hasVehicle();
    @Shadow
    public abstract Entity moveToWorld(ServerWorld destination);
    @Shadow 
    public abstract Vec3d getVelocity();
    @Shadow
    public abstract void setYaw(float yaw);

    @Inject(method = "baseTick", at = @At("TAIL"))
    public void baseTick(CallbackInfo ci) {
        this.tickCustomPortal();
    }

    @Inject(method = "refreshPositionAndAngles(DDDFF)V", at = @At("TAIL"))
    public void refreshPositionAndAngles(double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        if(this.inCustomPortal)
            this.yaw = 0;
    }

    @Unique
    public void setInCustomPortal(Portal portal) {
        if (this.method_30230()) {
            this.method_30229();
        } else {
            this.destPortal = portal.getLinked();
            this.inCustomPortal = true;
            this.portalColor = portal.getColor().id;
        }
    }

    @Unique
    public void tickCustomPortal() {
        if (this.world instanceof ServerWorld) {
            int i = this.getMaxNetherPortalTime();
            ServerWorld serverWorld = (ServerWorld)this.world;
            if (this.inCustomPortal) {
                MinecraftServer minecraftServer = serverWorld.getServer();
                if (!this.hasVehicle() && this.customPortalTime++ >= i && this.destPortal != null) {
                    this.world.getProfiler().push("portal");
                    BlockPos dest = destPortal.getSpawnPos();
                    double destX = dest.getX();
                    double destY = dest.getY();
                    double destZ = dest.getZ();
                    if(destPortal.length == 0)
                        destX += 0.5;
                    if(destPortal.width == 0)
                        destZ += 0.5;
                    if(destPortal.length % 2 != 0)
                        destX += 0.5;
                    if(destPortal.width % 2 != 0) 
                        destZ -= 0.5;
                    this.customPortalTime = i;
                    this.method_30229();
                    String destDimensionId = destPortal.getDimensionId();
                    if(!destDimensionId.equals(this.world.getRegistryKey().getValue().toString())) {
                        for(RegistryKey<World> registryKey : minecraftServer.getWorldRegistryKeys()) {
                            if(registryKey.getValue().toString().equals(destDimensionId)) {
                                ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
                                if(serverWorld2 != null) {
                                    this.lastNetherPortalPosition = destPortal.getLinked().getSpawnPos();
                                    this.moveToWorld(serverWorld2);
                                }
                            }
                        }
                    }
                    this.teleport(destX, destY, destZ);
                    this.world.getProfiler().pop();
                }
                this.inCustomPortal = false;
                this.portalColor = 0;
                this.destPortal = null;
            } else {
                if (this.customPortalTime > 0) {
                    this.customPortalTime -= 4;
                }
  
                if (this.customPortalTime < 0) {
                    this.customPortalTime = 0;
                }
            }
  
           this.tickNetherPortalCooldown();
        }
    }

    @Unique
    public Portal getDestPortal() { return destPortal; }

    @Unique
    public boolean isInCustomPortal() { return inCustomPortal; }
    
    @Unique
    public void notInCustomPortal() { inCustomPortal = false; }

    @Unique
    public int getPortalColor() { return portalColor; }

    @Unique 
    public void setPortalColor(int color) { this.portalColor = color; }

}
