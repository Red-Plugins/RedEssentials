package me.neon.redessentials.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.configuration.ConfigurationManager;

public class BlockedCommands implements Listener {
	
	private ConfigurationManager configurationManager = RedEssentials.getInstance().getModuleForClass(ConfigurationManager.class);
	private YamlConfiguration config = configurationManager.getDefaultConfig().getConfig();
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		for (String command : config.getStringList("BlockedCommands")) {
			if (message.contains(command)) {
				if (!player.isOp()) {
					event.setCancelled(true);
					player.sendMessage("§cInvalid command.");
				}
			}
		}
	}
}
