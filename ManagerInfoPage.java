import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Manager class to represent the manager's information
class Manager {
    private String name;
    private String department;
    private String position;
    private String contactNumber;

    public Manager(String name, String department, String position, String contactNumber) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.contactNumber = contactNumber;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getContactNumber() { return contactNumber; }
}

// ManagerInfoPage class to display the manager information in a table format
public class ManagerInfoPage extends JFrame {

    public ManagerInfoPage() {
        setTitle("Manager Info");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample manager data
        List<Manager> managers = new ArrayList<>();
        managers.add(new Manager("John Doe", "HR", "HR Manager", "123-456-789"));
        managers.add(new Manager("Jane Smith", "Sales", "Sales Manager", "987-654-321"));
        managers.add(new Manager("David Lee", "IT", "IT Manager", "555-123-456"));
        managers.add(new Manager("Emily Clark", "Housekeeping", "Housekeeping Manager", "444-987-654"));

        // Prepare the table data
        String[][] data = new String[managers.size()][4];
        for (int i = 0; i < managers.size(); i++) {
            data[i][0] = managers.get(i).getName();
            data[i][1] = managers.get(i).getDepartment();
            data[i][2] = managers.get(i).getPosition();
            data[i][3] = managers.get(i).getContactNumber();
        }
        String[] columns = {"Name", "Department", "Position", "Contact Number"};

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable managerTable = new JTable(tableModel);
        managerTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(managerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to go back to the previous page
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> dispose());  // Close this window

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Main method to run the ManagerInfoPage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManagerInfoPage());
    }
}
