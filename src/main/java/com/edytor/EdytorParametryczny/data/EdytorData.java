package com.edytor.EdytorParametryczny.data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import com.edytor.EdytorParametryczny.components.DrawComponent;

public class EdytorData {
	private static List<DrawComponent> cells;
	private static GraphModel model;
	private static GraphLayoutCache view;
	private static JGraph graph;
	
	
	private static JScrollPane rysownik;
	
	public static void Init()
	{
		cells = new ArrayList<DrawComponent>();
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model,new OurCellViewFactory());
		graph = new JGraph(model,view);
		graph.setCloneable(true);
        graph.setInvokesStopCellEditing(true);
        graph.setJumpToDefaultPort(true);
        
	}
	
	public static List<DrawComponent> GetCells()
	{
		return cells;
	}
	
	public static GraphModel GetModel()
	{
		return model;
	}
	
	public static JGraph GetGraph()
	{
		return graph;
	}
	
	public static void AddCell(DrawComponent cell)
	{
		cells.add(cell);
		
		//List<DefaultGraphCell> tmp = new ArrayList<DefaultGraphCell>();
		//for(DrawComponent com : cells)
		//{
			//tmp.add(com.GetCell());	
		//}
		
		graph.getGraphLayoutCache().insert(cell.GetCell());
	}
	
	public static void SetRysownik(JScrollPane rys)
	{
		rysownik = rys;
	}
	
	public static void Repaint()
	{
		if(rysownik!=null)
		{
			graph.getGraphLayoutCache().reload();
			graph.repaint();
			graph.refresh();
			rysownik.revalidate();
			rysownik.repaint();
		}
	}
	
}
