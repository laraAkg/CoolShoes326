package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.MyTableModel;

public class MyJTable extends JPanel{
	JTable jTable = new JTable(new MyTableModel());

	public void MyJTable(){
	    jTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
	    JScrollPane scrollPane = new JScrollPane(jTable);
	    add(scrollPane);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);	}
}
