package dev.custom.portals.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.custom.portals.CustomPortals;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;

public class ModMenuIntegration implements ModMenuApi {

    private static final ClothConfigScreenBuilder configScreenBuilder = new ClothConfigScreenBuilder();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> CustomPortals.getConfig().map(config -> configScreenBuilder.build(parent, (Config) config)).orElse(null);
    }
}
