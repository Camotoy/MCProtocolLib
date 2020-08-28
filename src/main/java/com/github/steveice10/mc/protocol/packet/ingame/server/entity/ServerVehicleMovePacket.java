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
public class ServerVehicleMovePacket implements Packet {
    private Vector3d position;
    private float yaw;
    private float pitch;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = Vector3d.from(in.readDouble(), in.readDouble(), in.readDouble());
        this.yaw = in.readFloat();
        this.pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeDouble(this.position.getX());
        out.writeDouble(this.position.getY());
        out.writeDouble(this.position.getZ());
        out.writeFloat(this.yaw);
        out.writeFloat(this.pitch);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
