package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Helper.Helper;

public class SearchPage implements ActionListener{
	ArrayList<String> alleStati;
	JButton buttonOK;
	Helper helper;
	JTextField tfName;
	JPanel panel;
	JDialog dialog;
	JComboBox statusList;
	String bestellnummer;
	public SearchPage() {
		alleStati = new ArrayList<>();
		alleStati.add("Auftrag bestellt");
		alleStati.add("Auftrag Aufbereiten");
		alleStati.add("Auftrag Versandbereit");
		alleStati.add("Auftrag Abgeschlossen");
		
		
		helper = new Helper();
		dialog = new JDialog();
		dialog.setLayout(new BorderLayout());
		dialog.setTitle("History");
		dialog.setSize(700, 600);
		dialog.setVisible(true);
		panel = new JPanel();

		JLabel label = new JLabel("Bestellnummer: ");
		panel.add(label);

		tfName = new JTextField("Example Nummer", 15);
		
		panel.add(tfName);
		buttonOK = new JButton("Search");
		buttonOK.addActionListener(this);
		
		panel.add(buttonOK);
		dialog.add(panel);
		dialog.setVisible(true);

	}

	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.buttonOK){
        	bestellnummer = tfName.getText();
        	ArrayList<String> stati = helper.connection.getStatiByBstlNR(tfName.getText());
        	int counter = 0;
        	for(String i : stati) {
        		JLabel j = new JLabel(i);
        		panel.add(j);
        		
        	}
        	
        	for(String i : stati) {
        			alleStati.remove(counter);
              	}
        	
        	statusList = new JComboBox(alleStati.toArray());
        	
        	statusList.setSelectedIndex(0);
        	statusList.addActionListener(this);
        	panel.add(statusList);
        	panel.repaint();
        	dialog.repaint();
        	panel.revalidate();
        	dialog.revalidate();
        	panel.doLayout();
        	dialog.doLayout();
        	
        }else if(ae.getSource() == this.statusList) {
        	helper.connection.setStatus(statusList.getSelectedItem().toString(), bestellnummer);
        	
        	panel.repaint();
        	dialog.repaint();
        	panel.revalidate();
        	dialog.revalidate();
        	panel.doLayout();
        	dialog.doLayout();
        }
        
    }

}
