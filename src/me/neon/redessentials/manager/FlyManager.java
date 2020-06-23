package me.neon.redessentials.manager;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.utils.IModule;

public class FlyManager implements IModule {
	
	private ArrayList<UUID> flyingPlayers = new ArrayList<UUID>();
	
	void addPlayerToList(UUID uuid) {
		if (contaisPlayerInList(uuid)) return;
		flyingPlayers.add(uuid);
	}
	
	void removePlayerFromList(UUID uuid) {
		if (!contaisPlayerInList(uuid)) return;
		flyingPlayers.remove(uuid);
	}
	
	public boolean contaisPlayerInList(UUID uuid) {
		if (flyingPlayers.contains(uuid)) return true;
		return false;
	}
	
	public void setFlightToPlayer(Player player) {
		UUID uuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		if (contaisPlayerInList(uuid)) return;
		addPlayerToList(uuid);
		player.setAllowFlight(true);
		player.setFlying(true);
	}
	
	public void removeFlightFromPlayer(Player player) {
		UUID uuid = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		if (!contaisPlayerInList(uuid)) return;
		removePlayerFromList(uuid);
		player.setAllowFlight(false);
		player.setFlying(false);
	}
}
