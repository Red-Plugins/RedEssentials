package me.neon.redessentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.setDeathMessage("");
	}
}
