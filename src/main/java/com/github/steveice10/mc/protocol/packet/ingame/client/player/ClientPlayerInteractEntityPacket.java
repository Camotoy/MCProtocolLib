package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.data.game.entity.player.InteractAction;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.math.vector.Vector3f;
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
public class ClientPlayerInteractEntityPacket implements Packet {
    private int entityId;
    private @NonNull InteractAction action;

    private Vector3f target;
    private @NonNull Hand hand;
    private boolean isSneaking;

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, boolean isSneaking) {
        this(entityId, action, Hand.MAIN_HAND, isSneaking);
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, Hand hand, boolean isSneaking) {
        this(entityId, action, Vector3f.ZERO, hand, isSneaking);
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.action = MagicValues.key(InteractAction.class, in.readVarInt());
        if(this.action == InteractAction.INTERACT_AT) {
            this.target = Vector3f.from(in.readFloat(), in.readFloat(), in.readFloat());
        }

        if(this.action == InteractAction.INTERACT || this.action == InteractAction.INTERACT_AT) {
            this.hand = MagicValues.key(Hand.class, in.readVarInt());
        }
        this.isSneaking = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(MagicValues.value(Integer.class, this.action));
        if(this.action == InteractAction.INTERACT_AT) {
            out.writeFloat(this.target.getX());
            out.writeFloat(this.target.getY());
            out.writeFloat(this.target.getZ());
        }

        if(this.action == InteractAction.INTERACT || this.action == InteractAction.INTERACT_AT) {
            out.writeVarInt(MagicValues.value(Integer.class, this.hand));
        }
        out.writeBoolean(this.isSneaking);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
