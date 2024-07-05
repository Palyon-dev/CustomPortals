package dev.custom.portals.util;

import net.minecraft.block.Block;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record DrawSpritePayload(Integer colorId) implements CustomPayload {
    public static final CustomPayload.Id<DrawSpritePayload> ID = new CustomPayload.Id<>(PortalHelper.DRAW_SPRITE_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, DrawSpritePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, DrawSpritePayload::colorId, DrawSpritePayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
