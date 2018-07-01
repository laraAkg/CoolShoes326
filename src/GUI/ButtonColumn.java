package GUI;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ButtonColumn extends AbstractCellEditor {

	private JButton renderButton;

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public ButtonColumn(JTable table,  int column) {
		renderButton = new JButton("Select");
	
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer( (TableCellRenderer) this );
		columnModel.getColumn(column).setCellEditor( (TableCellEditor) this );
		
	}

}
