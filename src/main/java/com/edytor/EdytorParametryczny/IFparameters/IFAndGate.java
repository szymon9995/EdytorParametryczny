package com.edytor.EdytorParametryczny.IFparameters;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.edytor.EdytorParametryczny.components.DrawAndGate;
import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class IFAndGate extends IFShape
{
	public IFAndGate(DrawComponent selected, JPanel editor) {
		super(selected, editor);
	}



	JTextField posX;
	JTextField posY;
	JTextField posW;
	JTextField posH;
	JButton colorButton;
	JTextField textField;
	ActionListener listener;
	
	JLabel XLabel;
	JLabel YLabel;
	JLabel WLabel;
	JLabel HLabel;
	JLabel CLabel;
	JLabel TLabel;
	
	DrawAndGate tmp;
	
	
	
	public void UpdateData()
	{
			tmp = (DrawAndGate)curSelected;
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
			
			
			XLabel = new JLabel("X:");
			YLabel = new JLabel("Y:");
			WLabel = new JLabel("Width:");
			HLabel = new JLabel("Height:");
			CLabel = new JLabel("");
			TLabel = new JLabel("Text:");
			
			objectEditor.add(XLabel);
			objectEditor.add(posX);
			objectEditor.add(YLabel);
			objectEditor.add(posY);
			objectEditor.add(WLabel);
			objectEditor.add(posW);
			objectEditor.add(HLabel);
			objectEditor.add(posH);
			objectEditor.add(CLabel);
			objectEditor.add(colorButton);
			objectEditor.add(TLabel);
			objectEditor.add(textField);
			
			objectEditor.setLayout(new BoxLayout(objectEditor, BoxLayout.PAGE_AXIS));
			
			listener = new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getSource() == posX)
							{
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
							if(e.getSource() == posY)
							{
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
							if(e.getSource() == posW)
							{
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
							if(e.getSource() == posH)
							{
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
							if(e.getSource() == colorButton)
							{
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
							if(e.getSource() == textField)
							{
								String text = textField.getText();
								if(text == null)
								{
									text = "";
								}
								
								tmp.SetText(text);
								EdytorData.Repaint();
							}
							
						}
				
					};
			posX.addActionListener(listener);
			posY.addActionListener(listener);
			posW.addActionListener(listener);
			posH.addActionListener(listener);
			colorButton.addActionListener(listener);
			textField.addActionListener(listener);
					
			
			objectEditor.revalidate();
			objectEditor.repaint();
			
			/*
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
			*/
		
	}
	
}
