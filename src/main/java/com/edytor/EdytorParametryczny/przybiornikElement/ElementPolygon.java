package com.edytor.EdytorParametryczny.przybiornikElement;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.edytor.EdytorParametryczny.components.DrawPolygon;
import com.edytor.EdytorParametryczny.components.DrawTriangle;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class ElementPolygon extends ElementPodstawa{

	@Override
	protected void OnButtonPress() {
		
		JPanel panel = new JPanel();
		
		JTextField fieldX = new JTextField("0",6);
		JTextField fieldY = new JTextField("0",6);
		JTextField fieldW = new JTextField("10",6);
		JTextField fieldH = new JTextField("10",6);
		JTextField fieldN = new JTextField("5",6);
		JColorChooser fieldCol = new JColorChooser();
		
		panel.add(fieldX);
		panel.add(fieldY);
		panel.add(fieldW);
		panel.add(fieldH);
		panel.add(fieldN);
		panel.add(fieldCol);
		
		int option = JOptionPane.showConfirmDialog(null, panel, "Elipse", JOptionPane.CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			int x,y,w,h,n;
			x=y=w=h=n=-1;
			if(fieldX.getText() != null)
			{
				x = Integer.parseInt(fieldX.getText());
			}
			if(fieldY.getText() != null)
			{
				y = Integer.parseInt(fieldY.getText());
			}
			if(fieldW.getText() != null)
			{
				w = Integer.parseInt(fieldW.getText());
			}
			if(fieldH.getText() != null)
			{
				h = Integer.parseInt(fieldH.getText());
			}
			if(fieldH.getText() != null)
			{
				n = Integer.parseInt(fieldN.getText());
			}
			
			if(x>-1 && y>-1 && w>0 && h>0)
			{
				DrawPolygon polygon = new DrawPolygon(w,h);
				polygon.SetCords(x, y);
				
				polygon.setNSize(n);
				Color col = fieldCol.getColor();
				if(col!=null)
				{
					polygon.SetBackgroundColor(col);
				}

				EdytorData.AddCell(polygon);
				
				//m_rysownik.AddCell(square.GetCell());
			}
		}
		
	}

	@Override
	protected String GetName() {
		
		return "Polygon";
	}

}
