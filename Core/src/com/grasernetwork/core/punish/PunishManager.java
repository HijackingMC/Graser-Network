package com.grasernetwork.core.punish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.grasernetwork.common.database.Database;
import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.punish.command.PunishCommand;
import com.grasernetwork.core.punish.data.Ban;
import com.grasernetwork.core.punish.data.Mute;
import com.grasernetwork.core.punish.data.Warn;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.ServerUtil;

public class PunishManager
{
	private CoreManager _manager;
	private Database _database;

	public PunishManager(CoreManager manager, Database database)
	{
		_manager = manager;
		_database = database;

		new PunishCommand(manager, this);
	}

	public void ban(final String target, final Player player, final int reason, final long time)
	{
		Profile staffProfile = _manager.getProfileManager().getProfile(player);
		if(Bukkit.getPlayer(target) == null)
		{
			OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
			Document document = _manager.getMongo().findDocumentById(offlinePlayer.getUniqueId().toString());
			
			if(document == null)
				return;
			
			//Punishment
			Document punishment = (Document) document.get("punishment");
			List<Document> ban = (List<Document>) punishment.get("ban");
			ban.add(new Document()
				.append("reasonId", reason)
				.append("staff", player.getName())
				.append("staffRank", staffProfile.rank.toString())
				.append("time", System.currentTimeMillis())
				.append("timeUnbanned", (System.currentTimeMillis() + time))
			);
			List<Document> mute = (List<Document>) punishment.get("mute");
			List<Document> warn = (List<Document>) punishment.get("warning");
			
			_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", player.getUniqueId().toString()), 
					new Document()
						.append("_id", player.getUniqueId().toString())
						.append("info", document.get("info"))
						.append("cosmetic", document.get("cosmetic"))
						.append("punishment", 
							new Document()
								.append("ban", ban)
								.append("mute", mute)
								.append("warning", warn)
						)
						.append("gameStats", document.get("gameStats"))
			);
			
			System.out.println("Document updated!");
		}
		else
		{
			Player t = Bukkit.getPlayer(target);
			Profile profile = _manager.getProfileManager().getProfile(t);
			profile.punishData.addBanData(
					new Ban(reason, player.getName(), staffProfile.rank, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + time)));
			
			String result = "";
			result += C.RedB + "You have been Banned!" + C.Reset + "\n";
			result += C.White + "Reason: " + C.Gray + Punishments.getReasonById(reason) + "\n";
			result += C.White + "Your ban expires at: " + C.Green + new Timestamp(System.currentTimeMillis() + time) + "\n\n";
			result += C.Blue + "Believe yor ban was a mistake?" + "\n" + C.Blue + "Visit " + C.Aqua + ServerUtil.WEBSITE.toLowerCase() + C.Blue + " for help.";
			t.kickPlayer(result);
		}
	}

	public void mute(final String target, final Player player, final int reason, final long time)
	{
		Profile staffProfile = _manager.getProfileManager().getProfile(player);
		if(Bukkit.getPlayer(target) == null)
		{
			OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
			Document document = _manager.getMongo().findDocumentById(offlinePlayer.getUniqueId().toString());
			
			if(document == null)
				return;
			
			//Punishment
			Document punishment = (Document) document.get("punishment");
			List<Document> mute = (List<Document>) punishment.get("mute");
			mute.add(new Document()
						.append("reasonId", reason)
						.append("staff", player.getName())
						.append("staffRank", staffProfile.rank.toString())
						.append("time", System.currentTimeMillis())
						.append("timeUnmuted", (System.currentTimeMillis() + time))
					);
			
			_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", player.getUniqueId().toString()), 
					new Document()
						.append("_id", player.getUniqueId().toString())
						.append("info", document.get("info"))
						.append("cosmetic", document.get("cosmetic"))
						.append("punishment", 
							new Document()
								.append("ban", punishment.get("ban"))
								.append("mute", mute)
								.append("warning", punishment.get("warning"))
						)
						.append("gameStats", document.get("gameStats"))
			);
			
			System.out.println("Document updated!");
		}
		else
		{
			Player t = Bukkit.getPlayer(target);
			PlayerUtil.message(t, "You have been Muted. (Information in /Profile)", ChatType.PUNISH);
			Profile profile = _manager.getProfileManager().getProfile(t);
			System.out.println(_manager.getProfileManager().Profiles.size());
			profile.punishData.addMuteData(
					new Mute(reason, player.getName(), staffProfile.rank, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + time)));
			profile.muted = true;
		}
	}

	public void warn(final String target, final Player player, final String reason)
	{
		Profile staffProfile = _manager.getProfileManager().getProfile(player);
		if(Bukkit.getPlayer(target) == null)
		{
			OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
			Document document = _manager.getMongo().findDocumentById(offlinePlayer.getUniqueId().toString());
			
			if(document == null)
				return;
			
			//Punishment
			Document punishment = (Document) document.get("punishment");
			List<Document> warn = (List<Document>) punishment.get("warning");
			warn.add(new Document()
				.append("reason", reason)
				.append("staff", player.getName())
				.append("staffRank", staffProfile.rank.toString())
				.append("time", System.currentTimeMillis())
			);
			
			_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", player.getUniqueId().toString()), 
					new Document()
						.append("_id", player.getUniqueId().toString())
						.append("info", document.get("info"))
						.append("cosmetic", document.get("cosmetic"))
						.append("punishment", 
							new Document()
								.append("ban", punishment.get("ban"))
								.append("mute", punishment.get("mute"))
								.append("warning", warn)
						)
						.append("gameStats", document.get("gameStats"))
			);
			
			System.out.println("Document updated!");
		}
		else
		{
			Player t = Bukkit.getPlayer(target);
			PlayerUtil.message(t, "You have been given a Warning.", ChatType.PUNISH);
			PlayerUtil.message(t, "(" + reason + ")", ChatType.PUNISH);
			Profile profile = _manager.getProfileManager().getProfile(t);
			profile.punishData.addWarnData(new Warn(reason, player.getName(), staffProfile.rank, new Timestamp(System.currentTimeMillis())));
		}
		
//		Bukkit.getScheduler().runTaskAsynchronously(_manager.getPlugin(), new Runnable()
//		{
//			private static final String INSERT = "INSERT INTO punish(uuid,name,staff,reason,time,type) VALUES (?,?,?,?,?,?)";
//			private Connection _connection = null;
//			@Override
//			public void run()
//			{
//				try
//				{
//					_connection = _database.getConnection();
//					if(_database.isClosed())
//					{
//						_database.openConnection();
//						warn(target, player, reason, time);
//						return;
//					}
//
//					PreparedStatement preparedStatement = _connection.prepareStatement(INSERT);
//					preparedStatement.setString(1, target.getUniqueId().toString());
//					preparedStatement.setString(2, target.getName());
//					preparedStatement.setString(3, player.getName());
//					preparedStatement.setString(4, reason);
//					preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//					preparedStatement.setString(6, Punishments.Type.WARN.toString());
//					preparedStatement.execute();
//
//					preparedStatement.close();
//				}
//				catch (SQLException e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
	}

	public void clearPunishments(final OfflinePlayer target, final Player player)
	{
		Bukkit.getScheduler().runTaskAsynchronously(_manager.getPlugin(), new Runnable()
		{
			private static final String BANS_SELECT = "SELECT uuid FROM bans WHERE uuid=?";
			private static final String BANS_DELETE = "DELETE FROM bans WHERE uuid=?";
			private static final String MUTES_SELECT = "SELECT uuid FROM mutes WHERE uuid=?";
			private static final String MUTES_DELETE = "DELETE FROM mutes WHERE uuid=?";
			private Connection _connection = null;
			public void run()
			{
				try
				{
					_connection = _database.getConnection();
					if(_database.isClosed())
					{
						_database.openConnection();
						clearPunishments(target, player);
						return;
					}

					PreparedStatement preparedStatement = _connection.prepareStatement(BANS_SELECT);
					preparedStatement.setString(1, target.getUniqueId().toString());
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next())
					{
						preparedStatement = _connection.prepareStatement(BANS_DELETE);
						preparedStatement.setString(1, target.getUniqueId().toString());
						preparedStatement.execute();
					}

					preparedStatement = _connection.prepareStatement(MUTES_SELECT);
					preparedStatement.setString(1, target.getUniqueId().toString());
					resultSet = preparedStatement.executeQuery();
					if(resultSet.next())
					{
						preparedStatement = _connection.prepareStatement(MUTES_DELETE);
						preparedStatement.setString(1, target.getUniqueId().toString());
						preparedStatement.execute();
					}

					System.out.println("PUNISH > " + player.getName() + " reset punishments for " + target.getName());

					preparedStatement.close();
					
					if (target.isOnline())
					{
						Player targetOn = (Player) target;
						_manager.getProfileManager().getProfile(targetOn).muted = false;
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void clearMutes(final OfflinePlayer target, final String victim)
	{
		Bukkit.getScheduler().runTaskAsynchronously(_manager.getPlugin(), new Runnable()
		{
			private static final String MUTES_SELECT = "SELECT uuid FROM mutes WHERE uuid=?";
			private static final String MUTES_DELETE = "DELETE FROM mutes WHERE uuid=?";
			private Connection _connection = null;
			public void run()
			{
				try
				{
					_connection = _database.getConnection();
					if(_database.isClosed())
					{
						_database.openConnection();
						clearMutes(target, victim);
						return;
					}

					PreparedStatement preparedStatement = _connection.prepareStatement(MUTES_SELECT);
					preparedStatement.setString(1, target.getUniqueId().toString());
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next())
					{
						preparedStatement = _connection.prepareStatement(MUTES_DELETE);
						preparedStatement.setString(1, target.getUniqueId().toString());
						preparedStatement.execute();
					}

					System.out.println("PUNISH > " + victim + " reset mutes for " + target.getName());

					preparedStatement.close();
					resultSet.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void clearBans(final OfflinePlayer target, final String victim)
	{
		Bukkit.getScheduler().runTaskAsynchronously(_manager.getPlugin(), new Runnable()
		{
			private static final String BANS_SELECT = "SELECT uuid FROM bans WHERE uuid=?";
			private static final String BANS_DELETE = "DELETE FROM bans WHERE uuid=?";
			private Connection _connection = null;
			public void run()
			{
				try
				{
					_connection = _database.getConnection();
					if(_database.isClosed())
					{
						_database.openConnection();
						clearMutes(target, victim);
						return;
					}

					PreparedStatement preparedStatement = _connection.prepareStatement(BANS_SELECT);
					preparedStatement.setString(1, target.getUniqueId().toString());
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next())
					{
						preparedStatement = _connection.prepareStatement(BANS_DELETE);
						preparedStatement.setString(1, target.getUniqueId().toString());
						preparedStatement.execute();
					}

					System.out.println("PUNISH > " + victim + " reset bans for " + target.getName());

					preparedStatement.close();
					resultSet.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
