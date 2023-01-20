package com.edytor.EdytorParametryczny.components.views;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

import com.edytor.EdytorParametryczny.components.views.ElipseVertexView.JGraphEllipseRenderer;

public class TriangleVertexView extends VertexView {


	private static final long serialVersionUID = 1L;

		public static transient JGraphTriangleRenderer renderer = new JGraphTriangleRenderer();

		public TriangleVertexView() {
			super();
		}

		public TriangleVertexView(Object cell) {
			super(cell);
		}

		public CellViewRenderer getRenderer() {
			DefaultGraphCell c = (DefaultGraphCell)cell;
			if(c.getAttributes().containsKey("triangle_float"))
			{
				float n = (float)c.getAttributes().get("triangle_float");
				renderer.SetTriangleFloat(n);
			}
			return renderer;
		}

		public static class JGraphTriangleRenderer extends VertexRenderer {

			private static final long serialVersionUID = 1L;

			private float triangle_float = 0.5f;
			
			public void SetTriangleFloat(float tri)
			{
				triangle_float = tri;
			}

			public void paint(Graphics g) {
				int b = borderWidth;
				Graphics2D g2 = (Graphics2D) g;
				Rectangle rect = getBounds();
				
				boolean tmp = selected;
				
				int xPoints[] = new int[] {0 , 0 + rect.width , (int) (0+ (rect.width * triangle_float))};
				int yPoints[] = new int[] {0 + rect.height , 0 + rect.height , 0};
				if (super.isOpaque()) {
					g.setColor(super.getBackground());
					if (gradientColor != null && !preview) {
						setOpaque(false);
						g2.setPaint(new GradientPaint(0, 0, getBackground(),
								getWidth(), getHeight(), gradientColor, true));
					}
					g.fillPolygon(xPoints,yPoints , 3);
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
					g.drawPolygon(xPoints,yPoints , 3);
				}
				if (selected) {
					g2.setStroke(GraphConstants.SELECTION_STROKE);
					g.setColor(highlightColor);
					g.drawPolygon(xPoints,yPoints , 3);
				}
			}
		}

	}
