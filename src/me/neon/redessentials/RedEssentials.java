package me.neon.redessentials;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.neon.redessentials.commands.CommandClearChat;
import me.neon.redessentials.commands.CommandClearInventory;
import me.neon.redessentials.commands.CommandDay;
import me.neon.redessentials.commands.CommandFeed;
import me.neon.redessentials.commands.CommandFly;
import me.neon.redessentials.commands.CommandGamemode;
import me.neon.redessentials.commands.CommandHat;
import me.neon.redessentials.commands.CommandHeal;
import me.neon.redessentials.commands.CommandInvsee;
import me.neon.redessentials.commands.CommandKill;
import me.neon.redessentials.commands.CommandNight;
import me.neon.redessentials.commands.CommandRedEssentials;
import me.neon.redessentials.commands.CommandRepair;
import me.neon.redessentials.commands.CommandSpawn;
import me.neon.redessentials.commands.CommandTeleport;
import me.neon.redessentials.commands.CommandVanish;
import me.neon.redessentials.commands.CommandWarp;
import me.neon.redessentials.commands.CommandWho;
import me.neon.redessentials.commands.CommandWorkbench;
import me.neon.redessentials.commands.manager.CommandManager;
import me.neon.redessentials.configuration.ConfigurationManager;
import me.neon.redessentials.listeners.JoinListener;
import me.neon.redessentials.listeners.PingListener;
import me.neon.redessentials.listeners.QuitListener;
import me.neon.redessentials.manager.BlockedCommands;
import me.neon.redessentials.manager.FlyManager;
import me.neon.redessentials.manager.RepairManager;
import me.neon.redessentials.manager.VanishManager;
import me.neon.redessentials.manager.WarpManager;
import me.neon.redessentials.utils.IModule;
import me.neon.redessentials.utils.UUIDFetcher;

public class RedEssentials extends JavaPlugin {

	private final Map<Class<? extends IModule>, IModule> modules = new HashMap<Class<? extends IModule>, IModule>();
	private CommandManager commandManager;
	private ConfigurationManager configurationManager;
	public ArrayList<String> allPlayers = new ArrayList<String>();
	
	public void onEnable() {
		registerModules();
		configurationManager = new ConfigurationManager();
		configurationManager.init();
		registerEvents();
		registerCommands();
		File locale = new File(RedEssentials.getInstance().getDataFolder() + "/Warps");
		if (!locale.exists()) { locale.mkdir(); }
	}
	
	public void onDisable() {
		List<Class<? extends IModule>> clazzez = new ArrayList<>();
	    clazzez.addAll(this.modules.keySet());
	    for (Class<? extends IModule> clazz : clazzez)
	      unregisterModuleForClass(clazz);
	}
	
	public static RedEssentials getInstance() {
		return getPlugin(RedEssentials.class);
	}
	
	void registerCommands() {
		commandManager = new CommandManager(this);
		commandManager.addCommand(new CommandRedEssentials());
		commandManager.addCommand(new CommandFly());
		commandManager.addCommand(new CommandGamemode());
		commandManager.addCommand(new CommandClearChat());
		commandManager.addCommand(new CommandClearInventory());
		commandManager.addCommand(new CommandDay());
		commandManager.addCommand(new CommandFeed());
		commandManager.addCommand(new CommandHat());
		commandManager.addCommand(new CommandHeal());
		commandManager.addCommand(new CommandInvsee());
		commandManager.addCommand(new CommandKill());
		commandManager.addCommand(new CommandNight());
		commandManager.addCommand(new CommandSpawn());
		commandManager.addCommand(new CommandTeleport());
		commandManager.addCommand(new CommandVanish());
		commandManager.addCommand(new CommandWarp());
		commandManager.addCommand(new CommandWho());
		commandManager.addCommand(new CommandWorkbench());
		commandManager.addCommand(new CommandRepair());
	}
	
	void registerModules() {
		registerModule(ConfigurationManager.class, new ConfigurationManager());
		registerModule(FlyManager.class, new FlyManager());
		registerModule(VanishManager.class, new VanishManager());
		registerModule(WarpManager.class, new WarpManager());
		registerModule(RepairManager.class, new RepairManager());
	}
	
	void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockedCommands(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PingListener(), this);
	}
	
	public <T extends IModule> void registerModule(Class<T> clazz, T module) {
	    if (clazz == null) throw new IllegalArgumentException("Class cannot be null"); 
	    if (module == null) throw new IllegalArgumentException("Module cannot be null"); 
	    if (this.modules.containsKey(clazz))
	      getLogger().warning("Overwriting module for class: " + clazz.getName()); 
	    this.modules.put(clazz, (IModule)module);
	  }
	
	@SuppressWarnings("unchecked")
	public <T extends IModule> T unregisterModuleForClass(Class<T> clazz) {
	    if (clazz == null) throw new IllegalArgumentException("Class cannot be null"); 
	    IModule iModule = (IModule)clazz.cast(this.modules.get(clazz));
	    return (T)iModule;
	}
	
	@SuppressWarnings("deprecation")
	public UUID translateNameToUUID(String name) {
		UUID id = null;
		if (name == null) return id;
		Collection<? extends Player> players = Bukkit.getOnlinePlayers();
		for (Player player : players) {
			if (player.getName().equalsIgnoreCase(name)) {
				id = player.getUniqueId();
				break;
			}
		}
		if (id == null && Bukkit.getServer().getOnlineMode()) {
			UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(new String[] {name}));
			try {
				Map<String, UUID> map = fetcher.call();
				for (Map.Entry<String, UUID> entry : map.entrySet()) {
					if (name.equalsIgnoreCase(entry.getKey())) {
						id = entry.getValue();
						break;
					}
				}
			} catch (Exception e) { }
		} else if (id == null && !Bukkit.getServer().getOnlineMode()) {
			id = Bukkit.getServer().getOfflinePlayer(name).getUniqueId();
		}
		return id;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public <T extends IModule> T getModuleForClass(Class<T> clazz) { 
		return clazz.cast(this.modules.get(clazz)); 
	}
}
