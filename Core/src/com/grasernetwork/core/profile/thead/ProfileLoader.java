package com.grasernetwork.core.profile.thead;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.bson.Document;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.profile.NameTag;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.friends.FriendManager;
import com.grasernetwork.core.punish.data.Ban;
import com.grasernetwork.core.punish.data.Mute;
import com.grasernetwork.core.punish.data.PunishData;
import com.grasernetwork.core.punish.data.Warn;
import com.grasernetwork.core.rank.Rank;

public class ProfileLoader extends BukkitRunnable
{
	private CoreManager _manager;
	private FriendManager _friendManager;
	private Profile _profile;

	public ProfileLoader(CoreManager manager, FriendManager friendManager, Profile profile)
	{
		_manager = manager;
		_friendManager = friendManager;
		_profile = profile;
	}

	@Override
	public void run()
	{
		Document document = _manager.getMongo().findDocumentById(_profile.getUUID().toString());
		if(document != null)
		{
			//Info
			Document info = (Document) document.get("info");
			_profile.rank = Rank.valueOf(info.get("rank").toString());
			_profile.netLevel = info.getInteger("netLevel", 1);
			_profile.netExp = info.getInteger("netExp", 0);
			_profile.crystals = info.getInteger("crystals", 500);
			_profile.firstLogin = info.getLong("firstLogin");
			_profile.lastLogin = info.getLong("lastLogin");
			_profile.language = Locale.forLanguageTag(info.getString("language"));
			
			//Cosmetic
//			List<GadgetData> gadgetData = new ArrayList<GadgetData>();
//			List<HatData> hatData = new ArrayList<HatData>();
//			List<MorphData> morphData = new ArrayList<MorphData>();
//			List<PetData> petData = new ArrayList<PetData>();
//			Document cosmetic = (Document) document.get("cosmetic");
//
//			//gadget
//			List<Document> gadget = (List<Document>) cosmetic.get("gadget");
//			for(Document list : gadget)
//			{
//				gadgetData.add(new GadgetData(GadgetList.getById(list.getInteger("gadgetId"))));
//			}
//
//			//Hat
//			List<Document> hat = (List<Document>) cosmetic.get("hat");
//			for(Document list : hat)
//			{
//				hatData.add(new HatData(HatList.getById(list.getInteger("hatId"))));
//			}
//
//			//Morph
//			List<Document> morph = (List<Document>) cosmetic.get("morph");
//			for(Document list : morph)
//			{
//				morphData.add(new MorphData(MorphList.getById(list.getInteger("morphId"))));
//			}
//
//			//Pet
//			List<Document> pet = (List<Document>) cosmetic.get("pet");
//			for(Document list : pet)
//			{
//				petData.add(
//						new PetData(PetType.getById(list.getInteger("petId")), list.getString("petName"),
//						new EmotionData(),
//						new LevelData(_profile.getName(), list.getInteger("level"), list.getInteger("exp")),
//						new FoodData(PetType.getById(list.getInteger("petId")), list.getInteger("food"), list.getInteger("thurst"))));
//			}
//
//			_profile.cosmeticData = new CosmeticData(gadgetData, hatData, morphData, petData);
			
			Document punishmemt = (Document) document.get("punishment");
			List<Document> ban = (List<Document>) punishmemt.get("ban");
			List<Ban> banData = null;
			if(ban != null)
			{
				banData = new ArrayList<Ban>();
				
				for(Document doc : ban)
				{
					banData.add(new Ban(
							doc.getInteger("reasonId", 100), 
							doc.getString("staff"), 
							Rank.valueOf(doc.getString("staffRank")), 
							new Timestamp(doc.getLong("timeBanned")), 
							new Timestamp(doc.getLong("timeUnbanned"))));
				}
			}
			
			List<Document> mute = (List<Document>) punishmemt.get("mute");
			List<Mute> muteData = null;
			if(mute != null)
			{
				muteData = new ArrayList<Mute>();
				
				for(Document doc : mute)
				{
					muteData.add(new Mute(
							doc.getInteger("reasonId", 100), 
							doc.getString("staff"), 
							Rank.valueOf(doc.getString("staffRank")), 
							new Timestamp(doc.getLong("timeMuted")), 
							new Timestamp(doc.getLong("timeUnmuted"))));
				}
			}
			
			List<Document> warn = (List<Document>) punishmemt.get("warning");
			List<Warn> warnData = new ArrayList<Warn>();
			if(warn != null)
			{
				for(Document doc : warn)
				{
					warnData.add(new Warn(doc.getString("reason"), doc.getString("staff"), Rank.valueOf(doc.getString("staffRank")), new Timestamp(doc.getLong("timeWarned"))));
				}
			}
			
			_profile.punishData = new PunishData(banData, muteData, warnData);
			
			new NameTag(_profile);
			return;
		}
		
//		player.sendMessage(C.GrayI + "Profile is being logged!");
		_manager.getMongo().getMongoDatabase().getCollection("users").insertOne(
				new Document()
				.append("_id", _profile.getUUID().toString())
				.append("info",
					new Document()
						.append("name", _profile.getName())
						.append("rank", "OWNER")
						.append("netLevel", 1)
						.append("netExp", 0)
						.append("crystals", 10000)
						.append("rep", 0)
						.append("firstLogin", System.currentTimeMillis())
						.append("lastLogin", System.currentTimeMillis())
						.append("language", Locale.ENGLISH.toString())
					)
				.append("cosmetic", 
					new Document()
						.append("costume", Arrays.asList(
								new Document().append("costumeId", 1)
						))
						.append("gadget", Arrays.asList(
								new Document().append("gadgetId", 1)
						))
						.append("hat", Arrays.asList(
								new Document().append("hatId", 1)
						))
						.append("morph", Arrays.asList(
								new Document().append("morphId", 1)
						))
						.append("particle", Arrays.asList(
								new Document().append("particleId", 1)
						))
						.append("pet", Arrays.asList(
								new Document()
									.append("petId", 1)
									.append("petName", "null")
									.append("prestige", 0)
									.append("level", 1)
									.append("exp", 0)
									.append("food", 25)
									.append("thurst", 25)
						))
				)
				.append("punishment", 
					new Document()
						.append("ban", new ArrayList<Document>())
						.append("mute", new ArrayList<Document>())
						.append("warning", new ArrayList<Document>())
				)
				.append("gameStats", Arrays.asList(
					new Document()
						.append("gameId", 1)
						.append("kills", 0)
						.append("deaths", 0)
						.append("wins", 0)
						.append("losses", 0)
						.append("draws", 0)
						.append("info", 
							new Document()
								.append("coins", 0)
								.append("coinsEarnt", 0)
						)
						.append("achievement", Arrays.asList(
							new Document()
								.append("achievementId", 1)
								.append("complete", false)
								.append("progress", 0)
						))
						.append("kits", Arrays.asList(
							new Document()
								.append("kitId", 1)
						))
				))
		);
		
		document = _manager.getMongo().findDocumentById(_profile.getUUID().toString());
		if(document != null)
		{
			//Info
			Document info = (Document) document.get("info");
			_profile.rank = Rank.valueOf(info.get("rank").toString());
			_profile.netLevel = info.getInteger("netLevel", 1);
			_profile.netExp = info.getInteger("netExp", 0);
			_profile.crystals = info.getInteger("crystals", 500);
			_profile.firstLogin = info.getLong("firstLogin");
			_profile.lastLogin = info.getLong("lastLogin");
			_profile.language = Locale.forLanguageTag(info.getString("language"));
			
			//Cosmetic
//			List<GadgetData> gadgetData = new ArrayList<GadgetData>();
//			List<HatData> hatData = new ArrayList<HatData>();
//			List<MorphData> morphData = new ArrayList<MorphData>();
//			List<PetData> petData = new ArrayList<PetData>();
//			Document cosmetic = (Document) document.get("cosmetic");
//
//			//gadget
//			List<Document> gadget = (List<Document>) cosmetic.get("gadget");
//			for(Document list : gadget)
//			{
//				gadgetData.add(new GadgetData(GadgetList.getById(list.getInteger("gadgetId"))));
//			}
//
//			//Hat
//			List<Document> hat = (List<Document>) cosmetic.get("hat");
//			for(Document list : hat)
//			{
//				hatData.add(new HatData(HatList.getById(list.getInteger("hatId"))));
//			}
//
//			//Morph
//			List<Document> morph = (List<Document>) cosmetic.get("morph");
//			for(Document list : morph)
//			{
//				morphData.add(new MorphData(MorphList.getById(list.getInteger("morphId"))));
//			}
//
//			//Pet
//			List<Document> pet = (List<Document>) cosmetic.get("pet");
//			for(Document list : pet)
//			{
//				petData.add(
//						new PetData(PetType.getById(list.getInteger("petId")), list.getString("petName"),
//						new EmotionData(),
//						new LevelData(_profile.getName(), list.getInteger("level"), list.getInteger("exp")),
//						new FoodData(PetType.getById(list.getInteger("petId")), list.getInteger("food"), list.getInteger("thurst"))));
//			}
//
//			_profile.cosmeticData = new CosmeticData(gadgetData, hatData, morphData, petData);
			
			Document punishmemt = (Document) document.get("punishment");
			List<Document> ban = (List<Document>) punishmemt.get("ban");
			List<Ban> banData = null;
			if(ban != null)
			{
				banData = new ArrayList<Ban>();
				
				for(Document doc : ban)
				{
					banData.add(new Ban(
							doc.getInteger("reasonId", 100), 
							doc.getString("staff"), 
							Rank.valueOf(doc.getString("staffRank")), 
							new Timestamp(doc.getLong("timeBanned")), 
							new Timestamp(doc.getLong("timeUnbanned"))));
				}
			}
			
			List<Document> mute = (List<Document>) punishmemt.get("mute");
			List<Mute> muteData = null;
			if(mute != null)
			{
				muteData = new ArrayList<Mute>();
				
				for(Document doc : mute)
				{
					muteData.add(new Mute(
							doc.getInteger("reasonId", 100), 
							doc.getString("staff"), 
							Rank.valueOf(doc.getString("staffRank")), 
							new Timestamp(doc.getLong("timeMuted")), 
							new Timestamp(doc.getLong("timeUnmuted"))));
				}
			}
			
			List<Document> warn = (List<Document>) punishmemt.get("warning");
			List<Warn> warnData = new ArrayList<Warn>();
			if(warn != null)
			{
				for(Document doc : warn)
				{
					warnData.add(new Warn(doc.getString("reason"), doc.getString("staff"), Rank.valueOf(doc.getString("staffRank")), new Timestamp(doc.getLong("timeWarned"))));
				}
			}
			
			_profile.punishData = new PunishData(banData, muteData, warnData);
			
			new NameTag(_profile);
			return;
		}
	}
}
