package com.cs51540.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SchedulePanel extends JPanel {
	JButton [][] buttons = new JButton[7][25];
	public SchedulePanel() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2,2,2,2);
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);

		try {
			String time = "7:30";
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			Date d = df.parse(time);
			int interval = 30;
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			for (int x = 1; x <= 25; x++){
				cal.add(Calendar.MINUTE, interval);
				gbc.gridy = x;
				JLabel timeLabel = new JLabel(df.format(cal.getTime()));
				add(timeLabel,gbc);
			}
		} catch (Exception e) {
		}

		JLabel monLabel = new JLabel("Monday");
		JLabel tueLabel = new JLabel("Tuesday");
		JLabel wedLabel = new JLabel("Wednesday");
		JLabel thuLabel = new JLabel("Thursday");
		JLabel friLabel = new JLabel("Friday");
		JLabel satLabel = new JLabel("Saturday");
		JLabel sunLabel = new JLabel("Sunday");
		monLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		friLabel.setHorizontalAlignment(SwingConstants.CENTER);
		satLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sunLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(monLabel,gbc);
		gbc.gridx = 2;
		add(tueLabel,gbc);
		gbc.gridx = 3;
		add(wedLabel,gbc);
		gbc.gridx = 4;
		add(thuLabel,gbc);
		gbc.gridx = 5;
		add(friLabel,gbc);
		gbc.gridx = 6;
		add(satLabel,gbc);
		gbc.gridx = 7;
		add(sunLabel,gbc);

		gbc.gridx = 7; //max x
		gbc.gridy = 25; //max y
		gbc.gridx = 1; //min x 
		gbc.gridy = 1; // min y
		gbc.gridheight = 1;

		for (int x = 1; x <= 7; x++){
			for (int y = 1; y <= 25; y++){
				gbc.gridx = x;
				gbc.gridy = y;
				JButton a = new JButton();
				a.setBorder(blackline);
				a.setBackground(Color.BLACK);
				a.setOpaque(false);
				add(a,gbc);
				buttons[x-1][y-1] = a;
			}
		}
		this.setBackground(Color.LIGHT_GRAY);
	}
}