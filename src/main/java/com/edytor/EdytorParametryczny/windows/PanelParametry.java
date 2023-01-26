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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;

import com.edytor.EdytorParametryczny.IFparameters.IFAndGate;
import com.edytor.EdytorParametryczny.IFparameters.IFDiamond;
import com.edytor.EdytorParametryczny.IFparameters.IFElipse;
import com.edytor.EdytorParametryczny.IFparameters.IFPolygon;
import com.edytor.EdytorParametryczny.IFparameters.IFRoundRect;
import com.edytor.EdytorParametryczny.IFparameters.IFSquare;
import com.edytor.EdytorParametryczny.IFparameters.IFTriangle;
import com.edytor.EdytorParametryczny.components.DrawAndGate;
import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.components.DrawDiamond;
import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.components.DrawPolygon;
import com.edytor.EdytorParametryczny.components.DrawRoundRect;
import com.edytor.EdytorParametryczny.components.DrawSquare;
import com.edytor.EdytorParametryczny.components.DrawTriangle;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class PanelParametry extends JPanel{

	private static final long serialVersionUID = -7222404443844087521L;
	
	JPanel objectsList;
	JPanel objectEditor;
	
	JLabel objectName;
	
	DrawComponent curSelected;
	
	public PanelParametry()
	{
		super();
		this.setBackground(Color.red);
		this.setPreferredSize(new Dimension(150,600));
		this.setLayout(new GridLayout(2,1));
		
		objectsList = new JPanel();
		objectEditor = new JPanel();
		
		objectsList.setBackground(new Color(116, 185, 255));
		objectEditor.setBackground(new Color(178, 190, 195) );
		objectsList.setBorder(BorderFactory.createLineBorder(Color.black));
		objectEditor.setBorder(BorderFactory.createLineBorder(Color.black));
		
		objectName = new JLabel("");
		
		curSelected = null;
		
		EdytorData.GetGraph().getSelectionCell();
		
		EdytorData.GetGraph().addGraphSelectionListener(new ParListener());
		this.add(objectsList);
		this.add(objectEditor);
		
		
		objectsList.add(objectName);
		
	}


	class ParListener implements GraphSelectionListener
	{

		@Override
		public void valueChanged(GraphSelectionEvent e) {
			
			List<DrawComponent> tmp = EdytorData.GetCells();
			
			int i=0;
			for(DrawComponent com : tmp)
			{
				if(com.GetCell() == e.getCell())
				{
					objectName.setText(com.GetName()+i);
					
					curSelected = com;
					if(curSelected instanceof DrawSquare)
					{
						IFSquare s = new IFSquare(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawElipse)
					{
						IFElipse s = new IFElipse(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawDiamond)
					{
						IFDiamond s = new IFDiamond(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawRoundRect)
					{
						IFRoundRect s = new IFRoundRect(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawTriangle)
					{
						IFTriangle s = new IFTriangle(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawPolygon)
					{
						IFPolygon s = new IFPolygon(curSelected,objectEditor);
						s.UpdateData();
					}
					else if(curSelected instanceof DrawAndGate)
					{
						IFAndGate s = new IFAndGate(curSelected,objectEditor);
						s.UpdateData();
					}
					break;
				}
				i++;
			}
			
		}

		
	}
	
	
	
}
