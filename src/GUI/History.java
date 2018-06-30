package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class History {

	public History() {
		JDialog dialog = new JDialog();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel history = new JLabel("History");
		JLabel name = new JLabel("Vorname, Nachname"); // TODO AUS Db lesen und
		String[] columns = new String[] { "OrderNr.", "Versandpartner", "Mitarbeiter", "Status" };
		Object[][] data = new Object[][] { { "TEST", "John", "40.0", "false" }, { "1", "John", "40.0", "false" },
				{ "1", "John", "40.0", "false" }, };
		JTable table = new JTable(data, columns);
		JLabel adresse = new JLabel("Testadresse \n 2020, Zürich"); // TODO
		// add the table to the frame
		// table.add(new JScrollPane(table));

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);

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

		bottomPanel.add(table, BorderLayout.CENTER);

//		dialog.add(topPanel, BorderLayout.NORTH);
		dialog.add(bottomPanel, BorderLayout.CENTER);
		dialog.setLocationRelativeTo(null);
		// dialog.pack();
		dialog.setVisible(true);
	}
}