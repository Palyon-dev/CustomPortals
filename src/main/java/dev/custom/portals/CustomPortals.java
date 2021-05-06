package dev.custom.portals;

import dev.custom.portals.blocks.PortalBlock;
import dev.custom.portals.data.BasePortalComponent;
import dev.custom.portals.data.WorldPortals;
import dev.custom.portals.items.PortalCatalyst;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomPortals implements ModInitializer, ClientModInitializer, WorldComponentInitializer {

        public static final ComponentKey<BasePortalComponent> PORTALS = ComponentRegistryV3.INSTANCE
                .getOrCreate(new Identifier("customportals:portals"), BasePortalComponent.class);

        public static final ItemGroup PORTALS_ITEM_GROUP = FabricItemGroupBuilder.build(
                new Identifier("customportals", "general"),
                () -> new ItemStack(Items.ENDER_EYE));

        public static final DefaultParticleType BLACK_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType BLUE_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType BROWN_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType CYAN_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType GRAY_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType GREEN_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType LIGHT_BLUE_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType LIGHT_GRAY_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType LIME_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType MAGENTA_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType ORANGE_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType PINK_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType RED_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType WHITE_PORTAL_PARTICLE = FabricParticleTypes.simple();
        public static final DefaultParticleType YELLOW_PORTAL_PARTICLE = FabricParticleTypes.simple();

        public static final Block BLACK_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BLACK).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block BLUE_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BLUE).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block BROWN_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.BROWN).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block CYAN_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.CYAN).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block GRAY_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.GRAY).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block GREEN_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.GREEN).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block LIGHT_BLUE_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIGHT_BLUE).nonOpaque().noCollision()
                        .ticksRandomly().strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block LIGHT_GRAY_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIGHT_GRAY).nonOpaque().noCollision()
                        .ticksRandomly().strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block LIME_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.LIME).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block MAGENTA_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.MAGENTA).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block ORANGE_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.ORANGE).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block PINK_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.PINK).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block PURPLE_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.PURPLE).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block RED_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.RED).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block WHITE_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.WHITE).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
                                return 11;
                        }));
        public static final Block YELLOW_PORTAL = new PortalBlock(
                AbstractBlock.Settings.of(Material.PORTAL, MaterialColor.YELLOW).nonOpaque().noCollision().ticksRandomly()
                        .strength(-1.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {
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
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "black_portal_particle"), BLACK_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "blue_portal_particle"), BLUE_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "brown_portal_particle"), BROWN_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "cyan_portal_particle"), CYAN_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "gray_portal_particle"), GRAY_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "green_portal_particle"), GREEN_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "light_blue_portal_particle"), LIGHT_BLUE_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "light_gray_portal_particle"), LIGHT_GRAY_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "lime_portal_particle"), LIME_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "magenta_portal_particle"), MAGENTA_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "orange_portal_particle"), ORANGE_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "pink_portal_particle"), PINK_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "red_portal_particle"), RED_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "white_portal_particle"), WHITE_PORTAL_PARTICLE);
                Registry.register(Registry.PARTICLE_TYPE, new Identifier("customportals", "yellow_portal_particle"), YELLOW_PORTAL_PARTICLE);
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
                ParticleFactoryRegistry.getInstance().register(BLACK_PORTAL_PARTICLE, BlackPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(BLUE_PORTAL_PARTICLE, BluePortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(BROWN_PORTAL_PARTICLE, BrownPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(CYAN_PORTAL_PARTICLE, CyanPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(GRAY_PORTAL_PARTICLE, GrayPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(GREEN_PORTAL_PARTICLE, GreenPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(LIGHT_BLUE_PORTAL_PARTICLE, LightBluePortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(LIGHT_GRAY_PORTAL_PARTICLE, LightGrayPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(LIME_PORTAL_PARTICLE, LimePortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(MAGENTA_PORTAL_PARTICLE, MagentaPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(ORANGE_PORTAL_PARTICLE, OrangePortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(PINK_PORTAL_PARTICLE, PinkPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(RED_PORTAL_PARTICLE, RedPortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(WHITE_PORTAL_PARTICLE, WhitePortalParticle.Factory::new);
                ParticleFactoryRegistry.getInstance().register(YELLOW_PORTAL_PARTICLE, YellowPortalParticle.Factory::new);
        }

        @Environment(EnvType.CLIENT)
        static class BlackPortalParticle extends PortalParticle {

                protected BlackPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        this.colorRed = this.colorGreen = this.colorBlue = 0.0F;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new BlackPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class BluePortalParticle extends PortalParticle {

                protected BluePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 0.2F * j;
                        this.colorGreen = 0.2F * j;
                        this.colorBlue = 1.0F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new BluePortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class BrownPortalParticle extends PortalParticle {

                protected BrownPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 0.575F * j;
                        this.colorGreen = 0.45F * j;
                        this.colorBlue = 0.325F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new BrownPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class CyanPortalParticle extends PortalParticle {

                protected CyanPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorBlue = 0.8F * j;
                        this.colorRed = 0.2F * j;
                        this.colorGreen = 0.65F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new CyanPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class GrayPortalParticle extends PortalParticle {

                protected GrayPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = this.colorGreen = this.colorBlue = 0.5F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new GrayPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class GreenPortalParticle extends PortalParticle {

                protected GreenPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 0.2F * j;
                        this.colorGreen = 0.5F * j;
                        this.colorBlue = 0.2F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new GreenPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class LightBluePortalParticle extends PortalParticle {

                protected LightBluePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 0.6F * j;
                        this.colorBlue = 1.0F * j;
                        this.colorGreen = 0.7F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new LightBluePortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class LightGrayPortalParticle extends PortalParticle {

                protected LightGrayPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = this.colorGreen = this.colorBlue = 0.7F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new LightGrayPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class LimePortalParticle extends PortalParticle {

                protected LimePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 0.5F * j;
                        this.colorGreen = 1.0F * j;
                        this.colorBlue = 0.3F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new LimePortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class MagentaPortalParticle extends PortalParticle {

                protected MagentaPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 1.0F * j;
                        this.colorGreen = 0.4F * j;
                        this.colorBlue = 1.0F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new MagentaPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class OrangePortalParticle extends PortalParticle {

                protected OrangePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 1.0F * j;
                        this.colorGreen = 0.7F * j;
                        this.colorBlue = 0.2F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new OrangePortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class PinkPortalParticle extends PortalParticle {

                protected PinkPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 1.0F * j;
                        this.colorGreen = 0.6F * j;
                        this.colorBlue = 0.9F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new PinkPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class RedPortalParticle extends PortalParticle {

                protected RedPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 1.0F * j;
                        this.colorGreen = 0.2F * j;
                        this.colorBlue = 0.2F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new RedPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class WhitePortalParticle extends PortalParticle {

                protected WhitePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = this.colorGreen = this.colorBlue = 1.0F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new WhitePortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }

        @Environment(EnvType.CLIENT)
        static class YellowPortalParticle extends PortalParticle {

                protected YellowPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprites) {
                        super(clientWorld, d, e, f, g, h, i);
                        setSprite(sprites.getSprite(world.random));
                        float j = this.random.nextFloat() * 0.6F + 0.4F;
                        this.colorRed = 1.0F * j;
                        this.colorGreen = 1.0F * j;
                        this.colorBlue = 0.2F * j;
                }

                @Environment(EnvType.CLIENT)
                public static class Factory implements ParticleFactory<DefaultParticleType> {
                        private final FabricSpriteProvider sprites;

                        public Factory(FabricSpriteProvider sprites) {
                                this.sprites = sprites;
                        }

                        @Override
                        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
                                return new YellowPortalParticle(world, x, y, z, vX, vY, vZ, sprites);
                        }
                }
        }
    

        @Override
        public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
                registry.register(PORTALS, WorldPortals.class, WorldPortals::new);
        }

}