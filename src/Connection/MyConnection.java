package Connection;

import java.sql.*;
import java.util.ArrayList;
import DAO.BestellStatus;
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
	
	public String getMitarbeiterFromBID(int bid) {
		String kundenName = "";

		try {
			 PreparedStatement ps = conn 
	                    .prepareStatement("SELECT FKMitarbeiter from Bestellungen where BID = ?");
			 PreparedStatement ps2 = conn 
	                    .prepareStatement("SELECT MAName, MAVorname from Mitarbeiter where MID = ?"); 
			 
	            ps.setInt(1, bid); 
	            
	            ResultSet rs = ps.executeQuery(); 
	            
	            while(rs.next())
	            	ps2.setInt(1, rs.getInt(1)); 
	            
	            rs=ps2.executeQuery();
	            
	            while(rs.next())
	            	kundenName = rs.getString(2) + ", " + rs.getString(1);
	            
	            ps.close(); 
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return kundenName;
	}


	public ArrayList<BestellStatus> getAllBestellstatus() {
		ArrayList<BestellStatus> bestellungStati = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT BSID as id, Bestellnummer as Bestellnummer, Status as Status, Bearbeitung as Bearbeitung, Lieferunggeplant as Lieferunggeplant from BestellStatus");
			BestellStatus bestuellungStatus;
			while (rs.next()) {
				bestuellungStatus = new BestellStatus();
				bestuellungStatus.setBsID(rs.getInt(1));
				bestuellungStatus.setBestellnr(rs.getString(2));
				bestuellungStatus.setStatus(rs.getString(3));
				bestuellungStatus.setBearbeitung(rs.getDate(4));
				bestuellungStatus.setLieferunngsgeplant(rs.getDate(5));
				bestellungStati.add(bestuellungStatus);
			}
			rs.close();
			stmt.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return bestellungStati;
	}
}