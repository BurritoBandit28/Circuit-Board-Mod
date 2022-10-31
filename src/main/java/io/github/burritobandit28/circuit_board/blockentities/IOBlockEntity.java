package io.github.burritobandit28.circuit_board.blockentities;

import io.github.burritobandit28.circuit_board.CircuitBoard;
import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlock;
import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlocks;
import io.github.burritobandit28.circuit_board.blocks.IOBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class IOBlockEntity extends BlockEntity {

    private BlockPos boardPos;
    private int rPower;
    private CircuitBoardBlocks.colours colours;

    public IOBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, CircuitBoardBlocks.colours colours) {
        super(type, pos, state);
        //setBoardPos(pos.offset(Direction.UP,1));
        this.colours = colours;
    }

    public static int getRedstoneOutput(BlockPos boardPos, World world, BlockPos pos, BlockState state, CircuitBoardBlocks.colours colours) {
        if (!world.isClient) {
            Direction facing;
            Direction opposite;
            Direction left;
            Direction right;
            CircuitBoardBlock boardBlock;

            try {
                facing = world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING);
                opposite = facing.getOpposite();
                left = facing.rotateYCounterclockwise();
                right = facing.rotateYClockwise();
                boardBlock = (CircuitBoardBlock) world.getBlockState(boardPos).getBlock();

            }
            catch (Exception e){
                return 0;
            }

            if (state.get(BooleanProperty.of("is_input"))) {
                try {
                    switch (colours) {
                        case green -> {
                            System.out.println(boardBlock.getStrongRedstonePower(state, world, boardPos, facing));
                            return world.getEmittedRedstonePower(boardPos.offset(facing, 1), world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING).getOpposite());
                        }
                        case red -> {
                            //System.out.println(boardBlock.getStrongRedstonePower(state, world, boardPos, left));
                            return world.getEmittedRedstonePower(boardPos.offset(world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING).rotateCounterclockwise(world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING).getAxis()), 1), world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING).rotateCounterclockwise(world.getBlockState(boardPos).get(Properties.HORIZONTAL_FACING).getAxis()).getOpposite());
                        }
                        default -> {
                            return world.getReceivedRedstonePower(pos);
                        }
                    }
                } catch (Exception ignored) {
                    //CircuitBoard.LOGGER.error("No circuit board found for IO block @ "+ pos.toString());
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    //okay this naming was confusing me as well, this means getting the redstone out put for the circuit board, so the input for the IOBlock
    public int getRedstoneInput(BlockPos pos, World world) {
        return world.getReceivedRedstonePower(pos);
    }

    public void setBoardPos(BlockPos pos){
        boardPos = pos;
        //System.out.println(pos.toString());
    }

    public void setRPower(int rPower, World world, BlockPos pos) {
        this.rPower = rPower;
        IOBlock block = (IOBlock) world.getBlockState(pos).getBlock();
        block.setSignalStrength(this.rPower);
    }

    public static void tick(World world, BlockPos pos, BlockState state, IOBlockEntity be) {
        be.setRPower(getRedstoneOutput(be.boardPos, world, pos, state, be.colours), world, pos);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        BlockPos pos;
        int[] nbtPos = nbt.getIntArray("board_pos");
        pos = new BlockPos(nbtPos[0], nbtPos[1], nbtPos[2]);
        setBoardPos(pos);
        System.out.println(pos + "aaaaaaaaaaaaaaaaaaaaaaaaaawawawawawwawaaaaa");
        //System.out.printf("%s, %s, %s",nbtPos[0], nbtPos[1], nbtPos[2]);
        super.readNbt(nbt);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        int[] pos;
        if (this.boardPos != null){
             pos = new int[]{this.boardPos.getX(), this.boardPos.getY(), this.boardPos.getZ()};
        }
        else {
            pos = new int[]{0, 0, 0};
        }
        //System.out.printf("%s, %s, %s",pos[0], pos[1], pos[2]);
        nbt.putIntArray("board_pos",pos);
        nbt.putInt("power", this.rPower);

        super.writeNbt(nbt);
    }
}
