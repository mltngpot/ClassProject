
package com.cs51540.dialogs;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jacob DeMuth
 */
public class DialogTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dialog Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
      
        JPanel optionsDialogPanel = new JPanel();
        
        JButton createButton = new JButton("Create Dialog");
        JButton editButton = new JButton("Edit Dialog");
        JButton deleteButton = new JButton("Delete Dialog");
        JButton errorButton = new JButton("Error Dialog");
        
        optionsDialogPanel.add(createButton);
        optionsDialogPanel.add(editButton);
        optionsDialogPanel.add(deleteButton);
        optionsDialogPanel.add(errorButton);
        
        frame.add(optionsDialogPanel);
        frame.setVisible(true);
 
        // Add action to the button
        createButton.addActionListener((ActionEvent e) -> {
          //  CreateDialog frame1 = new CreateDialog();
          //  frame1.setVisible(true);
        });
        
        // Add action to the button
        editButton.addActionListener((ActionEvent e) -> {
          //  EditDialog frame1 = new EditDialog();
          //  frame1.setVisible(true);
        });
        
        // Add action to the button
        deleteButton.addActionListener((ActionEvent e) -> {
            DeleteDialog frame1 = new DeleteDialog();
            frame1.setVisible(true);
        });
        
        // Add action to the button
        errorButton.addActionListener((ActionEvent e) -> {
            ErrorDialog frame1 = new ErrorDialog();
            frame1.setVisible(true);
        });
    }
}
