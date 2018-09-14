package de.funky33.customjoin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener
{
	
	@Override
	public void onEnable()
	{
		loadConfig();
		
		getLogger().info("Plugin by funky33");
		getLogger().info("Config.yml is buggie right now");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (player.hasPermission("customjoin.join.op"))
		{
			String join = getConfig().getString("Config.joinop");
			join = join.replace("[Player]", player.getName());
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', join));
			//event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.RED + player.getName());
		}
		else
		{
			String join = getConfig().getString("Config.joindefault");
			join = join.replace("[Player]", player.getName());
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', join));
			//event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + player.getName());
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		if (player.hasPermission("customjoin.quit.op"))
		{
			String quit = getConfig().getString("Config.leaveop");
			quit = quit.replace("[Player]", player.getName());
			event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quit));
			//event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.RED + player.getName());
		}
		else
		{
			String quit = getConfig().getString("Config.leavedefault");
			quit = quit.replace("[Player]", player.getName());
			event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quit));
			//event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + player.getName());
		}
	}
	
	public void loadConfig()
	{
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		saveConfig();
	}
}