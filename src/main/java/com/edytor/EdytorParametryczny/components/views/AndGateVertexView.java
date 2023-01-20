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
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class AndGateVertexView extends VertexView {


	private static final long serialVersionUID = 1L;

		public static transient JGraphAndRenderer renderer = new JGraphAndRenderer();

		public AndGateVertexView() {
			super();
		}

		public AndGateVertexView(Object cell) {
			super(cell);
		}

		public CellViewRenderer getRenderer() {
			return renderer;
		}

		public static class JGraphAndRenderer extends VertexRenderer {

			private static final long serialVersionUID = 1L;

			private float cut = 0.65f;

			public void paint(Graphics g) {
				int b = borderWidth;
				Graphics2D g2 = (Graphics2D) g;
				
				Rectangle bounds = getBounds();
				int x = (int) (  (bounds.getWidth() * cut) - ((bounds.width * (1-cut)/ 2 ) ));
				boolean tmp = selected;
				if (super.isOpaque()) {
					g.setColor(super.getBackground());
					if (gradientColor != null && !preview) {
						setOpaque(false);
						g2.setPaint(new GradientPaint(0, 0, getBackground(),
								getWidth(), getHeight(), gradientColor, true));
					}
					g2.fillRect(0, 0, (int) (bounds.width * cut), bounds.height);
					g2.fillArc(x, 0, (int) (bounds.width * (1-cut)), bounds.height, 90,-180);
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
					g2.drawRect(0, 0, (int) (bounds.width * cut), bounds.height);
					g2.drawArc(x, 0, (int) (bounds.width * (1-cut)), bounds.height, 90,270);
				}
				if (selected) {
					g2.setStroke(GraphConstants.SELECTION_STROKE);
					g.setColor(highlightColor);
					g2.drawRect(0, 0, (int) (bounds.width * cut), bounds.height);
					g2.drawArc(x, 0, (int) (bounds.width * (1-cut)), bounds.height, 90,270);
				}
			}
		}

	}
