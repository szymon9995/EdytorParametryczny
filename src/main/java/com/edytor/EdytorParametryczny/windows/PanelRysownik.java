package com.edytor.EdytorParametryczny.windows;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;

import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.components.DrawSquare;
import com.edytor.EdytorParametryczny.data.EdytorData;
import com.edytor.EdytorParametryczny.przybiornikElement.ElementPodstawa;

public class PanelRysownik extends JPanel{

	private static final long serialVersionUID = -9126965850474138238L;
	
	JScrollPane pane;
	
	public PanelRysownik()
	{
		super();
		this.setBackground(Color.white);
        
        pane = new JScrollPane(EdytorData.GetGraph());
        pane.setPreferredSize(new Dimension(550,600));
        
        this.add(pane);
        
        EdytorData.SetRysownik(pane);
        
        //Test();
        
	}
	
	private void Test()
	{
		DrawSquare square = new DrawSquare(30,30);
        square.SetCords(0, 40);
        square.SetBackgroundColor(Color.RED);
        square.SetOpaque(true);
        square.SetText("Test1");
        
        DrawSquare square2 = new DrawSquare(30,60);
        square2.SetCords(0, 80);
        square2.SetBackgroundColor(Color.YELLOW);
        square2.SetOpaque(true);
       
        
        EdytorData.AddCell(square);
        EdytorData.AddCell(square2);
        
        
        DefaultEdge edge = new DefaultEdge("test");
        
        edge.setSource(EdytorData.GetCells().get(0).GetCell().getChildAt(0));
        edge.setTarget(EdytorData.GetCells().get(1).GetCell().getChildAt(0));
        
        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);
        
        EdytorData.GetGraph().getGraphLayoutCache().insert(edge);
  
        
        
        DrawElipse elipse = new DrawElipse(100,100);
        elipse.SetCords(200, 40);
        elipse.SetBackgroundColor(Color.RED);
        elipse.SetOpaque(true);
        elipse.SetText("Test Elipse");
        EdytorData.AddCell(elipse);
        //EdytorData.AddCell(edge);
        
        
	}

}
