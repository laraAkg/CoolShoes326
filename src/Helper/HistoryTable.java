//package Helper;
//
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
//import DAO.BestellStatus;
//import DAO.Kunde;
//import GUI.History;
//
//public class HistoryTable extends JPanel {
//
//	JTable table;
//	JButton button;
//
//	public HistoryTable() {
//		setLayout(new FlowLayout());
//
//		int counter = 0;
//		int counter2 = 0;
//		button = new JButton("Select");
//		Helper helper = new Helper();
//		ArrayList<Kunde> kunden = helper.getKunden();
//
//		String[] columns = new String[] { "Bestellnummer", "Status", "Bearbeitung", "Lieferunggeplant" };
//
//
//		Object[][] data = new Object[kunden.size()][6];
//
//		for (Kunde k : kunden) {
//			data[counter][0] = ();
//
//			counter++;
//		}
//
//		// System.out.println(data[3][1]); //Test zur Ausgabe daes Srings
//		// [Y-pos][X-Pos]
//		table = new JTable(data, columns);
//		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
//		table.setFillsViewportHeight(true);
//		table.add(button);
//
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				History history = new History();
//			}
//		});
//
//		JScrollPane scrollPane = new JScrollPane(table);
//		add(scrollPane);
//	}
//}