package me.xxmatthewo.extendedsigns;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class ESPlayerListener extends PlayerListener
{
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			Block block = event.getClickedBlock();
			Player player = event.getPlayer();
			if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)
			{
				BlockState state = block.getState();
				Sign sign = (Sign) state;
				if(state instanceof Sign)
				{
					String signLine[] = sign.getLines();
					if(signLine[0].equalsIgnoreCase("[Message]"))
					{
						String pluginDir = new File("").getAbsolutePath() + "//plugins//ExtendedSigns//";
						if(new File(pluginDir + signLine[1] + ".txt").exists())
						{
							try
							{
								FileInputStream fstream = new FileInputStream(pluginDir + signLine[1] + ".txt");
								DataInputStream in = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(in));
								String strLine;
								while((strLine = br.readLine()) != null)
								{
									player.sendMessage(ChatColor.BLUE + strLine);
								}
								in.close();
							}
							catch (Exception e)
							{
								System.err.println(e.getMessage());
							}
						}
						else
						{
							player.sendMessage(ChatColor.GREEN + "[ES] Message: " + ChatColor.GOLD + signLine[1] + ChatColor.GREEN+ " does not exist.");
							player.sendMessage(ChatColor.GREEN + "[ES] Type /es help for more info.");
						}
					}
				}
			}
		}
	}
}
