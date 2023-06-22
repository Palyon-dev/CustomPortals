package dev.custom.portals.items;
import dev.custom.portals.util.PortalHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
public class PortalCatalyst extends Item {

    private Block portalBlock;

    public PortalCatalyst(Settings settings, Block portal) {
        super(settings);
        this.portalBlock = portal;
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        PlayerEntity playerEntity = ctx.getPlayer();
        World world = ctx.getWorld();
        BlockPos pos = new BlockPos(ctx.getBlockPos());
        Direction dir = ctx.getSide();
        pos = switch (dir) {
            case NORTH -> pos.north();
            case SOUTH -> pos.south();
            case EAST -> pos.east();
            case WEST -> pos.west();
            case UP -> pos.up();
            case DOWN -> pos.down();
        };
        if (PortalHelper.buildPortal(pos, portalBlock, world)) {
            if (playerEntity != null) playerEntity.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
            ctx.getStack().decrement(1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}