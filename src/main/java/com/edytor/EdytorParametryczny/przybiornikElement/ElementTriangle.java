package com.edytor.EdytorParametryczny.przybiornikElement;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.components.DrawTriangle;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class ElementTriangle extends ElementPodstawa{

	@Override
	protected void OnButtonPress() {
		
		JPanel panel = new JPanel();
		
		JTextField fieldX = new JTextField("0",6);
		JTextField fieldY = new JTextField("0",6);
		JTextField fieldW = new JTextField("10",6);
		JTextField fieldH = new JTextField("10",6);
		JSlider posTriFloat = new JSlider();
		posTriFloat.setMinimum(0);
		posTriFloat.setMaximum(100);
		posTriFloat.setValue(50);
		JColorChooser fieldCol = new JColorChooser();
		
		panel.add(new JLabel("X:"));
		panel.add(fieldX);
		panel.add(new JLabel("Y:"));
		panel.add(fieldY);
		panel.add(new JLabel("Width:"));
		panel.add(fieldW);
		panel.add(new JLabel("Height:"));
		panel.add(fieldH);
		panel.add(new JLabel("Spot:"));
		panel.add(posTriFloat);
		panel.add(new JLabel("Color:"));
		panel.add(fieldCol);
		
		int option = JOptionPane.showConfirmDialog(null, panel, "Triangle", JOptionPane.CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			int x,y,w,h;
			x=y=w=h=-1;
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
			
			if(x>-1 && y>-1 && w>0 && h>0)
			{
				DrawTriangle elipse = new DrawTriangle(w,h);
				elipse.SetCords(x, y);
				
				int tri = posTriFloat.getValue();
				float tri_float = tri / 100.0f;
				elipse.SetTriangleFloat(tri_float);
				
				Color col = fieldCol.getColor();
				if(col!=null)
				{
					elipse.SetBackgroundColor(col);
				}

				EdytorData.AddCell(elipse);
				
				//m_rysownik.AddCell(square.GetCell());
			}
		}
		
	}

	@Override
	protected String GetName() {
		
		return "Triangle";
	}

}
