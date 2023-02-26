package dev.custom.portals.blocks;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.config.CPSettings;
import dev.custom.portals.data.Portal;
import dev.custom.portals.registry.CPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortalBlockEntity extends BlockEntity {

    public PortalBlockEntity(BlockPos pos, BlockState state) {
        super(CPBlocks.PORTAL_BLOCK_ENTITY, pos, state);
    }
    
    public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, BlockEntity be) {
        if (world.isClient)
            return;
        if (CustomPortals.PORTALS.get(world).settingsChanged()) {
            CustomPortals.PORTALS.get(world).refreshPortals();
        }
        Portal portal = CustomPortals.PORTALS.get(world).getPortalFromPos(pos);
        if (portal != null) {
            //System.out.println(portal.hasRedstoneSignal());
            if (CPSettings.PortalRuneSettings.redstoneSetting() == CPSettings.RedstoneDropdown.OFF) {
                if (portal.hasLinked() && !(Boolean) state.get(PortalBlock.LIT) && !portal.hasRedstoneSignal()) {
                    //System.out.println("Turning portal on at " + CustomPortals.blockPosToString(portal.getSpawnPos()) + "...");
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, true), Block.NOTIFY_ALL);
                } else if ((!portal.hasLinked() || portal.hasRedstoneSignal()) && (Boolean) state.get(PortalBlock.LIT)) {
                    //System.out.println("Turning portal off at " + CustomPortals.blockPosToString(portal.getSpawnPos()) + "...");
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, false), Block.NOTIFY_ALL);
                }
            }
            else if (CPSettings.PortalRuneSettings.redstoneSetting() == CPSettings.RedstoneDropdown.ON) {
                if (portal.hasLinked() && !(Boolean) state.get(PortalBlock.LIT) && portal.hasRedstoneSignal()) {
                    //System.out.println("Turning portal on at " + CustomPortals.blockPosToString(portal.getSpawnPos()) + "...");
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, true), Block.NOTIFY_ALL);
                } else if ((!portal.hasLinked() || !portal.hasRedstoneSignal()) && (Boolean) state.get(PortalBlock.LIT)) {
                    //System.out.println("Turning portal off at " + CustomPortals.blockPosToString(portal.getSpawnPos()) + "...");
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, false), Block.NOTIFY_ALL);
                }
            }
            else {
                if (portal.hasLinked() && !(Boolean) state.get(PortalBlock.LIT))
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, true), Block.NOTIFY_ALL);
                if (!portal.hasLinked() && (Boolean) state.get(PortalBlock.LIT))
                    world.setBlockState(pos, (BlockState) state.with(PortalBlock.LIT, false), Block.NOTIFY_ALL);
            }
        }
    }
}
