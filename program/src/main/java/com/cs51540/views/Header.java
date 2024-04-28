package com.cs51540.views;
import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.User;

public class Header extends JPanel {

    JComboBox<User> cb;
    private IDataRepository DataRepository;
    private JLabel currentUser;

    public Header(IDataRepository DataRepository) {
        this.DataRepository = DataRepository;
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        cb = new JComboBox<>(DataRepository.GetUsers());
        add(cb, BorderLayout.EAST);
        currentUser = new JLabel("Welcome, please select a User!");
        add(currentUser, BorderLayout.WEST);

    }

    public User getUser(){
		System.out.println((User)cb.getSelectedItem());
        return (User)cb.getSelectedItem();
    }

    public void changeUserLabel(){
        currentUser.setText("You are currently viewing: " + getUser() + "'s schedule.");;
        }
}