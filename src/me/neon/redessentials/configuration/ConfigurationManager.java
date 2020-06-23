package me.neon.redessentials.configuration;

import me.neon.redessentials.utils.IModule;

public class ConfigurationManager implements IModule {
	
	private FileConfiguration defaultConfig;
	private FileConfiguration warpsConfig;
	
	public void init() {
		defaultConfig = new FileConfiguration();
		warpsConfig = new FileConfiguration("warps.yml");
	}

	public FileConfiguration getDefaultConfig() {
		if (defaultConfig == null) defaultConfig = new FileConfiguration();
		return defaultConfig; 
	}
	
	public FileConfiguration getWarpsConfig() {
		if (warpsConfig == null) warpsConfig = new FileConfiguration("warps.yml");
		return warpsConfig;
	}
}
