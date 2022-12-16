package com.edytor.EdytorParametryczny;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.edytor.EdytorParametryczny.data.EdytorData;
import com.edytor.EdytorParametryczny.windows.*;

public class MainWindow {
	
	private JFrame window;
	
	private JPanel przybiornik;
	private JPanel parametry;
	private JPanel rysownik;
	private JMenuBar menuEdytora;
	
	public MainWindow()
	{
		EdytorData.Init();
		
		window = new JFrame();
		window.setTitle("Edytor Parametryczny");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		
		
		przybiornik = new PanelPrzybiornik();
		parametry = new PanelParametry();
		rysownik = new PanelRysownik();
		menuEdytora = new MenuEdytora();
		
		rysownik.setPreferredSize(new Dimension(400,600));
		
		przybiornik.setBorder(BorderFactory.createLineBorder(Color.black));
		parametry.setBorder(BorderFactory.createLineBorder(Color.black));
		
		window.add(menuEdytora,BorderLayout.NORTH);
		window.add(przybiornik,BorderLayout.WEST);
		window.add(parametry,BorderLayout.EAST);
		window.add(rysownik,BorderLayout.CENTER);
		
	}
	
	public void Show()
	{
		window.setVisible(true);
	}
}
