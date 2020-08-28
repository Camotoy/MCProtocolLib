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
public class ServerEntityVelocityPacket implements Packet {
    private int entityId;
    private Vector3d motion;

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.motion = Vector3d.from(in.readShort() / 8000D, in.readShort() / 8000D, in.readShort() / 8000D);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeShort((int) (this.motion.getX() * 8000));
        out.writeShort((int) (this.motion.getY() * 8000));
        out.writeShort((int) (this.motion.getZ() * 8000));
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
