package dev.custom.portals.blocks;

import java.util.List;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.data.CustomPortal;
import dev.custom.portals.config.CPSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class EnhancerRuneBlock extends AbstractRuneBlock {

    public EnhancerRuneBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void registerOnPortal(CustomPortal portal, World world) {
        portal.addWeakEnhancer();
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
        if (!world.isClient())
            CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
    }

    @Override
    public void unregisterOnPortal(CustomPortal portal, World world) {
        portal.removeWeakEnhancer();
        if (portal.hasLinked())
            CustomPortals.PORTALS.get(world).tryWithAll(portal.getLinked());
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
        if (!world.isClient())
            CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
    }
}
