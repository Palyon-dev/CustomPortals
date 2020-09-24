package dev.custom.portals;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.data.BasePortalComponent;
import dev.custom.portals.data.WorldPortals;
import dev.custom.portals.items.PortalCatalyst;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomPortals implements ModInitializer, ClientModInitializer, WorldComponentInitializer {

    public static final ComponentType<BasePortalComponent> PORTALS = ComponentRegistry.INSTANCE
            .registerIfAbsent(new Identifier("customportals:portals"), BasePortalComponent.class);

    public static final ItemGroup PORTALS_ITEM_GROUP = FabricItemGroupBuilder.build(
        new Identifier("customportals", "general"),
        () -> new ItemStack(Items.ENDER_EYE));

    public static final Block BLACK_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BLACK).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block BLUE_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BLUE).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block BROWN_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BROWN).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block CYAN_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.CYAN).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block GRAY_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.GRAY).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block GREEN_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.GREEN).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block LIGHT_BLUE_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIGHT_BLUE).nonOpaque().noCollision()
                    .ticksRandomly().strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block LIGHT_GRAY_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIGHT_GRAY).nonOpaque().noCollision()
                    .ticksRandomly().strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block LIME_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIME).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block MAGENTA_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.MAGENTA).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block ORANGE_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.ORANGE).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block PINK_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.PINK).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block PURPLE_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.PURPLE).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block RED_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.RED).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block WHITE_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.WHITE).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Block YELLOW_PORTAL = new PortalBlock(
            AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.YELLOW).nonOpaque().noCollision().ticksRandomly()
                    .strength(-1.0F).sounds(BlockSoundGroup.GLASS).lightLevel((state) -> {
                        return 11;
                    }));
    public static final Item BLACK_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), BLACK_PORTAL);
    public static final Item BLUE_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), BLUE_PORTAL);
    public static final Item BROWN_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), BROWN_PORTAL);
    public static final Item CYAN_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), CYAN_PORTAL);
    public static final Item GRAY_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), GRAY_PORTAL);
    public static final Item GREEN_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), GREEN_PORTAL);
    public static final Item LIGHT_BLUE_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), LIGHT_BLUE_PORTAL);
    public static final Item LIGHT_GRAY_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), LIGHT_GRAY_PORTAL);
    public static final Item LIME_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), LIME_PORTAL);
    public static final Item MAGENTA_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), MAGENTA_PORTAL);
    public static final Item ORANGE_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), ORANGE_PORTAL);
    public static final Item PINK_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), PINK_PORTAL);
    public static final Item PURPLE_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), PURPLE_PORTAL);
    public static final Item RED_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), RED_PORTAL);
    public static final Item WHITE_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), WHITE_PORTAL);
    public static final Item YELLOW_PORTAL_CATALYST = new PortalCatalyst(
            new Item.Settings().maxCount(16).group(PORTALS_ITEM_GROUP), YELLOW_PORTAL);

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("customportals", "black_portal_catalyst"),
                BLACK_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "blue_portal_catalyst"), BLUE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "brown_portal_catalyst"),
                BROWN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "cyan_portal_catalyst"), CYAN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "gray_portal_catalyst"), GRAY_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "green_portal_catalyst"),
                GREEN_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "light_blue_portal_catalyst"),
                LIGHT_BLUE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "light_gray_portal_catalyst"),
                LIGHT_GRAY_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "lime_portal_catalyst"), LIME_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "magenta_portal_catalyst"),
                MAGENTA_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "orange_portal_catalyst"),
                ORANGE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "pink_portal_catalyst"), PINK_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "purple_portal_catalyst"),
                PURPLE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "red_portal_catalyst"), RED_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "white_portal_catalyst"),
                WHITE_PORTAL_CATALYST);
        Registry.register(Registry.ITEM, new Identifier("customportals", "yellow_portal_catalyst"),
                YELLOW_PORTAL_CATALYST);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "black_portal"), BLACK_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "blue_portal"), BLUE_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "brown_portal"), BROWN_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "cyan_portal"), CYAN_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "gray_portal"), GRAY_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "green_portal"), GREEN_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "light_blue_portal"), LIGHT_BLUE_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "light_gray_portal"), LIGHT_GRAY_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "lime_portal"), LIME_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "magenta_portal"), MAGENTA_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "orange_portal"), ORANGE_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "pink_portal"), PINK_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "purple_portal"), PURPLE_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "red_portal"), RED_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "white_portal"), WHITE_PORTAL);
        Registry.register(Registry.BLOCK, new Identifier("customportals", "yellow_portal"), YELLOW_PORTAL);
    }

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.BLACK_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.BLUE_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.BROWN_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.CYAN_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.GRAY_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.GREEN_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.LIGHT_BLUE_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.LIGHT_GRAY_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.LIME_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.MAGENTA_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.ORANGE_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.PINK_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.PURPLE_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.RED_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.WHITE_PORTAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomPortals.YELLOW_PORTAL, RenderLayer.getTranslucent());
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(PORTALS, WorldPortals.class, WorldPortals::new);
    }

}