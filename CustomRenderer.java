package mainpck;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CustomRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		setOpaque(true);
		if (table.getModel().getValueAt(row, 9).equals(1.0)) {
			setBackground(Color.yellow);
			setText("YES");
			setBackground(Color.yellow);
		} else {
			setText("NO");
		}
		return this;
	}

}
