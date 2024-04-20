package com.cs51540.views;
import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
	public Footer() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		JButton button = new JButton("Export Schedule");
		add(button, BorderLayout.EAST);
	}
}