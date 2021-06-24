package dev.custom.portals.config;

import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigGroup;

@ConfigEntries
public class CPSettings implements ConfigGroup {
    private static boolean portals_always_unlimited;
    private static int default_range;
    private static int range_with_enhancer;
    private static int range_with_strong_enhancer;

    public CPSettings() {
        default_range = 100;
        range_with_enhancer = 1000;
        range_with_strong_enhancer = 10000;
    }

    public static boolean portalsAlwaysUnlimited() { return portals_always_unlimited; }
    public static int getDefaultRange() { return default_range; }
    public static int getRangeWithEnhancer() { return range_with_enhancer; }
    public static int getRangeWithStrongEnhancer() { return range_with_strong_enhancer; }
}
