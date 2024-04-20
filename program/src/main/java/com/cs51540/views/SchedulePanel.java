package com.cs51540.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SchedulePanel extends JPanel {
    private final EventListener eventListener;
    private final JButton[][] buttons = new JButton[7][25];
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    public SchedulePanel() {
        this.eventListener = new EventListener();
        setupSchedule();
    }

    private void setupSchedule() {
        setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(1, 1, 1, 1);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

        try {
            String time = "7:30";
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(time);
            int interval = 30;
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            for (int x = 1; x <= 25; x++) {
                cal.add(Calendar.MINUTE, interval);
                gbc.gridy = x;
                JLabel timeLabel = new JLabel(df.format(cal.getTime()));
                timeLabel.setFont(new Font("Dialog",Font.BOLD,12));
                add(timeLabel, gbc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int x = 0; x < daysOfWeek.length; x++) {
            JLabel dayLabel = new JLabel(daysOfWeek[x]);
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dayLabel.setFont(new Font("Dialog",Font.BOLD,18));
            gbc.gridx = x + 1;
            gbc.gridy = 0;
            add(dayLabel, gbc);
        }

        

        for (int x = 1; x <= 7; x++) {
            for (int y = 1; y <= 25; y++) {
                gbc.gridx = x;
                gbc.gridy = y;
                JButton button = new JButton();
                button.setBorder(blackline);
                button.setBackground(Color.WHITE);
                button.setOpaque(false);
                add(button, gbc);
                buttons[x - 1][y - 1] = button;
                int day = x - 1;  // Day calculation
                int startTime = y - 1;  // Start time calculation
                //int endTime = startTime + 1;  // End time calculation
                button.addActionListener(e -> {
                    String eventName = JOptionPane.showInputDialog(null, "Enter event name:");
                    String eventEnd = JOptionPane.showInputDialog(null, "Enter event end time:");
                    int endTime = convertEndTimeToIndex(eventEnd);
                    if (eventName != null && !eventName.trim().isEmpty()) {
                        eventListener.addEvent(day, startTime, endTime, eventName);
                        updateCalendarDisplay();
                    }
                });
            }
        }

        setBackground(Color.LIGHT_GRAY);
    }

    private void updateCalendarDisplay() {
		System.out.println("Updating calendar display...");
	
		for (int day = 0; day < 7; day++) {
			for (int slot = 0; slot < 25; slot++) { // Adjusted to iterate over 24 slots only
				String event = eventListener.getEvent(day, slot);
				JButton button = buttons[day][slot]; // Adjusted to directly access the button
				System.out.println("Day: " + day + ", Slot: " + slot + ", Event: " + event); // Debugging output
				if (event != null && !event.isEmpty()) {
					button.setText(event);
                    gbc.gridx = day + 1;
                    gbc.gridy = slot + 1;
                    gbc.gridheight = 1;  //Change according to meeting length - Kenneth (Make a for loop later for this shit)
                    gbl.setConstraints(button,gbc);
                    //buttons[day][slot].setVisible(false);
                    System.out.println("Color should be yellow");
					button.setBackground(Color.YELLOW); // Set background color for events
				} else {
                    System.out.println("Color should be white");
					button.setText(""); // Clear text if no event
					button.setBackground(Color.WHITE); // Set default background color
				}
			}
		}
	
		System.out.println("Calendar display updated.");
	}

    public static int convertEndTimeToIndex(String endTime) {
        try {
            if(endTime.length()<3){
                endTime = endTime + ":00";
            }
            // Split the end time string by colon to get hour and minute parts
            String[] timeParts = endTime.split(":");
            if (timeParts.length != 2) {
                throw new IllegalArgumentException("Invalid time format");
            }
            int endHour = Integer.parseInt(timeParts[0]);
            int endMinute = Integer.parseInt(timeParts[1]);

            // Calculate total minutes from 8:00 am to end time
            int totalMinutes = (endHour - 8) * 60 + endMinute;

            // Calculate array index (each position represents 30 minutes)
            int arrayIndex = totalMinutes / 30;
            if (arrayIndex < 0 || arrayIndex >= 25) {
                throw new IllegalArgumentException("Invalid time range");
            }
            return arrayIndex;
        } catch (IllegalArgumentException e) {
            System.err.println("Error converting end time to index: " + e.getMessage());
            return -1; // Return -1 for invalid input or errors
        }
    }
}