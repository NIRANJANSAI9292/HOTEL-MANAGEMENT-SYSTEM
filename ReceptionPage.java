import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Room class
class Room {
    private String roomNumber;
    private boolean isAvailable;
    private boolean isCleaned;
    private int price;
    private String bedType;

    public Room(String roomNumber, boolean isAvailable, boolean isCleaned, int price, String bedType) {
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.isCleaned = isCleaned;
        this.price = price;
        this.bedType = bedType;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getAvailability() { return isAvailable ? "Available" : "Occupied"; }
    public String getStatus() { return isCleaned ? "Cleaned" : "Not Cleaned"; }
    public int getPrice() { return price; }
    public String getBedType() { return bedType; }
}

// Customer class
class Customer {
    private String name;
    private String documentType;
    private String documentNumber;
    private String country;
    private String gender;
    private String roomNumber;
    private String status;
    private String deposit;

    public Customer(String name, String documentType, String documentNumber, String country, String gender, String roomNumber, String status, String deposit) {
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.country = country;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.status = status;
        this.deposit = deposit;
    }

    public String getName() { return name; }
    public String getDocumentType() { return documentType; }
    public String getDocumentNumber() { return documentNumber; }
    public String getGender() { return gender; }
    public String getCountry() { return country; }
    public String getRoomNumber() { return roomNumber; }
    public String getStatus() { return status; }
    public String getDeposit() { return deposit; }
}

// ReceptionPage class (main page)
public class ReceptionPage extends JFrame {
    private List<Customer> customerList;

    public ReceptionPage() {
        customerList = new ArrayList<>();
        setTitle("Hotel Reception");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Background image panel
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("fourth.png");  // Use your desired image path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(13, 1, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton newCustomerButton = new JButton("New Customer Form");
        JButton departmentButton = new JButton("Department");
        JButton allEmployeeButton = new JButton("All Employee Info");
        JButton customerInfoButton = new JButton("Customer Info");
        JButton managerInfoButton = new JButton("Manager Info");
        JButton checkoutButton = new JButton("Checkout");
        JButton updateStatusButton = new JButton("Update Status");
        JButton updateRoomStatusButton = new JButton("Update Room Status");
        JButton searchRoomButton = new JButton("SearchRoomPage");
        JButton roomPageButton = new JButton("Room Page");
        JButton pickUpServiceButton = new JButton("Pick Up Service");
        JButton logoutButton = new JButton("Logout");

        Dimension buttonSize = new Dimension(150, 30);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JButton[] buttons = {
                newCustomerButton, departmentButton, allEmployeeButton, customerInfoButton,
                managerInfoButton, checkoutButton, updateStatusButton, updateRoomStatusButton, searchRoomButton,
                roomPageButton, pickUpServiceButton, logoutButton
        };

        for (JButton button : buttons) {
            button.setPreferredSize(buttonSize);
            button.setFont(buttonFont);
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            buttonPanel.add(button);
        }

        // Add action listeners
        newCustomerButton.addActionListener(e -> new NewCustomerForm(customerList));
        allEmployeeButton.addActionListener(e -> new AllEmployeePage());
        customerInfoButton.addActionListener(e -> new CustomerInfoPage(customerList));
        pickUpServiceButton.addActionListener(e -> new PickUpServicePage());
        searchRoomButton.addActionListener(e -> new SearchRoomPage());
        managerInfoButton.addActionListener(e -> new ManagerInfoPage());
        updateRoomStatusButton.addActionListener(e -> new UpdateRoomStatus());
        checkoutButton.addActionListener(e -> new CheckoutPage(customerList));


        roomPageButton.addActionListener(e -> {
            List<Room> rooms = new ArrayList<>();
            rooms.add(new Room("101", true, true, 2000, "Single"));
            rooms.add(new Room("102", false, false, 2500, "Double"));
            rooms.add(new Room("103", true, false, 3000, "Suite"));
            rooms.add(new Room("104", false, true, 3500, "Deluxe"));
            rooms.add(new Room("105", true, true, 4000, "Executive"));
            new RoomPage(rooms);
        });
        departmentButton.addActionListener(e -> new DepartmentPage());
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging Out...");
            new LoginPage();
            dispose();
        });

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReceptionPage::new);
    }
}

// RoomPage class
class RoomPage extends JFrame {
    public RoomPage(List<Room> rooms) {
        setTitle("Room Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Room Number", "Availability", "Status", "Price (₹)", "Bed Type"}, 0);

        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getAvailability(),
                    room.getStatus(),
                    room.getPrice(),
                    room.getBedType()
            });
        }

        JTable roomTable = new JTable(tableModel);
        roomTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

// DepartmentPage class
class DepartmentPage extends JFrame {
    public DepartmentPage() {
        setTitle("Department Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample department data
        String[][] data = {
                {"HR", "Human Resources", "Responsible for recruitment, employee welfare, and payroll."},
                {"Sales", "Sales Department", "Handles customer relations, sales, and marketing."},
                {"IT", "Information Technology", "Manages the hotel's technological infrastructure."},
                {"Housekeeping", "Housekeeping", "Ensures cleanliness and maintenance of rooms."}
        };
        String[] columns = {"Department Code", "Department Name", "Description"};

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable departmentTable = new JTable(tableModel);
        departmentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(departmentTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

// Placeholder classes for other pages
class NewCustomerForm extends JFrame {
    public NewCustomerForm(List<Customer> customerList) {
        setTitle("New Customer Form");
        setSize(400, 400);
        setVisible(true);
    }
}

class AllEmployeePage extends JFrame {
    public AllEmployeePage() {
        setTitle("All Employee Info");
        setSize(400, 400);
        setVisible(true);
    }
}

class CustomerInfoPage extends JFrame {
    public CustomerInfoPage(List<Customer> customerList) {
        setTitle("Customer Info");
        setSize(400, 400);
        setVisible(true);
    }
}

class SearchRoomPage extends JFrame {
    public SearchRoomPage() {
        setTitle("Search Room");
        setSize(400, 400);
        setVisible(true);
    }
}

class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 400);
        setVisible(true);
    }

    public static void registerUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    }
}
