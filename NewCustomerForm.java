import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class NewCustomerForm extends JFrame {
    private JComboBox<String> idTypeDropdown;
    private JTextField idNumberField;
    private JTextField nameField;
    private JRadioButton maleRadio, femaleRadio;
    private JTextField countryField;
    private JComboBox<String> availableRoomsDropdown;
    private JLabel checkInTimeLabel;
    private JTextField depositField;
    private List<Customer> customerList;
    public NewCustomerForm(List<Customer> customerList) {  // Pass customerList to share with ReceptionPage
        RoomsPage roomsPage = RoomsPage.getInstance(); 
        this.customerList = customerList;
        setTitle("New Customer Form");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBackground(Color.LIGHT_GRAY);
        idTypeDropdown = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter ID", "Driving License"});
        idNumberField = new JTextField(15);
        nameField = new JTextField(15);
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        countryField = new JTextField(15);
        availableRoomsDropdown = new JComboBox<>();
        updateAvailableRooms(roomsPage); // Populate available rooms
        checkInTimeLabel = new JLabel();
        updateCheckInTime(); // Set current check-in time
        depositField = new JTextField(15);

        // Add components to form panel
        formPanel.add(new JLabel("ID Type:")); formPanel.add(idTypeDropdown);
        formPanel.add(new JLabel("ID Number:")); formPanel.add(idNumberField);
        formPanel.add(new JLabel("Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
            add(maleRadio); add(femaleRadio);
        }});
        formPanel.add(new JLabel("Country:")); formPanel.add(countryField);
        formPanel.add(new JLabel("Allocated Room:")); formPanel.add(availableRoomsDropdown);
        formPanel.add(new JLabel("Check-In Time:")); formPanel.add(checkInTimeLabel);
        formPanel.add(new JLabel("Deposit:")); formPanel.add(depositField);

        // Buttons for actions
        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(e -> addCustomer(roomsPage));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        // Add buttons to panel
        formPanel.add(addCustomerButton); formPanel.add(backButton);

        // Add panels to frame
        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addCustomer(RoomsPage roomsPage) {
        String idType = (String) idTypeDropdown.getSelectedItem();
        String idNumber = idNumberField.getText();
        String name = nameField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        String country = countryField.getText();
        String roomNumber = (String) availableRoomsDropdown.getSelectedItem();
        String depositText = depositField.getText();

        // Parse deposit as double
        try {
            double deposit = Double.parseDouble(depositText);

            if (validateForm()) {
                // Create a new customer and add to the shared customer list
                Customer customer = new Customer(idType, idNumber, name, gender, country, roomNumber, deposit, checkInTimeLabel.getText());
                customerList.add(customer);

                // Update the room status to occupied
                roomsPage.updateRoomStatus(roomNumber, "Occupied");
                JOptionPane.showMessageDialog(this, "Customer added successfully!");

                // Open CustomerInfoPage to display the added customer's information
                new CustomerInfoPage(customerList);

                dispose(); // Close the form after adding
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid deposit amount. Please enter a valid number.");
        }
    }

    private boolean validateForm() {
        // Ensure all required fields are filled in correctly
        return !idNumberField.getText().isEmpty() && !nameField.getText().isEmpty() &&
                (maleRadio.isSelected() || femaleRadio.isSelected()) && 
                !countryField.getText().isEmpty() && 
                availableRoomsDropdown.getSelectedItem() != null && 
                !depositField.getText().isEmpty();
    }

    private void updateCheckInTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        checkInTimeLabel.setText(sdf.format(new Date()));
    }

    private void updateAvailableRooms(RoomsPage roomsPage) {
        availableRoomsDropdown.removeAllItems(); // Clear current items
        for (String room : roomsPage.getAvailableRooms()) {
            availableRoomsDropdown.addItem(room); // Populate with available rooms
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewCustomerForm(new ArrayList<>()));
    }
}

// The Customer class
class Customer {
    private String idType;
    private String idNumber;
    private String name;
    private String gender;
    private String country;
    private String roomNumber;
    private double deposit;
    private String checkInTime;

    public Customer(String idType, String idNumber, String name, String gender, String country, String roomNumber, double deposit, String checkInTime) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.roomNumber = roomNumber;
        this.deposit = deposit;
        this.checkInTime = checkInTime;
    }

    // Getters for customer information
    public String getIdType() { return idType; }
    public String getIdNumber() { return idNumber; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getCountry() { return country; }
    public String getRoomNumber() { return roomNumber; }
    public double getDeposit() { return deposit; }
    public String getCheckInTime() { return checkInTime; }

    public Object getDocumentType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDocumentType'");
    }

    public Object getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }
}

// Mock RoomsPage class for room status management
class RoomsPage {
    private static RoomsPage instance;

    public static RoomsPage getInstance() {
        if (instance == null) {
            instance = new RoomsPage();
        }
        return instance;
    }

    // Get available rooms (mockup for now, implement based on your requirements)
    public List<String> getAvailableRooms() {
        // Placeholder for available rooms
        return new ArrayList<>(List.of("101", "102", "103","104","105"));
    }

    // Update room status (e.g., mark as occupied)
    public void updateRoomStatus(String roomNumber, String status) {
        // Implement room status update logic here
        System.out.println("Room " + roomNumber + " status updated to " + status);
    }
}

// CustomerInfoPage class for displaying customer info
class CustomerInfoPage extends JFrame {
    public CustomerInfoPage(List<Customer> customerList) {
        setTitle("Customer Info");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a table model to hold the customer data
        String[] columnNames = {"ID Type", "ID Number", "Name", "Gender", "Country", "Room Number", "Deposit", "Check-In Time"};
        String[][] data = new String[customerList.size()][8];

        // Populate the table data from the customerList
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            data[i][0] = customer.getIdType();
            data[i][1] = customer.getIdNumber();
            data[i][2] = customer.getName();
            data[i][3] = customer.getGender();
            data[i][4] = customer.getCountry();
            data[i][5] = customer.getRoomNumber();
            data[i][6] = String.valueOf(customer.getDeposit());
            data[i][7] = customer.getCheckInTime();
        }

        // Create a JTable to display customer information
        JTable customerTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}