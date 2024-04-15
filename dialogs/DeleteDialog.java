package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jacob DeMuth
 */
public class DeleteDialog extends JFrame {
    public DeleteDialog()
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
                JOptionPane.showMessageDialog(yesButton, "Event Deleted!");
                setVisible(false);
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
