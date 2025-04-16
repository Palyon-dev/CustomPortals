package dev.custom.portals;

import dev.custom.portals.registry.CPBlocks;
import dev.custom.portals.registry.CPItems;
import dev.custom.portals.registry.CPParticles;
import dev.custom.portals.util.ClientUtil;
import dev.custom.portals.util.DrawSpritePayload;
import dev.custom.portals.util.PortalHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CustomPortalsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CPBlocks.setBlockRenderLayers();
        CPItems.registerItemTooltips();
        CPParticles.registerFactoryRegistries();
        ClientPlayNetworking.registerGlobalReceiver(DrawSpritePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientUtil.transitionBackgroundSpriteModel = payload.colorId() == 0 ? null : PortalHelper.getPortalBlockFromColorId(payload.colorId());
            });
        });
    }
    
}
