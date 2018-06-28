package GUI;

import java.util.ArrayList;

import javax.swing.JTable;

public class Stati {
	private String aufbereiten = "Auftrag aufbereiten";
	private String verspätet = "Teilauftrag verspätet";
	private String versandbereit = "Auftrag versandbereit";
	private String abgeholt = "Auftrag abgeholt";
	private String geliefert = "Auftrag geliefert";
	private String[] columns = new String[] { "OrderNr.", "Versandpartner", "Mitarbeiter", "Status" };
	private Object[][] data = new Object[][] { { "1", "John", "40.0", "false" }, { "1", "John", "40.0", "false" },
			{ "1", "John", "40.0", "false" }, };
	private ArrayList<String> stati = new ArrayList<>();
	JTable table = new JTable(data, columns);

	public void addStatiToArrayList() {
		stati.add(aufbereiten);
		stati.add(verspätet);
		stati.add(versandbereit);
		stati.add(abgeholt);
		stati.add(geliefert);
	}
}
