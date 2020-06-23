package me.neon.redessentials.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.commands.manager.AbstractCommand;
import me.neon.redessentials.manager.RepairManager;

public class CommandRepair extends AbstractCommand {
	
	public CommandRepair() {
		super(true, "repair");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		if (args.length < 1) return ReturnType.SYNTAX_ERROR;
		
		RepairManager repairManager = RedEssentials.getInstance().getModuleForClass(RepairManager.class);
		
		if (!args[0].trim().toLowerCase().equals("hand") && !args[0].trim().toLowerCase().equals("all")) return ReturnType.SYNTAX_ERROR;
		
		if (args[0].trim().toLowerCase().equals("hand")) {
			if (((Player) sender).getItemInHand().getType() == Material.AIR) {
				sender.sendMessage("§cYou must have something in your hand.");
			}
			repairManager.repairItem(((Player) sender).getItemInHand());
			sender.sendMessage("§aRepaired item.");
 		}
		if (args[0].trim().toLowerCase().equals("all")) {
			repairManager.repairAll(((Player) sender));
			sender.sendMessage("§aAll items have been repaired.");
		}
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.repair";
	}

	@Override
	public String getSyntax() {
		return "/repair (hand/all)";
	}

	@Override
	public String getDescription() {
		return "Repair a item.";
	}
}
