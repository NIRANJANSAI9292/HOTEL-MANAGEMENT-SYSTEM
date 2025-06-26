import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerInfoPage extends JFrame {
    public CustomerInfoPage(List<Customer> customerList) {
        setTitle("Customer Info");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Define table columns
        String[] columnNames = {"ID Type", "ID Number", "Name", "Gender", "Country", "Room Number", "Status", "Deposit"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate table model with customer data
        for (Customer customer : customerList) {
            tableModel.addRow(new Object[]{
                    customer.getDocumentType(),
                    customer.getDocumentType(),
                    customer.getName(),
                    customer.getGender(),
                    customer.getCountry(),
                    customer.getRoomNumber(),
                    customer.getStatus(),
                    customer.getDeposit()
            });
        }

        // Create table with the model
        JTable customerTable = new JTable(tableModel);
        customerTable.setFillsViewportHeight(true);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create back button and add action listener
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose()); // Close CustomerInfoPage

        // Add button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
