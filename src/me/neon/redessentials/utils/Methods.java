package me.neon.redessentials.utils;

import org.bukkit.ChatColor;

public class Methods {
	
	public static class CustomDouble {
		
		public static double parseDouble(String amount) {
			double formatedAmount = 0.0;
			try {
				formatedAmount = Double.parseDouble(amount);
			} catch (NumberFormatException e) { }
			return formatedAmount;
		}
	}
	
	public static String formatText(String text) {
        if (text == null || text.equals("")) return "";
        return formatText(text, false);
    }

	public static String formatText(String text, boolean cap) {
        if (text == null || text.equals("")) return "";
        if (cap) text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
