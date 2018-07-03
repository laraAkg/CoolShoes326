package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.BestellStatus;

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

	public String getMitarbeiterFromBID(int bid) {
		String kundenName = "";

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT FKMitarbeiter from Bestellungen where BID = ?");
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAName, MAVorname from Mitarbeiter where MID = ?");

			ps.setInt(1, bid);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				ps2.setInt(1, rs.getInt(1));

			rs = ps2.executeQuery();

			while (rs.next())
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

	public ArrayList<String> getAllKunde() {
		ArrayList<String> kundenListe = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select KName, KVorname from Kunde");

			while (rs.next()) {
				kundenListe.add(rs.getString(1) + " " + rs.getString(2));
			}
			rs.close();
			stmt.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return kundenListe;
	}

	public ArrayList<String> getAllMitarbeiter() {
		ArrayList<String> mitarbeiterListe = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select MAName, MAVorname from Mitarbeiter");
			while (rs.next()) {
				mitarbeiterListe.add(rs.getString(1) + " " + rs.getString(2));
			}
			rs.close();
			stmt.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return mitarbeiterListe;
	}

	public ArrayList<String> getStatiByBstlNR(String bestellNummer) {
		ArrayList<String> statis = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT Status from BestellStatus where Bestellnummer = ?");

			ps.setString(1, bestellNummer);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				statis.add(rs.getString(1));

			ps.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return statis;
	}

	public int getMitarbeiterByName(String name) {
		String[] vorNachName = name.split(" ");
		int i = 0;
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT MID from Mitarbeiter where MAName = ? and MAVorname = ?");

			ps.setString(1, vorNachName[0]);
			ps.setString(2, vorNachName[1]);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				i = rs.getInt(1);

			ps.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return i;
	}

	public int getKundeIdByName(String name) {
		String[] vorNachName = name.split(" ");

		int i = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT KID from Kunde where KName = ? and KVorname = ?");

			ps.setString(1, vorNachName[0]);
			ps.setString(2, vorNachName[1]);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				i = rs.getInt(1);

			ps.close();
		} catch (SQLException err) {
			System.out.println("ungültiger SQL-Befehl");
		}
		return i;
	}

	public void setLateStatus(String bestellNummer, String mitarbeiter, String Kunde, String datum) {
		
		
		ArrayList<String> alphabet = new ArrayList<>();
		fillAlphabet(alphabet);

		int kundenID = getKundeIdByName(Kunde);
		int mitarbeiterId = getMitarbeiterByName(mitarbeiter);
		try {
			PreparedStatement ps2 = conn.prepareStatement(
					"INSERT INTO Bestellungen (FKKunde, FKStatus, FKMitarbeiter)\n" + "VALUES (?, ?, ?);");
			ps2.setInt(1, kundenID);
			ps2.setInt(2, 3);
			ps2.setInt(3, mitarbeiterId);
			ps2.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO BestellStatus (BestellNummer, Status, Bearbeitung, Lieferunggeplant)\n" + "VALUES (?, ?, ?, ?);");
			int counter = 0;
			if(bestellNummer.contains("A")) {
				for(String i : alphabet) {
					counter++;
					if(bestellNummer.endsWith(i)) {
						bestellNummer = bestellNummer.replace(alphabet.get(counter-1), "");
						bestellNummer += alphabet.get(counter);
						break;
					}
				}
			}else {
				bestellNummer += "A";
			}
			
			
			ps.setString(1, bestellNummer);
			ps.setString(2, "Teilauftrag verspätet");
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			ps.setDate(3, sqlDate);
			ps.setDate(4, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String setStatus(String status, String bestellNummer, String mitarbeiter, String Kunde) {
		
		if(status == ""){
			return "Status Abgeschlossen";
		}
		
		int kundenID = getKundeIdByName(Kunde);
		int mitarbeiterId = getMitarbeiterByName(mitarbeiter);
		try {
			PreparedStatement ps2 = conn.prepareStatement(
					"INSERT INTO Bestellungen (FKKunde, FKStatus, FKMitarbeiter)\n" + "VALUES (?, ?, ?);");
			ps2.setInt(1, kundenID);
			ps2.setInt(2, 3);
			ps2.setInt(3, mitarbeiterId);
			ps2.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO BestellStatus (BestellNummer, Status, Bearbeitung)\n" + "VALUES (?, ?, ?);");
			ps.setString(1, bestellNummer);
			ps.setString(2, status);
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			ps.setDate(3, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";

	}
	
	public void fillAlphabet(ArrayList<String> alphabet) {
		alphabet.add("B");
		alphabet.add("C");
		alphabet.add("D");
		alphabet.add("E");
		alphabet.add("F");
		alphabet.add("G");
		alphabet.add("H");
		alphabet.add("I");
		alphabet.add("J");
		alphabet.add("K");
		alphabet.add("L");
		alphabet.add("M");
		alphabet.add("N");
		alphabet.add("O");
		alphabet.add("P");
		alphabet.add("Q");
		alphabet.add("R");
		alphabet.add("S");
		alphabet.add("T");
		alphabet.add("U");
		alphabet.add("V");
		alphabet.add("W");
		alphabet.add("X");
		alphabet.add("Y");
		alphabet.add("Z");
	}
}