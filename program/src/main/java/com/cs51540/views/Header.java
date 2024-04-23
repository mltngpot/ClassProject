package com.cs51540.views;
import javax.swing.*;

import com.cs51540.interfaces.IDataRepository;

import java.awt.*;

public class Header extends JPanel {
	String[] users = {"Kenneth", "Henry", "Jacob", "Travis"};
	JComboBox<String> cb = new JComboBox<String>(users);
	private IDataRepository DataRepository;
	public Header(IDataRepository DataRepository) {
		this.DataRepository = DataRepository;
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		//add(new JLabel("This is an example Header for components!"),BorderLayout.WEST);
		add(cb, BorderLayout.EAST);
	}

	public String getUser(){
		return (String)cb.getSelectedItem();
	}
}
