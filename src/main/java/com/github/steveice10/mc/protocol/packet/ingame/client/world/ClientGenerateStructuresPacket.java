package com.github.steveice10.mc.protocol.packet.ingame.client.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.math.vector.Vector3i;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClientGenerateStructuresPacket implements Packet {
    private @NonNull Vector3i position;
    private int levels;
    private boolean keepJigsaws;

    @Override
    public void read(NetInput in) throws IOException {
        this.position = Position.read(in);
        this.levels = in.readVarInt();
        this.keepJigsaws = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        Position.write(out, this.position);
        out.writeVarInt(this.levels);
        out.writeBoolean(this.keepJigsaws);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
