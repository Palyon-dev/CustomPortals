package dev.custom.portals.data;

import dev.custom.portals.CustomPortals;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class WorldPortals extends PortalComponent implements WorldSyncedComponent {
    private World world;

    public WorldPortals(World world) { this.world = world; }

    @Override
    public World getWorld() { return world; }

    @Override
    public ComponentType<?> getComponentType() {
        return (ComponentType<BasePortalComponent>)CustomPortals.PORTALS;
    }

    @Override
    public void syncWithAll(MinecraftServer server) {
        Iterable<ServerWorld> worlds = server.getWorlds();
        for (ServerWorld serverWorld : worlds) {
            CustomPortals.PORTALS.get(serverWorld).setPortalRegistry(this.getPortalRegistry());
        }
    }
}
