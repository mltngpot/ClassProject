/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import com.cs51540.models.Schedule;

public class CreateDialog extends JFrame {
    public CreateDialog()
    {
        super();
        setTitle("Create Event");
        setSize(400, 400);
        // Variables that are used throughout the class
        // Putting them here so it's easier to change their values
        String[] onlineChoices = {"Online", "In person"};
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] users = {"Jacob DeMuth", "Travis Thurn", "Kenneth Guernsey", "Henry Kroll"};
        
        // Create Main Panel with fields
        JPanel mainPanel = new JPanel();
        
        // Fields
        // Event Name
        JLabel eventNameLabel = new JLabel("Event Name: ");
        JTextField eventNameInput = new JTextField(16);
        
        // Who is the host of the event
        // The reason this is just a label is beacuse I assume when editing (cont.)
        // A event, you won't be editing who is the host otherwise this could be a JComboBox
        JLabel hostFieldLabel = new JLabel("Host: ");
        JLabel hostLabel = new JLabel("Insert Host Name Here");
        
        // Choice of if event is online or in person
        JLabel onlineChoiceLabel = new JLabel("Online or In person: ");
        JComboBox<String> onlineChoiceInput = new JComboBox<>(onlineChoices);
        onlineChoiceInput.setSelectedIndex(1);
        
        // Location of Event
        JLabel locationLabel = new JLabel("Location: ");
        JTextField locationInput = new JTextField(16);
        
        // Start Day
        JLabel startDayLabel = new JLabel("Start Day: ");
        JComboBox<String> startDayChoice = new JComboBox<>(days);
        startDayChoice.setSelectedIndex(6);
        
        // Start Hour
        JLabel startHourLabel = new JLabel("Start Hour: ");
        JSpinner startHourChoice = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
        startHourChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)startHourChoice.getEditor()).getTextField().setEditable(false);
        
        // Start Minute
        JLabel startMinuteLabel = new JLabel("Start Minute: ");
        JSpinner startMinuteChoice = new JSpinner(new SpinnerNumberModel(0, 0, 30, 30));
        startMinuteChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)startMinuteChoice.getEditor()).getTextField().setEditable(false);
        
        // End Day
        JLabel endDayLabel = new JLabel("End Day: ");
        JComboBox endDayChoice = new JComboBox(days);
        endDayChoice.setSelectedIndex(6);
        
        // End Hour
        JLabel endHourLabel = new JLabel("End Hour: ");
        JSpinner endHourChoice = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
        endHourChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)endHourChoice.getEditor()).getTextField().setEditable(false);
        
        // End Minute
        JLabel endMinuteLabel = new JLabel("End Minute: ");
        JSpinner endMinuteChoice = new JSpinner(new SpinnerNumberModel(0, 0, 30, 30));
        endMinuteChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)endMinuteChoice.getEditor()).getTextField().setEditable(false);
        
        // Users Attending
        // Control Click to select multiple users
        JLabel attendeesLabel = new JLabel("Attendees: ");
        JList attendeeChoice = new JList(users);
        
        //Create Event
        JButton createButton = new JButton("Create Event");
        
        // Set the component layout to not look totally awful
        // Essentially creates a label on left and input on right format
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;
        GridBagConstraints right = new GridBagConstraints();
        right.weightx = 2.0;
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
        

        //Add fields to panel
        mainPanel.add(eventNameLabel, left);
        mainPanel.add(eventNameInput, right);
        
        mainPanel.add(hostFieldLabel, left);
        mainPanel.add(hostLabel, right);
        
        mainPanel.add(onlineChoiceLabel, left);
        mainPanel.add(onlineChoiceInput, right);
        
        mainPanel.add(locationLabel, left);
        mainPanel.add(locationInput, right);
        
        mainPanel.add(startDayLabel, left);
        mainPanel.add(startDayChoice, right);
        
        mainPanel.add(startHourLabel, left);
        mainPanel.add(startHourChoice, right);
        
        mainPanel.add(startMinuteLabel, left);
        mainPanel.add(startMinuteChoice, right);
        
        mainPanel.add(endDayLabel, left);
        mainPanel.add(endDayChoice, right);
        
        mainPanel.add(endHourLabel, left);
        mainPanel.add(endHourChoice, right);
        
        mainPanel.add(endMinuteLabel, left);
        mainPanel.add(endMinuteChoice, right);
        
        mainPanel.add(attendeesLabel, left);
        mainPanel.add(attendeeChoice, right);
        
        getContentPane().add(BorderLayout.SOUTH, createButton);
        
        add(mainPanel);

        // Add action to the button
        createButton.addActionListener((ActionEvent e) -> {
            
            int startHour = (Integer) startHourChoice.getValue();
            int startMinute = (Integer) startMinuteChoice.getValue();
            String startDay = startDayChoice.getSelectedItem().toString();
            
            
            int endHour = (Integer) endHourChoice.getValue();
            int endMinute = (Integer) endMinuteChoice.getValue();
            String endDay = endDayChoice.getSelectedItem().toString();
            
            int Id = 0;
            
            int Owner = 0;
            
            String Title = eventNameInput.getText();
            
            LocalDateTime Start = LocalDateTime.now();
            
            LocalDateTime End = LocalDateTime.now();
            
            Schedule schedule = new Schedule(Id,Owner,Title,Start,End);
            
            
            JOptionPane.showMessageDialog(createButton, "Event Created!");
        });
    }
}
