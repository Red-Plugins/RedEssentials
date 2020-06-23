package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandDay extends AbstractCommand {

	public CommandDay() {
		super(true, "day");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		((Player) sender).getWorld().setTime(1000L);
		((Player) sender).getWorld().setThundering(false);
		sender.sendMessage("§cTime changed to day.");
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.day";
	}

	@Override
	public String getSyntax() {
		return "/day";
	}

	@Override
	public String getDescription() {
		return "Change the weather to day.";
	}
}
