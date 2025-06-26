import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PickUpServicePage extends JFrame {

    public PickUpServicePage() {
        setTitle("Pick Up Service");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for car selection with arrow-mark dropdown
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JLabel carLabel = new JLabel("Select a Car Type:");
        String[] carNames = {"Mercedes", "Benz", "Fortuner", "Rolls Royce"};
        JComboBox<String> carComboBox = new JComboBox<>(carNames);
        carComboBox.setPreferredSize(new Dimension(150, 25));

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");
        
        // Table to display driver information
        String[] columnNames = {"Driver Name", "Age", "Gender", "Company", "Brand", "Availability", "Location"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable driverTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(driverTable);
        
        // Action listener for the Submit button to display driver details
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCar = (String) carComboBox.getSelectedItem();
                tableModel.setRowCount(0);  // Clear existing rows
                String[] driverDetails = getDriverDetails(selectedCar);

                if (driverDetails != null) {
                    tableModel.addRow(driverDetails);
                } else {
                    JOptionPane.showMessageDialog(null, "No details available for " + selectedCar);
                }
            }
        });

        // Action listener for the Back button to return to Reception Page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close PickUpServicePage
                new ReceptionPage();  // Open ReceptionPage
            }
        });

        // Add components to the selection panel
        selectionPanel.add(carLabel);
        selectionPanel.add(carComboBox);
        selectionPanel.add(submitButton);

        // Panel for the back button, placed at the bottom
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backPanel.add(backButton);

        // Add components to the main frame
        add(selectionPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to get driver details based on selected car
    private String[] getDriverDetails(String car) {
        switch (car) {
            case "Mercedes":
                return new String[]{"Alice Johnson", "30", "Female", "Luxury Cars Inc.", "Mercedes", "Available", "Downtown"};
            case "Benz":
                return new String[]{"Bob Smith", "40", "Male", "Benz Co.", "Benz", "Available", "Uptown"};
            case "Fortuner":
                return new String[]{"Charlie Brown", "35", "Male", "SUVs Ltd.", "Fortuner", "Available", "Suburbs"};
            case "Rolls Royce":
                return new String[]{"Diana Prince", "28", "Female", "Luxury Rides", "Rolls Royce", "Available", "City Center"};
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PickUpServicePage::new);
    }
}
