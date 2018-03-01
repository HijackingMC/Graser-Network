package com.grasernetwork.core.chat;

import java.util.Date;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.punish.data.Mute;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;
import org.json.simple.JSONObject;

public class ChatManager implements Listener
{
	private CoreManager _manager;

	public ChatManager(CoreManager manager)
	{
		_manager = manager;

		Bukkit.getPluginManager().registerEvents(this, _manager.getPlugin());
	}

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event)
	{
		if(event.isCancelled())
			return;
		
		String format = "";
		Player player = event.getPlayer();
		Profile profile = _manager.getProfileManager().getProfile(player);

		if(profile.muted)
		{
			if (silenceCheck(profile))
			{
				event.setCancelled(true);
				return;
			}
		}
		
		if (hasUnicode(player, event.getMessage()))
		{
			event.setCancelled(true);
			return;
		}
		
		if (profile.getRank() != Rank.ALL)
			format = profile.getRank().getColourBold() + profile.getRank().getName().toUpperCase() + " ";
		
		String message;
		if (profile.getRank().hasPermission(Rank.QA))
			message = colorise(event.getMessage());
		else
			message = event.getMessage();

		format += profile.getRank().getColour() + profile.getName() + " " + C.White + JSONObject.escape(message);
		
		event.setCancelled(true);
		
		for(Player recipient : Bukkit.getOnlinePlayers())
			PlayerUtil.message(recipient, format);
	}
	
	private boolean silenceCheck(Profile profile)
	{
		Mute muteData = null;
		for(Mute data : profile.punishData.getMuteData())
		{
			if(data.getUnmute().getTime() < System.currentTimeMillis())
				continue;
			
			muteData = data;
		}
		
		if(muteData == null)
		{
			profile.muted = false;
			return false;
		}
		
		Date current = new Date(System.currentTimeMillis());
		Date dt1 = new Date(muteData.getUnmute().getTime());
		
		long diff = dt1.getTime() - current.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		int diffDays = (int) diff / (1000 * 60 * 60 * 24);
		
		PlayerUtil.message(Bukkit.getPlayer(profile.getUUID()), "You are currently muted for: %s days, %s hours, %s minutes, %s seconds.", new String[] { String.valueOf(diffDays), String.valueOf(diffHours), String.valueOf(diffMinutes), String.valueOf(diffSeconds) }, ChatType.PUNISH);
		return true;
	}
	
	private boolean hasUnicode(Player player, String string)
	{
		if (string.contains("[^\\x00-\\x7F]"))
		{
			PlayerUtil.message(player, "Your message contains unicode. Please refrain from using it.");
			return true;
		}
		return false;
	}
	
	private String colorise(String string)
	{
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}
