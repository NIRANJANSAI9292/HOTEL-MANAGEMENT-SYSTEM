import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AllEmployeePage extends JFrame {
    public AllEmployeePage() {
        setTitle("All Employee Info");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        String[][] employeeData = {
            {"Ramya", "30", "Female", "Accountant", "50000", "9876543210", "123456789012", "alice@example.com"},
            {"John", "25", "Male", "Manager", "60000", "8765432109", "123456789013", "bob@example.com"},
            {"Sam", "28", "Female", "Room Service", "55000", "7654321098", "123456789014", "carol@example.com"},
            {"David", "35", "Male", "Chef", "70000", "6543210987", "123456789015", "david@example.com"}
        };
        String[] columnNames = {"Name", "Age", "Gender", "Department", "Salary", "Phone Number", "Aadhar", "Email"};

        JTable employeeTable = new JTable(employeeData, columnNames);
        employeeTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                try {
                    new ReceptionPage(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ReceptionPage is not defined!");
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
public static void main(String[] args) {
    SwingUtilities.invokeLater(AllEmployeePage::new);
}
}

