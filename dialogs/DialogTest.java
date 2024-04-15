package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        createButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                CreateDialog frame = new CreateDialog();
                frame.setVisible(true);
            }
        });
        
        // Add action to the button
        editButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                EditDialog frame = new EditDialog();
                frame.setVisible(true);
            }
        });
        
        // Add action to the button
        deleteButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                DeleteDialog frame = new DeleteDialog();
                frame.setVisible(true);
            }
        });
        
        // Add action to the button
        errorButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ErrorDialog frame = new ErrorDialog();
                frame.setVisible(true);
            }
        });
    }
}
