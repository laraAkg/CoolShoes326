import java.util.ArrayList;

import Connection.MyConnection;
import DAO.Kunde;

public class Main {
	public static void main(String[] args) {
		MyConnection connection = new MyConnection();
		ArrayList<Kunde> kunden = connection.getAllKunde();
		for (Kunde kunde : kunden) {
			System.out.println(kunde.getVorname());
		}
	}
}
