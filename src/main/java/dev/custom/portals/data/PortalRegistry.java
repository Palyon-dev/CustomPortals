package dev.custom.portals.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.util.math.BlockPos;

public class PortalRegistry {

    private List<Portal> portals;
    private HashMap<BlockPos, Portal> portalPositions;

    public PortalRegistry() {
        portals = new ArrayList<Portal>();
        portalPositions = new HashMap<BlockPos, Portal>();
    }
    
    public void register(Portal portal) {
        for (Portal p : portals) {
            portal.tryLink(p);
        }
        for (BlockPos blockPos : portal.getPortalBlocks()) {
            portalPositions.put(blockPos, portal);
        }
        portals.add(portal);
    }

    public void unregister(Portal portal) {
        portals.remove(portal);
        if(portal.hasLinked()) {
            portal.getLinked().setLinked(null);
            for (Portal p : portals) {
                portal.getLinked().tryLink(p);
            }
        }
        for (BlockPos blockPos : portal.getPortalBlocks()) {
            portalPositions.remove(blockPos);
        }
    }

    public List<Portal> getPortals() { return portals; }

    public void clear() { portals.clear(); }

    public Portal getPortalFromPos(BlockPos pos) {
        return portalPositions.get(pos);
    }
}
