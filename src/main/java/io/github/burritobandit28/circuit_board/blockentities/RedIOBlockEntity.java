package io.github.burritobandit28.circuit_board.blockentities;

import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RedIOBlockEntity extends IOBlockEntity{
    public RedIOBlockEntity(BlockPos pos, BlockState state) {
        super(CircuitBoardBlockEntity.RED_IO_BLOCK_ENTITY_TYPE, pos, state, CircuitBoardBlocks.colours.red);
    }
}
