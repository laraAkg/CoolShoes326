package Helper;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import DAO.BestellStatus;

public class Table extends JFrame {
 
 JTable table;
 
 public Table(){
  setLayout(new FlowLayout());
  
  int counter = 0;
  int counter2 = 0;
  
  Helper helper = new Helper();
	
  ArrayList<BestellStatus> bestellungen = helper.getStati();
  
  String[] columnNames = { "Bestellnr.", "Lieferung erwartet", "zuletzt bearbeitet(Person)",
			"zuletzt bearbeitet(Zeit)", "Status" };
  
  Object[][] data = new Object[bestellungen.size()][5];

  
  
  for(BestellStatus b : bestellungen) {
	  
		 data[counter][0] = bestellungen.get(counter).getBestellnr();
		 data[counter][1] = bestellungen.get(counter).getBearbeitung();
		 data[counter][2] = bestellungen.get(counter).getLieferunngsgeplant();
		 data[counter][3] = bestellungen.get(counter).getBsID();
		 data[counter][4] = bestellungen.get(counter).getStatus();
		 
	  counter++;
  }
  

  
  //System.out.println(data[3][1]); //Test zur Ausgabe daes Srings [Y-pos][X-Pos]
  table = new JTable(data, columnNames);
  table.setPreferredScrollableViewportSize(new Dimension(500, 100));
  table.setFillsViewportHeight(true);
  
  JScrollPane scrollPane = new JScrollPane(table);
  add(scrollPane);
  
 }

 public static void main(String args[]) {
	 Table gui = new Table();
  gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  gui.setSize(600,200);;
  gui.setVisible(true);
  gui.setTitle("Test Tabelle");
 }
}