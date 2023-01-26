package com.edytor.EdytorParametryczny.components.views;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class ElipseVertexView extends VertexView {


	private static final long serialVersionUID = 1L;

		public static transient JGraphEllipseRenderer renderer = new JGraphEllipseRenderer();

		public ElipseVertexView() {
			super();
		}

		public ElipseVertexView(Object cell) {
			super(cell);
		}

		public CellViewRenderer getRenderer() {
			return renderer;
		}

		public static class JGraphEllipseRenderer extends VertexRenderer {

			private static final long serialVersionUID = 1L;


			public void paint(Graphics g) {
				int b = borderWidth;
				Graphics2D g2 = (Graphics2D) g;
				Dimension d = getSize();
				boolean tmp = selected;
				if (super.isOpaque()) {
					g.setColor(super.getBackground());
					if (gradientColor != null && !preview) {
						setOpaque(false);
						g2.setPaint(new GradientPaint(0, 0, getBackground(),
								getWidth(), getHeight(), gradientColor, true));
					}
					g.fillOval(b - 1, b - 1, d.width - b, d.height - b);
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
					g.drawOval(b - 1, b - 1, d.width - b, d.height - b);
				}
				if (selected) {
					g2.setStroke(GraphConstants.SELECTION_STROKE);
					g.setColor(highlightColor);
					g.drawOval(b - 1, b - 1, d.width - b, d.height - b);
				}
			}
		}

	}
