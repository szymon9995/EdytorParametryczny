package com.edytor.EdytorParametryczny.windows;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuEdytora extends JMenuBar{

	private static final long serialVersionUID = 7001878944333697466L;

	JMenu plik;
	JMenuItem zapisz;
	JMenuItem wyczysc;
	JMenuItem otworz;
	
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
		
		this.add(plik);
	}
	
	
	
}
