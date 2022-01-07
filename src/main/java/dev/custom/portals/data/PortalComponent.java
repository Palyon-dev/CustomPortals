package dev.custom.portals.data;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.MapColor;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import dev.custom.portals.config.CPSettings;

public class PortalComponent implements BasePortalComponent {
    private boolean portalsAlwaysUnlimited;
    private boolean portalsAlwaysInterdim;
    private int defaultRange;
    private int rangeWithEnhancer;
    private int rangeWithStrongEnhancer;

    private PortalRegistry portalRegistry;

    public PortalComponent() { 
        portalRegistry = new PortalRegistry();
        portalsAlwaysUnlimited = CPSettings.PortalRuneSettings.portalsAlwaysUnlimited();
        portalsAlwaysInterdim = CPSettings.PortalRuneSettings.portalsAlwaysInterdim();
        defaultRange = CPSettings.PortalRangeSettings.getDefaultRange();
        rangeWithEnhancer = CPSettings.PortalRangeSettings.getRangeWithEnhancer();
        rangeWithStrongEnhancer = CPSettings.PortalRangeSettings.getRangeWithStrongEnhancer();
    }

    @Override
    public PortalRegistry getPortalRegistry() { return portalRegistry; }

    @Override
    public Portal getPortalFromPos(BlockPos pos) {
        return portalRegistry.getPortalFromPos(pos);
    }

    @Override
    public boolean settingsChanged() {
        boolean portalsAlwaysUnlimited = CPSettings.PortalRuneSettings.portalsAlwaysUnlimited();
        boolean portalsAlwaysInterdim = CPSettings.PortalRuneSettings.portalsAlwaysInterdim();
        int defaultRange = CPSettings.PortalRangeSettings.getDefaultRange();
        int rangeWithEnhancer = CPSettings.PortalRangeSettings.getRangeWithEnhancer();
        int rangeWithStrongEnhancer = CPSettings.PortalRangeSettings.getRangeWithStrongEnhancer();
        if (portalsAlwaysUnlimited != this.portalsAlwaysUnlimited) {
            this.portalsAlwaysUnlimited = portalsAlwaysUnlimited;
            return true;
        }
        if (portalsAlwaysInterdim != this.portalsAlwaysInterdim) {
            this.portalsAlwaysInterdim = portalsAlwaysInterdim;
            return true;
        }
        if (defaultRange != this.defaultRange) {
            this.defaultRange = defaultRange;
            return true;
        }
        if (rangeWithEnhancer != this.rangeWithEnhancer) {
            this.rangeWithEnhancer = rangeWithEnhancer;
            return true;
        }
        if (rangeWithStrongEnhancer != this.rangeWithStrongEnhancer) {
            this.rangeWithStrongEnhancer = rangeWithStrongEnhancer;
            return true;
        }
        return false;
    }

    @Override
    public void setPortalRegistry(PortalRegistry portalRegistry) { this.portalRegistry = portalRegistry; }

    @Override
    public void registerPortal(Portal portal) {
        portalRegistry.register(portal);
    }

    @Override
    public void unregisterPortal(Portal portal) {
        portalRegistry.unregister(portal);
    }

    @Override
    public void tryWithAll(Portal portal) {
        portalRegistry.tryWithAll(portal);
    }

    @Override
    public void refreshPortals() {
        portalRegistry.refreshPortals();
    }

    @Override
    public void clearPortals() {
        portalRegistry.clear();
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        NbtList portalData = tag.getList("portals", 10);
        for(int i = 0; i < portalData.size(); i++) {
            NbtCompound curTag = portalData.getCompound(i);
            String frameId = curTag.getString("frameId");
            String dimensionId = curTag.getString("dimensionId");
            BlockPos spawnPos = new BlockPos(curTag.getInt("xSpawn"), curTag.getInt("ySpawn"), curTag.getInt("zSpawn"));
            MapColor color = MapColor.get(curTag.getInt("color"));
            List<BlockPos> portalBlocks = new ArrayList<BlockPos>();
            NbtList portalBlocksData = curTag.getList("blocks", 10);
            for(int j = 0; j < portalBlocksData.size(); j++) {
                NbtCompound curBlockTag = portalBlocksData.getCompound(j);
                portalBlocks.add(new BlockPos(curBlockTag.getInt("xPos"), curBlockTag.getInt("yPos"), curBlockTag.getInt("zPos")));
            }
            int length = curTag.getInt("length");
            int width = curTag.getInt("width");
            int hasteRunes = curTag.getInt("haste");
            int gateRunes = curTag.getInt("gate");
            int weakEnhancerRunes = curTag.getInt("weak");
            int strongEnhancerRunes = curTag.getInt("strong");
            int infinityRunes = curTag.getInt("infinity");
            Portal portal = new Portal(frameId, dimensionId, color, spawnPos, portalBlocks, length, width, 
                hasteRunes, gateRunes, weakEnhancerRunes, strongEnhancerRunes, infinityRunes);
            registerPortal(portal);
        }
        
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList portalData = new NbtList();
        for(Portal curPortal : portalRegistry.getPortals()) {
            NbtCompound curTag = new NbtCompound();
            curTag.putString("frameId", curPortal.getFrameId());
            curTag.putString("dimensionId", curPortal.getDimensionId());
            curTag.putInt("xSpawn", curPortal.getSpawnPos().getX());
            curTag.putInt("ySpawn", curPortal.getSpawnPos().getY());
            curTag.putInt("zSpawn", curPortal.getSpawnPos().getZ());
            curTag.putInt("color", curPortal.getColor().id);
            curTag.putInt("haste", curPortal.getHasteRunes());
            curTag.putInt("gate", curPortal.getGateRunes());
            curTag.putInt("weak", curPortal.getWeakEnhancerRunes());
            curTag.putInt("strong", curPortal.getStrongEnhancerRunes());
            curTag.putInt("infinity", curPortal.getInfinityRunes());
            NbtList portalBlockData = new NbtList();
            for (BlockPos blockPos : curPortal.getPortalBlocks()) {
                NbtCompound blockTag = new NbtCompound();
                blockTag.putInt("xPos", blockPos.getX());
                blockTag.putInt("yPos", blockPos.getY());
                blockTag.putInt("zPos", blockPos.getZ());
                portalBlockData.add(blockTag);
            }
            curTag.put("blocks", portalBlockData);
            curTag.putInt("length", curPortal.length);
            curTag.putInt("width", curPortal.width);
            portalData.add(curTag);
        }
        tag.put("portals", portalData);
    }
    
    @Override
    public void syncWithAll(MinecraftServer server) {}
}
