package com.github.steveice10.mc.protocol.data.game.world.block;

import com.nukkitx.math.vector.Vector3i;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class BlockChangeRecord {
    private final @NonNull Vector3i position;
    private final @NonNull int block;
}
