package com.grasernetwork.util.json;

public class JsonBuilder
{
	protected StringBuilder Builder;

	public JsonBuilder(String text)
	{
		this(new StringBuilder(), text);
	}
	
	public JsonBuilder(StringBuilder builder, String text)
	{	
		Builder = builder;		
		Builder.append("{\"text\":\"" + text + "\"");
	}
	
	public JsonBuilder color(String color)
	{
		Builder.append(", color:" + color);
		return this;
	}
	
	public JsonBuilder bold()
	{
		Builder.append(", bold:true");

		return this;
	}

	public JsonBuilder italic()
	{
		Builder.append(", italic:true");

		return this;
	}

	public JsonBuilder underlined()
	{
		Builder.append(", underlined:true");

		return this;
	}

	public JsonBuilder strikethrough()
	{
		Builder.append(", strikethrough:true");

		return this;
	}

	public JsonBuilder obfuscated()
	{
		Builder.append(", obfuscated:true");

		return this;
	}
	
	public ChildBuilder extra(String text)
	{
		Builder.append(", \"extra\":[");
		return new ChildBuilder(this, Builder, text);
	}
	
	public JsonBuilder click(String action, String value)
	{
		Builder.append(", \"clickEvent\":{\"action\":\"" + action + "\",\"value\":\"" + value + "\"}");
		
		return this;
	}

	public JsonBuilder hover(String action, String value)
	{
		Builder.append(", \"hoverEvent\":{\"action\":\"" + action + "\",\"value\":\"" + value + "\"}");
		
		return this;
	}

	public JsonBuilder click(ClickEvent event, String value)
	{
		return click(event.toString(), value);
	}

	public JsonBuilder hover(HoverEvent event, String value)
	{
		return hover(event.toString(), value);
	}

	public JsonBuilder color(Color color)
	{
		return color(color.toString());
	}
	
	@Override
	public String toString()
	{
		Builder.append("}");
		
		return Builder.toString();
	}
}
