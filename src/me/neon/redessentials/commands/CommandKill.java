package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandKill extends AbstractCommand {

	public CommandKill() {
		super(true, "kill");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length == 1) {
			Player unknownPlayer = Bukkit.getPlayerExact(args[0]);
			if (unknownPlayer == null) return ReturnType.INVALID_PLAYER;
			unknownPlayer.setHealth(0);
			sender.sendMessage("§cDead player.");
			return ReturnType.SUCCESS;
		}
		
		((Player) sender).setHealth(0.0D);
		sender.sendMessage("§cDead player.");
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
		return "RedEssentials.command.kill";
	}

	@Override
	public String getSyntax() {
		return "/kill (player)";
	}

	@Override
	public String getDescription() {
		return "Kill a player.";
	}
}
