package com.edytor.EdytorParametryczny.components.views;

import org.jgraph.graph.VertexView;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;


public class DiamondVertexView extends VertexView{

	private static final long serialVersionUID = -8252358916550471203L;
	
	public static transient JGraphDiamondRenderer renderer = new JGraphDiamondRenderer();

	public DiamondVertexView() {
		super();
	}

	public DiamondVertexView(Object cell) {
		super(cell);
	}
	public CellViewRenderer getRenderer() {
		return renderer;
	}

	public static class JGraphDiamondRenderer extends VertexRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		JGraphDiamondRenderer() {
			super();
		}

		public void paint(Graphics g) {
			int b = borderWidth;
			Graphics2D g2 = (Graphics2D) g;
			Dimension d = getSize();
			boolean tmp = selected;
			int width = d.width - b;
			int height = d.height - b;
			int halfWidth = (d.width - b) / 2;
			int halfHeight = (d.height - b) / 2;
			int[] xpoints = {halfWidth, width, halfWidth, 0};
			int[] ypoints = {0, halfHeight, height, halfHeight};
			Polygon diamond = new Polygon(xpoints, ypoints, 4);
			if (super.isOpaque()) {
				g.setColor(super.getBackground());
				if (gradientColor != null && !preview) {
					setOpaque(false);
					g2.setPaint(new GradientPaint(0, 0, getBackground(), getWidth(),
                     		getHeight(), gradientColor, true));
				}
				g.fillPolygon(diamond);
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
				g.drawPolygon(diamond);
			}
			if (selected) {
				g2.setStroke(GraphConstants.SELECTION_STROKE);
				g.setColor(highlightColor);
				g.drawPolygon(diamond);
			}
		}

		protected void paintBorder(Graphics g) {
			super.paintBorder(g);
		}
	}

}
