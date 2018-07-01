package Helper;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private String[] columnNames = { "Bestellnr.", "Lieferung erwartet", "zuletzt bearbeitet(Person)",
			"zuletzt bearbeitet(Zeit)", "Status" };
	Helper helper = new Helper();
	private Object[][] data = { { helper.getKunden().get(1).getAdreesse(), helper.getKunden().get(1).getAdreesse(),
			helper.getKunden().get(1).getAdreesse(), helper.getKunden().get(1).getAdreesse(),helper.getKunden().get(1).getAdreesse() } };

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