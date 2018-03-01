package com.grasernetwork.lobby.profile.gui.achievements;

import com.grasernetwork.util.inventory.Menu;

public class AchievementPortalMenu extends Menu
{
	public AchievementPortalMenu(String title, int rows)
	{
		super(title, rows);
	}
//	public AchievementPortalMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
//	{
//		super("Achievement Menu", 5);
//
//		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName("&cBack");
//
//		this.addMenuItem(new MenuItem(back.build())
//		{
//			@Override
//			public void onClick(Player player, InventoryClickType clickType)
//			{
//				MenuAPI.openMenu(player, new ProfileMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
//			}
//		}, 36);
//	}
}