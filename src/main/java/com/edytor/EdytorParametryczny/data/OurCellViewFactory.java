package com.edytor.EdytorParametryczny.data;

import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.VertexView;

import com.edytor.EdytorParametryczny.components.views.AndGateVertexView;
import com.edytor.EdytorParametryczny.components.views.DiamondVertexView;
import com.edytor.EdytorParametryczny.components.views.ElipseVertexView;
import com.edytor.EdytorParametryczny.components.views.PolygonVertexView;
import com.edytor.EdytorParametryczny.components.views.RoundRectVertexView;
import com.edytor.EdytorParametryczny.components.views.TriangleVertexView;

public class OurCellViewFactory extends DefaultCellViewFactory{

	private static final long serialVersionUID = -4130593481117881670L;

	protected VertexView createVertexView(Object cell) {
		
		DefaultGraphCell c = (DefaultGraphCell)cell;
		if(c.getAttributes().containsKey("cellShape"))
		{
			String shapeName = (String)c.getAttributes().get("cellShape");
			switch(shapeName)
			{
			
			case "Elipse":
				return new ElipseVertexView(cell);
				
			case "Diamond":
				return new DiamondVertexView(cell);
				
			case "RoundRect":
				return new RoundRectVertexView(cell);
				
			case "Triangle":
				return new TriangleVertexView(cell);
				
			case "Polygon":
				return new PolygonVertexView(cell);
				
			case "AndGate":
				return new AndGateVertexView(cell);
				
			case "Default":
			case "Square":
			default:
				break;
			}
		}
		
		return new VertexView(cell);
	}
}
