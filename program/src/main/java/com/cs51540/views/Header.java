package com.cs51540.views;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.User;

public class Header extends JPanel {

    public JComboBox<User> cb;
    private IDataRepository DataRepository;
    private JLabel currentUser;

    public Header(IDataRepository DataRepository) {
        this.DataRepository = DataRepository;
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        User[] users = {new User(-1, Color.magenta, "ClientView"),null,null,null,null};
        System.arraycopy(DataRepository.GetUsers(), 0, users, 1, 4);
        cb = new JComboBox<>(users);
        add(cb, BorderLayout.EAST);
        currentUser = new JLabel("Welcome, you are currently viewing everybodies schedule!");
        add(currentUser, BorderLayout.WEST);

    }

    public User getUser(){
		System.out.println((User)cb.getSelectedItem());
        return (User)cb.getSelectedItem();
    }

    public void changeUserLabel(){
        currentUser.setText("You are currently viewing as the user: " + getUser() + ".");;
        }
}