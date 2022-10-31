package io.github.burritobandit28.circuit_board.blocks;

import io.github.burritobandit28.circuit_board.CircuitBoard;
import io.github.burritobandit28.circuit_board.blockentities.CircuitBoardBlockEntity;
import io.github.burritobandit28.circuit_board.blockentities.GreenIOBlockEntity;
import io.github.burritobandit28.circuit_board.blockentities.IOBlockEntity;
import io.github.burritobandit28.circuit_board.blockentities.RedIOBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class IOBlock extends BlockWithEntity implements BlockEntityProvider {


    private static final BooleanProperty isInput = BooleanProperty.of("is_input");
    private CircuitBoardBlocks.colours colour;

    private int signalStrength;

    public IOBlock(Settings settings, CircuitBoardBlocks.colours colours) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(isInput, true));
        this.colour = colours;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        CircuitBoard.LOGGER.info("appending properties");
        builder.add(isInput);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockState(pos).get(isInput)) {
            world.setBlockState(pos, state.with(isInput, false));
            player.playSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, 1f, 1f);

        } else {
            world.setBlockState(pos, state.with(isInput, true));
            player.playSound(SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, 1f, 1f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        switch (colour) {
            case green->{ return new GreenIOBlockEntity(pos, state);}
            case red->{ return new RedIOBlockEntity(pos, state);}
            default -> {
                CircuitBoard.LOGGER.warn("No appropriate block entity found for this IO block");
                return null;
            }
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {


        return checkType(type, getBlockEntity(colour), (world1, pos, state1, be) -> IOBlockEntity.tick(world1, pos, state1, (IOBlockEntity) be));
    }
    private BlockEntityType getBlockEntity(CircuitBoardBlocks.colours colours) {
        switch (colours) {
            case green-> {  return CircuitBoardBlockEntity.GREEN_IO_BLOCK_ENTITY_TYPE;
            }
            case red -> {   return CircuitBoardBlockEntity.RED_IO_BLOCK_ENTITY_TYPE;
            }
            default -> {
                CircuitBoard.LOGGER.warn("No appropriate block entity found for this IO block");
                return null;
            }
        }
    }

    public boolean emitsRedstonePower(BlockState state) {
        return state.get(isInput);
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return this.signalStrength;
    }
    public void setSignalStrength(int strength) {
        this.signalStrength = strength;
    }
}

/*
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean pwrd = state.get(powered);
            if (pwrd != world.isReceivingRedstonePower(pos)) {
                if (pwrd) world.setBlockState(pos, state.with(powered, false));
                else world.setBlockState(pos, state.with(powered, true));
            }

        }
    }

     */
