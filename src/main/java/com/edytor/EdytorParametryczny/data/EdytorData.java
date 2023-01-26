package com.edytor.EdytorParametryczny.data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import com.edytor.EdytorParametryczny.components.DrawComponent;

public class EdytorData {
	private static List<DrawComponent> cells;
	private static List<DefaultEdge> edges;
	private static GraphModel model;
	private static GraphLayoutCache view;
	private static JGraph graph;
	
	
	private static JScrollPane rysownik;
	
	public static void Init()
	{
		cells = new ArrayList<DrawComponent>();
		edges = new ArrayList<DefaultEdge>();
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model,new OurCellViewFactory());
		graph = new JGraph(model,view);
		graph.setCloneable(false);
        graph.setInvokesStopCellEditing(true);
        graph.setJumpToDefaultPort(true);
        
	}
	
	public static List<DrawComponent> GetCells()
	{
		return cells;
	}
	
	public static List<DefaultEdge> GetEdges()
	{
		return edges;
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
	
	public static void AddEdge(String text,int arrowType,int source,int target)
	{

        DefaultEdge edge = new DefaultEdge(text);
        
        if(source > -1)
        	edge.setSource(EdytorData.GetCells().get(source).GetCell().getChildAt(0));
        if(target > -1)
        	edge.setTarget(EdytorData.GetCells().get(target).GetCell().getChildAt(0));
        
        GraphConstants.setLineEnd(edge.getAttributes(), arrowType);
        GraphConstants.setEndFill(edge.getAttributes(), true);
        
        graph.getGraphLayoutCache().insert(edge);
        edges.add(edge);
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
	
	public static void Clear()
	{		
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model,new OurCellViewFactory());
		graph.setModel(model);
		graph.setGraphLayoutCache(view);

		cells.clear();
		edges.clear();
		Repaint();
		System.out.println("Cleared");
	}
	
	public static void LoadView(GraphLayoutCache v)
	{
		view = v;
	}
	
}
