package me.hgilman.Curse;

import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Material;


public class CurseBlockListener extends BlockListener {
	
	public static Curse plugin;

	public CurseBlockListener(Curse instance)
	{ 

		plugin = instance;
	}
	
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
