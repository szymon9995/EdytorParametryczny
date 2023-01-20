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

import com.edytor.EdytorParametryczny.Test3;
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
        
        Test();
        
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
        
        DrawSquare square3 = new DrawSquare(30,30);
   
        Test3 t3 = new Test3(square3.GetCell());
        String msg = (String)square3.GetCell().getAttributes().get("Rectangle2D");
        Map m = square3.GetCell().getAttributes();
        
        square3.GetCell().getAttributes().put("cellShape", "default");
        
        System.out.println(square3.GetCell().getAttributes().keySet().toString());
        System.out.println(square3.GetCell().getAttributes().values().toString());
        System.out.println(msg);
        System.out.println(m.size());
        square3.SetCords(100, 100);
        square3.SetBackgroundColor(Color.GREEN);
        square3.SetOpaque(true);
        square3.SetText("Test1");
        EdytorData.AddCell(square3);
        
        
        DrawElipse elipse = new DrawElipse(100,100);
        elipse.SetCords(200, 40);
        elipse.SetBackgroundColor(Color.RED);
        elipse.SetOpaque(true);
        elipse.SetText("Test Elipse");
        EdytorData.AddCell(elipse);
        //EdytorData.AddCell(edge);
        
        
	}

}
