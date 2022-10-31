package io.github.burritobandit28.circuit_board.items;

import io.github.burritobandit28.circuit_board.blocks.CircuitBoardBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class CircuitBoardItems {

    public static final BlockItem GREEN_IO_BLOCK_ITEM = new BlockItem(CircuitBoardBlocks.GREEN_IO_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem RED_IO_BLOCK_ITEM = new BlockItem(CircuitBoardBlocks.RED_IO_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem CIRCUIT_BOARD_ITEM = new BlockItem(CircuitBoardBlocks.CIRCUIT_BOARD_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));

}
