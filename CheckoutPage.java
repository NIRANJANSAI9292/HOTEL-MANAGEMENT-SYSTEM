import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

class CheckoutPage extends JFrame {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private List<Customer> customerList;

    public CheckoutPage(List<Customer> customerList) {
        this.customerList = customerList;
        setTitle("Checkout Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table Model
        tableModel = new DefaultTableModel(new Object[]{"Name", "Room Number", "Status", "Deposit"}, 0);
        customerTable = new JTable(tableModel);
        customerTable.setFillsViewportHeight(true);
        add(new JScrollPane(customerTable), BorderLayout.CENTER);

        // Buttons
        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");

        checkoutButton.addActionListener((ActionEvent e) -> {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a customer to check out.");
                return;
            }

            String roomNumber = (String) tableModel.getValueAt(selectedRow, 1);
            customerList.removeIf(customer -> customer.getRoomNumber().equals(roomNumber));

            updateTable();
            JOptionPane.showMessageDialog(null, "Customer checked out successfully!");
        });

        backButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        updateTable();
        setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Customer customer : customerList) {
            tableModel.addRow(new Object[]{
                    customer.getName(),
                    customer.getRoomNumber(),
                    customer.getStatus(),   // ✅ No more compilation error
                    customer.getDeposit()
            });
        }
    }
}
