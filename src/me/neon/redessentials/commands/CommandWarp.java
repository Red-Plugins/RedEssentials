package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.WarpManager;

public class CommandWarp extends AbstractCommand {

	public CommandWarp() {
		super(true, "warp");
	}
	
	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length < 1) return ReturnType.FAILURE;
		
		WarpManager warpManager = RedEssentials.getInstance().getModuleForClass(WarpManager.class);
		
		if (args.length == 2) {
			if (!args[0].trim().toLowerCase().equals("create") && !args[0].trim().toLowerCase().equals("delete")) return ReturnType.SYNTAX_ERROR;
			String warpName = args[1].trim().toLowerCase();
			
			if (args[0].trim().toLowerCase().equals("create")) {
				if (warpManager.containsWarp(warpName)) return ReturnType.FAILURE;
				warpManager.createWarp(warpName, ((Player) sender).getLocation());
				sender.sendMessage("§aWarp successfully created.");
				return ReturnType.SUCCESS;
			}
			if (args[0].trim().toLowerCase().equals("delete")) {
				if (!warpManager.containsWarp(warpName)) return ReturnType.FAILURE;
				warpManager.deleteWarp(warpName);
				sender.sendMessage("§cWarp successfully deleted.");
				return ReturnType.SUCCESS;
			}
		}
		
		String warpName = args[0].trim().toLowerCase();
		if (warpManager.containsWarp(warpName)) {
			((Player) sender).teleport(warpManager.getWarpLocation(warpName));
			sender.sendMessage("§aSuccessfully teleported to §7" + "'" + warpName + "'" + "§a.");
			return ReturnType.SUCCESS;
		} else {
			sender.sendMessage("§cInvalid warp.");
			return ReturnType.FAILURE;
		}
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		if (args.length == 1) {
			WarpManager warpManager = RedEssentials.getInstance().getModuleForClass(WarpManager.class);
			return warpManager.getWarps();
		}
		return null;
	}

	@Override
	public String getPermissionNode() {
		return null;
	}

	@Override
	public String getSyntax() {
		return "/warp (warp-name) a";
	}

	@Override
	public String getDescription() {
		return "Move around the server.";
	}
}
