package com.grasernetwork.game.games.zombies.weapon.gun.type;

import com.grasernetwork.game.games.zombies.weapon.gun.GunType;
import com.grasernetwork.game.games.zombies.weapon.gun.StandardGun;

/**
 * Created by Teddeh on 02/04/2016.
 */
public class SubMachine extends StandardGun
{
	public SubMachine(int id, String name, int totalClipAmmo, int totalAmmoHold, long reloadTime, float recoil, boolean canUpgrade, int wallCost, int wallAmmoCost, int upgradedAmmoCost, String upgradedName)
	{
		super(id, name, totalClipAmmo, totalAmmoHold, reloadTime, recoil, canUpgrade, wallCost, wallAmmoCost, upgradedAmmoCost, upgradedName, GunType.SUB_MACHINE);
	}
}
