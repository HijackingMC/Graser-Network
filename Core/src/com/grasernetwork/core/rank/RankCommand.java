package com.grasernetwork.core.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.type.AdminCommand;
import com.grasernetwork.core.profile.NameTag;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

/**
 * Created by TeddehDev on 16/01/2016.
 */
public class RankCommand extends AdminCommand
{

	private static final String UPDATE = "UPDATE profile SET rank=? WHERE uuid=?";

	private CoreManager _manager;

	public RankCommand(CoreManager manager)
	{
		super("rank", "/rank <username> <rank>", "Set a players rank.");

		_manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 2)
		{
			sendArgs(sender);
			return;
		}

		final Rank rank = checkRank(args[1]);
		if(rank == null)
		{
			PlayerUtil.message(sender, "This rank %s cannot be found..", new String[]{args[1].toUpperCase()}, ChatType.ERROR);
			return;
		}

		final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		if(target == null)
		{
			PlayerUtil.message(sender, "Player '%s' cannot be found!", new String[]{args[0]}, ChatType.ERROR);
			return;
		}

		if(target.isOnline())
		{
			Profile targetProfile = _manager.getProfileManager().getProfile(Bukkit.getPlayerExact(target.getName()));
			Rank previous = targetProfile.getRank();
			targetProfile.rank = rank;

			//Console log, Evidence.
			System.out.println(target.getName() + " has received a rank update (" + previous.getName() + " -> " + rank.getName() + ") - victim:" + sender.getName());
			
			//Player + Target messages.
			PlayerUtil.message(sender, target.getName() + " has received there rank update.", ChatType.SUCCESS);
			PlayerUtil.message(Bukkit.getPlayerExact(target.getName()), "Your rank has been updated to %s!", new String[]{rank.getBoldName()}, ChatType.SERVER);
			
			//Name Tag
			NameTag.removePlayerTag(targetProfile);
			new NameTag(targetProfile);
			return;
		}

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				Connection connection = null;

				try
				{
					connection = _manager.getDatabase().getConnection();

					if (connection.isClosed())
						_manager.getDatabase().openConnection();

					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
					preparedStatement.setString(1, rank.toString());
					preparedStatement.setString(2, target.getUniqueId().toString());
					preparedStatement.execute();

					preparedStatement.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}.runTaskAsynchronously(_manager.getPlugin());

		PlayerUtil.message(sender, target.getName() + " has received there rank update.", ChatType.SUCCESS);
	}

	private Rank checkRank(String args)
	{
		Rank rank = Rank.valueOf(args.toUpperCase());
		return rank;
	}
}
