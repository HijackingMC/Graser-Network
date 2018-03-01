package com.grasernetwork.util.json;

public class ChildBuilder extends JsonBuilder
{
	private JsonBuilder _parent;
	
	public ChildBuilder(String text)
	{
		this(new StringBuilder(), text);
	}
	
	public ChildBuilder(StringBuilder builder, String text)
	{
		this(null, builder, text);
	}
	
	public ChildBuilder(JsonBuilder parent, StringBuilder builder, String text)
	{
		super(builder, text);

		_parent = parent;
	}
	
	public ChildBuilder add(String text)
	{
		Builder.append("}, ");
		return new ChildBuilder(_parent, Builder, text);
	}
	
	@Override
	public ChildBuilder color(String color)
	{
		super.color(color);

		return this;
	}

	@Override
	public ChildBuilder bold()
	{
		super.bold();

		return this;
	}
	
	@Override
	public ChildBuilder click(String action, String value)
	{
		super.click(action, value);
		
		return this;
	}
	
	@Override
	public ChildBuilder hover(String action, String value)
	{
		super.hover(action, value);
		
		return this;
	}
	
	@Override
	public String toString()
	{
		Builder.append("}");
		
		if (_parent != null)
		{
			Builder.append("]");
			return _parent instanceof ChildBuilder ? ((ChildBuilder)_parent).toString() : _parent.toString();
		}
		else
			return Builder.toString();
	}
}
