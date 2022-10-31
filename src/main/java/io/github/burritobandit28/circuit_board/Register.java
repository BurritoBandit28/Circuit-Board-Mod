package io.github.burritobandit28.circuit_board;


import io.github.burritobandit28.circuit_board.blockentities.CircuitBoardBlockEntity;
import io.github.burritobandit28.circuit_board.blockentities.GreenIOBlockEntity;
import io.github.burritobandit28.circuit_board.blockentities.RedIOBlockEntity;
import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlocks;
import io.github.burritobandit28.circuit_board.items.CircuitBoardItems;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.util.registry.Registry;

public class Register {

    public static void registerItems() {
        CircuitBoard.LOGGER.info("Registering items");
        Registry.register(Registry.ITEM, CircuitBoard.ID("circuit_board"), CircuitBoardItems.CIRCUIT_BOARD_ITEM);
        Registry.register(Registry.ITEM, CircuitBoard.ID("green_io_block"), CircuitBoardItems.GREEN_IO_BLOCK_ITEM);
        Registry.register(Registry.ITEM, CircuitBoard.ID("red_io_block"), CircuitBoardItems.RED_IO_BLOCK_ITEM);
    }

    public static void registerBlocks() {
        CircuitBoard.LOGGER.info("Registering blocks");
        Registry.register(Registry.BLOCK, CircuitBoard.ID("circuit_board"), CircuitBoardBlocks.CIRCUIT_BOARD_BLOCK);
        Registry.register(Registry.BLOCK, CircuitBoard.ID("green_io_block"), CircuitBoardBlocks.GREEN_IO_BLOCK);
        Registry.register(Registry.BLOCK, CircuitBoard.ID("red_io_block"), CircuitBoardBlocks.RED_IO_BLOCK);
    }

    public static void registerBlockEntities() {
        CircuitBoard.LOGGER.info("Registering block entities");
        CircuitBoardBlockEntity.GREEN_IO_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, CircuitBoard.ID("green_io_block_entity"),
                FabricBlockEntityTypeBuilder.create(GreenIOBlockEntity::new, CircuitBoardBlocks.GREEN_IO_BLOCK).build());
        CircuitBoardBlockEntity.RED_IO_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, CircuitBoard.ID("red_io_block_entity"),
                FabricBlockEntityTypeBuilder.create(RedIOBlockEntity::new, CircuitBoardBlocks.RED_IO_BLOCK).build());
    }

    public static void register(){
        registerBlocks();
        registerItems();
        registerBlockEntities();
        CircuitBoard.LOGGER.info("Registering done");
    }
}
