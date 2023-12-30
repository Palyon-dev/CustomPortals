package dev.custom.portals;

import dev.custom.portals.registry.CPBlocks;
import dev.custom.portals.registry.CPParticles;
import net.fabricmc.api.ClientModInitializer;

public class CustomPortalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CPBlocks.setBlockRenderLayers();
        CPParticles.registerFactoryRegistries();
    }
    
}
