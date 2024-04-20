package com.cs51540.views;
import javax.swing.*;
import java.awt.*;
import com.cs51540.interfaces.IDataRepository;
public class Footer extends JPanel {
	private IDataRepository DataRepository;

	public Footer(IDataRepository DataRepository) {
		this.DataRepository = DataRepository;
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		JButton button = new JButton("Export Schedule");
		add(button, BorderLayout.EAST);
	}
}