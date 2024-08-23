package net.dawkmeow.quinta;

import net.dawkmeow.quinta.event.PlayerTickHandler;
import net.dawkmeow.quinta.item.ModItems;
import net.dawkmeow.quinta.item.ModItemsGroup;
import net.dawkmeow.quinta.networking.ModNetworking;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quinta implements ModInitializer {
	public static final String MOD_ID = "quinta";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Quinta mod");
		ModItems.registerModItems();
		ModItemsGroup.registerItemGroups();
		ModNetworking.registerC2SPackets();
		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
	}
}