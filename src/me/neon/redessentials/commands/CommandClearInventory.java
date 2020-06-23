package me.neon.redessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandClearInventory extends AbstractCommand {

	public CommandClearInventory() {
		super(true, "clearinventory");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length == 1) {
			Player targetPlayer = Bukkit.getPlayerExact(args[0]);
			if (targetPlayer == null) return ReturnType.INVALID_PLAYER;
			targetPlayer.getInventory().clear();
			targetPlayer.getInventory().setHelmet(new ItemStack(Material.AIR));
			targetPlayer.getInventory().setChestplate(new ItemStack(Material.AIR));
			targetPlayer.getInventory().setLeggings(new ItemStack(Material.AIR));
			targetPlayer.getInventory().setBoots(new ItemStack(Material.AIR));
			sender.sendMessage("§7" + targetPlayer.getName() + " §cclean inventory.");
			return ReturnType.SUCCESS;
		}
		
		((Player) sender).getInventory().clear();
		((Player) sender).getInventory().setHelmet(new ItemStack(Material.AIR));
		((Player) sender).getInventory().setChestplate(new ItemStack(Material.AIR));
		((Player) sender).getInventory().setLeggings(new ItemStack(Material.AIR));
		((Player) sender).getInventory().setBoots(new ItemStack(Material.AIR));
		sender.sendMessage("§cClean inventory.");
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
		return "RedEssentials.command.clearinventory";
	}

	@Override
	public String getSyntax() {
		return "/clearinventory (player)";
	}

	@Override
	public String getDescription() {
		return "Clear a inventory.";
	}
}
