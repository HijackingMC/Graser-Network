package com.grasernetwork.lobby.profile.gui.cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class ParticleMenu extends Menu
{
	public ParticleMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Particle Menu", 6);
		
		int[] slots = new int[]{10,11,12,13,14,15,16,20,21,22,23,24,30,31,32};
		
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder blank = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(C.Yellow + "COMING SOON!");
		
		for (Integer i : slots)
			this.addMenuItem(new MenuItem(blank.build()), i);
		
		this.addMenuItem(new MenuItem(back.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				player.closeInventory();
			}
		}, 45);
		
		this.setMenuCloseBehaviour(new MenuAPI.MenuCloseBehaviour() {
			private boolean isClosing = false;
			
			@Override
			public void onClose(Player player, final Menu menu, boolean bypassMenuCloseBehaviour) {
				if (!bypassMenuCloseBehaviour && !isClosing) {
					isClosing = true;
					
					MenuAPI.openMenu(player, new CosmeticsPortalMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
				}
			}
		});
	}
}