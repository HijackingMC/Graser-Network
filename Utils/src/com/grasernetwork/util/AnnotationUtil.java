package com.grasernetwork.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Material;

public class AnnotationUtil
{
	public static boolean isInteger(Object object)
	{
		return (object instanceof Integer);
	}
	
	public static boolean isString(Object object)
	{
		return (object instanceof String);
	}
	
	public static boolean isBoolean(Object object)
	{
		return (object instanceof Boolean);
	}
	
	public static boolean isMaterial(Object object)
	{
		return (Material.valueOf(object.toString()) != null);
	}
	
	public static Collection<Material> getMaterials(Class<?> clazz)
	{
		List<Material> temp = new ArrayList<Material>();
		for(Object object : getAnnotationValues(clazz))
		{
			if(isInteger(object))
				continue;
			
			if(isString(object))
				continue;
			
			if(isMaterial(object))
				temp.add(Material.valueOf(object.toString()));
		}
		
		return (temp.isEmpty() ? null : temp);
	}
	
	public static Collection<Object> getAnnotationValues(Class<?> clazz)
	{
		List<Object> temp = new ArrayList<Object>();
		for(Annotation annotation : clazz.getAnnotations())
		{
			Class<? extends Annotation> type = annotation.annotationType();
			for(Method method : type.getDeclaredMethods())
			{
				try
				{
					Object object = method.invoke(annotation, (Object[])null);
					temp.add(object);
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
				catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return (temp.isEmpty() ? null : temp);
	}
}
