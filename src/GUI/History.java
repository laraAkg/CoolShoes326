package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import DAO.Kunde;
import Helper.Helper;

public class History {

	public History() {
		JDialog dialog = new JDialog();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		Helper helper = new Helper();
		//ArrayList<Kunde> kunden = helper.connection.getAllKunde();
		int id = 1;
//		JLabel name = new JLabel(kunden.get(id).getVorname() + " " + kunden.get(id).getNachname());
//		JLabel adresse = new JLabel(kunden.get(id).getAdreesse() + ", " + kunden.get(id).getOrt() + " " + kunden.get(id).getPlz());

		String[] columns = new String[] { "OrderNr.", "Versandpartner", "Mitarbeiter", "Status" };
		Object[][] data = new Object[][] { { "TEST", "John", "40.0", "false" }, { "1", "John", "40.0", "false" },
				{ "1", "John", "40.0", "false" }, };
		JTable table = new JTable(data, columns);

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);

		dialog.setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		dialog.setTitle("History");
		dialog.setSize(700, 600);
//		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		adresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.WHITE);	
//		topPanel.add(name, BorderLayout.CENTER);
//		topPanel.add(adresse, BorderLayout.EAST);
		bottomPanel.add(table, BorderLayout.SOUTH);
		dialog.add(bottomPanel, BorderLayout.CENTER);
		dialog.add(topPanel, BorderLayout.NORTH);
		
		dialog.setLocationRelativeTo(null);
//		dialog.pack();
		dialog.setVisible(true);
	}
}