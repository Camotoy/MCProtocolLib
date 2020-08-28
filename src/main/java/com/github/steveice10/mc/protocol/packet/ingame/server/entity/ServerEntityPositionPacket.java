package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.math.vector.Vector3d;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ServerEntityPositionPacket implements Packet {
    private int entityId;
    private Vector3d movement;
    private boolean onGround;

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.movement = Vector3d.from(in.readShort() / 4096D, in.readShort() / 4096D, in.readShort() / 4096D);
        this.onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeShort((int) (this.movement.getX() * 4096));
        out.writeShort((int) (this.movement.getY() * 4096));
        out.writeShort((int) (this.movement.getZ() * 4096));
        out.writeBoolean(this.onGround);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
