package me.neon.redessentials.commands;

import java.util.List; 

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandNight extends AbstractCommand {

	public CommandNight() {
		super(true, "night");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		((Player) sender).getWorld().setTime(14000L);
		sender.sendMessage("§cTime changed to night.");
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.night";
	}

	@Override
	public String getSyntax() {
		return "/night";
	}

	@Override
	public String getDescription() {
		return "Change the weather to night.";
	}
}
