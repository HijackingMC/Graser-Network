package com.grasernetwork.core.command.general;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

public class CrystalsCommand extends CommandBase
{
	private CoreManager _manager;
	
	public CrystalsCommand(CoreManager manager)
	{
		super("crystals", Rank.ADMIN, "/crystals <give/take/set> <player> <amount>", "Update your gamemode.");
		
		_manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length != 3)
		{
			sendArgs(sender);
			return;
		}
		
		try
		{
			
		}
		catch (Exception e)
		{
			PlayerUtil.message(sender, args[2] + " cannot be seen as a number..", ChatType.ERROR);
			return;
		}
		
		if(Bukkit.getPlayerExact(args[1]) == null)
		{
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
			Document document = _manager.getMongo().findDocumentById(target.getUniqueId().toString());
			
			if(document == null)
				return;
			
			Document info = (Document) document.get("info");
			int currentCrystals = info.getInteger("crystals");
			
			if (args[0].equalsIgnoreCase("give"))
			{
				info.append("crystals", (currentCrystals + Integer.parseInt(args[2])));
				
				_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", target.getUniqueId().toString()),
						new Document()
							.append("info", info)
							.append("cosmetic", document.get("cosmetic"))
							.append("punishment", document.get("punishment"))
							.append("gameStats", document.get("gameStats"))//yes
						);
			}
			else if (args[0].equalsIgnoreCase("take"))
			{
				info.append("crystals", (currentCrystals - Integer.parseInt(args[2])));
				
				_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", target.getUniqueId().toString()),
						new Document()
							.append("info", info)
							.append("cosmetic", document.get("cosmetic"))
							.append("punishment", document.get("punishment"))
							.append("gameStats", document.get("gameStats"))
						);
			}
			else if (args[0].equalsIgnoreCase("set"))
			{
				info.append("crystals", (Integer.parseInt(args[2])));
				
				_manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", target.getUniqueId().toString()),
						new Document()
							.append("info", info)
							.append("cosmetic", document.get("cosmetic"))
							.append("punishment", document.get("punishment"))
							.append("gameStats", document.get("gameStats"))
						);
			}
			
			PlayerUtil.message(sender, "'%s''s crystal count is now: " + info.get("crystals") ,new String[] { args[1] }, ChatType.SUCCESS);
		}
		else
		{
			Player target = Bukkit.getPlayerExact(args[1]);
			Profile profile = _manager.getProfileManager().getProfile(target);
			
			if (args[0].equalsIgnoreCase("give"))
			{
				long amount = profile.crystals += Integer.parseInt(args[2]);
				if(amount < 0)
					amount = 0;
				
				if(amount > Integer.MAX_VALUE)
					amount = Integer.MAX_VALUE;
				
				profile.crystals = (int) amount;
			}
			else if (args[0].equalsIgnoreCase("take"))
			{
				long amount = profile.crystals -= Integer.parseInt(args[2]);
				if(amount < 0)
					amount = 0;
				
				if(amount > Integer.MAX_VALUE)
					amount = Integer.MAX_VALUE;
				
				profile.crystals = (int) amount;
			}
			else if (args[0].equalsIgnoreCase("set"))
			{
				long amount = profile.crystals = Integer.parseInt(args[2]);
				if(amount < 0)
					amount = 0;
				
				if(amount > Integer.MAX_VALUE)
					amount = Integer.MAX_VALUE;
				
				profile.crystals = (int) amount;
			}
			
			PlayerUtil.message(sender, "'%s''s crystal count is now: " + profile.crystals, new String[] { args[1] }, ChatType.SUCCESS);
		}
	}
}
