package com.grasernetwork.lobby.cosmetic.crates.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bukkit.Material;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICrate
{
	int id();
	String name();
	Material material();
	Material fallMaterial();
}
