package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Helper.Helper;

public class SearchPage implements ActionListener {

	ArrayList<String> alleStati;
	ArrayList<String> alleMitarbeiter;
	ArrayList<String> alleKunde;

	JButton buttonOK;
	JButton buttonSend;
	JButton buttonLate;
	Helper helper;
	JTextField tfName;
	JTextField dateField;
	JPanel panel;
	JFrame dialog;
	JComboBox statusList;
	JComboBox alleMitarbeiterCB;
	JComboBox alleKundeCB;
	JFrame frame;

	String bestellnummer;

	public SearchPage(JFrame frame) {
		this.frame = frame;
		alleStati = new ArrayList<>();
		alleStati.add("Auftrag Bestellt");
		alleStati.add("Auftrag Aufbereiten");
		alleStati.add("Auftrag Versandbereit");
		alleStati.add("Auftrag Abgeschlossen");
		alleStati.add("");

		helper = new Helper();
		dialog = new JFrame();
		dialog.setLayout(new BorderLayout());
		dialog.setTitle("History");
		dialog.setSize(700, 600);
		dialog.setVisible(true);
		panel = new JPanel();

		JLabel label = new JLabel("Bestellnummer: ");
		panel.add(label);

		tfName = new JTextField("Beispiel Nummer", 15);
		dateField = new JTextField("2015-03-20", 15);

		panel.add(tfName);
		buttonOK = new JButton("Search");
		buttonOK.addActionListener(this);
		buttonSend = new JButton("Send");
		buttonSend.addActionListener(this);
		buttonLate = new JButton("Auftrag verspätet");
		buttonLate.addActionListener(this);
		panel.add(buttonOK);
		dialog.add(panel);
		dialog.setVisible(true);
		alleMitarbeiter = helper.connection.getAllMitarbeiter();
		alleKunde = helper.connection.getAllKunde();

	}

	public void actionPerformed(ActionEvent ae) {
		// Die Quelle wird mit getSource() abgefragt und mit den
		// Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
		// der Buttons ist, wird der Text des JLabels entsprechend geändert
		if (ae.getSource() == this.buttonOK) {
			bestellnummer = tfName.getText();
			ArrayList<String> stati = helper.connection.getStatiByBstlNR(tfName.getText());

			if (!stati.isEmpty()) {
				int counter = 0;
				for (String i : stati) {
					JLabel j = new JLabel(i);
					panel.add(j);
				}

				for (String i : stati) {
					alleStati.remove(counter);
				}

				statusList = new JComboBox(alleStati.toArray());
				alleMitarbeiterCB = new JComboBox(alleMitarbeiter.toArray());
				alleKundeCB = new JComboBox(alleKunde.toArray());

				statusList.setSelectedIndex(0);
				statusList.addActionListener(this);
				alleMitarbeiterCB.setSelectedIndex(0);
				alleMitarbeiterCB.addActionListener(this);
				alleKundeCB.setSelectedIndex(0);
				alleKundeCB.addActionListener(this);
				panel.add(alleKundeCB);
				panel.add(alleMitarbeiterCB);
				panel.add(statusList);
				panel.add(buttonSend);
				panel.add(buttonLate);
				panel.add(dateField);
				panel.revalidate();
				dialog.revalidate();

			} else {
				JOptionPane.showMessageDialog(dialog, "Please enter a valid Order Number.");
			}

		} else if (ae.getSource() == this.buttonSend) {
			if(helper.connection.setStatus(statusList.getSelectedItem().toString(), bestellnummer,
					alleMitarbeiterCB.getSelectedItem().toString(), alleKundeCB.getSelectedItem().toString()).equals("Status Abgeschlossen")){
				JOptionPane.showMessageDialog(dialog, "Status abgeschlossen, wàhle einen anderen.");
			}
			
			frame.setVisible(true);
			frame.revalidate();
			dialog.dispose();
		} else if (ae.getSource() == this.buttonLate) {
			helper.connection.setLateStatus(bestellnummer, alleMitarbeiterCB.getSelectedItem().toString(),
					alleKundeCB.getSelectedItem().toString(), dateField.getText());
			frame.setVisible(true);
			dialog.dispose();
		}

	}

}
