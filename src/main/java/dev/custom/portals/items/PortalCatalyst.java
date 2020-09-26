package dev.custom.portals.items;

import java.util.ArrayList;
import java.util.List;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.data.Portal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class PortalCatalyst extends Item {

    private Block portalBlock;

    public PortalCatalyst(Settings settings, Block portal) {
        super(settings);
        this.portalBlock = portal;
    }

    public Pair<Boolean, BlockPos> isValidUDPortal(BlockPos pos, Direction side, World world) {
        Pair<Boolean, BlockPos> invalid = new Pair<>(false, pos);
        BlockPos startPos = pos;
        if(side != Direction.NORTH) {
            for(int i = 0; world.getBlockState(startPos.south()).isAir(); i++) {
                if(i > 23)
                    return invalid;
                startPos = startPos.south();
            }
        }
        if(!world.getBlockState(startPos.south()).isFullCube(world, startPos.south()))
            return invalid;
        Block frameMaterial = world.getBlockState(startPos.south()).getBlock();
        BlockPos curPos = startPos;
        for(int i = 0; world.getBlockState(curPos.west()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.south()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.west();
        }
        if(!world.getBlockState(curPos.south()).isOf(frameMaterial))
            return invalid;
        Pair<Boolean, BlockPos> valid = new Pair<>(true, curPos);
        for(int i = 0; world.getBlockState(curPos.north()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.west()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.north();
        }
        if(!world.getBlockState(curPos.west()).isOf(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.east()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.north()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.east();
        }
        if(!world.getBlockState(curPos.north()).isOf(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.south()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.east()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.south();
        }
        if(!world.getBlockState(curPos.east()).isOf(frameMaterial))
            return invalid;
        while(!curPos.equals(startPos)) {
            if(!world.getBlockState(curPos).isAir() || 
            !world.getBlockState(curPos.south()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.west();
        }
        return valid;
    }

    public Pair<Boolean, BlockPos> isValidEWPortal(BlockPos pos, Direction side, World world) {
        Pair<Boolean, BlockPos> invalid = new Pair<>(false, pos);
        if(!(world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.down()).isAir()))
            return invalid;
        BlockPos startPos = pos;
        if(side != Direction.UP) {
            for(int i = 0; world.getBlockState(startPos.down()).isAir(); i++) {
                if(i > 23)
                    return invalid;
                startPos = startPos.down();
            }
        }
        if(!world.getBlockState(startPos.down()).isFullCube(world, startPos.down()))
            return invalid;
        Block frameMaterial = world.getBlockState(startPos.down()).getBlock();
        BlockPos curPos = startPos;
        for(int i = 0; world.getBlockState(curPos.south()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.down()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.south();
        }
        if(!world.getBlockState(curPos.down()).isOf(frameMaterial))
            return invalid;
        Pair<Boolean, BlockPos> valid = new Pair<>(true, curPos);
        for(int i = 0; world.getBlockState(curPos.up()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.south()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.up();
        }
        if(!world.getBlockState(curPos.south()).isOf(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.north()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.up()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.north();
        }
        if(!world.getBlockState(curPos.up()).getBlock().is(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.down()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.north()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.down();
        }
        if(!world.getBlockState(curPos.north()).isOf(frameMaterial))
            return invalid;
        while(!curPos.equals(startPos)) {
            if(!world.getBlockState(curPos).isAir() || 
            !world.getBlockState(curPos.down()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.south();
        }
        return valid;
    }

    public Pair<Boolean, BlockPos> isValidNSPortal(BlockPos pos, Direction side, World world) {
        Pair<Boolean, BlockPos> invalid = new Pair<>(false, pos);
        if(!(world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.down()).isAir()))
            return invalid;
        BlockPos startPos = pos;
        if(side != Direction.UP) {
            for(int i = 0; world.getBlockState(startPos.down()).isAir(); i++) {
                if(i > 23)
                    return invalid;
                startPos = startPos.down();
            }
        }
        if(!world.getBlockState(startPos.down()).isFullCube(world, startPos.down()))
            return invalid;
        Block frameMaterial = world.getBlockState(startPos.down()).getBlock();
        BlockPos curPos = startPos;
        for(int i = 0; world.getBlockState(curPos.west()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.down()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.west();
        }
        if(!world.getBlockState(curPos.down()).isOf(frameMaterial))
            return invalid;
        Pair<Boolean, BlockPos> valid = new Pair<>(true, curPos);
        for(int i = 0; world.getBlockState(curPos.up()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.west()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.up();
        }
        if(!world.getBlockState(curPos.west()).isOf(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.east()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.up()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.east();
        }
        if(!world.getBlockState(curPos.up()).isOf(frameMaterial))
            return invalid;
        for(int i = 0; world.getBlockState(curPos.down()).isAir(); i++) {
            if(i > 23 || !world.getBlockState(curPos.east()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.down();
        }
        if(!world.getBlockState(curPos.east()).isOf(frameMaterial))
            return invalid;
        while(!curPos.equals(startPos)) {
            if(!world.getBlockState(curPos).isAir() || 
            !world.getBlockState(curPos.down()).isOf(frameMaterial))
                return invalid;
            curPos = curPos.west();
        }
        return valid;
    }

    public Pair<Integer, Integer> getDimensions(World world, BlockPos corner, Direction.Axis axis) {
        int x = 0;
        int z = 0;
        if(axis != Direction.Axis.X) {
            for(BlockPos curPos = corner; world.getBlockState(curPos).isOf(portalBlock); curPos = curPos.east()) {
                x++;
            }
        }
        if(axis != Direction.Axis.Z) {
            for(BlockPos curPos = corner; world.getBlockState(curPos).isOf(portalBlock); curPos = curPos.north()) {
                z++;
            }
        }
        return new Pair<Integer, Integer>(x, z);
    }

    public boolean activatePortal(BlockPos pos, Direction side, World world) {
        Pair<Boolean, BlockPos> ns = isValidNSPortal(pos, side, world);
        Pair<Boolean, BlockPos> ew = isValidEWPortal(pos, side, world);
        Pair<Boolean, BlockPos> ud = isValidUDPortal(pos, side, world);
        if(ns.getLeft()) {
            BlockState state = portalBlock.getDefaultState();
            BlockPos curPosOuter = ns.getRight();
            List<BlockPos> portalBlocks = new ArrayList<BlockPos>();
            while(world.getBlockState(curPosOuter).isAir()) {
                BlockPos curPosInner = curPosOuter;
                while(world.getBlockState(curPosInner).isAir()) {
                    world.setBlockState(curPosInner, state);
                    portalBlocks.add(curPosInner);
                    curPosInner = curPosInner.east();
                }
                curPosOuter = curPosOuter.up();
            }
            Pair<Integer, Integer> dimensions = getDimensions(world, ns.getRight(), Direction.Axis.Z);
            BlockPos center = ns.getRight().east(dimensions.getLeft()/2);
            Portal portal = new Portal(world.getBlockState(ns.getRight().down()).getBlock().getTranslationKey(), 
            world.getRegistryKey().getValue().toString(), portalBlock.getDefaultMaterialColor(), 
            center, portalBlocks, dimensions.getLeft(), dimensions.getRight());
            CustomPortals.PORTALS.get(world).registerPortal(portal);
            if(!world.isClient)
                CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
            return true;
        }
        if(ew.getLeft()) {
            BlockState state = portalBlock.getDefaultState().rotate(BlockRotation.CLOCKWISE_90);
            BlockPos curPosOuter = ew.getRight();
            List<BlockPos> portalBlocks = new ArrayList<BlockPos>();
            while(world.getBlockState(curPosOuter).isAir()) {
                BlockPos curPosInner = curPosOuter;
                while(world.getBlockState(curPosInner).isAir()) {
                    world.setBlockState(curPosInner, state);
                    portalBlocks.add(curPosInner);
                    curPosInner = curPosInner.north();
                }
                curPosOuter = curPosOuter.up();
            }
            Pair<Integer, Integer> dimensions = getDimensions(world, ew.getRight(), Direction.Axis.X);
            BlockPos center = ew.getRight().north(dimensions.getRight()/2 - 1);
            Portal portal = new Portal(world.getBlockState(ew.getRight().down()).getBlock().getTranslationKey(), 
                world.getRegistryKey().getValue().toString(), portalBlock.getDefaultMaterialColor(), 
                center, portalBlocks, dimensions.getLeft(), dimensions.getRight());
            CustomPortals.PORTALS.get(world).registerPortal(portal);
            if(!world.isClient)
                CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
            return true;
        }
        if(ud.getLeft()) {
            BlockState state = portalBlock.getDefaultState().rotate(BlockRotation.COUNTERCLOCKWISE_90);
            BlockPos curPosOuter = ud.getRight();
            List<BlockPos> portalBlocks = new ArrayList<BlockPos>();
            while(world.getBlockState(curPosOuter).isAir()) {
                BlockPos curPosInner = curPosOuter;
                while(world.getBlockState(curPosInner).isAir()) {
                    world.setBlockState(curPosInner, state);
                    portalBlocks.add(curPosInner);
                    curPosInner = curPosInner.east();
                }
                curPosOuter = curPosOuter.north();
            }
            Pair<Integer, Integer> dimensions = getDimensions(world, ud.getRight(), Direction.Axis.Y);
            BlockPos center = ud.getRight().east(dimensions.getLeft()/2).north(dimensions.getRight()/2 - 1);
            Portal portal = new Portal(world.getBlockState(ud.getRight().south()).getBlock().getTranslationKey(), 
            world.getRegistryKey().getValue().toString(), portalBlock.getDefaultMaterialColor(), 
            center, portalBlocks, dimensions.getLeft(), dimensions.getRight());
            CustomPortals.PORTALS.get(world).registerPortal(portal);
            if(!world.isClient)
                CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
            return true;
        }
        return false;
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        PlayerEntity playerEntity = ctx.getPlayer();
        World world = ctx.getWorld();
        BlockPos pos = new BlockPos(ctx.getHitPos());
        Direction dir = ctx.getSide();
        if(dir == Direction.NORTH)
            pos = pos.north();
        else if(dir == Direction.WEST) 
            pos = pos.west();
        else if(dir == Direction.DOWN)
            pos = pos.down();
        if(activatePortal(pos, dir, world)) {
            playerEntity.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
            ctx.getStack().decrement(1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}