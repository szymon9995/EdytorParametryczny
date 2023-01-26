package com.edytor.EdytorParametryczny.przybiornikElement;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.edytor.EdytorParametryczny.windows.PanelRysownik;

public abstract class ElementPodstawa {
	protected JButton button;
	
	//protected static PanelRysownik m_rysownik;
	
	public ElementPodstawa()
	{
		button = new JButton(GetName());
		button.setFont(new Font("Courier",Font.PLAIN,11));
		button.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						OnButtonPress();
						
					}
			
				}
				
		);
	}
	
	protected abstract void OnButtonPress();
	protected abstract String GetName();
	
	public JButton GetButton()
	{
		return button;
	}
	
	/*
	public static void SetRysownik(PanelRysownik rysownik)
	{
		m_rysownik = rysownik;
	}*/
	
}
