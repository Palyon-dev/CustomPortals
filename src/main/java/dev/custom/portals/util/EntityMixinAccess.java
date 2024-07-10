package dev.custom.portals.util;

import dev.custom.portals.data.CustomPortal;

public interface EntityMixinAccess {
    
    public void setInCustomPortal(CustomPortal portal);
    public boolean isInCustomPortal();
    public boolean isInNetherPortal();
    public void notInCustomPortal();
    public int getPortalColor();
    public void setPortalColor(int color);
    public void setInTransition(boolean bl);
    public CustomPortal getDestPortal();
}
