package me.hgilman.Curse;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import me.hgilman.Curse.CurseTranslator;


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
	
	public void onPlayerChat(PlayerChatEvent event)
	{	
		if (plugin.isCursed(event.getPlayer())) // If the player in question is cursed...
		{
		event.setMessage(ChatColor.LIGHT_PURPLE + new CurseTranslator(event.getMessage()).getTranslation());	
		}
	}
	
}
