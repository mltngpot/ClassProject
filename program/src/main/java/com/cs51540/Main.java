package com.cs51540; 
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

public class Main{
    public static void main(String[] args) {
		SimpleFrame frame = new SimpleFrame();
		frame.setVisible(true);
    }
}

class SimpleFrame extends JFrame{
    public SimpleFrame(){
		setTitle("Scheduler");
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

class SchedulePanel extends JPanel {
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
			// TODO: handle exception
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

		gbc.gridx = 7; //max values
		gbc.gridy = 25;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;

		for (int x = 1; x <= 7; x++){
			for (int y = 1; y <= 25; y++){
				gbc.gridx = x;
				gbc.gridy = y;
				JPanel a = new JPanel();
				a.setBorder(blackline);
				a.setBackground(Color.BLACK);
				a.setOpaque(false);
				add(a,gbc);
			}
		}
		this.setBackground(Color.LIGHT_GRAY);
	}
}

class MainPanel extends JPanel{
	public MainPanel(){
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		 add(new SchedulePanel(), BorderLayout.CENTER); //might need to change to center later on


	}
}

class Header extends JPanel {
	public Header() {
		add(new JLabel("This is an example Header for components!"));

	}
}

class Footer extends JPanel {
	public Footer() {
		add(new JLabel("This is an example Footer for components!"));
	}
}