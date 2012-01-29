package me.hgilman.Curse;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.String;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import me.hgilman.Curse.CurseBlockListener;

public class Curse extends JavaPlugin {

	private HashMap<String,Boolean> cursedPlayers = new HashMap<String,Boolean>(); // The cursedPlayers hashmap.

	private final CurseBlockListener blockListener = new CurseBlockListener(this);
	private final CursePlayerListener playerListener = new CursePlayerListener(this);

	Logger log = Logger.getLogger("Minecraft"); // Get the logger.

	public void onEnable()
	{
		log.info("Curse plugin enabling...");

		PluginManager pm = this.getServer().getPluginManager();

		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);


		new File("plugins/Curse").mkdir();
		File playersFile = new File("plugins/Curse/CursedPlayers.dat");

		if(!(playersFile.exists()))
		{
			try
			{
				playersFile.createNewFile(); 
			}
			catch (IOException ex)
			{ 
				ex.printStackTrace(); //explained below.
			}
		}
		else
		{
			setCursedPlayers(load("plugins/Curse/CursedPlayers.dat"));
		}
		
		Player[] onlinePlayers = this.getServer().getOnlinePlayers();
		for (int i = 0; i < onlinePlayers.length; i++) // For every online player...
		{
			if (isCursed(onlinePlayers[i])) // If the player is cursed...
			{
				newLightningStrike(onlinePlayers[i]); // Schedule a new lightning strike event.
			}
		}
		
		log.info("Curse plugin enabled.");
	}

	public void onDisable()
	{
		save(getCursedPlayers(), "plugins/Curse/CursedPlayers.dat");

		log.info("Curse plugin disabled.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

		if(cmd.getName().equalsIgnoreCase("curse")){ // If the player typed /curse then do the following...
			if (sender instanceof Player) // If they're a player...
			{
				if (sender.isOp()) // TODO: Replace with permissions.
				{
					if (args.length == 0) // If they didn't give an arg...
					{
						sender.sendMessage("Error! You must specify a player.");
						return false;
					}
					else if (this.getServer().getPlayer(args[0]) != null) // If the input matched a player...
					{
						
						Player targetPlayer = this.getServer().getPlayer(args[0]);
						
						if (isCursed(targetPlayer)) // If the player is cursed...
						{
							uncursePlayer(targetPlayer); // Uncurse the player.
							sender.sendMessage("Uncursed " + targetPlayer.getName() + ".");
							
						}
						else // If they are not cursed.
						{
							cursePlayer(targetPlayer); // Curse the player.
							sender.sendMessage("Cursed " + targetPlayer.getName() + ".");
						}
						
					}
					else // They gave input, but it was bad.
					{
						sender.sendMessage("Error! Invalid input!");
						sender.sendMessage(ChatColor.DARK_PURPLE + "Note that at this time offline players cannot be cursed.");
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				sender.sendMessage("This command can only be used ingame.");
				return false;
			}



			return true;
		} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
		return false; }

	public HashMap<String,Boolean> getCursedPlayers()
	{
		return cursedPlayers;
	}

	public void setCursedPlayers(HashMap<String,Boolean> newCursedPlayers)
	{
		cursedPlayers = newCursedPlayers;
	}

	public void save(HashMap<String,Boolean> pluginEnabled, String path)
	{
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(pluginEnabled);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String,Boolean> load(String path)
	{
		try
		{

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			Object result = ois.readObject();
			//you can feel free to cast result to HashMap<String,Boolean> if you know there's that HashMap in the file
			return (HashMap<String,Boolean>)result;


		}
		catch(Exception e)
		{

			e.printStackTrace();
		}
		return null;
	}

	public boolean isCursed(Player player)
	{

		if (!(getCursedPlayers().containsKey(player.getName()))) // If the player was not on the list...
		{
			return false;
		}
		
		else if (this.getCursedPlayers().get(player.getName()).booleanValue()) // If they're cursed...
		{
			return true;
		}
		else
		{
			return false; // If they're on the list but are not cursed...
		}

	}
	
	public void cursePlayer(Player player) // Curses the player.
	{
		getCursedPlayers().put(player.getName(), true);
		newLightningStrike(player); // Schedule a new lightning strike.
	}
	
	public void uncursePlayer(Player player)
	{
		getCursedPlayers().put(player.getName(), false); // Uncurses the player.
	}
	
	public void newLightningStrike(Player player) // Schedules a new lightningstrike effect on the player.
	{
		Random generator = new Random();
		int nextStrikeTicks = generator.nextInt(1200);
		double nextStrikeSeconds = nextStrikeTicks / 20;
		player.getServer().getScheduler().scheduleSyncDelayedTask(this, new RunnableStrikeLightningEffect(this, player), nextStrikeTicks);
		log.info("Scheduled lightning strike effect for " + player.getName() + " in " + nextStrikeSeconds + " seconds.");
	}


}
