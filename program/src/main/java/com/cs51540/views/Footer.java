package com.cs51540.views;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.cs51540.data.ScheduleIO;
import com.cs51540.interfaces.IDataRepository;

public class Footer extends JPanel {
	private IDataRepository DataRepository;

	public Footer(IDataRepository DataRepository) {
		ScheduleIO scheduleio = new ScheduleIO("schedule.data");
		this.DataRepository = DataRepository;
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		JButton button = new JButton("Export Schedule");
		add(button, BorderLayout.EAST);
	}
}