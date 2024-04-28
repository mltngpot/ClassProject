package com.cs51540.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.cs51540.interfaces.IDataRepository;

public class Scheduler extends JFrame{
    public Scheduler(IDataRepository DataRepository){
		setTitle("PNW Scheduler");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(2560,1600));
		Header header = new Header(DataRepository);
		add(new MainPanel(DataRepository, header), BorderLayout.CENTER);
	    add(header, BorderLayout.NORTH);
		add(new Footer(DataRepository), BorderLayout.SOUTH);
		pack();
    }
}