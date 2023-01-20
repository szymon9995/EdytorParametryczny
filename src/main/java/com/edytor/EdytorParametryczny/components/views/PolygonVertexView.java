package com.edytor.EdytorParametryczny.components.views;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class PolygonVertexView extends VertexView {


	private static final long serialVersionUID = 1L;

		public static transient JGraphPolygonRenderer renderer = new JGraphPolygonRenderer();

		public PolygonVertexView() {
			super();
		}

		public PolygonVertexView(Object cell) {
			super(cell);
		}

		public CellViewRenderer getRenderer() {
			DefaultGraphCell c = (DefaultGraphCell)cell;
			if(c.getAttributes().containsKey("n_size"))
			{
				int n = (int)c.getAttributes().get("n_size");
				renderer.SetN(n);
			}
			return renderer;
		}

		public static class JGraphPolygonRenderer extends VertexRenderer {

			private static final long serialVersionUID = 1L;

			private int n_times = 5;
			
			public void SetN(int n)
			{
				n_times = n;
			}

			public void paint(Graphics g) {
				int b = borderWidth;
				Graphics2D g2 = (Graphics2D) g;
				Rectangle rect = getBounds();
				
				boolean tmp = selected;
				
				int xPoints[] = new int[n_times];
				int yPoints[] = new int[n_times];
				
				int x = rect.getBounds().width/2;
				int y = rect.getBounds().height/2;
				
				int rw = rect.getBounds().width/2;
				int rh = rect.getBounds().height/2;
 				
				for(int i=0;i<n_times;i++)
				{
					xPoints[i] = ((int) (x + (rw * Math.cos(2 * Math.PI * i / n_times))));
					yPoints[i] = ((int) (y + (rh * Math.sin(2 * Math.PI * i / n_times))));
				}
				
				//int xPoints[] = new int[] {0 , 0 + rect.width , 0+(rect.width/2)};
				//int yPoints[] = new int[] {0 + rect.height , 0 + rect.height , 0};
				if (super.isOpaque()) {
					g.setColor(super.getBackground());
					if (gradientColor != null && !preview) {
						setOpaque(false);
						g2.setPaint(new GradientPaint(0, 0, getBackground(),
								getWidth(), getHeight(), gradientColor, true));
					}
					
					g.fillPolygon(xPoints,yPoints , n_times);
				}
				try {
					setBorder(null);
					setOpaque(false);
					selected = false;
					super.paint(g);
				} finally {
					selected = tmp;
				}
				if (bordercolor != null) {
					g.setColor(bordercolor);
					g2.setStroke(new BasicStroke(b));
					g.drawPolygon(xPoints,yPoints , n_times);
				}
				if (selected) {
					g2.setStroke(GraphConstants.SELECTION_STROKE);
					g.setColor(highlightColor);
					g.drawPolygon(xPoints,yPoints , n_times);
				}
			}
		}

	}

