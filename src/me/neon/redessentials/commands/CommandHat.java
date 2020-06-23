package me.neon.redessentials.commands;

import java.util.List; 

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.neon.redessentials.commands.manager.AbstractCommand;

public class CommandHat extends AbstractCommand {

	public CommandHat() {
		super(true, "hat");
	}

	@Override
	protected ReturnType runCommand(CommandSender sender, String... args) {
		ItemStack helmetSlot = ((Player) sender).getInventory().getHelmet();
		ItemStack handSlot = ((Player) sender).getInventory().getItemInHand();
		
		if (handSlot.getType() == Material.AIR) {
			sender.sendMessage("§cNeed an item in your hand.");
			return ReturnType.FAILURE;
		}
		
		System.out.println(helmetSlot);
		((Player) sender).getInventory().setHelmet(handSlot);
		handSlot.setType(Material.AIR);
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> onTab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "RedEssentials.command.hat";
	}

	@Override
	public String getSyntax() {
		return "/hat";
	}

	@Override
	public String getDescription() {
		return "Put something on your head.";
	}
}
