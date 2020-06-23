package me.neon.redessentials.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.VanishManager;

public class CommandVanish extends AbstractCommand {

	public CommandVanish() {
		super(true, "vanish");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		VanishManager vanishManager = RedEssentials.getInstance().getModuleForClass(VanishManager.class);
		UUID playerUUID = RedEssentials.getInstance().translateNameToUUID(sender.getName().toLowerCase());
		
		if (vanishManager.containsPlayerInList(playerUUID)) {
			vanishManager.removeVanishFromPlayer((Player) sender);
			sender.sendMessage("§cVanish mode disabled.");
			return ReturnType.SUCCESS;
		}
		if (!vanishManager.containsPlayerInList(playerUUID)) {
			vanishManager.setVanishToPlayer((Player) sender);
			sender.sendMessage("§aVanish mode activated.");
			return ReturnType.SUCCESS;
		}
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.vanish";
	}

	@Override
	public String getSyntax() {
		return "/vanish";
	}

	@Override
	public String getDescription() {
		return "Stay invisible or visible to all players.";
	}
}
