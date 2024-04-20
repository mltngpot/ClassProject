package com.cs51540.views;
import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
	String[] users = {"Kenneth", "Henry", "Jacob", "Travis"};
	JComboBox<String> cb = new JComboBox<String>(users);
	public Header() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(new JLabel("This is an example Header for components!"),BorderLayout.WEST);
		add(cb, BorderLayout.EAST);
	}

	public String getUser(){
		return (String)cb.getSelectedItem();
	}
}
