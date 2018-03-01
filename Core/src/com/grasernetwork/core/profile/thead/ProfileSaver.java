package com.grasernetwork.core.profile.thead;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bukkit.entity.Player;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.core.profile.friends.FriendManager;
import com.grasernetwork.core.punish.data.Ban;
import com.grasernetwork.core.punish.data.Mute;
import com.grasernetwork.core.punish.data.Warn;

public class ProfileSaver
{
	@SuppressWarnings("unchecked")
	public ProfileSaver(CoreManager manager, ProfileManager profileManager, FriendManager friendManager, Player player, boolean hasLeft)
	{
		Profile profile = profileManager.getProfile(player);
		Document document = manager.getMongo().findDocumentById(profile.getUUID().toString());
		
		//Info
		Document info = (Document) document.get("info");
		info.append("name", player.getName());
		info.append("rank", profile.rank.toString());
		info.append("netLevel", profile.netLevel);
		info.append("netExp", profile.netExp);
		info.append("crystals", profile.crystals);
		info.append("lastLogin", System.currentTimeMillis());
		info.append("language", profile.language.toLanguageTag());
		
//		//Cosmetic
//		Document cosmetic = (Document) document.get("cosmetic");
//		List<Document> costume = (List<Document>) cosmetic.get("costume");
//		List<Document> costumeArray = new ArrayList<Document>();
//		for(Document doc : costume)//TODO costume
//		{
//			costumeArray.add(doc);
//		}
//
//		List<Document> gadget = (List<Document>) cosmetic.get("gadget");
//		List<Document> gadgetArray = new ArrayList<Document>();
//		for(GadgetData data : profile.cosmeticData.getGadgetData())
//		{
//			gadgetArray.add(
//					new Document()
//						.append("gadgetId", data.getGadgetType().getGadgetId())
//					);
//		}
//
//		List<Document> hat = (List<Document>) cosmetic.get("hat");
//		List<Document> hatArray = new ArrayList<Document>();
//		for(HatData data : profile.cosmeticData.getHatData())
//		{
//			hatArray.add(
//					new Document()
//						.append("hatId", data.getHatType().getHatId())
//					);
//		}
//
//		List<Document> morph = (List<Document>) cosmetic.get("morph");
//		List<Document> morphArray = new ArrayList<Document>();
//		for(MorphData data : profile.cosmeticData.getMorphData())
//		{
//			morphArray.add(
//					new Document()
//						.append("morphId", data.getMorphType().getMorphId())
//					);
//		}
//
//		List<Document> particle = (List<Document>) cosmetic.get("particle");
//		List<Document> particleArray = new ArrayList<Document>();
//		for(Document doc : particle)//TODO particle
//		{
//			particleArray.add(doc);
//		}
//
//		List<Document> pet = (List<Document>) cosmetic.get("pet");
//		List<Document> petArray = new ArrayList<Document>();
//		for(PetData data : profile.cosmeticData.getPetData())
//		{
//			petArray.add(
//					new Document()
//						.append("petId", data.getPetType().getPetId())
//						.append("petName", data.getName())
//						.append("prestige", data.getLevelData().getPrestige())
//						.append("level", data.getLevelData().getLevel())
//						.append("exp", data.getLevelData().getExp())
//						.append("food", data.getFoodData().getFoodLevel())
//						.append("thurst", data.getFoodData().getThurstLevel())
//					);
//		}
		
		//Punishment
		Document punishment = (Document) document.get("punishment");
		List<Document> ban = (List<Document>) punishment.get("ban");
		List<Document> banArray = null;
		if(ban != null)
		{
			banArray = new ArrayList<Document>();
			for(Ban data : profile.punishData.getBanData())
			{
				banArray.add(
						new Document()
							.append("reasonId", data.getReasonId())
							.append("staff", data.getStaff())
							.append("staffRank", data.getStaffRank().toString())
							.append("timeBanned", data.getTime().getTime())
							.append("timeUnbanned", data.getUnbanned().getTime())
						);
			}
		}
		
		List<Document> mute = (List<Document>) punishment.get("mute");
		List<Document> muteArray = null;
		if(mute != null)
		{
			muteArray = new ArrayList<Document>();
			for(Mute data : profile.punishData.getMuteData())
			{
				muteArray.add(
						new Document()
							.append("reasonId", data.getReasonId())
							.append("staff", data.getStaff())
							.append("staffRank", data.getStaffRank().toString())
							.append("timeMuted", data.getTime().getTime())
							.append("timeUnmuted", data.getUnmute().getTime())
						);
			}
		}
		
		List<Document> warn = (List<Document>) punishment.get("warning");
		List<Document> warnArray = new ArrayList<Document>();
		if(warn != null)
		{
			System.out.println("SAVER, warn is not empty");
			System.out.println(profile.punishData.getWarnData().size());
			for(Warn data : profile.punishData.getWarnData())
			{
				System.out.println(data.getReason());
				warnArray.add(
						new Document()
							.append("reason", data.getReason())
							.append("staff", data.getStaff())
							.append("staffRank", data.getStaffRank().toString())
							.append("timeWarned", data.getTime().getTime())
						);
			}
		}
		
		//Game
		List<Document> game = (List<Document>) document.get("gameStats");
		List<Document> gameArray = new ArrayList<Document>();
		for(Document doc : game)
		{
			gameArray.add(doc);
		}
		
		manager.getMongo().getMongoDatabase().getCollection("users").replaceOne(new Document("_id", player.getUniqueId().toString()), 
				new Document()
					.append("_id", player.getUniqueId().toString())
					.append("info", info)
					.append("cosmetic", 
						new Document()
//							.append("costume", costumeArray)
//							.append("gadget", gadgetArray)
//							.append("hat", hatArray)
//							.append("morph", morphArray)
//							.append("particle", particleArray)
//							.append("pet", petArray)
					)
					.append("punishment", 
						new Document()
							.append("ban", (banArray == null ? ban : banArray))
							.append("mute", (muteArray == null ? mute : muteArray))
							.append("warning", (warnArray == null ? warn : warnArray))
					)
					.append("gameStats", gameArray)
		);
		
		System.out.println("Document updated!");
	}
}
