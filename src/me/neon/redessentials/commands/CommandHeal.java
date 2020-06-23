package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandHeal extends AbstractCommand {

	public CommandHeal() {
		super(true, "heal");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length == 1) {
			Player targetPlayer = Bukkit.getPlayerExact(args[0]);
			if (targetPlayer == null) return ReturnType.INVALID_PLAYER;
			targetPlayer.setHealth(20);
			targetPlayer.setFoodLevel(20);
			targetPlayer.setFireTicks(0);
			sender.sendMessage("§7" + targetPlayer.getName() + " §ccured.");
			return ReturnType.SUCCESS;
		}
		
		((Player) sender).setHealth(20);
		((Player) sender).setFoodLevel(20);
		((Player) sender).setFireTicks(0);
		sender.sendMessage("§cCured.");
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
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.heal";
	}

	@Override
	public String getSyntax() {
		return "/heal";
	}

	@Override
	public String getDescription() {
		return "Heal a player.";
	}
}
