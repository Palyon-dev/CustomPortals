package dev.custom.portals;

import dev.custom.portals.config.CPSettings;
import dev.custom.portals.data.BasePortalComponent;
import dev.custom.portals.data.WorldPortals;
import dev.custom.portals.registry.CPBlocks;
import dev.custom.portals.registry.CPItems;
import dev.custom.portals.registry.CPParticles;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import me.lortseam.completeconfig.data.Config;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class CustomPortals implements ModInitializer, WorldComponentInitializer {

        public static final String MOD_ID = "customportals";

        public static final ComponentKey<BasePortalComponent> PORTALS = ComponentRegistryV3.INSTANCE
                .getOrCreate(new Identifier("customportals:portals"), BasePortalComponent.class);

        public static final Config CONFIG = new Config(MOD_ID, new CPSettings());

        @Override
        public void onInitialize() {
                CONFIG.load();
                CPBlocks.registerBlocks();
                CPItems.registerItems();
                CPParticles.registerParticles();
        }

        @Override
        public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
                registry.register(PORTALS, WorldPortals.class, WorldPortals::new);
        }

        // for debugging purposes
        public static String blockPosToString(BlockPos pos) {
                return "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")";
        }

}