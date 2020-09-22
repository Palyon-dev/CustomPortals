package dev.custom.portals.data;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public interface BasePortalComponent extends Component {
    PortalRegistry getPortalRegistry();
    Portal getPortalFromPos(BlockPos pos);
    void setPortalRegistry(PortalRegistry portalRegistry);
    void registerPortal(Portal portal);
    void unregisterPortal(Portal portal);
    void clearPortals();
    void syncWithAll(MinecraftServer server);
}
