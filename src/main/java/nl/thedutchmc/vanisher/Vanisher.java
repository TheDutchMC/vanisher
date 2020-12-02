package nl.thedutchmc.vanisher;

import org.bukkit.plugin.java.JavaPlugin;

public class Vanisher extends JavaPlugin {

	public static Vanisher INSTANCE;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		getCommand("vanish").setExecutor(new CommandHandler(this));
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void logInfo(String log) {
		INSTANCE.getLogger().info(log);
	}
	
	public static void logWarn(String log) {
		INSTANCE.getLogger().warning(log);
	}
}
