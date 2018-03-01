package com.grasernetwork.core.profile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.grasernetwork.core.rank.Rank;

public class NameTag
{
	public NameTag(Profile profile)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = manager.getMainScoreboard();
		Team team = null;
		
		for(Rank rank : Rank.values())
		{
			if(scoreboard.getTeam(rank.toString()) == null)
				continue;
			
			if(!scoreboard.getTeam(rank.toString()).hasEntry(profile.getName()))
				continue;
			
			scoreboard.getTeam(rank.toString()).removeEntry(profile.getName());
		}
		
		if(scoreboard.getTeam(profile.rank.toString()) == null)
		{
			team = scoreboard.registerNewTeam(profile.rank.toString());
			if(profile.rank != Rank.ALL)
				team.setPrefix(profile.getRank().getBoldName() + ChatColor.RESET + " ");
			System.out.println("Team created.");
			team.setNameTagVisibility(NameTagVisibility.ALWAYS);
		}
		else
		{
			team = scoreboard.getTeam(profile.rank.toString());
			if(profile.rank != Rank.ALL)
				team.setPrefix(profile.getRank().getBoldName() + ChatColor.RESET + " ");
			team.setNameTagVisibility(NameTagVisibility.ALWAYS);
			System.out.println("Existing Team recovered.");
		}
		
		if(!team.hasEntry(profile.getName()))
		{
			System.out.println("Team Entry added, " + profile.getName() + ".");
			team.addEntry(profile.getName());
		}
	}
	
	public static void removePlayerTag(Profile profile)
	{
		Player player = Bukkit.getPlayer(profile.getUUID());
		if(player == null || !player.isOnline())
			return;
		
		Scoreboard scoreboard = player.getScoreboard();
		if(scoreboard == null)
		{
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			scoreboard = manager.getMainScoreboard();
		}
		
		Team team = scoreboard.getTeam(profile.rank.toString());
		
		if(team == null)
			return;
		
		if(!team.hasEntry(profile.getName()))
			return;
		
		team.removeEntry(profile.getName());
	}
}
