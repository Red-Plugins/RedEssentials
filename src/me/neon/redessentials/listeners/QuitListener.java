package me.neon.redessentials.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.manager.FlyManager;

public class QuitListener implements Listener {
	
	FlyManager flyManager = RedEssentials.getInstance().getModuleForClass(FlyManager.class);
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID playerUuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		event.setQuitMessage("");
		if (flyManager.contaisPlayerInList(playerUuid)) {
			flyManager.removeFlightFromPlayer(player);
		}
	}
}
