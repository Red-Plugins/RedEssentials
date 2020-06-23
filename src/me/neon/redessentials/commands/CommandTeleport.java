package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandTeleport extends AbstractCommand {

	public CommandTeleport() {
		super(true, "teleport");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length < 1) return ReturnType.SYNTAX_ERROR;
		
		if (args[0].trim().toLowerCase().equals("all")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.teleport((Player) sender);
			}
			sender.sendMessage("§cAll teleported to you.");
			return ReturnType.SUCCESS;
		}
		
		Player unknownPlayerOne = Bukkit.getPlayerExact(args[0]);
		if (unknownPlayerOne == null) return ReturnType.INVALID_PLAYER;
		String unknownNameOne = unknownPlayerOne.getName();
		
		if (args.length == 2) {
			Player unknownPlayerTwo = Bukkit.getPlayerExact(args[1]);
			if (unknownPlayerTwo == null) return ReturnType.INVALID_PLAYER;
			String unknownNameTwo = unknownPlayerTwo.getName();
			unknownPlayerOne.teleport(unknownPlayerTwo);
			sender.sendMessage("§cTeleported §7" + unknownNameOne + " §cto §7" + unknownNameTwo + "§c.");
			return ReturnType.SUCCESS;
		}
		
		((Player) sender).teleport(unknownPlayerOne);
		sender.sendMessage("§cTeleported to §7" + unknownNameOne + "§c.");
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
		if (args.length == 2) {
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
		return "RedEssentials.command.teleport";
	}

	@Override
	public String getSyntax() {
		return "/teleport (player/all)";
	}

	@Override
	public String getDescription() {
		return "Teleport to a player.";
	}
}
