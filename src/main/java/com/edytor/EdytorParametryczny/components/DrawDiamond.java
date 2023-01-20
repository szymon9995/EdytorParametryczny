package com.edytor.EdytorParametryczny.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;

import org.jgraph.graph.GraphConstants;

public class DrawDiamond extends DrawComponent{

	@Override
	public String GetName() {
		return "Diamond";
	}

private Dimension size;
	
	public DrawDiamond(int width,int height)
	{
		super();
		size = new Dimension();
		SetSize(width,height);
	}
	
	public void SetSize(int width,int height)
	{
		size.width = width;
		size.height = height;
		
		Update();
	}
	
	public Dimension GetSize()
	{
		return size;
	}
	
	public int GetWidth()
	{
		return size.width;
	}
	
	public int GetHeight()
	{
		return size.height;
	}
	
	public void SetWidth(int width)
	{
		size.width = width;
		Update();
	}
	
	public void SetHeight(int height)
	{
		size.height = height;
		Update();
	}

	@Override
	protected void Update() {
		if(text !=null)
		{
			cell.setUserObject(text);
		}
		
		
		GraphConstants.setBounds(cell.getAttributes(),
                new Rectangle2D.Double(x, y, size.width, size.height));

        if (bg != null) {
            GraphConstants.setGradientColor(cell.getAttributes(), bg);
            GraphConstants.setOpaque(cell.getAttributes(), isOpaque);
        }
        
        if(fg !=null)
        {
        	//unsuded for square
        }

        if (raised) {
            GraphConstants.setBorder(cell.getAttributes(),
                    BorderFactory.createRaisedBevelBorder());
        } else 
        {
            GraphConstants.setBorderColor(cell.getAttributes(),
                    Color.black);
        }
        
	}

}
