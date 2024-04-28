/**
 *
 * @author Jacob DeMuth
 */

package com.cs51540.dialogs;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ErrorDialog extends JFrame {
    public ErrorDialog()
    {
        super();
        setTitle("Error");
        setSize(250, 150);
        JPanel mainPanel = new JPanel();
        
        JLabel eventNameLabel = new JLabel("A Conflict has occured ");
        JButton okButton = new JButton("Ok");
        
        mainPanel.add(eventNameLabel);  
        mainPanel.add(okButton);
        
        add(mainPanel);
        
        okButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            this.dispose();
        });
    }
}
