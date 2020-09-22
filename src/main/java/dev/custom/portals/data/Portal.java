package dev.custom.portals.data;

import java.util.List;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.math.BlockPos;

public class Portal {

    public final int length;
    public final int width;

    private String frameId;
    private String dimensionId;
    private MaterialColor color;
    private BlockPos spawnPos;
    private List<BlockPos> portalBlocks;
    private Portal linked;

    public Portal(String frameId, String dimensionId, MaterialColor color, BlockPos spawnPos, List<BlockPos> portalBlocks, int length, int width) {
        this.frameId = frameId;
        this.dimensionId = dimensionId;
        this.color = color;
        this.spawnPos = spawnPos;
        this.portalBlocks = portalBlocks;
        this.length = length;
        this.width = width;
    }

    public String getFrameId() { return frameId; }

    public String getDimensionId() { return dimensionId; }

    public MaterialColor getColor() { return color; }

    public Portal getLinked() { return linked; }

    public BlockPos getSpawnPos() { return spawnPos; }

    public List<BlockPos> getPortalBlocks() { return portalBlocks; }

    public boolean hasLinked() { return linked != null; }

    public void setLinked(Portal portal) { linked = portal; }

    public void tryLink(Portal portal) {
        if(portal.getColor() == color && portal.getFrameId().equals(frameId) && portal != this) {
            linked = portal;
            portal.setLinked(this);
        }
    }
}
