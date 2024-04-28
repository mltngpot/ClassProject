/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.Schedule;


public class DeleteDialog extends JFrame {
    private IDataRepository DataRepository;

    public DeleteDialog(IDataRepository DataRepository, int scheduleId)
    {
        super();
        setTitle("Delete Event");
        setSize(300, 200);
        JPanel mainPanel = new JPanel();
        
        JLabel deleteLabel = new JLabel("Are you sure you want to delete this event?");
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        
        mainPanel.add(deleteLabel); 
        mainPanel.add(yesButton);
        mainPanel.add(noButton);
        
        add(mainPanel);        
        yesButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                int eventId = scheduleId;
                DataRepository.DeleteSchedule(eventId);
                JOptionPane.showMessageDialog(yesButton, "Event Deleted!");
                dispose();
            }
        });
        
        noButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
            }
        });
    }
}
