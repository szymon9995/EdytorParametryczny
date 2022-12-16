package com.edytor.EdytorParametryczny.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;

import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.components.DrawSquare;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class PanelParametry extends JPanel{

	private static final long serialVersionUID = -7222404443844087521L;
	
	JPanel objectsList;
	JPanel objectEditor;
	
	DrawComponent curSelected;
	
	public PanelParametry()
	{
		super();
		this.setBackground(Color.red);
		this.setPreferredSize(new Dimension(150,600));
		this.setLayout(new GridLayout(2,1));
		
		objectsList = new JPanel();
		objectEditor = new JPanel();
		
		objectsList.setBackground(Color.cyan);
		objectEditor.setBackground(Color.white );
		objectsList.setBorder(BorderFactory.createLineBorder(Color.black));
		objectEditor.setBorder(BorderFactory.createLineBorder(Color.black));
		
		curSelected = null;
		
		EdytorData.GetGraph().getSelectionCell();
		
		EdytorData.GetGraph().addGraphSelectionListener(new ParListener());
		this.add(objectsList);
		this.add(objectEditor);
		
	}


	class ParListener implements GraphSelectionListener
	{

		@Override
		public void valueChanged(GraphSelectionEvent e) {
			
			List<DrawComponent> tmp = EdytorData.GetCells();
			
			for(DrawComponent com : tmp)
			{
				if(com.GetCell() == e.getCell())
				{
					curSelected = com;
					IFSquare s = new IFSquare();
					s.UpdateData();
					break;
				}
			}
			
		}

		
	}
	
	class IFSquare
	{
		JTextField posX;
		JTextField posY;
		JTextField posW;
		JTextField posH;
		JButton colorButton;
		JTextField textField;
		
		DrawSquare tmp;
		
		public void UpdateData()
		{
				tmp = (DrawSquare)curSelected;
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
}
