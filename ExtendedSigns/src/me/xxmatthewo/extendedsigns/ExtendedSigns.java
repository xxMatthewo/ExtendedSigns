package me.xxmatthewo.extendedsigns;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtendedSigns extends JavaPlugin
{
	private final ESPlayerListener PlayerListener = new ESPlayerListener();

	public void onEnable()
	{
		System.out.println("ExtendedSigns 1.0 Enabled");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, this.PlayerListener, Event.Priority.Normal, this);
		getCommand("es").setExecutor(new ESCommand(this));
		getCommand("al").setExecutor(new ALCommand(this));
	}
	
	public void onDisable()
	{
		System.out.println("ExtendedSigns 1.0 Disabled");
	}

}
