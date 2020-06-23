package me.neon.redessentials.manager;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.utils.IModule;

public class VanishManager implements IModule {

	private ArrayList<UUID> vanishingPlayers = new ArrayList<UUID>();
	
	void addPlayerToList(UUID uuid) {
		if (containsPlayerInList(uuid)) return;
		vanishingPlayers.add(uuid);
	}
	
	void removePlayerFromList(UUID uuid) {
		if (!containsPlayerInList(uuid)) return;
		vanishingPlayers.remove(uuid);
	}
	
	public boolean containsPlayerInList(UUID uuid) {
		if (vanishingPlayers.contains(uuid)) return true;
		return false;
	}
	
	public void setVanishToPlayer(Player player) {
		UUID uuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		if (containsPlayerInList(uuid)) return;
		addPlayerToList(uuid);
		for (Player unknownPlayer : Bukkit.getOnlinePlayers()) {
			unknownPlayer.hidePlayer(player);
		}
	}
	
	public void removeVanishFromPlayer(Player player) {
		UUID uuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		if (!containsPlayerInList(uuid)) return;
		removePlayerFromList(uuid);
		for (Player unknownPlayer : Bukkit.getOnlinePlayers()) {
			unknownPlayer.showPlayer(player);
		}
	}
}
