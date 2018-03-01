package com.grasernetwork.lobby;

import com.grasernetwork.core.Core;
import com.grasernetwork.lobby.cosmetic.CosmeticManager;
import com.grasernetwork.lobby.cosmetic.crates.CratesManager;
import com.grasernetwork.lobby.cosmetic.particle.ParticleManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.lobby.elements.ConnectionManager;
import com.grasernetwork.lobby.elements.DamageManager;
import com.grasernetwork.lobby.elements.SpawnManager;
import com.grasernetwork.lobby.elements.StackerManager;
import com.grasernetwork.lobby.elements.WorldManager;
import com.grasernetwork.lobby.npc.NpcManager;
import com.grasernetwork.lobby.profile.command.ProfileCommand;

public class LobbyManager
{
	public LobbyManager(Core plugin)
	{
		SpawnManager spawnManager = new SpawnManager();
		
		new NpcManager(plugin);
		new DamageManager(plugin, spawnManager);
		new ConnectionManager(plugin, spawnManager, plugin.getManager().getProfileManager());
		new WorldManager(plugin);
		new StackerManager(plugin);

		CosmeticManager cosmeticManager = new CosmeticManager(plugin.getManager(), plugin.getManager().getProfileManager());
		//TODO: Temp location
		new ParticleManager(plugin);
		PetManager petManager = new PetManager(plugin, plugin.getManager().getProfileManager());
		new CratesManager(plugin);

//		new FriendCommand(plugin.getManager().getF);

		new ProfileCommand(cosmeticManager.getGadgetManager(), cosmeticManager.getHatManager(), cosmeticManager.getMorphManager(), cosmeticManager.getPetManager(), plugin.getManager().getProfileManager());
	}
}
