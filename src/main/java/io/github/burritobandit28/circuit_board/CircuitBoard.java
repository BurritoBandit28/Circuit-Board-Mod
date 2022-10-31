package io.github.burritobandit28.circuit_board;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircuitBoard implements ModInitializer {

	public static final String MOD_ID = "circuit_board";

	public static Identifier ID(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static final Logger LOGGER = LoggerFactory.getLogger("Circuit Board");

	@Override
	public void onInitialize() {
		Register.register();
	}
}
