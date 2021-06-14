import javax.swing.*;
import javax.swing.table.*;

public class TableCheckBox extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public TableCheckBox() {
        Object[] columnNames = {"Type", "Company", "Shares", "Price", "Boolean"};
        Object[][] data = {
            {"Buy", "IBM", 1000, 80.50, false},
            {"Sell", "MicroSoft", (2000), (6.25), Boolean.TRUE},
            {"Sell", "Apple", Integer.valueOf(3000), (7.35), Boolean.TRUE},
            {"Buy", "Nortel", Integer.valueOf(4000), Double.valueOf(20.00), Boolean.FALSE}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return data[0][columnIndex].getClass();
        }
        };

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                TableCheckBox frame = new TableCheckBox();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocation(150, 150);
                frame.setVisible(true);
            }
        });
    }
}