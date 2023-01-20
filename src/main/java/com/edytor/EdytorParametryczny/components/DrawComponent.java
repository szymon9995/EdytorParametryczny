package com.edytor.EdytorParametryczny.components;

import java.awt.Color;

import org.jgraph.graph.DefaultGraphCell;

public abstract class DrawComponent {

	
	protected DefaultGraphCell cell;
	
	protected int x;
	protected int y;
	protected Color bg;
	protected Color fg;
	protected boolean isOpaque;
	protected boolean raised;
	protected String text;
	
	
	public String GetName()
	{
		return "default";
	}
	protected abstract void Update();
	
	
	@SuppressWarnings("unchecked")
	protected void SetCellShapeAtribute()
	{
		cell.getAttributes().put("cellShape", GetName());
	}
	
	public DefaultGraphCell GetCell()
	{
		return cell;
	}
	
	public DrawComponent()
	{
		cell = new DefaultGraphCell();
		x=0;
		y=0;
		bg = Color.white;
		fg = Color.black;
		raised = true;
		isOpaque = true;
		text = null;
		
		cell.addPort();
		
		SetCellShapeAtribute();
	}
	
	public void SetCords(int x,int y)
	{
		this.x=x;
		this.y=y;
		
		Update();
	}
	
	public void SetBackgroundColor(Color color)
	{
		bg = color;
		
		Update();
	}
	
	public void SetForegroundColor(Color color)
	{
		fg = color;
		
		Update();
	}
	
	public void SetOpaque(boolean opaque)
	{
		isOpaque = opaque;
		
		Update();
	}
	
	public void SetText(String name)
	{
		text = name;
		
		Update();
	}
	
	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
	public void SetX(int x)
	{
		this.x=x;
		Update();
	}
	
	public void SetY(int y)
	{
		this.y=y;
		Update();
	}
	
	public Color GetBackroundColor()
	{
		return bg;
	}
	
	public String GetText()
	{
		return text;
	}
}
