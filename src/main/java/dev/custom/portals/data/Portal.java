package dev.custom.portals.data;

import java.util.List;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.math.BlockPos;

public class Portal {

    public final int length;
    public final int width;
    private final String frameId;
    private final String dimensionId;
    private final MaterialColor color;
    private final BlockPos spawnPos;
    private final List<BlockPos> portalBlocks;
    
    private Portal linked;

    public Portal(final String frameId, final String dimensionId, final MaterialColor color, final BlockPos spawnPos,
            final List<BlockPos> portalBlocks, final int length, final int width) {
        this.frameId = frameId;
        this.dimensionId = dimensionId;
        this.color = color;
        this.spawnPos = spawnPos;
        this.portalBlocks = portalBlocks;
        this.length = length;
        this.width = width;
    }

    public String getFrameId() {
        return frameId;
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public MaterialColor getColor() {
        return color;
    }

    public Portal getLinked() {
        return linked;
    }

    public BlockPos getSpawnPos() {
        return spawnPos;
    }

    public List<BlockPos> getPortalBlocks() {
        return portalBlocks;
    }

    public boolean hasLinked() {
        return linked != null;
    }

    public boolean isInterdimensional() {
        return hasLinked() && !dimensionId.equals(linked.getDimensionId());
    }

    public void setLinked(final Portal portal) {
        linked = portal;
    }

    public void tryLink(final Portal portal) {
        if(portal.getColor() == color && portal.getFrameId().equals(frameId) && portal != this) {
            linked = portal;
            portal.setLinked(this);
        }
    }
}
