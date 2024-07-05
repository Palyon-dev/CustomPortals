package dev.custom.portals.util;

import dev.custom.portals.data.Portal;
import net.minecraft.block.Block;

public interface EntityMixinAccess {
    
    public void setInCustomPortal(Portal portal);
    public void tickCustomPortal();
    public boolean isInCustomPortal();
    public boolean isInNetherPortal();
    public void notInCustomPortal();
    public int getPortalColor();
    public void setPortalColor(int color);
    public int getMaxCustomPortalTime();
    public Portal getDestPortal();
}
