package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandWorkbench extends AbstractCommand {

	public CommandWorkbench() {
		super(true, "workbench");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		((Player) sender).openWorkbench(((Player) sender).getLocation(), true);
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.workbench";
	}

	@Override
	public String getSyntax() {
		return "/workbench";
	}

	@Override
	public String getDescription() {
		return "Open a crafttable.";
	}
}
