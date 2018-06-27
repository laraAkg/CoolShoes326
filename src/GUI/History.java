package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class History {

	public History() {
		// headers for the table
		String[] columns = new String[] { "Id", "Name", "Hourly Rate", "Part Time" };

		// actual data for the table in a 2d array
		Object[][] data = new Object[][] { { 1, "John", 40.0, false }, { 2, "Rambo", 70.0, false },
				{ 3, "Zorro", 60.0, true }, };
		JDialog dialog = new JDialog();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel history = new JLabel("History");
		JLabel name = new JLabel("Vorname, Nachname"); // TODO AUS Db lesen und
														// Anzeigen
		JLabel adresse = new JLabel("Testadresse \n 2020, Zürich"); // TODO
		JTable table = new JTable(data, columns);

		// add the table to the frame
		// table.add(new JScrollPane(table));

		dialog.setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		dialog.setTitle("History");
		dialog.setSize(700, 600);
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		history.setFont(new Font("Tahoma", Font.PLAIN, 35));
		topPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.WHITE);

		topPanel.add(history, BorderLayout.NORTH);
		topPanel.add(name, BorderLayout.WEST);
		topPanel.add(adresse, BorderLayout.EAST);
		
		bottomPanel.add(table, BorderLayout.SOUTH);
		
		dialog.add(topPanel, BorderLayout.NORTH);
		dialog.add(bottomPanel, BorderLayout.CENTER);
		dialog.setLocationRelativeTo(null);
		// dialog.pack();
		dialog.setVisible(true);
	}
}