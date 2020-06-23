package me.neon.redessentials.utils;

import java.util.Arrays; 

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomItemStack {
	
	private ItemStack itemStack;
	
	public CustomItemStack(Material material, String title, String[] list) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(Arrays.asList(list));
		item.setItemMeta(meta);
		this.itemStack = item;
	}
	
	public CustomItemStack(Material material, String title) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(Arrays.asList(new String[] {""}));
		item.setItemMeta(meta);
		this.itemStack = item;
	}
	
	public CustomItemStack(String title) {
		ItemStack item = new ItemStack(Material.ARROW);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(Arrays.asList(new String[] {""}));
		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		this.itemStack = item;
	}
	
	public CustomItemStack(Player owner, String title, String[] list) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(Arrays.asList(list));
		meta.setOwner(owner.getName());
		item.setItemMeta(meta);
		this.itemStack = item;
	}
	
	public ItemStack create() {
		return itemStack;
	}
}
