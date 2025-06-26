import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private int age;
    private String gender;
    private String department;

    public Employee(String name, int age, String gender, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
}

public class DepartmentPage extends JFrame {
    private List<Employee> employees;
    private JTable employeeTable;

    public DepartmentPage() {
        setTitle("Hotel Department");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure it exits the application when closed
        setLayout(new BorderLayout());

        // Sample data for employees
        employees = new ArrayList<>();
        employees.add(new Employee("John Doe", 30, "Male", "Reception"));
        employees.add(new Employee("Jane Smith", 28, "Female", "Housekeeping"));
        employees.add(new Employee("Michael Brown", 35, "Male", "Food & Beverage"));
        employees.add(new Employee("Emily White", 25, "Female", "Management"));
        employees.add(new Employee("David Lee", 40, "Male", "Maintenance"));

        // Panel for heading
        JPanel headerPanel = new JPanel();
        JLabel headingLabel = new JLabel("Hotel Department Employees");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headingLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Name", "Age", "Gender", "Department"}, 0);

        // Table to display employee data
        employeeTable = new JTable(tableModel);
        employeeTable.setFillsViewportHeight(true);

        // Adding data to the table
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                    employee.getName(),
                    employee.getAge(),
                    employee.getGender(),
                    employee.getDepartment()
            });
        }

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Set window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DepartmentPage();
        });
    }
}
