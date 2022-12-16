package com.edytor.EdytorParametryczny.windows;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.edytor.EdytorParametryczny.przybiornikElement.*;

import javax.swing.JPanel;

public class PanelPrzybiornik extends JPanel{

	private static final long serialVersionUID = -3171714815465134284L;
	
	private List<ElementPodstawa> elements;
	
	
	public PanelPrzybiornik()
	{
		super();
		this.setBackground(Color.yellow);
		elements = new ArrayList<ElementPodstawa>();
		
		elements.add(new ElementSquare());
		
		for(ElementPodstawa el : elements)
		{
			this.add(el.GetButton());
		}
	}
	
	

}
