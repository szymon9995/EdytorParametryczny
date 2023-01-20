package com.edytor.EdytorParametryczny.IFparameters;

import javax.swing.JPanel;

import com.edytor.EdytorParametryczny.components.DrawComponent;

public abstract class IFShape {

	protected DrawComponent curSelected;
	protected JPanel objectEditor;
	
	public IFShape(DrawComponent selected,JPanel editor)
	{
		curSelected = selected;
		objectEditor = editor;
	}
	
	public abstract void UpdateData();
}
