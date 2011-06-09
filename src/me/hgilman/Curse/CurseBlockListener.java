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
		boolean cursed = plugin.getCursedPlayers().get(placingPlayer.getName()).booleanValue(); // Whether or not they are cursed.
		
		if (cursed)
		{
			placedBlock.setType(Material.WOOL);
		}
		
	}
	
}
