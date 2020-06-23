package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandFeed extends AbstractCommand {

	public CommandFeed() {
		super(true, "feed");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length == 1) {
			Player targetPlayer = Bukkit.getPlayerExact(args[0]);
			if (targetPlayer == null) return ReturnType.INVALID_PLAYER;
			targetPlayer.setFoodLevel(20);
			sender.sendMessage("§7" + targetPlayer.getName() + " §chunger satiated.");
			return ReturnType.SUCCESS;
		}
		
		((Player) sender).setFoodLevel(20);
		sender.sendMessage("§cSated hunger.");
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
		return "RedEssentials.command.feed";
	}

	@Override
	public String getSyntax() {
		return "/feed (player)";
	}

	@Override
	public String getDescription() {
		return "Feed a player.";
	}
}
