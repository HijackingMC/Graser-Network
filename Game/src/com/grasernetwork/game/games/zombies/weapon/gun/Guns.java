package com.grasernetwork.game.games.zombies.weapon.gun;

import com.grasernetwork.game.games.zombies.weapon.gun.type.AssultRifle;

/**
 * Created by Teddeh on 02/04/2016.
 */
public enum Guns
{
	AK_47(
			new AssultRifle(
					1,                         //Gun ID
					"AK-47",                   //Gun Name
					30,                        //Clip max capacity
					270,                       //Hold max capacity
					1L,                        //Reload time
					0.2f,                      //Recoil
					true,                      //Upgradable
					1500,                      //Wall price
					800,                       //Wall ammo price
					2500,                      //Wall upgraded ammo price
					"AK-47 TEST"               //Upgraded Name
			)
	),

	AN_94(
			new AssultRifle(
					2,                         //Gun ID
					"AN-94",                   //Gun Name
					50,                        //Clip max capacity
					400,                       //Hold max capacity
					1L,                        //Reload time
					0.3f,                      //Recoil
					true,                      //Upgradable
					1500,                      //Wall price
					800,                       //Wall ammo price
					2500,                      //Wall upgraded ammo price
					"AN-94 TEST"               //Upgraded Name
			)
	);

	private StandardGun gun;

	Guns(StandardGun gun)
	{
		this.gun = gun;
	}

	public StandardGun getGun()
	{
		return (StandardGun) gun.clone();
	}
}
