/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import com.cs51540.interfaces.IDataRepository;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.cs51540.models.Schedule;
import com.cs51540.models.User;


public class CreateDialog extends JFrame{
    private Schedule schedule;
    private IDataRepository DataRepository;
    private final User[] users;

    public CreateDialog(IDataRepository DataRepository)
    {
        super();
        setTitle("Create Event");
        setSize(400, 400);
        // Variables that are used throughout the class
        // Putting them here so it's easier to change their values
        String[] onlineChoices = {"Online", "In person"};
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        //ArrayList to hold the users and their ids
        ArrayList<String> userList = new ArrayList<>();
        ArrayList<Integer> userID = new ArrayList<>();
        
        //Get the user list
        users = DataRepository.GetUsers();
        
        //Create a dictionary to associate users and ids
        Dictionary<String, Integer> dict = new Hashtable<>();
        for (User user : users)
        {
            userList.add(user.Name);
            userID.add(user.Id);
            dict.put(user.Name, user.Id);    
        }
        
        //Reversed version of above dictionary
        Dictionary<Integer, String> revDict = new Hashtable<>();
        for (User user : users)
        {
            revDict.put(user.Id, user.Name);    
        }
        
        
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
        
        String[] array = userList.toArray(new String[0]);
        JList<String> attendeeChoice = new JList<>(array);
        
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
            //TODO
            //set proper IDs
            int eventId = 1;
            int ownerId = 1;
            //basics
            String Title = eventNameInput.getText();
            String MeetingType = onlineChoiceInput.getSelectedItem().toString();
            String Location = locationInput.getText();
            
            //date stuff
            int startHour = (Integer) startHourChoice.getValue();
            int startMinute = (Integer) startMinuteChoice.getValue();
            String startDay = startDayChoice.getSelectedItem().toString();
            
            
            int endHour = (Integer) endHourChoice.getValue();
            int endMinute = (Integer) endMinuteChoice.getValue();
            String endDay = endDayChoice.getSelectedItem().toString();       
            
            LocalDate sunday = getSunday();
            LocalDateTime Start = sunday.plusDays(getDayOffset(startDay)).atTime(startHour, startMinute);
            LocalDateTime End = sunday.plusDays(getDayOffset(endDay)).atTime(endHour, endMinute);
            
            //Attendee
            int[] userIndex = attendeeChoice.getSelectedIndices();
            ArrayList<String> selected = new ArrayList<>();
            ArrayList<Integer> userIDSelected = new ArrayList<>();
            for(int i = 0; i < userIndex.length; i++)
            {
                selected.add(attendeeChoice.getModel().getElementAt(userIndex[i]));
            }
            for (String x : selected)
            {
                userIDSelected.add(dict.get(x));
            }
            
            //TODO
            //create object
            //Correct one
            schedule = new Schedule(eventId,ownerId,MeetingType,Location,Title,Start,End,userIDSelected);
           // schedule = new Schedule(eventId,ownerId,MeetingType,Location,Title,Start,End);
            //schedule = new Schedule(eventId,ownerId,MeetingType,Location,Title,Start,End);
            //System.out.println(getSchedule());
            
            //TODO
            //Action Handler to create event properly
            // call Action Handler
            //actionPerformed(null);
        });
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public LocalDate getSunday()  
    {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        LocalDate sunday = null;
        switch(dayOfWeek) {
        case SUNDAY -> sunday = today;
        case MONDAY -> sunday = today.minusDays(1);
        case TUESDAY -> sunday = today.minusDays(2);
        case WEDNESDAY -> sunday = today.minusDays(3);
        case THURSDAY -> sunday = today.minusDays(4);
        case FRIDAY -> sunday = today.minusDays(5);
        case SATURDAY -> sunday = today.minusDays(6);
        }
        return sunday;
    }

    public int getDayOffset(String day) 
    {
        int result = 0;
        switch(day)
        {
            case "Sunday":
                result = 0;
                break;
            case "Monday":
                result = 1;
                break;
            case "Tuesday":
                result = 2;
                break;
            case "Wednesday":
                result = 3;
                break;
            case "Thursday":
                result = 4;
                break;
            case "Friday":
                result = 5;
                break;
            case "Saturday":
                result = 6;
                break;
        }
        return result;
    }
}
