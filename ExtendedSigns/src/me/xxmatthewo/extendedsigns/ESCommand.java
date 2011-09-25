package me.xxmatthewo.extendedsigns;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class ESCommand implements CommandExecutor 
{
	private final ExtendedSigns plugin;
	static String lines[], strLine, owner;
	static int a = 0,b,c;

	public ESCommand(ExtendedSigns plugin) {
	    this.plugin = plugin;
	}

	public boolean onCommand(CommandSender cs, Command command, String label, String[] args)
    {
		
		String pluginDir = new File("").getAbsolutePath() + "//plugins//ExtendedSigns//";
		
		if(args[0].equalsIgnoreCase("help"))
		{
			cs.sendMessage(ChatColor.DARK_GREEN + "=======[ExtendedSigns]=======");
			cs.sendMessage(ChatColor.GREEN + "/es new [MSGID] How to create a new sign message");
			cs.sendMessage(ChatColor.GREEN + "/al [MSGID] [STRING] Adds line of text to said message");
			cs.sendMessage(ChatColor.GREEN + "/es read [MSGID] Display the said message");
			cs.sendMessage(ChatColor.GREEN + "/es del [MSGID] Delete said message");
			cs.sendMessage(ChatColor.GREEN + "Example: /es read xxMatthewo");
			cs.sendMessage(ChatColor.RED + "Note: Message ID's can only be one word.");
			return true;
		}
		if(args[0].equalsIgnoreCase("read"))
		{
			if(!(args[1] == null))
			{
				if(new File(pluginDir + args[1] + ".txt").exists())
				{
					try
					{
						FileInputStream fstream = new FileInputStream(pluginDir + args[1] + ".txt");
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String strLine;
						cs.sendMessage(ChatColor.GREEN+"[ES] Reading Message: "+ChatColor.GOLD+args[1]+ChatColor.GREEN+" ...");
						while((strLine = br.readLine()) != null)
						{
							cs.sendMessage(ChatColor.BLUE + strLine);
						}
						in.close();
						return true;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						cs.sendMessage(ChatColor.RED+"[ES] IOError: This error is unknown, please report it to the plugin forum page.");
						return true;
					}
				}
				else
				{
					cs.sendMessage(ChatColor.GREEN + "[ES] Message: "+ChatColor.GOLD+args[1]+ChatColor.GREEN + " does not exist.");
					return true;
				}
				
			}
			else
			{
				cs.sendMessage(ChatColor.RED+"[ES] Error: You must enter a message id.");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("new"))
		{
			if(!(args[1] ==  null))
			{
				if(!(new File(pluginDir + args[1] + ".txt").exists()))
				{
					try
					{
						File createFile = new File(pluginDir + args[1] + ".txt");
						createFile.createNewFile();
						PrintWriter pw = new PrintWriter(new FileWriter(pluginDir + args[1] + ".txt"));
						pw.println("Owner: "+cs.getName());
						pw.close();
						cs.sendMessage(ChatColor.GREEN + "[ES] Message: "+ChatColor.GOLD+args[1]+ChatColor.GREEN + " does not exist. Creating the new message.");
						cs.sendMessage(ChatColor.GREEN + "[ES] You can add lines to the file by typing "+ChatColor.GOLD+ "/al "+args[1]+"[word] [word] [etc]");
						return true;
					}
					catch(Exception e)
					{
						System.err.println(e.getMessage());
						cs.sendMessage(ChatColor.RED+"[ES] IOError: This error is unknown, please report it to the plugin forum page.");
						return true;
					}
					
				}
				else
				{
					cs.sendMessage(ChatColor.RED + "[ES] Error: Message: "+ChatColor.GOLD+args[1]+ChatColor.RED + " already exists.");
					cs.sendMessage(ChatColor.RED + "[ES] If you created this message, type /es del "+args[1]+" to delete it, then create a new blank one.");
					return true;
				}
			}
		}
		if(args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("delete"))
		{
			if(new File(pluginDir + args[1] + ".txt").exists())
			{
				try
				{
					parse(pluginDir + args[1] + ".txt");
					if(owner.equalsIgnoreCase("Owner: "+cs.getName()) || cs.isOp())
					{
						File del = new File(pluginDir + args[1] + ".txt");
						del.delete();
						cs.sendMessage(ChatColor.GREEN+"[ES] Message: "+ChatColor.GOLD+args[1]+ChatColor.GREEN+" was successfully deleted." );
						return true;
					}
					else
					{
						cs.sendMessage(ChatColor.RED+"[ES] Error: You did not create Message: "+ChatColor.GOLD+args[1]+ChatColor.RED+". This means you can not delete it." );
					}
					return true;
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
					cs.sendMessage(ChatColor.RED+"[ES] IOError: This error is unknown, please report it to the plugin forum page.");
					return true;
				}
			}
			else
			{
				cs.sendMessage(ChatColor.RED+"[ES] Message: "+ChatColor.GOLD+args[1]+ChatColor.RED+" does not exist.");
				return true;
			}
		}
		return true;
	}
	public static void parse(String fileLoc) throws IOException
	{
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(fileLoc)));
		FileInputStream in = new FileInputStream(fileLoc);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		lnr.skip(Long.MAX_VALUE);
		String lines[];
		String strLine;
		lines = new String[1 + lnr.getLineNumber()];
		while ((strLine = br.readLine()) != null)   
		{
			lines[a] = strLine;
			a++;
		}
		br.close();
		lnr.close();
		in.close();
		owner = lines[0];
	}

}
