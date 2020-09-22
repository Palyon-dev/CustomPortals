package dev.custom.portals.util;

import dev.custom.portals.data.Portal;

public interface EntityMixinAccess {
    
    public void setInCustomPortal(Portal portal);
    public void tickCustomPortal();
    public boolean isInCustomPortal();
    public void notInCustomPortal();
    public int getPortalColor();
    public void setPortalColor(int color);
    public Portal getDestPortal();
}
