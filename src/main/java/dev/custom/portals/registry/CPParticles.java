package dev.custom.portals.registry;

import dev.custom.portals.CustomPortals;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class CPParticles {

    public static final SimpleParticleType BLACK_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType BLUE_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType BROWN_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType CYAN_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType GRAY_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType GREEN_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType LIGHT_BLUE_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType LIGHT_GRAY_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType LIME_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType MAGENTA_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType ORANGE_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType PINK_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType RED_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType WHITE_PORTAL_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType YELLOW_PORTAL_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "black_portal_particle"), BLACK_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "blue_portal_particle"), BLUE_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "brown_portal_particle"), BROWN_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "cyan_portal_particle"), CYAN_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "gray_portal_particle"), GRAY_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "green_portal_particle"), GREEN_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "light_blue_portal_particle"), LIGHT_BLUE_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "light_gray_portal_particle"), LIGHT_GRAY_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "lime_portal_particle"), LIME_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "magenta_portal_particle"), MAGENTA_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "orange_portal_particle"), ORANGE_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "pink_portal_particle"), PINK_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "red_portal_particle"), RED_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "white_portal_particle"), WHITE_PORTAL_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CustomPortals.MOD_ID, "yellow_portal_particle"), YELLOW_PORTAL_PARTICLE);
    }

    @Environment(EnvType.CLIENT)
    public static void registerFactoryRegistries() {
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

            protected BlackPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
                    super(clientWorld, d, e, f, g, h, i, sprite);
                    this.red = this.green = this.blue = 0.0F;
            }

            @Environment(EnvType.CLIENT)
            public static class Factory implements ParticleFactory<SimpleParticleType> {
                    private final FabricSpriteProvider spriteProvider;

                    public Factory(FabricSpriteProvider sprites) {
                            this.spriteProvider = sprites;
                    }

                    @Override
                    public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
                            return new BlackPortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
                    }
            }
    }

    @Environment(EnvType.CLIENT)
    static class BluePortalParticle extends PortalParticle {

            protected BluePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
                    super(clientWorld, d, e, f, g, h, i, sprite);
                    float j = this.random.nextFloat() * 0.6F + 0.4F;
                    this.red = 0.2F * j;
                    this.green = 0.2F * j;
                    this.blue = 1.0F * j;
            }

            @Environment(EnvType.CLIENT)
            public static class Factory implements ParticleFactory<SimpleParticleType> {
                    private final FabricSpriteProvider spriteProvider;

                    public Factory(FabricSpriteProvider sprites) {
                            this.spriteProvider = sprites;
                    }

                    @Override
                    public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
                            return new BluePortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
                    }
            }
    }

    @Environment(EnvType.CLIENT)
    static class BrownPortalParticle extends PortalParticle {

            protected BrownPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
                    super(clientWorld, d, e, f, g, h, i, sprite);
                    float j = this.random.nextFloat() * 0.6F + 0.4F;
                    this.red = 0.575F * j;
                    this.green = 0.45F * j;
                    this.blue = 0.325F * j;
            }

            @Environment(EnvType.CLIENT)
            public static class Factory implements ParticleFactory<SimpleParticleType> {
                    private final FabricSpriteProvider spriteProvider;

                    public Factory(FabricSpriteProvider sprites) {
                            this.spriteProvider = sprites;
                    }

                    @Override
                    public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
                            return new BrownPortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
                    }
            }
    }

    @Environment(EnvType.CLIENT)
    static class CyanPortalParticle extends PortalParticle {

            protected CyanPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
                    super(clientWorld, d, e, f, g, h, i, sprite);
                    float j = this.random.nextFloat() * 0.6F + 0.4F;
                    this.blue = 0.8F * j;
                    this.red = 0.2F * j;
                    this.green = 0.65F * j;
            }

            @Environment(EnvType.CLIENT)
            public static class Factory implements ParticleFactory<SimpleParticleType> {
                    private final FabricSpriteProvider spriteProvider;

                    public Factory(FabricSpriteProvider sprites) {
                            this.spriteProvider = sprites;
                    }

                    @Override
                    public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
                            return new CyanPortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
                    }
            }
    }

    @Environment(EnvType.CLIENT)
    static class GrayPortalParticle extends PortalParticle {

            protected GrayPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
                    super(clientWorld, d, e, f, g, h, i, sprite);
                    float j = this.random.nextFloat() * 0.6F + 0.4F;
                    this.red = this.green = this.blue = 0.5F * j;
            }

            @Environment(EnvType.CLIENT)
            public static class Factory implements ParticleFactory<SimpleParticleType> {
                    private final FabricSpriteProvider spriteProvider;

                    public Factory(FabricSpriteProvider sprites) {
                            this.spriteProvider = sprites;
                    }

                    @Override
                    public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
                            return new GrayPortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
                    }
            }
    }

    @Environment(EnvType.CLIENT)
    static class GreenPortalParticle extends PortalParticle {

        protected GreenPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 0.2F * j;
            this.green = 0.5F * j;
            this.blue = 0.2F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new GreenPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class LightBluePortalParticle extends PortalParticle {

        protected LightBluePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 0.6F * j;
            this.blue = 1.0F * j;
            this.green = 0.7F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new LightBluePortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class LightGrayPortalParticle extends PortalParticle {

        protected LightGrayPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = this.green = this.blue = 0.7F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new LightGrayPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class LimePortalParticle extends PortalParticle {

        protected LimePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 0.5F * j;
            this.green = 1.0F * j;
            this.blue = 0.3F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new LimePortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class MagentaPortalParticle extends PortalParticle {

        protected MagentaPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 1.0F * j;
            this.green = 0.4F * j;
            this.blue = 1.0F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new MagentaPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class OrangePortalParticle extends PortalParticle {

        protected OrangePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 1.0F * j;
            this.green = 0.7F * j;
            this.blue = 0.2F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new OrangePortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class PinkPortalParticle extends PortalParticle {

        protected PinkPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 1.0F * j;
            this.green = 0.6F * j;
            this.blue = 0.9F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new PinkPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class RedPortalParticle extends PortalParticle {

        protected RedPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 1.0F * j;
            this.green = 0.2F * j;
            this.blue = 0.2F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new RedPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class WhitePortalParticle extends PortalParticle {

        protected WhitePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = this.green = this.blue = 1.0F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new WhitePortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    static class YellowPortalParticle extends PortalParticle {

        protected YellowPortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
            super(clientWorld, d, e, f, g, h, i, sprite);
            float j = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = 1.0F * j;
            this.green = 1.0F * j;
            this.blue = 0.2F * j;
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final FabricSpriteProvider spriteProvider;

            public Factory(FabricSpriteProvider sprites) {
                this.spriteProvider = sprites;
            }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientWorld world, double d, double e, double f, double g, double h, double i, Random random) {
                return new YellowPortalParticle(world, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
            }
        }
    }
}
