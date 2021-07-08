package dev.custom.portals.config;

import java.util.ArrayList;
import java.util.List;

import dev.custom.portals.CustomPortals;
import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.api.ConfigEntry.Boolean;
import me.lortseam.completeconfig.data.Config;;


public final class CPSettings extends Config implements ConfigContainer {

    public CPSettings() {
        super(CustomPortals.MOD_ID);
    }

    @ConfigEntries
    @Transitive
    public static class PortalRangeSettings implements ConfigGroup {

        private static boolean portals_always_unlimited;
        private static int default_range;
        private static int range_with_enhancer;
        private static int range_with_strong_enhancer;

        public PortalRangeSettings() {
            default_range = 100;
            range_with_enhancer = 1000;
            range_with_strong_enhancer = 10000;
        }

        public static boolean portalsAlwaysUnlimited() { return portals_always_unlimited; }
        public static int getDefaultRange() { return default_range; }
        public static int getRangeWithEnhancer() { return range_with_enhancer; }
        public static int getRangeWithStrongEnhancer() { return range_with_strong_enhancer; }
    }

    @Transitive
    public static class BlockFilterSettings implements ConfigGroup {

        @ConfigEntry(tooltipTranslationKeys = {"listTooltipLine1", "listTooltipLine2"})
        @Boolean(trueTranslationKey = "whitelistTrue", falseTranslationKey = "whitelistFalse")
        private static boolean whitelist;
        @ConfigEntry(tooltipTranslationKeys = {"blocksTooltipLine1", "blocksTooltipLine2"})
        private static List<String> filteredBlocks = new ArrayList<>();
        
        public static boolean isWhitelist() { return whitelist; }
        public static List<String> getBlocks() { return filteredBlocks; }
    }
}
