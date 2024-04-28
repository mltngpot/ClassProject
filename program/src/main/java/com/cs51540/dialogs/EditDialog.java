/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;
import com.cs51540.models.User;

public class EditDialog extends JFrame {
    private Schedule schedule;
    private IDataRepository DataRepository;
    private final User[] users;
  
    public EditDialog(IDataRepository DataRepository, int scheduleId)
    {
        super();
        this.DataRepository = DataRepository;
        setTitle("Edit Event");
        setSize(400, 400);
        // Variables that are used throughout the class
        // Putting them here so it's easier to change their values
        String[] onlineChoices = {"Online", "In person"};
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
     
        // Create Main Panel with fields
        JPanel mainPanel = new JPanel();
        
        this.DataRepository = DataRepository;
        
        // TODO 
        // Remove test ID variable
        int id = scheduleId;
        // Get schedule
        schedule = DataRepository.GetSchedule(id);
        
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
        
        // Fields
        // Event Name
        JLabel eventNameLabel = new JLabel("Event Name: ");
        JTextField eventNameInput = new JTextField(16);
        eventNameInput.setText(schedule.Title);
        
        // Who is the host of the event
        // The reason this is just a label is beacuse I assume when editing (cont.)
        // A event, you won't be editing who is the host otherwise this could be a JComboBox
        JLabel hostFieldLabel = new JLabel("Host: ");
        String ownerName = revDict.get(schedule.Owner);
        JLabel hostLabel = new JLabel(ownerName);
        
        // Choice of if event is online or in person
        JLabel onlineChoiceLabel = new JLabel("Online or In person: ");
        JComboBox onlineChoiceInput = new JComboBox(onlineChoices);
        String type = schedule.MeetingType;
        if (type == "Online")
        {
            onlineChoiceInput.setSelectedIndex(0);
        }
        else
        {
            onlineChoiceInput.setSelectedIndex(1);
        }
        
        // Location of Event
        JLabel locationLabel = new JLabel("Location: ");
        JTextField locationInput = new JTextField(16);
        locationInput.setText(schedule.Location);
        
        // Start Day
        JLabel startDayLabel = new JLabel("Start Day: ");
        JComboBox startDayChoice = new JComboBox(days);
        
        int index = 0;
        DayOfWeek dayOfWeek = schedule.Start.getDayOfWeek();
        switch (dayOfWeek)
        {
            case SUNDAY :
                index = 0;
                break;
            case MONDAY:
                index = 1;
                break;
            case TUESDAY:
                index = 2;
                break;
            case WEDNESDAY:
                index = 3;
                break;
            case THURSDAY:
                index = 4;
                break;
            case FRIDAY:
                index = 5;
                break;
            case SATURDAY:
                index = 6;
                break;
        }
        startDayChoice.setSelectedIndex(index);
        
        // Start Hour
        JLabel startHourLabel = new JLabel("Start Hour: ");
        JSpinner startHourChoice = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
        startHourChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)startHourChoice.getEditor()).getTextField().setEditable(false);
        int startHour = schedule.Start.getHour();
        startHourChoice.setValue(Integer.valueOf(startHour));
        
        
        // Start Minute
        JLabel startMinuteLabel = new JLabel("Start Minute: ");
        JSpinner startMinuteChoice = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        startMinuteChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)startMinuteChoice.getEditor()).getTextField().setEditable(false);
        int startMinute = schedule.Start.getMinute();
        startMinuteChoice.setValue(Integer.valueOf(startMinute));
        
        // End Day
        JLabel endDayLabel = new JLabel("End Day: ");
        JComboBox endDayChoice = new JComboBox(days);
        endDayChoice.setSelectedIndex(6);
        index = 0;
        dayOfWeek = schedule.End.getDayOfWeek();
        switch (dayOfWeek)
        {
            case SUNDAY :
                index = 0;
                break;
            case MONDAY:
                index = 1;
                break;
            case TUESDAY:
                index = 2;
                break;
            case WEDNESDAY:
                index = 3;
                break;
            case THURSDAY:
                index = 4;
                break;
            case FRIDAY:
                index = 5;
                break;
            case SATURDAY:
                index = 6;
                break;
        }
        endDayChoice.setSelectedIndex(index);
        System.out.println(index);
        System.out.println(dayOfWeek);
        
        // End Hour
        JLabel endHourLabel = new JLabel("End Hour: ");
        JSpinner endHourChoice = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
        endHourChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)endHourChoice.getEditor()).getTextField().setEditable(false);
        int endHour = schedule.End.getHour();
        endHourChoice.setValue(Integer.valueOf(endHour));
        
        // End Minute
        JLabel endMinuteLabel = new JLabel("End Minute: ");
        JSpinner endMinuteChoice = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        endMinuteChoice.setBounds(70, 130, 50, 40);
        //Added this so we don't have to do data validation on time
        //Remove line if we want users to be able to type the number
        //You can hold the spinner arrows so its not that bad
        ((JSpinner.DefaultEditor)endMinuteChoice.getEditor()).getTextField().setEditable(false);
        int endMinute = schedule.End.getMinute();
        endMinuteChoice.setValue(Integer.valueOf(endMinute));

        // Users Attending
        // Control Click to select multiple users
        JLabel attendeesLabel = new JLabel("Attendees: ");
        
        String[] array = userList.toArray(new String[0]);
        JList<String> attendeeChoice = new JList<>(array);
        java.util.List<Integer> temp;
        temp = schedule.Attendees;
        int[] userIds = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++)
        {
            userIds[i] = temp.get(i);
        }
        attendeeChoice.setSelectedIndices(userIds);
       
        // Save Changes
        JButton saveButton = new JButton("Save");
        
        // Cancel Changes
        JButton cancelButton = new JButton("Cancel");
        
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
        
        mainPanel.add(saveButton);
        mainPanel.add(cancelButton);
        
        add(mainPanel);
        
        
        // Add action to the buttons
        saveButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            //TODO
            //set proper IDs
            int eventId = schedule.Id;
            int ownerId = schedule.Owner;
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
            
                schedule = new Schedule(eventId,ownerId,MeetingType,Location,Title,Start,End,userIDSelected);

                JOptionPane.showMessageDialog(saveButton, "Changes Saved");
                
                setVisible(false);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
            }
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