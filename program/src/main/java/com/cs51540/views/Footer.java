package com.cs51540.views;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.User;

public class Footer extends JPanel {

	public Footer(IDataRepository DataRepository) {
		this.DataRepository = DataRepository;
		BorderLayout layout = new BorderLayout();
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