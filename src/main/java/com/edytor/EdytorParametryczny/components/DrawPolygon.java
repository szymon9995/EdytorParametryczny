package com.edytor.EdytorParametryczny.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;

import org.jgraph.graph.GraphConstants;

public class DrawPolygon extends DrawComponent{

	private Dimension size;
	
	private int n_size;
	
	@Override
	public String GetName() {
		return "Polygon";
	}
	
	public DrawPolygon(int width,int height)
	{
		super();
		size = new Dimension();
		n_size = 5;
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
	
	public int getNSize()
	{
		return n_size;
	}
	
	public void setNSize(int n)
	{
		n_size = n;
		if(n_size <5)
			n_size = 5;
		if(n_size > 10)
			n_size  = 10;
		Update();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void Update() {
		if(text !=null)
		{
			cell.setUserObject(text);
		}
		cell.getAttributes().put("n_size", n_size);
		
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
