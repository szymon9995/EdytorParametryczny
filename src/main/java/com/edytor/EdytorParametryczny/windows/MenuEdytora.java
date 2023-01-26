package com.edytor.EdytorParametryczny.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.edytor.EdytorParametryczny.data.EdytorData;
import com.edytor.EdytorParametryczny.data.SaveLoadSystem;

public class MenuEdytora extends JMenuBar{

	private static final long serialVersionUID = 7001878944333697466L;

	JMenu plik;
	JMenuItem zapisz;
	JMenuItem wyczysc;
	JMenuItem otworz;
	ActionListener listener;
	
	public MenuEdytora()
	{
		super();
		
		plik = new JMenu("Plik");
		
		zapisz = new JMenuItem("Zapisz");
		wyczysc = new JMenuItem("Wyczysc");
		otworz = new JMenuItem("Otworz...");
		
		plik.add(zapisz);
		plik.add(otworz);
		plik.add(wyczysc);
		
		listener = new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == zapisz)
						{
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setDialogTitle("Specify a file to save");   
							FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files","json","json");
							fileChooser.setFileFilter(filter);
							
							int userSelection = fileChooser.showSaveDialog(plik);
							 
							if (userSelection == JFileChooser.APPROVE_OPTION) {
							    String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
								SaveLoadSystem.Save(fileToSave);
							}
						}
						if(e.getSource() == wyczysc)
						{
							EdytorData.Clear();
						}
						if(e.getSource() == otworz)
						{
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setDialogTitle("Specify a file to load");   
							FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files","json","json");
							fileChooser.setFileFilter(filter);
							
							int userSelection = fileChooser.showOpenDialog(plik);
							 
							if (userSelection == JFileChooser.APPROVE_OPTION) {
							    String fileToLoad = fileChooser.getSelectedFile().getAbsolutePath();
							    SaveLoadSystem.Load(fileToLoad);
							}
						}
						
					}
			
				};
		zapisz.addActionListener(listener);
		otworz.addActionListener(listener);
		wyczysc.addActionListener(listener);
		
		this.add(plik);
	}
	
	
	
}
