package me.neon.redessentials.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.neon.redessentials.utils.IModule;

public class RepairManager implements IModule {
	
	public void repairAllItems(ItemStack[] items) {
		for (ItemStack item : items) {
			if (item == null || item.getType().isBlock() || item.getDurability() == 0) continue;
			repairItem(item);
		}
	}
	
	public void repairAll(Player player) {
		repairAllItems(player.getInventory().getContents());
		repairAllItems(player.getInventory().getArmorContents());
		player.updateInventory();
	}
	
	public void repairItem(ItemStack item) {
		if (item.getType().isBlock() || item.getType().getMaxDurability() < 1) return;
		if (item.getDurability() == 0) return;
		item.setDurability((short) 0);
	}
}
