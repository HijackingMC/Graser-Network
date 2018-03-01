package com.grasernetwork.util;

public class TextUtil
{
	public static String getBar(String done, String todo, float start, float finish, String symbol, int size)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < size; i++)
		{
			if(((float)i / (float)size) < (start / finish))
			{
				builder.append(done + symbol + C.Reset);
				continue;
			}
			builder.append(todo + symbol + C.Reset);
		}
		builder.append(C.Reset);
		return builder.toString();
	}
	
	public static String getBarAnimated(String done, String todo, float start, float finish, String symbol)
	{
		int size = symbol.toCharArray().length;
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < size; i++)
		{
			if(((float)i / (float)size) < (start / finish))
			{
				builder.append(done + symbol);
				continue;
			}
			builder.append(todo + symbol);
		}
		
		return builder.toString();
	}
}
