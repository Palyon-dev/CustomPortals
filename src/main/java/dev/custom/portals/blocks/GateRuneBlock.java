package dev.custom.portals.blocks;

import java.util.List;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.data.Portal;
import net.minecraft.block.AbstractBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GateRuneBlock extends AbstractRuneBlock {

    public GateRuneBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void registerOnPortal(Portal portal, World world) {
        portal.addGate();
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
    }

    @Override
    public void unregisterOnPortal(Portal portal, World world) {
        portal.removeGate();
        if (portal.hasLinked())
            CustomPortals.PORTALS.get(world).tryWithAll(portal.getLinked());
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("item.customportals.gate_rune.tooltip").formatted(Formatting.GRAY));
    }
}
