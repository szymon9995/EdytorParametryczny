package com.edytor.EdytorParametryczny.IFparameters;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class IFElipse extends IFShape
{
	public IFElipse(DrawComponent selected, JPanel editor) {
		super(selected, editor);
	}



	JTextField posX;
	JTextField posY;
	JTextField posW;
	JTextField posH;
	JButton colorButton;
	JTextField textField;
	
	DrawElipse tmp;
	
	
	
	public void UpdateData()
	{
			tmp = (DrawElipse)curSelected;
			objectEditor.removeAll();
			
			
			String x = ""+tmp.GetX();
			posX = new JTextField(x,10);
			String y = ""+tmp.GetY();
			posY = new JTextField(y,10);
			String w = ""+tmp.GetWidth();
			posW = new JTextField(w,10);
			String h = ""+tmp.GetHeight();
			posH = new JTextField(h,10);
			colorButton = new JButton("Color");
			textField = new JTextField(tmp.GetText(),10);
			
			
			objectEditor.add(posX);
			objectEditor.add(posY);
			objectEditor.add(posW);
			objectEditor.add(posH);
			objectEditor.add(colorButton);
			objectEditor.add(textField);
			
			objectEditor.revalidate();
			objectEditor.repaint();
			
			
			posX.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(posX.getText()!=null)
							{
								int xPos =Integer.parseInt(posX.getText());
								if(xPos>-1)
								{
									tmp.SetX(xPos);
									EdytorData.Repaint();
								}
							}
							
						}
				
					}
					);
			
			posY.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(posY.getText()!=null)
					{
						int yPos =Integer.parseInt(posY.getText());
						if(yPos>-1)
						{
							tmp.SetY(yPos);
							EdytorData.Repaint();
						}
					}
					
				}
		
			}
			);
			
			posW.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(posW.getText()!=null)
					{
						int wPos =Integer.parseInt(posW.getText());
						if(wPos>-1)
						{
							tmp.SetWidth(wPos);
							EdytorData.Repaint();
						}
					}
					
				}
		
			}
			);
			
			posH.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(posH.getText()!=null)
					{
						int hPos =Integer.parseInt(posH.getText());
						if(hPos>-1)
						{
							tmp.SetHeight(hPos);
							EdytorData.Repaint();
						}
					}
					
				}
		
			}
			);
			
			colorButton.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							JPanel panel = new JPanel();
							
							JColorChooser fieldCol = new JColorChooser();
							panel.add(fieldCol);
							
							int option = JOptionPane.showConfirmDialog(null, panel, "Color", JOptionPane.CANCEL_OPTION);
							if(option == JOptionPane.OK_OPTION)
							{
								Color color = fieldCol.getColor();
								if(color !=null)
								{
									tmp.SetBackgroundColor(color);
									EdytorData.Repaint();
								}
							}
							
						}
				
					}
					
					);
			
			textField.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							String text = textField.getText();
							if(text == null)
							{
								text = "";
							}
							
							tmp.SetText(text);
							EdytorData.Repaint();
							
						}
				
					}
					
					);
			
		
	}
	
}
