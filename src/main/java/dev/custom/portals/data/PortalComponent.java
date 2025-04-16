package dev.custom.portals.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.block.MapColor;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class PortalComponent implements BasePortalComponent {

    private PortalRegistry portalRegistry;

    public PortalComponent() {
        portalRegistry = new PortalRegistry();
    }

    @Override
    public PortalRegistry getPortalRegistry() { return portalRegistry; }

    @Override
    public CustomPortal getPortalFromPos(BlockPos pos) {
        return portalRegistry.getPortalFromPos(pos);
    }

    @Override
    public void setPortalRegistry(PortalRegistry portalRegistry) { this.portalRegistry = portalRegistry; }

    @Override
    public void registerPortal(CustomPortal portal) {
        portalRegistry.register(portal);
    }

    @Override
    public void unregisterPortal(CustomPortal portal) {
        portalRegistry.unregister(portal);
    }

    @Override
    public void tryWithAll(CustomPortal portal) {
        portalRegistry.tryWithAll(portal);
    }

    @Override
    public void refreshPortals() {
        portalRegistry.refreshPortals();
    }

    /*@Override
    public void clearPortals() {
        portalRegistry.clear();
    }*/

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList portalData = tag.getList("portals").get();
        for(int i = 0; i < portalData.size(); i++) {
            NbtCompound curTag = portalData.getCompound(i).get();
            String frameId = curTag.getString("frameId").get();
            String dimensionId = curTag.getString("dimensionId").get();
            BlockPos spawnPos = new BlockPos(curTag.getInt("xSpawn").get(), curTag.getInt("ySpawn").get(), curTag.getInt("zSpawn").get());
            MapColor color = MapColor.get(curTag.getInt("color").get());
            List<BlockPos> portalBlocks = new ArrayList<BlockPos>();
            NbtList portalBlocksData = curTag.getList("blocks").get();
            for(int j = 0; j < portalBlocksData.size(); j++) {
                NbtCompound curBlockTag = portalBlocksData.getCompound(j).get();
                portalBlocks.add(new BlockPos(curBlockTag.getInt("xPos").get(), curBlockTag.getInt("yPos").get(), curBlockTag.getInt("zPos").get()));
            }

            int length = curTag.getInt("length").orElse(0);
            int width = curTag.getInt("width").orElse(0);
            float offsetX, offsetZ;

            // for backwards compatibility with earlier versions
            if (length != 0 || width != 0) {
                offsetX = length == 0 || length % 2 != 0 ? 0.5f : 0.0f;
                offsetZ = width == 0 ? 0.5f : width % 2 != 0 ? -0.5f : 0.0f;
            }
            else {
                offsetX = curTag.getFloat("offsetX").get();
                offsetZ = curTag.getFloat("offsetZ").get();
            }

            int hasteRunes = curTag.getInt("haste").get();
            int gateRunes = curTag.getInt("gate").get();
            int weakEnhancerRunes = curTag.getInt("weak").get();
            int strongEnhancerRunes = curTag.getInt("strong").get();
            int infinityRunes = curTag.getInt("infinity").get();

            UUID creatorId = UUID.fromString(curTag.getString("creatorId").get());
            CustomPortal portal = new CustomPortal(frameId, dimensionId, color, spawnPos, portalBlocks, offsetX, offsetZ, creatorId,
                hasteRunes, gateRunes, weakEnhancerRunes, strongEnhancerRunes, infinityRunes);
            registerPortal(portal);
        }
        
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList portalData = new NbtList();
        for(CustomPortal curPortal : portalRegistry.getPortals()) {
            NbtCompound curTag = new NbtCompound();
            curTag.putString("frameId", curPortal.getFrameId());
            curTag.putString("dimensionId", curPortal.getDimensionId());
            curTag.putInt("xSpawn", curPortal.getSpawnPos().getX());
            curTag.putInt("ySpawn", curPortal.getSpawnPos().getY());
            curTag.putInt("zSpawn", curPortal.getSpawnPos().getZ());
            curTag.putFloat("offsetX", curPortal.offsetX);
            curTag.putFloat("offsetZ", curPortal.offsetZ);
            curTag.putInt("color", curPortal.getColor().id);
            curTag.putInt("haste", curPortal.getHasteRunes());
            curTag.putInt("gate", curPortal.getGateRunes());
            curTag.putInt("weak", curPortal.getWeakEnhancerRunes());
            curTag.putInt("strong", curPortal.getStrongEnhancerRunes());
            curTag.putInt("infinity", curPortal.getInfinityRunes());
            if (curPortal.getCreatorId() != null) {
                String id = curPortal.getCreatorId().toString();
                curTag.putString("creatorId", id);
            }
            NbtList portalBlockData = new NbtList();
            for (BlockPos blockPos : curPortal.getPortalBlocks()) {
                NbtCompound blockTag = new NbtCompound();
                blockTag.putInt("xPos", blockPos.getX());
                blockTag.putInt("yPos", blockPos.getY());
                blockTag.putInt("zPos", blockPos.getZ());
                portalBlockData.add(blockTag);
            }
            curTag.put("blocks", portalBlockData);

            portalData.add(curTag);
        }
        tag.put("portals", portalData);
    }
    
    @Override
    public void syncWithAll(MinecraftServer server) {}
}
