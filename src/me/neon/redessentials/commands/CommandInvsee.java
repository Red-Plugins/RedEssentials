package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandInvsee extends AbstractCommand {

	public CommandInvsee() {
		super(true, "invsee");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length < 1) return ReturnType.SYNTAX_ERROR;
		
		Player targetPlayer = Bukkit.getPlayerExact(args[0]);
		if (targetPlayer == null) return ReturnType.INVALID_PLAYER;
		
		((Player) sender).openInventory(targetPlayer.getInventory());
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		if (args.length == 1) {
			List<String> players = new ArrayList<String>();
			for (Player unknownPlayer : Bukkit.getOnlinePlayers()) {
				players.add(unknownPlayer.getName());
			}
			return players;
		}
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.invsee";
	}

	@Override
	public String getSyntax() {
		return "/invsee (player)";
	}

	@Override
	public String getDescription() {
		return "Open a inventory.";
	}
}
