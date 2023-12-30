package dev.custom.portals.config;

import java.util.ArrayList;
import java.util.List;

import dev.custom.portals.CustomPortals;
import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.api.ConfigEntry.Boolean;
import me.lortseam.completeconfig.api.ConfigEntry.Dropdown;
import me.lortseam.completeconfig.data.Config;;


public final class CPSettings extends Config implements ConfigContainer {

    public CPSettings() {
        super(CustomPortals.MOD_ID);
    }

    @ConfigEntries(includeAll = true)
    @Transitive
    public static class GeneralSettings implements ConfigGroup {
        private static boolean portals_always_unlimited = false;
        private static boolean portals_always_interdimensional = false;
        @ConfigEntry.Dropdown
        private static HasteDropdown hasteDropdown = HasteDropdown.CREATIVE;
        @ConfigEntry.Dropdown
        @ConfigEntry(descriptionKey = "redstoneTooltip")
        private static RedstoneDropdown redstoneDropdown = RedstoneDropdown.OFF;
        @ConfigEntry(descriptionKey = "privatePortalsTooltip")
        private static boolean private_portals = false;

        public static boolean portalsAlwaysUnlimited() { return portals_always_unlimited; }
        public static HasteDropdown portalsAlwaysHaste() { return hasteDropdown; }

        public static RedstoneDropdown redstoneSetting() { return redstoneDropdown; }
        public static boolean portalsAlwaysInterdim() { return portals_always_interdimensional; }
        public static boolean arePortalsPrivate() { return private_portals; }
    }

    public enum HasteDropdown {
        YES, NO, CREATIVE
    }

    public enum RedstoneDropdown {
        OFF, ON, NONE
    }

    @ConfigEntries(includeAll = true)
    @Transitive
    public static class PortalRangeSettings implements ConfigGroup {


        private static int default_range;
        private static int range_with_enhancer;
        private static int range_with_strong_enhancer;

        public PortalRangeSettings() {
            default_range = 100;
            range_with_enhancer = 1000;
            range_with_strong_enhancer = 10000;
        }
        public static int getDefaultRange() { return default_range; }
        public static int getRangeWithEnhancer() { return range_with_enhancer; }
        public static int getRangeWithStrongEnhancer() { return range_with_strong_enhancer; }
    }

    @Transitive
    public static class BlockFilterSettings implements ConfigGroup {

        @ConfigEntry(descriptionKey = "listTooltip")
        private static boolean whitelist;
        @ConfigEntry(descriptionKey = "blocksTooltip")
        private static List<String> filteredBlocks = new ArrayList<>();

        public static boolean isWhitelist() { return whitelist; }
        public static List<String> getBlocks() { return filteredBlocks; }
    }
}
