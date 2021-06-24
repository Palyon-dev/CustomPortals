package dev.custom.portals.blocks;

import java.util.List;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.config.CPSettings;
import dev.custom.portals.data.Portal;
import net.minecraft.block.AbstractBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StrongEnhancerRuneBlock extends AbstractRuneBlock {

    public StrongEnhancerRuneBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void registerOnPortal(Portal portal, World world) {
        portal.addStrongEnhancer();
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
    }
    
    @Override
    public void unregisterOnPortal(Portal portal, World world) {
        portal.removeStrongEnhancer();
        if (portal.hasLinked())
            CustomPortals.PORTALS.get(world).tryWithAll(portal.getLinked());
        CustomPortals.PORTALS.get(world).tryWithAll(portal);
    }

    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("item.customportals.strong_enhancer_rune.tooltip", CPSettings.getRangeWithStrongEnhancer()).formatted(Formatting.GRAY));
    }
}