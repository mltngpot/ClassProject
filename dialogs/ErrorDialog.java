/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorDialog extends JFrame {
    public ErrorDialog()
    {
        super();
        setTitle("Error");
        setSize(250, 150);
        JPanel mainPanel = new JPanel();
        
        JLabel eventNameLabel = new JLabel("A Error has occured ");
        JButton okButton = new JButton("Ok");
        
        mainPanel.add(eventNameLabel);  
        mainPanel.add(okButton);
        
        add(mainPanel);
        
        okButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
            }
        });
    }
}
