import Connection.MyConnection;
import DAO.Kunde;

public class Main {
	public static void main(String[] args) {
		MyConnection connection = new MyConnection();
		Kunde kunde = new Kunde();
		kunde.getEmail();
	}
}
