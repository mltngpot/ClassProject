package com.cs51540.views;
import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.cs51540.interfaces.IDataRepository;
import com.cs51540.models.User;

public class Header extends JPanel {

    JComboBox<User> cb;
    private IDataRepository DataRepository;

    public Header(IDataRepository DataRepository) {
        this.DataRepository = DataRepository;
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        cb = new JComboBox<>(DataRepository.GetUsers());
        add(cb, BorderLayout.EAST);
    }

    public User getUser(){
		System.out.println((User)cb.getSelectedItem());
        return (User)cb.getSelectedItem();
    }
}