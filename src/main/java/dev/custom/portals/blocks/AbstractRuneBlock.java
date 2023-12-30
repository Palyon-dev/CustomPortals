package dev.custom.portals.blocks;

import java.util.ArrayList;
import java.util.List;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.data.Portal;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AbstractRuneBlock extends WallMountedBlock {
    protected static final VoxelShape CEILING_SHAPE;
    protected static final VoxelShape FLOOR_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;

    protected AbstractRuneBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(FACE, WallMountLocation.WALL));
    }

    public void registerOnPortal(Portal portal, World world) {}

    public void unregisterOnPortal(Portal portal, World world) {}

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.offset(getDirection(state).getOpposite());
        return world.getBlockState(blockPos).isFullCube(world, blockPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction)state.get(FACING);
        switch((WallMountLocation)state.get(FACE)) {
        case FLOOR:
            return FLOOR_SHAPE;
        case WALL:
            switch(direction) {
            case EAST:
               return EAST_SHAPE;
            case WEST:
               return WEST_SHAPE;
            case SOUTH:
               return SOUTH_SHAPE;
            case NORTH:
            default:
               return NORTH_SHAPE;
            }
        case CEILING:
        default:
          return CEILING_SHAPE;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        BlockPos blockPos = getBlockMountedPos(pos, state);
        Portal portal;
        List<BlockPos> adjacents = new ArrayList<BlockPos>();
        adjacents.add(blockPos.down());
        adjacents.add(blockPos.east());
        adjacents.add(blockPos.west());
        adjacents.add(blockPos.south());
        adjacents.add(blockPos.north());
        adjacents.add(blockPos.up());
        for (BlockPos adjacent : adjacents) {
            portal = CustomPortals.PORTALS.get(world).getPortalFromPos(adjacent);
            if (portal != null) {
                if ((adjacent.equals(blockPos.up()) || adjacent.equals(blockPos.down())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.Y) {
                    registerOnPortal(portal, world);
                }
                if ((adjacent.equals(blockPos.east()) || adjacent.equals(blockPos.west())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.Z) {
                    registerOnPortal(portal, world);
                }
                if ((adjacent.equals(blockPos.north()) || adjacent.equals(blockPos.south())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.X) {
                    registerOnPortal(portal, world);
                }
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        BlockPos blockPos = getBlockMountedPos(pos, state);
        Portal portal;
        List<BlockPos> adjacents = new ArrayList<BlockPos>();
        adjacents.add(blockPos.down());
        adjacents.add(blockPos.east());
        adjacents.add(blockPos.west());
        adjacents.add(blockPos.south());
        adjacents.add(blockPos.north());
        adjacents.add(blockPos.up());
        for (BlockPos adjacent : adjacents) {
            portal = CustomPortals.PORTALS.get(world).getPortalFromPos(adjacent);
            if (portal != null) {
                if ((adjacent.equals(blockPos.up()) || adjacent.equals(blockPos.down())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.Y) {
                    unregisterOnPortal(portal, world);
                }
                if ((adjacent.equals(blockPos.east()) || adjacent.equals(blockPos.west())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.Z) {
                    unregisterOnPortal(portal, world);
                }
                if ((adjacent.equals(blockPos.north()) || adjacent.equals(blockPos.south())) && (Direction.Axis)world.getBlockState(adjacent).get(Properties.AXIS) != Direction.Axis.X) {
                    unregisterOnPortal(portal, world);
                }
            }
        }
    }

    public BlockPos getBlockMountedPos(BlockPos pos, BlockState state) {
        Direction direction = (Direction)state.get(FACING);
        WallMountLocation orientation = (WallMountLocation)state.get(FACE);
        switch(orientation) {
            case FLOOR: 
                return pos.down();
            case WALL:
                switch(direction) {
                    case EAST: return pos.west();
                    case WEST: return pos.east();
                    case SOUTH: return pos.north();
                    default:
                    case NORTH: return pos.south();
                }
            default:
            case CEILING: return pos.up();
        }
    }

    static {
        CEILING_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        FLOOR_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
        NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
        SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
        WEST_SHAPE = Block.createCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    }
    
}
