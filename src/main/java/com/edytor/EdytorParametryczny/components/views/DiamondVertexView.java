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
	public Point2D getPerimeterPoint(EdgeView edge, Point2D source, Point2D p) {
		Point2D center = AbstractCellView.getCenterPoint(this);
		double halfwidth = getBounds().getWidth() / 2;
		double halfheight = getBounds().getHeight() / 2;
		Point2D top = new Point2D.Double(center.getX(), center.getY() - halfheight);
		Point2D bottom = new Point2D.Double(center.getX(), center.getY() + halfheight);
		Point2D left = new Point2D.Double(center.getX() - halfwidth, center.getY());
 		Point2D right = new Point2D.Double(center.getX() + halfwidth, center.getY());
		if (center.getX() == p.getX()) {
			if (center.getY() > p.getY())
				return (top);
			return bottom;
		}
		if (center.getY() == p.getY()) {
			if (center.getX() > p.getX())
				return (left);
			return right;
		}

		Point2D i;
		if (p.getX() < center.getX())
			if (p.getY() < center.getY())
				i = intersection(p, center, top, left);
			else
				i = intersection(p, center, bottom, left);
		else if (p.getY() < center.getY())
			i = intersection(p, center, top, right);
		else
			i = intersection(p, center, bottom, right);
		return i;
	}

	private Point2D intersection(Point2D lineOneStart, Point2D lineOneEnd,
									Point2D lineTwoStart, Point2D lineTwoEnd) {
		double m1 = (lineOneEnd.getY() - lineOneStart.getY())
					/ (lineOneEnd.getX() - lineOneStart.getX());
		double b1 = lineOneStart.getY() - m1 * lineOneStart.getX();
		double m2 = (lineTwoEnd.getY() - lineTwoStart.getY())
					/ (lineTwoEnd.getX() - lineTwoStart.getX());
		double b2 = lineTwoStart.getY() - m2 * lineTwoStart.getX();
		double xinter = (b1 - b2) / (m2 - m1);
		double yinter = m1 * xinter + b1;
		Point2D intersection = getAttributes().createPoint(xinter, yinter);
		return intersection;
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
