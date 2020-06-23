package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandClearChat extends AbstractCommand {

	public CommandClearChat() {
		super(true, "clearchat");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		for (int i = 0; i < 100; i++) { Bukkit.broadcastMessage(""); }
		Bukkit.broadcastMessage("§c- §fGlobal chat cleared by §7" + sender.getName() + "§c.");
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.clearchat";
	}

	@Override
	public String getSyntax() {
		return "/clearchat";
	}

	@Override
	public String getDescription() {
		return "Clear global chat.";
	}
}
