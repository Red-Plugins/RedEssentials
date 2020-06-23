package me.neon.redessentials.utils;

public class ConsoleMessage {
	
	public static void send(MessageLevel level, String message) {
		if (message == null || message.isEmpty()) return;
		switch (level) {
			case INFO:
				System.out.println("[RedEssentials - INFO] " + message);
				break;
			case SUCCESS:
				System.out.println("[RedEssentials - SUCCESS] " + message);
				break;
		}
	}
	
	public enum MessageLevel {INFO, SUCCESS}
}
