package Helper;

import java.util.ArrayList;
import Connection.MyConnection;
import DAO.BestellStatus;
import DAO.Kunde;

public class Helper {
	public MyConnection connection = new MyConnection();

	
	public static void main(String[] args) {
	Helper helper = new Helper();
	helper.getStati();
	}
	
	public ArrayList<BestellStatus> getStati(){
		ArrayList<BestellStatus> stati = connection.getAllBestellstatus();
		 for (BestellStatus status : stati) {
			 System.out.println(status);
		 }
		return stati;
	}
}
