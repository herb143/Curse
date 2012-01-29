package me.hgilman.Curse;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Material;


public class CurseBlockListener implements Listener {
	
	public static Curse plugin;

	public CurseBlockListener(Curse instance)
	{ 

		plugin = instance;
	}
	
	@EventHandler
	public void onBlockPlace (BlockPlaceEvent event)
	{
		Block placedBlock = event.getBlock();
		Player placingPlayer = event.getPlayer();
		
		if (plugin.isCursed(placingPlayer)) // If the player is cursed.
		{
			placedBlock.setType(Material.WOOL);
		}
		
	}
	
}
