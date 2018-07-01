package Helper;

 import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO.BestellStatus;
 
 public class MyTableModel extends AbstractTableModel {
	 
 	private String[] columnNames = { "Bestellnr.", "Lieferung erwartet", "zuletzt bearbeitet(Person)",
 			"zuletzt bearbeitet(Zeit)", "Status" };
 	Helper helper = new Helper();
 	
 	ArrayList<BestellStatus> bestellungen = helper.getStati();
 	
 	private Object[][] data = { { bestellungen.get(1).getBearbeitung(), bestellungen.get(1).getBestellnr(), bestellungen.get(1).getBsID(), bestellungen.get(1).getLieferunngsgeplant(), bestellungen.get(1).getStatus() }};
 		
 	public int getColumnCount() {
 		return columnNames.length;
 	}
 
 	public int getRowCount() {
 		return data.length;
 	}
 
 	public String getColumnName(int col) {
 		return columnNames[col];
 	}
 
 	public Object getValueAt(int row, int col) {
 		return data[row][col];
 	}
 }