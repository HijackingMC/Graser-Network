package com.grasernetwork.core.punish.command;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.punish.PunishManager;
import com.grasernetwork.core.punish.gui.PunishMenu;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.ServerUtil;
import com.grasernetwork.util.inventory.MenuAPI;

/**
 * Created by TeddehDev on 17/01/2016.
 */
public class PunishCommand extends CommandBase
{
	private CoreManager _manager;
	private PunishManager _punishManager;

	public PunishCommand(CoreManager manager, PunishManager punishManager)
	{
		super("punish", Rank.HELPER, "/punish <username>", "Punish rule breakers.");

		_manager = manager;
		_punishManager = punishManager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		Profile profile = _manager.getProfileManager().getProfile(sender);
		if (!hasPermission(profile))
		{
			PlayerUtil.message(sender,
					"You do not have permission, requires [%s]",
					new String[] { getRequiredRank().getBoldName() },
					ChatType.ERROR);
			return;
		}

		if (args.length < 1)
		{
			sendArgs(sender);
			return;
		}


		OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		Document doc = _manager.getMongo()
				.findDocumentById(target.getUniqueId().toString());
		
		if (target == null || doc == null)
		{
			PlayerUtil.message(sender, "Player '%s' has never joined %s!",
					new String[] { args[0], ServerUtil.SERVER },
					ChatType.ERROR);
			return;
		}

		MenuAPI.openMenu(sender,
				new PunishMenu(sender, _punishManager, profile, target));
	}
}
