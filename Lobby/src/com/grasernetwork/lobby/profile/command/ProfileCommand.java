package com.grasernetwork.lobby.profile.command;

import org.bukkit.entity.Player;

import com.grasernetwork.core.command.type.PlayerCommand;
import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.lobby.profile.gui.ProfileMenu;
import com.grasernetwork.util.inventory.MenuAPI;

public class ProfileCommand extends PlayerCommand
{
	private GadgetManager _gadgetManager;
	private HatManager _hatManager;
	private MorphManager _morphManager;
	private PetManager _petManager;
	private ProfileManager _profileManager;

	public ProfileCommand(GadgetManager gadgetManager, HatManager hatManager, MorphManager morphManager, PetManager petManager, ProfileManager profileManager)
	{
		super("profile", "/profile", "View your global profile.");

		_gadgetManager = gadgetManager;
		_hatManager = hatManager;
		_morphManager = morphManager;
		_petManager = petManager;
		_profileManager = profileManager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		MenuAPI.openMenu(sender, new ProfileMenu(sender, _gadgetManager, _hatManager, _morphManager, _petManager, _profileManager));
	}
}

