package Helper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import DAO.BestellStatus;
import GUI.History;

public class Table extends JPanel {

	JTable table;
	JButton button;

	public Table() {
		setLayout(new FlowLayout());

		int counter = 0;
		button = new JButton("Select");
		Helper helper = new Helper();
		ArrayList<BestellStatus> bestellungen = helper.getStati();
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();

		String[] columnNames = { "Bestellnr.", "Bearbeitung", "Lieferunggeplant",
				"zuletzt bearbeitet(Zeit)", "Status", "" };

		Object[][] data = new Object[bestellungen.size()][6];

		for (BestellStatus b : bestellungen) {

			data[counter][0] = b.getBestellnr();
			data[counter][1] = b.getBearbeitung();
			data[counter][2] = b.getLieferunngsgeplant();
			data[counter][3] = helper.connection.getMitarbeiterFromBID(b.getBsID());
			data[counter][4] = b.getStatus();
			data[counter][5] = button;
			counter++;
		}

		
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
//        table.getColumn("Button1").setCellRenderer(buttonRenderer);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				History history = new History();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}