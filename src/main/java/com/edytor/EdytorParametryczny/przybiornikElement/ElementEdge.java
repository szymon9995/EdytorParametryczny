package com.edytor.EdytorParametryczny.przybiornikElement;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jgraph.graph.GraphConstants;

import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.data.EdytorData;

public class ElementEdge extends ElementPodstawa{

	private ListSelectionListener listener;
	private JList<Object> selection1;
	private JScrollPane listSelection1;
	private JList<Object> selection2;
	private JScrollPane listSelection2;
	private int s1;
	private int s2;
	@SuppressWarnings("rawtypes")
	private JComboBox arrowTypesBox;
	private Map<String,Integer> arrowNames;
	private String selectedArrowName;
	private ItemListener itemListener;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void OnButtonPress() {
		JPanel panel = new JPanel();
		
		List<String>cellNames = getListOfCells();
		
		selection1 = new JList<Object>(cellNames.toArray());
		selection1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selection1.setLayoutOrientation(JList.VERTICAL);
		listSelection1 = new JScrollPane(selection1);
		
		selection2 = new JList<Object>(cellNames.toArray());
		selection2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		selection2.setLayoutOrientation(JList.VERTICAL);
		listSelection2 = new JScrollPane(selection2);
		
		selectedArrowName = "Classic";
		CreateArrowMap();
		
		arrowTypesBox = new JComboBox();
		for(Map.Entry<String, Integer> o : arrowNames.entrySet())
		{
			arrowTypesBox.addItem(o.getKey());
		}
		
		panel.add(new JLabel("Source:"));
		panel.add(listSelection1);
		panel.add(new JLabel("Target:"));
		panel.add(listSelection2);
		panel.add(new JLabel("Arrow Type:"));
		panel.add(arrowTypesBox);
		
		s1 = -1;
		s2 = -1;
		
		listener = new ListSelectionListener()
				{

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(e.getSource() == selection1)
						{
							if (e.getValueIsAdjusting() == false)
							{
								s1 = selection1.getSelectedIndex();
							}
						}
						if(e.getSource() == selection2)
						{
							if (e.getValueIsAdjusting() == false)
							{
								s2 = selection2.getSelectedIndex();
							}
						}
						
					}
			
				};
				
		selection1.addListSelectionListener(listener);
		selection2.addListSelectionListener(listener);
				
				itemListener = new ItemListener()
						{

							@Override
							public void itemStateChanged(ItemEvent e) {
								if(e.getSource() == arrowTypesBox)
								{
									if(e.getStateChange() == ItemEvent.SELECTED)
									{
										selectedArrowName = arrowTypesBox.getSelectedItem().toString();
									}
								}
								
							}
						
						};
						
		arrowTypesBox.addItemListener(itemListener);
		
				int option = JOptionPane.showConfirmDialog(null, panel, "Edge", JOptionPane.CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION)
				{
					
					EdytorData.AddEdge("", arrowNames.get(selectedArrowName), s1, s2);
				}
	}
	
	private void CreateArrowMap()
	{
		arrowNames = new HashMap<String,Integer>();
		arrowNames.put("Classic", GraphConstants.ARROW_CLASSIC);
		arrowNames.put("Circle", GraphConstants.ARROW_CIRCLE);
		arrowNames.put("Diamond", GraphConstants.ARROW_DIAMOND);
		arrowNames.put("Line", GraphConstants.ARROW_LINE);
		arrowNames.put("DoubleLine", GraphConstants.ARROW_DOUBLELINE);
		arrowNames.put("None", GraphConstants.ARROW_NONE);
		arrowNames.put("Simple", GraphConstants.ARROW_SIMPLE);
		arrowNames.put("Technical", GraphConstants.ARROW_TECHNICAL);
	}
	
	private List<String> getListOfCells()
	{
		List<DrawComponent> cells =  EdytorData.GetCells();
		
		List<String>cellNames = new ArrayList<String>();
		int i=0;
		for (DrawComponent cell : cells)
		{
			cellNames.add(cell.GetName() + i);
			i++;
		}
		
		return cellNames;
	}

	@Override
	protected String GetName() {
		return "Edge";
	}

}
