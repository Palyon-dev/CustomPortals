package dev.custom.portals.util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ScreenTransitionPayload(Boolean isTransitioning) implements CustomPayload {
    public static final CustomPayload.Id<ScreenTransitionPayload> ID = new CustomPayload.Id<>(PortalHelper.SCREEN_TRANSITION_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, ScreenTransitionPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOLEAN, ScreenTransitionPayload::isTransitioning, ScreenTransitionPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
