package me.hgilman.Curse;

import org.bukkit.entity.Player;

public class RunnableStrikeLightningEffect implements Runnable {
	
	private Curse plugin;
	private Player player;
	
	
	public RunnableStrikeLightningEffect(Curse instance, Player playerInstance)
	{
		plugin = instance;
		player = playerInstance;
	}

	public void run()
	{
		if(plugin.isCursed(player) && player.isOnline()) // If the player is cursed and online...
		{
			player.getWorld().strikeLightningEffect(player.getLocation()); // Strike the lightning effect at the player's location.
			plugin.newLightningStrike(player); // Schedule a new lightning strike.
		}
		
	}
	
}
