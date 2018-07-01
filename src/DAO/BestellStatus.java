package DAO;

import java.util.ArrayList;
import java.util.Date;

public class BestellStatus {

	private int bsID;
	private String bestellnr;
	private String status;
	private Date bearbeitung;
	private Date lieferunngsgeplant;


	public Date getLieferunngsgeplant() {
		return lieferunngsgeplant;
	}

	public void setLieferunngsgeplant(Date lieferunngsgeplant) {
		this.lieferunngsgeplant = lieferunngsgeplant;
	}

	public int getBsID() {
		return bsID;
	}

	public void setBsID(int bsID) {
		this.bsID = bsID;
	}

	public String getBestellnr() {
		return bestellnr;
	}

	public void setBestellnr(String bestellnr) {
		this.bestellnr = bestellnr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBearbeitung() {
		return bearbeitung;
	}

	public void setBearbeitung(Date bearbeitung) {
		this.bearbeitung = bearbeitung;
	}
}
