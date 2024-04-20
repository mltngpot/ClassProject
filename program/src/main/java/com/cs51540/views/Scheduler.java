package com.cs51540.views;

import java.awt.*;
import javax.swing.*;

public class Scheduler extends JFrame{
    public Scheduler(){
		setTitle("PNW Scheduler");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(2560,1600));
		add(new MainPanel(), BorderLayout.CENTER);
	    add(new Header(), BorderLayout.NORTH);
		add(new Footer(), BorderLayout.SOUTH);
		pack();
    }
}