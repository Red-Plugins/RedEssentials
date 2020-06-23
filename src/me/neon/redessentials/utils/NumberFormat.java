package me.neon.redessentials.utils;

import java.text.DecimalFormat;

import org.bukkit.configuration.file.YamlConfiguration;

import me.neon.redessentials.RedEssentials;
import me.neon.redessentials.configuration.ConfigurationManager; 

public class NumberFormat {
	
	private ConfigurationManager configurationManager = RedEssentials.getInstance().getModuleForClass(ConfigurationManager.class);
	private YamlConfiguration config = configurationManager.getDefaultConfig().getConfig();
	
	private String formattedNumber;
	private String[] suffix;
	
	public NumberFormat(Double value) {
		setSuffix(config.getStringList("SymbolsForFormattedNumbers").toArray(new String[0]));
		int index = 0;
		while (value / 1000.0D >= 1.0D) {
			value /= 1000.0D;
			index++;
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		this.formattedNumber = decimalFormat.format(value) + suffix[index];
	}
	
	void setSuffix(String[] suffix) {
		this.suffix = suffix;
	}

	public String[] getSuffix() {
		return suffix;
	}

	public String getFormattedNumber() {
		return formattedNumber;
	}
}
