package io.github.burritobandit28.circuit_board.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.StringIdentifiable;

public class CircuitBoardBlocks {

        public static final Block GREEN_IO_BLOCK = new IOBlock(FabricBlockSettings.of(Material.SCULK, MapColor.LIME).nonOpaque(), colours.green);
        public static final Block RED_IO_BLOCK = new IOBlock(FabricBlockSettings.of(Material.SCULK, MapColor.RED).nonOpaque(), colours.red);
        public static final Block CIRCUIT_BOARD_BLOCK = new CircuitBoardBlock(FabricBlockSettings.copyOf(Blocks.REPEATER));

        public enum colours{
                red,
                pink,
                green,
                blue
        }
}
