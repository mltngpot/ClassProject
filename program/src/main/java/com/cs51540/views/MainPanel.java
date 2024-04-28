package com.cs51540.views;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.cs51540.interfaces.IDataRepository;

public class MainPanel extends JPanel{
	public MainPanel(IDataRepository DataRepository){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(new SchedulePanel(DataRepository), BorderLayout.CENTER);

	}
}