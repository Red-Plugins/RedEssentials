package me.neon.redessentials.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.configuration.ConfigurationManager;
import me.neon.redessentials.utils.IModule;

public class WarpManager implements IModule {
	
	private ConfigurationManager configurationManager = RedEssentials.getInstance().getModuleForClass(ConfigurationManager.class);
	private YamlConfiguration warpsConfig = configurationManager.getWarpsConfig().getConfig();
	
	public void createWarp(String name, Location location) {
		if (containsWarp(name)) return;
		warpsConfig.set("Warps." + name + ".World", location.getWorld().getName());
		warpsConfig.set("Warps." + name + ".X", location.getBlockX());
		warpsConfig.set("Warps." + name + ".Y", location.getBlockY());
		warpsConfig.set("Warps." + name + ".Z", location.getBlockZ());
		warpsConfig.set("Warps." + name + ".Yaw", location.getYaw());
		warpsConfig.set("Warps." + name + ".Pitch", location.getPitch());
		configurationManager.getWarpsConfig().saveConfiguration();
	}
	
	public void deleteWarp(String name) {
		if (!containsWarp(name)) return; 
		warpsConfig.set("Warps." + name + ".World", null);
		warpsConfig.set("Warps." + name, null);
		configurationManager.getWarpsConfig().saveConfiguration();
	}
	
	public Location getWarpLocation(String name) {
		World world = Bukkit.getWorld(warpsConfig.getString("Warps." + name + ".World"));
		double x = warpsConfig.getDouble("Warps." + name + ".X");
		double y = warpsConfig.getDouble("Warps." + name + ".Y");
		double z = warpsConfig.getDouble("Warps." + name + ".Z");
		float yaw = (float) warpsConfig.getDouble("Warps." + name + ".Yaw");
		float pitch = (float) warpsConfig.getDouble("Warps." + name + ".Pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	public List<String> getWarps() {
		List<String> warps = new ArrayList<String>();
		for (String warp : warpsConfig.getConfigurationSection("Warps").getKeys(false)) {
			if (containsWarp(warp)) {
				warps.add(warp);
			}
		}
		return warps;
	}
 	
	public boolean containsWarp(String name) {
		for (String warpName : warpsConfig.getConfigurationSection("Warps").getKeys(false)) {
			if (warpsConfig.getString("Warps." + warpName + ".World") == null) return false;
			if (warpName.toLowerCase().equals(name.toLowerCase())) return true;
		}
		return false;
	}
}
