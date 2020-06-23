package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandWho extends AbstractCommand {

	public CommandWho() {
		super(false, "who");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		sender.sendMessage("§cList of players: §7" + RedEssentials.getInstance().allPlayers);
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.who";
	}

	@Override
	public String getSyntax() {
		return "/who";
	}

	@Override
	public String getDescription() {
		return "See who's online.";
	}
}
