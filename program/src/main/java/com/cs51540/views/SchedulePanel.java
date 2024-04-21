package com.cs51540.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.crypto.Data;

import com.cs51540.dialogs.CreateDialog;
import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.Slot;
import com.cs51540.models.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SchedulePanel extends JPanel {
    private final EventListener eventListener;
    private final Slot[][] slots = new Slot[7][25];
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    private User CurrentUser;
    private IDataRepository DataRepository;

    public SchedulePanel(IDataRepository DataRepository) {
        this.eventListener = new EventListener();
        this.DataRepository = DataRepository;
        CurrentUser = DataRepository.GetUser(0); //TODO Hard coded to 0, need to be able to change
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
        InitializeButtons();
        setBackground(Color.LIGHT_GRAY);
    }

    private void InitializeButtons() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int x = 1; x <= 7; x++) {
            for (int y = 1; y <= 25; y++) {
                gbc.gridx = x;
                gbc.gridy = y;
                Slot slot = new Slot();
                slot.setBorder(blackline);
                slot.setBackground(Color.WHITE);
                slot.setOpaque(true);
                add(slot, gbc);
                slots[x - 1][y - 1] = slot;
                // Day calculation
                int startTime = y - 1;  // Start time calculation
                
                slot.addActionListener(e -> {
                    // add edit functionality
                    // check to see this has a schedule in it
                    CreateDialog dialog = new CreateDialog();
                    dialog.setVisible(true);
                    dialog.addActionListener((ActionEvent e) -> {
                        Schedule schedule = dialog.getSchedule();
                        DataRepository.AddSchedule(schedule);
                        dialog.dispose();
                    });
                    updateCalendarDisplay();
                });
            }
        }
    }

    private void updateCalendarDisplay() {
        InitializeButtons();

        Schedule[] schedules = DataRepository.GetWeekSchedule(LocalDate.now());
        for(Schedule schedule : schedules){
            try {
            int dayIndex = getDayIndex(schedule.Start);
            int slotIndex = getSlotIndex(schedule.Start);
            int endIndex = getSlotIndex(schedule.End);
            User owner = DataRepository.GetUser(schedule.Owner);
            Slot slot = slots[dayIndex][slotIndex];
            slot.setBackground(owner.DisplayColor);
            slot.setText(schedule.Title);
            // finish adding schedule information
            // Button row thing
            }
            catch (Exception e)
            {
                System.err.println("Schedule Id: " + schedule.Id + "Handling Error");
                System.err.println(e.getMessage());
            }
        }      

	}

    private int getDayIndex(LocalDateTime date)
    {
        int result = 0;
        switch(date.getDayOfWeek()) {
            case SUNDAY -> result = 0;
            case MONDAY -> result = 1;
            case TUESDAY -> result = 2;
            case WEDNESDAY -> result = 3;
            case THURSDAY -> result = 4;
            case FRIDAY -> result = 5;
            case SATURDAY -> result = 6;
        }
        return result;
    }

    private int getSlotIndex(LocalDateTime time) throws Exception {
        int result;
        int hour = time.getHour() - 8;
        int minute = time.getMinute();
        if (hour < 0 && hour > 12)
            throw new Exception("Time out of range");
        result = hour * 2;
        if (minute >= 30)
            result++;
            
        return result;
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