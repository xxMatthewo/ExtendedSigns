package me.xxmatthewo.extendedsigns;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ALCommand implements CommandExecutor 
{

	private final ExtendedSigns plugin;

	public ALCommand(ExtendedSigns plugin) 
	{
	    this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args)
	{
		String pluginDir = new File("").getAbsolutePath() + "//plugins//ExtendedSigns//";
		try
		{
			BufferedWriter output;
	    	output = new BufferedWriter(new FileWriter(pluginDir + args[0] + ".txt", true));
	    	String combined = "";
	    	for(int i=1;i<args.length;i++)
	    	{
	    		combined += args[i].toString() + " ";
	    	}
	    	output.append(combined);
	    	output.newLine();
	    	output.close();
	    	arg0.sendMessage(ChatColor.GREEN +"[ES] Line added successfully added to Message: "+ChatColor.GOLD+args[0]);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return true;
	}
}