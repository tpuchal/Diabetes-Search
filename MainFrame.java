package mainpck;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static JLabel label;
	private static DefaultTableModel model;
	private static JScrollPane scrollPane;

	public MainFrame() {
		setSize(800, 600);
		setTitle("Diabetes search");
		setLayout(new BorderLayout());
		
		model = new DefaultTableModel(new String[] { "Number of times pregnant",
				"Plasma glucose concentration a 2 hours in an oral glucose tolerance test",
				"Diastolic blood pressure (mm Hg)", "Triceps skin fold thickness (mm)",
				"2-Hour serum insulin (mu U/ml)", "Body mass index (weight in kg/(height in m)^2)",
				"Diabetes pedigree function", "Age (years)", "Class variable (0 or 1)", "Passed" }, 0);
		table = new OutputTable();
		table.setModel(model);
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(9).setCellRenderer(new CustomRenderer());
		scrollPane = new JScrollPane(table);
		label = new JLabel("Output will be here");
		add(label, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.CENTER);

	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getPane() {
		return scrollPane;
	}

	public static JLabel getLabel() {
		return label;
	}

	public static DefaultTableModel getModel() {
		return model;
	}

}
