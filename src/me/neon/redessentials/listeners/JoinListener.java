package me.neon.redessentials.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.manager.FlyManager;

public class JoinListener implements Listener {
	
	FlyManager flyManager = RedEssentials.getInstance().getModuleForClass(FlyManager.class);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID playerUuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		event.setJoinMessage("");
		if (player.isOp()) {
			if (!flyManager.contaisPlayerInList(playerUuid)) {
				flyManager.setFlightToPlayer(player);
				player.sendMessage("§aFlight mode activated automatically.");
			}
		}
		if (RedEssentials.getInstance().allPlayers.contains(player.getName())) return;
		RedEssentials.getInstance().allPlayers.add(player.getName());
	}
}
