package me.hgilman.Curse;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Curse extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft"); // Get the logger.
	
	public void onEnable()
	{ 
		log.info("Curse plugin enabled.");
	}
	
	
	public void onDisable()
	{
		log.info("Curse plugin disabled.");
	}
	
}
