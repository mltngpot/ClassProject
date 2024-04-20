package com.cs51540.views;
import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	public MainPanel(){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(new SchedulePanel(), BorderLayout.CENTER);
	}
}