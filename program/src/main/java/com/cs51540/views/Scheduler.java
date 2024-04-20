package com.cs51540.views;

import java.awt.*;
import javax.swing.*;

import com.cs51540.interfaces.IDataRepository;

public class Scheduler extends JFrame{
    public Scheduler(IDataRepository DataRepository){
		setTitle("PNW Scheduler");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(2560,1600));
		add(new MainPanel(DataRepository), BorderLayout.CENTER);
	    add(new Header(DataRepository), BorderLayout.NORTH);
		add(new Footer(DataRepository), BorderLayout.SOUTH);
		pack();
    }
}