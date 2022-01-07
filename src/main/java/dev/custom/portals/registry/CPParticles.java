package dev.custom.portals.registry;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.registry.CPParticles.BlackPortalParticle;
import dev.custom.portals.registry.CPParticles.BluePortalParticle;
import dev.custom.portals.registry.CPParticles.BrownPortalParticle;
import dev.custom.portals.registry.CPParticles.CyanPortalParticle;
import dev.custom.portals.registry.CPParticles.GrayPortalParticle;
import dev.custom.portals.registry.CPParticles.GreenPortalParticle;
import dev.custom.portals.registry.CPParticles.LightBluePortalParticle;
import dev.custom.portals.registry.CPParticles.LightGrayPortalParticle;
import dev.custom.portals.registry.CPParticles.LimePortalParticle;
import dev.custom.portals.registry.CPParticles.MagentaPortalParticle;
import dev.custom.portals.registry.CPParticles.OrangePortalParticle;
import dev.custom.portals.registry.CPParticles.PinkPortalParticle;
import dev.custom.portals.registry.CPParticles.RedPortalParticle;
import dev.custom.portals.registry.CPParticles.WhitePortalParticle;
import dev.custom.portals.registry.CPParticles.YellowPortalParticle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CPParticles {

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

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "black_portal_particle"), BLACK_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "blue_portal_particle"), BLUE_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "brown_portal_particle"), BROWN_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "cyan_portal_particle"), CYAN_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "gray_portal_particle"), GRAY_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "green_portal_particle"), GREEN_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "light_blue_portal_particle"), LIGHT_BLUE_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "light_gray_portal_particle"), LIGHT_GRAY_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "lime_portal_particle"), LIME_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "magenta_portal_particle"), MAGENTA_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "orange_portal_particle"), ORANGE_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "pink_portal_particle"), PINK_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "red_portal_particle"), RED_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "white_portal_particle"), WHITE_PORTAL_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomPortals.MOD_ID, "yellow_portal_particle"), YELLOW_PORTAL_PARTICLE);
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
}
