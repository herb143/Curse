package me.hgilman.Curse;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;


public class CursePlayerListener extends PlayerListener {
	
	public static Curse plugin;

	public CursePlayerListener(Curse instance)
	{ 

		plugin = instance;
	}
	
	public void onPlayerJoin (PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if(plugin.isCursed(player)) // If the player is cursed...
		{
			plugin.newLightningStrike(player); // Schedule a new lightning strike on them.
		}
		
	}
	
}
