package Connection;

import java.sql.*;
import java.util.ArrayList;
import DAO.BestuellungStatus;
import DAO.Kunde;

public class MyConnection {

	private static Connection conn;

	public MyConnection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn = DriverManager.getConnection("jdbc:ucanaccess://CoolShoes.accdb");
		} catch (ClassNotFoundException err) {
			System.out.println("Treiber kann nicht geladen werden");
		} catch (SQLException err) {
			System.out.println("Verbindung kann nicht aufgebaut werden");
		}
	}

	public ArrayList<Kunde> getAllKunde() {
		ArrayList<Kunde> kunden = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT KID as ID, KName as Name, KVorname as Vorname, KAdresse as Adresse, KPLZ as PLZ, KOrt as Ort, KEmailAdresse as Email, KPWD as Passwort FROM Kunde");
			while (rs.next()) {
				Kunde kunde = new Kunde();
				kunde.setId(rs.getInt("ID"));
				kunde.setVorname(rs.getString("Vorname"));
				kunde.setNachname(rs.getString("Name"));
				kunde.setAdreesse(rs.getString("Adresse"));
				kunde.setPlz(rs.getString("PLZ"));
				kunde.setOrt(rs.getString("Ort"));
				kunde.setEmail(rs.getString("Email"));
				kunde.setPasswort(rs.getString("Passwort"));
				kunden.add(kunde);
			}
			rs.close();
			stmt.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return kunden;
	}

	public ArrayList<String[]> getAllBestellstatus(String tabelle) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT BID as id, FKKunde as Kunde, FKStatus as Status, FKMitarbeiter as Mitarbeiter FROM Bestellungen");
			BestuellungStatus bestuellungStatus = new BestuellungStatus();
			while (rs.next()) {
				bestuellungStatus.setBsID(rs.getInt(""));
				bestuellungStatus.setStatus(rs.getString(""));
				bestuellungStatus.setBearbeitung(rs.getDate(""));
				bestuellungStatus.setLieferunngsgeplant(rs.getDate(""));
			}
			rs.close();
			stmt.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return null;
	}
}
