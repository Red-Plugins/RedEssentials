package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.FlyManager;

public class CommandGamemode extends AbstractCommand {
	
	public CommandGamemode() {
		super(true, "gamemode");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length < 1) return ReturnType.SYNTAX_ERROR;
		FlyManager flyManager = RedEssentials.getInstance().getModuleForClass(FlyManager.class);
		Player player = (Player) sender;
		
		int gamemodeNumber = 0;
		try {
			gamemodeNumber = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			return ReturnType.SYNTAX_ERROR;
		}
		
		if (gamemodeNumber > 3) return ReturnType.SYNTAX_ERROR;
		
		if (args.length == 2) {
			Player unknownPlayer = Bukkit.getPlayerExact(args[1]);
			if (unknownPlayer == null) return ReturnType.INVALID_PLAYER;
			UUID playerUUID = RedEssentials.getInstance().translateNameToUUID(unknownPlayer.getName().toLowerCase());
			
			if (gamemodeNumber == 0) {
				unknownPlayer.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("§aGamemode changed."); 
				if (player != unknownPlayer) { 
					unknownPlayer.sendMessage("§aGamemode changed.");
				}
				if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
				flyManager.removeFlightFromPlayer(unknownPlayer);
				return ReturnType.SUCCESS;
			}
			if (gamemodeNumber == 1) {
				unknownPlayer.setGameMode(GameMode.CREATIVE);
				player.sendMessage("§aGamemode changed.");
				if (player != unknownPlayer) { 
					unknownPlayer.sendMessage("§aGamemode changed.");
				}
				if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
				flyManager.removeFlightFromPlayer(unknownPlayer);
				unknownPlayer.setGameMode(GameMode.CREATIVE);
				return ReturnType.SUCCESS;
			}
			if (gamemodeNumber == 2) {
				unknownPlayer.setGameMode(GameMode.ADVENTURE);
				player.sendMessage("§aGamemode changed.");
				if (player != unknownPlayer) { 
					unknownPlayer.sendMessage("§aGamemode changed.");
				}
				if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
				flyManager.removeFlightFromPlayer(unknownPlayer);
				return ReturnType.SUCCESS;
			}
			if (gamemodeNumber == 3) {
				unknownPlayer.setGameMode(GameMode.SPECTATOR);
				player.sendMessage("§aGamemode changed.");
				if (player != unknownPlayer) { 
					unknownPlayer.sendMessage("§aGamemode changed.");
				};
				if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
				flyManager.removeFlightFromPlayer(unknownPlayer);
				return ReturnType.SUCCESS;
			}
		}
		UUID playerUUID = RedEssentials.getInstance().translateNameToUUID(player.getName().toLowerCase());
		
		if (gamemodeNumber == 0) {
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage("§aGamemode changed.");
			if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
			flyManager.removeFlightFromPlayer(player);
			return ReturnType.SUCCESS;
		}
		if (gamemodeNumber == 1) {
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage("§aGamemode changed.");
			if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
			flyManager.removeFlightFromPlayer(player);
			player.setGameMode(GameMode.CREATIVE);
			return ReturnType.SUCCESS;
		}
		if (gamemodeNumber == 2) {
			player.setGameMode(GameMode.ADVENTURE);
			player.sendMessage("§aGamemode changed.");
			if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
			flyManager.removeFlightFromPlayer(player);
			return ReturnType.SUCCESS;
		}
		if (gamemodeNumber == 3) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("§aGamemode changed.");
			if (!flyManager.contaisPlayerInList(playerUUID)) return ReturnType.SUCCESS;
			flyManager.removeFlightFromPlayer(player);
			return ReturnType.SUCCESS;
		}
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		if (args.length == 1) {
			return Arrays.asList(new String[] {"0", "1" , "2", "3"});
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
		return "RedEssentials.command.gamemode";
	}

	@Override
	public String getSyntax() {
		return "/gamemode (0/1/2/3)";
	}

	@Override
	public String getDescription() {
		return "Change your gamemode.";
	}
}
