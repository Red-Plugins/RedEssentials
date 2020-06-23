package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.WarpManager;

public class CommandSpawn extends AbstractCommand {

	public CommandSpawn() {
		super(true, "spawn");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		WarpManager warpManager = RedEssentials.getInstance().getModuleForClass(WarpManager.class);
		
		String warpName = "spawn".toLowerCase();
		if (warpManager.containsWarp(warpName)) {
			((Player) sender).teleport(warpManager.getWarpLocation(warpName));
			sender.sendMessage("§aSuccessfully teleported to §7" + "'spawn'" + "§a.");
			return ReturnType.SUCCESS;
		} else {
			sender.sendMessage("§cInvalid warp.");
			return ReturnType.FAILURE;
		}
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.spawn";
	}

	@Override
	public String getSyntax() {
		return "/spawn";
	}

	@Override
	public String getDescription() {
		return "Teleport to spawn.";
	}
}
