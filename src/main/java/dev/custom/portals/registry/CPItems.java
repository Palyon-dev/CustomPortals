package dev.custom.portals.registry;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.items.PortalCatalyst;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CPItems {

    public static final ItemGroup PORTALS_ITEM_GROUP = CustomPortals.PORTALS_ITEM_GROUP;
    
    // Items
    public static final Item WHITE_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.WHITE_PORTAL);
    public static final Item ORANGE_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.ORANGE_PORTAL);
    public static final Item MAGENTA_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.MAGENTA_PORTAL);
    public static final Item LIGHT_BLUE_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.LIGHT_BLUE_PORTAL);
    public static final Item YELLOW_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.YELLOW_PORTAL);
    public static final Item LIME_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.LIME_PORTAL);
    public static final Item PINK_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.PINK_PORTAL);
    public static final Item GRAY_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.GRAY_PORTAL);
    public static final Item LIGHT_GRAY_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.LIGHT_GRAY_PORTAL);
    public static final Item CYAN_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.CYAN_PORTAL);
    public static final Item PURPLE_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.PURPLE_PORTAL);
    public static final Item BLUE_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.BLUE_PORTAL);
    public static final Item BROWN_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.BROWN_PORTAL);
    public static final Item GREEN_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.GREEN_PORTAL);
    public static final Item RED_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.RED_PORTAL);
    public static final Item BLACK_PORTAL_CATALYST = new PortalCatalyst(
        new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CPBlocks.BLACK_PORTAL);

    // BlockItems
    public static final BlockItem HASTE_RUNE = new BlockItem(
        CPBlocks.HASTE_RUNE_BLOCK, new Item.Settings().maxCount(1).group(PORTALS_ITEM_GROUP));
    public static final BlockItem GATE_RUNE = new BlockItem(
        CPBlocks.GATE_RUNE_BLOCK, new Item.Settings().maxCount(1).group(PORTALS_ITEM_GROUP));
    public static final BlockItem WEAK_ENHANCER_RUNE = new BlockItem(
        CPBlocks.WEAK_ENHANCER_RUNE_BLOCK, new Item.Settings().maxCount(1).group(PORTALS_ITEM_GROUP));
    public static final BlockItem STRONG_ENHANCER_RUNE = new BlockItem(
        CPBlocks.STRONG_ENHANCER_RUNE_BLOCK, new Item.Settings().maxCount(1).group(PORTALS_ITEM_GROUP));
    public static final BlockItem INFINITY_RUNE = new BlockItem(
        CPBlocks.INFINITY_RUNE_BLOCK, new Item.Settings().maxCount(1).group(PORTALS_ITEM_GROUP));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "white_portal_catalyst"),
            WHITE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "orange_portal_catalyst"),
            ORANGE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "magenta_portal_catalyst"),
            MAGENTA_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "light_blue_portal_catalyst"),
            LIGHT_BLUE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "yellow_portal_catalyst"),
            YELLOW_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "lime_portal_catalyst"), LIME_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "pink_portal_catalyst"), PINK_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "gray_portal_catalyst"), GRAY_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "light_gray_portal_catalyst"),
            LIGHT_GRAY_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "cyan_portal_catalyst"), CYAN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "purple_portal_catalyst"),
            PURPLE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "blue_portal_catalyst"), BLUE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "brown_portal_catalyst"),
            BROWN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "green_portal_catalyst"),
            GREEN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "red_portal_catalyst"), RED_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "black_portal_catalyst"),
            BLACK_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "haste_rune"), HASTE_RUNE);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "gate_rune"), GATE_RUNE);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "weak_enhancer_rune"), WEAK_ENHANCER_RUNE);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "strong_enhancer_rune"), STRONG_ENHANCER_RUNE);
        Registry.register(Registry.ITEM, new Identifier(CustomPortals.MOD_ID, "infinity_rune"), INFINITY_RUNE);
    }
}
