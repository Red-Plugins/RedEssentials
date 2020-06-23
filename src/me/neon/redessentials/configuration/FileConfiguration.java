package me.neon.redessentials.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.utils.ConsoleMessage;
import me.neon.redessentials.utils.ConsoleMessage.MessageLevel;

public class FileConfiguration {
	
	private JavaPlugin plugin;
	private String fileName;
	private File file;
	private File directory;
	private YamlConfiguration config;
	private boolean isDefaultDirectory;
	private boolean isDefaultConfiguration;
	
	public FileConfiguration() {
		this.setPlugin(RedEssentials.getInstance());
		this.isDefaultConfiguration = true;
		this.isDefaultDirectory = true;
		setDefaultFileName();
		this.file = new File(getPlugin().getDataFolder(), getFileName());
		if (file == null || config == null) {
			if (!file.exists()) saveDefaultConfiguration();
			reloadConfiguration();
		}
	}
	
	public FileConfiguration(String fileName) {
		this.setPlugin(RedEssentials.getInstance());
		this.fileName = fileName;
		this.isDefaultDirectory = true;
		this.file = new File(getPlugin().getDataFolder(), getFileName());
		if (file == null || config == null) {
			if (!file.exists()) saveDefaultConfiguration();
			reloadConfiguration();
		}
	}
	
	public FileConfiguration(File directory, String fileName) {
		this.setPlugin(RedEssentials.getInstance());
		this.fileName = fileName;
		this.directory = directory;
		this.file = new File(getDirectory(), getFileName());
		if (file == null || config == null) {
			if (!file.exists()) saveDefaultConfiguration();
			reloadConfiguration();
		}
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setDefaultFileName() {
		this.fileName = "config.yml";
	}
	
	public File getFile() {
		return file;
	}

	public File getDirectory() {
		return directory;
	}

	public YamlConfiguration getConfig() {
		return config;
	}
	
	public void saveConfiguration()  {
		try {
			getConfig().save(getFile());
		} catch (IOException e) {
			ConsoleMessage.send(MessageLevel.INFO, "Error saving this configuration: " + getFileName());
		}
	}
	
	public void saveDefaultConfiguration() {
		getPlugin().saveResource(getFileName(), false);
	}
	
	public void reloadConfiguration() {
		if (isDefaultConfiguration) {
			if (isDefaultDirectory) {
				this.config = YamlConfiguration.loadConfiguration(getFile());
				return;
			}
		}
		if (isDefaultDirectory) {
			this.config = YamlConfiguration.loadConfiguration(getFile());
			return;
		}
		if (!getFile().exists()) {
			try {
				getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(getFile());
	}
	
	public void deleteConfiguration() {
		getFile().delete();
	}
	
	public String getString(String path) {
		return getConfig().getString(path); 
	}
	
	public boolean hasConfiguration() {
		if (getFile().exists())
			return true;
		return false;
	}
}
