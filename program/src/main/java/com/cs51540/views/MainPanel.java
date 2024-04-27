package com.cs51540.views;
import java.awt.*;
import javax.swing.*;

import com.cs51540.data.DataRepository;;

public class MainPanel extends JPanel{
	public MainPanel(DataRepository DataRepository){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(new SchedulePanel(DataRepository), BorderLayout.CENTER);

	}
}