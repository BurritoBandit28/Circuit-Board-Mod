package io.github.burritobandit28.circuit_board.blockentities;

import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GreenIOBlockEntity extends IOBlockEntity{
    public GreenIOBlockEntity(BlockPos pos, BlockState state) {
        super(CircuitBoardBlockEntity.GREEN_IO_BLOCK_ENTITY_TYPE, pos, state, CircuitBoardBlocks.colours.green);
    }
}
