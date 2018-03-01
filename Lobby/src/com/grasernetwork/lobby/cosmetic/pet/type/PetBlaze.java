package com.grasernetwork.lobby.cosmetic.pet.type;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.pet.Pet;
import com.grasernetwork.lobby.cosmetic.pet.component.Movement;
import net.minecraft.server.v1_9_R1.*;

import java.util.LinkedHashSet;

public class PetBlaze extends EntityBlaze implements Pet, Movement
{
	public PetBlaze(World world)
	{
		super(world);

		LinkedHashSet<?> goalB = (LinkedHashSet<?>) PetReflection.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalB.clear();

		LinkedHashSet<?> goalC = (LinkedHashSet<?>) PetReflection.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
		goalC.clear();

		LinkedHashSet<?> targetB = (LinkedHashSet<?>) PetReflection.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetB.clear();

		LinkedHashSet<?> targetC = (LinkedHashSet<?>) PetReflection.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
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
