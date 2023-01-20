package com.edytor.EdytorParametryczny.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import com.edytor.EdytorParametryczny.przybiornikElement.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelPrzybiornik extends JPanel{

	private static final long serialVersionUID = -3171714815465134284L;
	
	private List<ElementPodstawa> elements;
	
	
	public PanelPrzybiornik()
	{
		super();
		this.setBackground(Color.yellow);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
		elements = new ArrayList<ElementPodstawa>();
		
		elements.add(new ElementSquare());
		elements.add(new ElementElipse());
		elements.add(new ElementDiamond());
		elements.add(new ElementRoundRect());
		elements.add(new ElementTriangle());
		elements.add(new ElementPolygon());
		elements.add(new ElementAndGate());
		
		for(ElementPodstawa el : elements)
		{
			this.add(el.GetButton());
		}
	}
	
	

}
