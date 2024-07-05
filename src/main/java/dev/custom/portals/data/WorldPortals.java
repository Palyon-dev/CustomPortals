package dev.custom.portals.data;

import dev.custom.portals.CustomPortals;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class WorldPortals extends PortalComponent implements AutoSyncedComponent {
    private World world;

    public WorldPortals(World world) { this.world = world; }

    public World getWorld() { return world; }

    @Override
    public void syncWithAll(MinecraftServer server) {
        Iterable<ServerWorld> worlds = server.getWorlds();
        for (ServerWorld serverWorld : worlds) {
            CustomPortals.PORTALS.get(serverWorld).setPortalRegistry(this.getPortalRegistry());
        }
    }
}
