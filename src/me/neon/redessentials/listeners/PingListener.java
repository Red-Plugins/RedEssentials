package me.neon.redessentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {

	@EventHandler
	public void onServerPing(ServerListPingEvent event) {
		event.setMotd("�cTest server!!");
	}
}
