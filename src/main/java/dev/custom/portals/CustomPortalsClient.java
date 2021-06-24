package dev.custom.portals;

import dev.custom.portals.registry.CPBlocks;
import dev.custom.portals.registry.CPParticles;
import me.lortseam.completeconfig.gui.ConfigScreenBuilder;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class CustomPortalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            ConfigScreenBuilder.setMain(CustomPortals.MOD_ID, new ClothConfigScreenBuilder());
        }
        CPBlocks.setBlockRenderLayers();
        CPParticles.registerFactoryRegistries();
    }
    
}
