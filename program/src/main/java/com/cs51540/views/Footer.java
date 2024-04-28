package com.cs51540.views;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import com.cs51540.data.ScheduleIO;
import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.User;

public class Footer extends JPanel {

	BorderLayout layout = new BorderLayout();
	public Footer(IDataRepository DataRepository, Header header) {
		setLayout(layout);
		JButton button = new JButton("Export Schedule");
		button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				ScheduleIO scheduleio = new ScheduleIO(DataRepository,"/Users/kenguerns4/Desktop/Java Course/ClassProject/ClassProject-11");
				scheduleio.saveSchedules();
                }
			});
		add(button, BorderLayout.EAST);
	}
}