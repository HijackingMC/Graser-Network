
package com.grasernetwork.core.profile;

import com.grasernetwork.common.database.Database;
import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.profile.friends.FriendManager;
import com.grasernetwork.core.profile.thead.ProfileLoader;
import com.grasernetwork.core.profile.thead.ProfileSaver;
import com.grasernetwork.core.punish.PunishManager;
import com.grasernetwork.core.punish.Punishments;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ServerUtil;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by TeddehDev on 15/01/2016.
 */
public class ProfileManager implements Listener
{
	public HashMap<Player, Profile> Profiles = new HashMap<Player, Profile>();

	private CoreManager _manager;
	private FriendManager _friendManager;
	private Database _database;
	private PunishManager _punishManager;

	public ProfileManager(CoreManager manager, FriendManager friendManager,
			Database database, PunishManager punishManager)
	{
		_manager = manager;
		_friendManager = friendManager;
		_database = database;
		_punishManager = punishManager;

		Bukkit.getPluginManager().registerEvents(this, _manager.getPlugin());
	}

	public Profile getProfile(Player player)
	{
		if (Profiles.containsKey(player))
			return Profiles.get(player);

		Profile profile = new Profile(player);
		profile.setJoinedTime(System.currentTimeMillis());
		new ProfileLoader(_manager, _friendManager, profile)
				.runTask(_manager.getPlugin());
		Profiles.put(player, profile);
		return profile;
	}

	/**
	 * This method should be ran when the server is still active.
	 */
	public void saveProfileAsync(final Player player, final boolean hasLeft)
	{
		Bukkit.getScheduler().runTaskAsynchronously(_manager.getPlugin(),
				new Runnable()
				{
					@Override
					public void run()
					{
						saveProfile(player, hasLeft);
					}
				});
	}

	public void saveProfile(Player player, boolean hasLeft)
	{
		new ProfileSaver(_manager, this, _friendManager, player, hasLeft);
	}

	@EventHandler
	public void playerPreJoin(AsyncPlayerPreLoginEvent event)
	{
		OfflinePlayer player = Bukkit.getOfflinePlayer(event.getUniqueId());

		Document document = _manager.getMongo()
				.findDocumentById(player.getUniqueId().toString());
		if (document == null)
			return;

		Document punishment = (Document) document.get("punishment");
		List<Document> ban = (List<Document>) punishment.get("ban");
		String result = null;

		for (Document data : ban)
		{
			Timestamp timestamp = new Timestamp(data.getLong("timeUnbanned"));
			if (timestamp.getTime() > System.currentTimeMillis())
			{
				result = "";
				result += C.RedB + "You're Banned!" + C.Reset + "\n";
				result += C.White + "Reason: " + C.Gray + Punishments
						.getReasonById(data.getInteger("reasonId", 100)) + "\n";
				result += C.White + "Your ban expires at: " + C.Green
						+ getTimeUnbanned(timestamp) + "\n\n";
				result += C.Blue + "Believe yor ban was a mistake?" + "\n"
						+ C.Blue + "Visit " + C.Aqua
						+ ServerUtil.WEBSITE.toLowerCase() + C.Blue
						+ " for help.";
			}
		}

		if (result == null)
			return;

		event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, result);
	}

	public String getTimeUnbanned(Timestamp stamp)
	{
		Timestamp current = new Timestamp(System.currentTimeMillis());
		long diff = stamp.getTime() - current.getTime();
		long days = (int) diff / (1000 * 60 * 60 * 24);
		long hours = diff / (60 * 60 * 1000) % 24;
		long minutes = diff / (60 * 1000) % 60;
		long seconds = diff / 1000 % 60;

		return (!(days <= 0) ? "Days: " + days + ", " : "")
				+ (!(hours <= 0) ? "Hours: " + hours + ", " : "")
				+ (!(minutes <= 0) ? "Minutes: " + minutes + ", " : "")
				+ (!(seconds <= 0) ? "Seconds: " + seconds : "");
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		final Player player = event.getPlayer();
		Profile profile = getProfile(player);
		System.out.println("Profile loaded for " + player.getName());
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		player.setLevel(-100);
		final BossBar bar = Bukkit.createBossBar(
				"Hey there " + player.getName(), BarColor.RED,
				BarStyle.SEGMENTED_10, BarFlag.DARKEN_SKY);// Apperently create
															// fog? idk lol
		bar.addPlayer(player);
		bar.setProgress(0);

		player.setGlowing(true);

		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				if (player == null || !player.isOnline())
				{
					if (player != null)
						bar.removePlayer(player);
					this.cancel();
				}
				bar.setColor(BarColor.values()[new Random()
						.nextInt(BarColor.values().length)]);
				double d = 0.1 + bar.getProgress();
				if (d > 1)
					d = 0;
				bar.setProgress(d);

			}
		}.runTaskTimer(_manager.getPlugin(), 20, 20);
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		Profile profile = getProfile(player);
		saveProfileAsync(player, true);
		System.out.println("Profile saved for " + player.getName());
	}
}
