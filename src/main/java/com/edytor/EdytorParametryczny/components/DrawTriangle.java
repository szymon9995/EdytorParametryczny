package com.edytor.EdytorParametryczny.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;

import org.jgraph.graph.GraphConstants;

public class DrawTriangle extends DrawComponent{

	private Dimension size;
	private float triangle_float;
	
	@Override
	public String GetName() {
		return "Triangle";
	}
	
	public DrawTriangle(int width,int height)
	{
		super();
		size = new Dimension();
		triangle_float = 0.5f;
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
	
	public float GetTrinagleFloat()
	{
		return triangle_float;
	}
	
	public void SetTriangleFloat(float tri)
	{
		triangle_float = tri;
		if(triangle_float>1.0f)
			triangle_float = 1.0f;
		if(triangle_float < 0.0f)
			triangle_float = 0.0f;
		Update();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void Update() {
		if(text !=null)
		{
			cell.setUserObject(text);
		}
		cell.getAttributes().put("triangle_float", triangle_float);
		
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