package com.grasernetwork.lobby.cosmetic.pet.type;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.pet.Pet;
import com.grasernetwork.lobby.cosmetic.pet.component.Movement;
import net.minecraft.server.v1_9_R1.*;

import java.util.List;

public class PetSquid extends EntitySquid implements Pet, Movement
{
	public PetSquid(World world)
	{
		super(world);

		List<?> goalB = (List<?>) PetReflection.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalB.clear();
		
		List<?> goalC = (List<?>) PetReflection.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
		goalC.clear();
		
		List<?> targetB = (List<?>) PetReflection.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetB.clear();
		
		List<?> targetC = (List<?>) PetReflection.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
		targetC.clear();

		goalSelector.a(0, new PathfinderGoalFloat(this));
		goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	}

	@Override
	public String getPetName()
	{
		return "Stinky Pig";
	}

	@Override
	public RarityType getRarityType()
	{
		return RarityType.COMMON;
	}

	@Override
	public void onSpawn()
	{
//		System.out.println("SPAWNED");
	}

	@Override
	public void onDestroy()
	{
//		System.out.println("DESTROYED");
	}

	@Override
	public double getSpeed()
	{
		return 0.2;
	}
}
