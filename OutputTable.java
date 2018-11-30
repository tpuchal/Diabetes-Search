package mainpck;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class OutputTable extends JTable {
	private static final long serialVersionUID = 1L;

	public OutputTable() {
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component comp = super.prepareRenderer(renderer, row, column);
		if ((Double) getModel().getValueAt(row, 9) > 0.0) {
			comp.setBackground(Color.yellow);
		} else{
			comp.setBackground(Color.WHITE);
		}

		return comp;
	}

}
