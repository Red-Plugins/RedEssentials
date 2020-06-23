package me.neon.redessentials.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.FlyManager;

public class CommandFly extends AbstractCommand {
	
	public CommandFly() {
		super(true, "fly");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		FlyManager flyManager = RedEssentials.getInstance().getModuleForClass(FlyManager.class);
		
		UUID playerUUID = RedEssentials.getInstance().translateNameToUUID(sender.getName().toLowerCase());
		
		if (flyManager.contaisPlayerInList(playerUUID)) {
			flyManager.removeFlightFromPlayer((Player) sender); 
			sender.sendMessage("§cFlight mode disabled.");
			return ReturnType.SUCCESS;
		}
		if (!flyManager.contaisPlayerInList(playerUUID)) {
			flyManager.setFlightToPlayer((Player) sender);
			sender.sendMessage("§aFlight mode activated.");
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
		return "RedEssentials.command.fly";
	}

	@Override
	public String getSyntax() {
		return "/fly";
	}

	@Override
	public String getDescription() {
		return "Change your flight mode.";
	}
}
