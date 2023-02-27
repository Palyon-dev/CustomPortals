package dev.custom.portals.util;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.blocks.AbstractRuneBlock;
import dev.custom.portals.config.CPSettings;
import dev.custom.portals.data.Portal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.*;

public class PortalHelper {

    private static BlockPos getUp(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.Y ? pos.north() : pos.up();
    }

    private static BlockPos getDown(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.Y ? pos.south() : pos.down();
    }

    private static BlockPos getLeft(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.X ? pos.south() : pos.east();
    }

    private static BlockPos getRight(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.X ? pos.north() : pos.west();
    }

    private static int getTranslatedX(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.X ? pos.getZ() : pos.getX();
    }

    private static int getTranslatedY(BlockPos pos, Direction.Axis axis) {
        return axis == Direction.Axis.Y ? pos.getZ() : pos.getY();
    }

    private static List<BlockPos> getAdjacentsOnPlane(BlockPos blockPos, Direction.Axis axis) {
        List<BlockPos> adjacents = new ArrayList<>();
        adjacents.add(getUp(blockPos, axis));
        adjacents.add(getLeft(blockPos, axis));
        adjacents.add(getRight(blockPos, axis));
        adjacents.add(getDown(blockPos, axis));
        return adjacents;
    }

    private static List<BlockPos> getAdjacents(BlockPos blockPos) {
        List<BlockPos> adjacents = new ArrayList<>();
        adjacents.add(blockPos.up());
        adjacents.add(blockPos.down());
        adjacents.add(blockPos.north());
        adjacents.add(blockPos.south());
        adjacents.add(blockPos.east());
        adjacents.add(blockPos.west());
        return adjacents;
    }

    private static SpawnPosData determineSpawnPos(List<BlockPos> portalBlocks, Block portalBlock, Direction.Axis axis, World world) {
        int leftmost = getTranslatedX(portalBlocks.get(0), axis), rightmost = getTranslatedX(portalBlocks.get(0), axis);
        for (BlockPos blockPos : portalBlocks) {
            int x = getTranslatedX(blockPos, axis);
            if (x < leftmost) leftmost = x;
            if (x > rightmost) rightmost = x;
        }
        int xSpawn = (rightmost + leftmost) / 2;
        int length = rightmost - leftmost + 1;
        if (length % 2 == 0 && leftmost >= 0) xSpawn++;
        List<BlockPos> midPortalBlocks = new ArrayList<>();
        for (BlockPos blockPos : portalBlocks) {
            int x = getTranslatedX(blockPos, axis);
            if (x == xSpawn) midPortalBlocks.add(blockPos);
        }
        BlockPos bottom = midPortalBlocks.get(0);
        for (BlockPos blockPos : midPortalBlocks) {
            if (getTranslatedY(blockPos, axis) < getTranslatedY(bottom, axis)) bottom = blockPos;
        }
        // TODO algorithm that ensures a default spawn position that the player can fit in is always selected if available
        /*if (!world.getBlockState(getUp(bottom, axis)).isOf(portalBlock)
                && !world.getBlockState(getDown(bottom, axis)).isOf(portalBlock)) {
            BlockPos leftBottom = getLeft(bottom, axis);
        }*/
        int ySpawn = getTranslatedY(bottom, axis);
        int height = 1; // unused unless portal is oriented along Y axis
        if (axis == Direction.Axis.Y) {
            BlockPos pos = bottom;
            while (world.getBlockState(getDown(pos, axis)).isOf(portalBlock)) {
                pos = getDown(pos, axis);
                height++;
            }
            ySpawn += height / 2;
        }
        int trueXSpawn = axis == Direction.Axis.X ? bottom.getX() : xSpawn;
        int trueYSpawn = axis == Direction.Axis.Y ? bottom.getY() : ySpawn;
        int trueZSpawn = axis == Direction.Axis.Z ? bottom.getZ() : axis == Direction.Axis.X ? xSpawn : ySpawn;
        float offsetX = 0.0f;
        float offsetZ = 0.0f;
        switch (axis) {
            case X -> {
                offsetX = 0.5f;
                if (length % 2 != 0) offsetZ = 0.5f;
            }
            case Y -> {
                if (length % 2 != 0) offsetX = 0.5f;
                if (height % 2 != 0) offsetZ = 0.5f;
            }
            case Z -> {
                offsetZ = 0.5f;
                if (length % 2 != 0) offsetX = 0.5f;
            }
        }
        BlockPos spawnPos = new BlockPos(new Vec3d(trueXSpawn, trueYSpawn, trueZSpawn));
        //System.out.println(CustomPortals.blockPosToString(spawnPos));
        return new SpawnPosData(spawnPos, offsetX, offsetZ);
    }

    public static boolean buildPortal(BlockPos startPos, Block portalBlock, World world) {
        if (buildPortal(startPos, Direction.Axis.X, portalBlock, world)) return true;
        if (buildPortal(startPos, Direction.Axis.Y, portalBlock, world)) return true;
        return buildPortal(startPos, Direction.Axis.Z, portalBlock, world);
    }

    public static boolean buildPortal(BlockPos blockPos, Direction.Axis axis, Block portalBlock, World world) {

        if (!world.getBlockState(blockPos).isAir()) return false;

        // figure out which block will make up the frame of the portal
        List<BlockPos> adjacents = getAdjacentsOnPlane(blockPos, axis);
        Block frameMaterial = Blocks.AIR;
        for (BlockPos pos : adjacents) {
            BlockState blockState = world.getBlockState(pos);
            if (!blockState.isAir()) {
                if (frameMaterial != Blocks.AIR) {
                    if (!frameMaterial.equals(blockState.getBlock())) return false;
                }
                else if (blockState.isFullCube(world, pos)) frameMaterial = blockState.getBlock();
                else return false;
            }
        }

        BlockPos startPos = blockPos;

        // if we haven't found a frame block, go down on the portal until we find one
        if (frameMaterial == Blocks.AIR) {
            for (int i = 0; world.getBlockState(getDown(startPos, axis)).isAir(); i++) {
                if (i > 30) return false;
                startPos = getDown(startPos, axis);
            }
            if (!world.getBlockState(getDown(startPos, axis)).isFullCube(world, getDown(startPos, axis))) return false;
            frameMaterial = world.getBlockState(getDown(startPos, axis)).getBlock();
        }

        // verify that the frame block is a valid portal frame block
        String frameId = Registry.BLOCK.getId(frameMaterial).toString();
        List<String> blockIds = CPSettings.BlockFilterSettings.getBlocks();
        if (CPSettings.BlockFilterSettings.isWhitelist()) {
            if (!blockIds.contains(frameId)) return false;
        } else {
            if (blockIds.contains(frameId)) return false;
        }

        List<BlockPos> portalBlocks = new ArrayList<>();

        // DFS algorithm to find the block positions of all the portal blocks
        Stack<BlockPos> s = new Stack<>();
        Set<BlockPos> visited = new HashSet<>();
        Set<BlockPos> frames = new HashSet<>();
        s.push(startPos);
        while (!s.isEmpty()) {
            BlockPos curPos = s.pop();
            if (!visited.add(curPos)) continue;
            if (visited.size() > 900) return false;
            List<BlockPos> curAdjacents = getAdjacentsOnPlane(curPos, axis);
            for (BlockPos pos : curAdjacents) {
                BlockState blockState = world.getBlockState(pos);
                if (blockState.isAir()) {
                    if (!visited.contains(pos))
                        s.push(pos);
                }
                else {
                    if (!blockState.isOf(frameMaterial))
                        return false;
                    frames.add(pos);
                }
            }
            portalBlocks.add(curPos);
        }

        // rotate blockState to ensure portal blocks are facing the proper direction
        BlockState portalState = switch (axis) {
            case X -> portalBlock.getDefaultState().rotate(BlockRotation.CLOCKWISE_90);
            case Y -> portalBlock.getDefaultState().rotate(BlockRotation.COUNTERCLOCKWISE_90);
            case Z -> portalBlock.getDefaultState();
        };

        for (BlockPos pos : portalBlocks) {
            world.setBlockState(pos, portalState);
        }

        SpawnPosData spawnPosData = determineSpawnPos(portalBlocks, portalBlock, axis, world);

        Portal portal = new Portal(frameId, world.getRegistryKey().getValue().toString(),
                portalBlock.getDefaultMapColor(), spawnPosData.blockPos, portalBlocks, spawnPosData.offsetX,
                spawnPosData.offsetZ);
        CustomPortals.PORTALS.get(world).registerPortal(portal);

        // register any runes that were already on the portal frames
        for (BlockPos framePos : frames) {
            List<BlockPos> frameAdjacents = getAdjacents(framePos);
            for (BlockPos adj : frameAdjacents) {
                Block block = world.getBlockState(adj).getBlock();
                if (block instanceof AbstractRuneBlock)
                    if (((AbstractRuneBlock)block).getBlockMountedPos(adj, world.getBlockState(adj)).equals(framePos))
                        ((AbstractRuneBlock)block).registerOnPortal(portal, world);
            }
        }
        if(!world.isClient)
            CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
        return true;
    }

    static class SpawnPosData {
        protected BlockPos blockPos;
        protected float offsetX;
        protected float offsetZ;

        protected SpawnPosData(BlockPos blockPos, float offsetX, float offsetZ) {
            this.blockPos = blockPos;
            this.offsetX = offsetX;
            this.offsetZ = offsetZ;
        }
    }
}
