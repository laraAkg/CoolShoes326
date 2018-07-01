package Helper;
import java.util.ArrayList;
import Connection.MyConnection;
import DAO.BestuellungStatus;
import DAO.Kunde;

public class Helper {
	MyConnection connection = new MyConnection();

	public ArrayList<Kunde> getKunden() {
		ArrayList<Kunde> kunden = connection.getAllKunde();
		 for (Kunde kunde : kunden) {
			 kunde.getAdreesse();
			 kunde.getEmail();
			 kunde.getId();
		 }
		return kunden;
	}
	public static void main(String[] args) {
	Helper helper = new Helper();
	helper.getStati();
	}
	public ArrayList<BestuellungStatus> getStati(){
		ArrayList<BestuellungStatus> stati = connection.getAllBestellstatus();
		 for (BestuellungStatus status : stati) {
			 System.out.println(status);
		 }
		return stati;
	}
}
