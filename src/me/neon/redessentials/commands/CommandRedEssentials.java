package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandRedEssentials extends AbstractCommand {
	
	RedEssentials instance;
	
	public CommandRedEssentials() {
		super(false, "redessentials");
		instance = RedEssentials.getInstance();
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		sender.sendMessage("§7Version " + instance.getDescription().getVersion() + " Created by §c§lRedPlugins");
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return null;
	}

	@Override
	public String getSyntax() {
		return "/redessentials";
	}

	@Override
	public String getDescription() {
		return "Shows all available commands.";
	}
}
