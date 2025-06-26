import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Room {
    private String roomNumber;
    private boolean isAvailable;
    private boolean isCleaned;
    private double price;
    private String bedType;

    public Room(String roomNumber, boolean isAvailable, boolean isCleaned, double price, String bedType) {
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.isCleaned = isCleaned;
        this.price = price;
        this.bedType = bedType;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getAvailability() { return isAvailable ? "Available" : "Occupied"; }
    public String getStatus() { return isCleaned ? "Cleaned" : "Uncleaned"; }
    public double getPrice() { return price; }
    public String getBedType() { return bedType; }
}

public class Roompage extends JFrame {

    public Roompage(List<Room> rooms) {
        setTitle("Rooms Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Room Number", "Availability", "Status", "Price (₹)", "Bed Type"}, 0);
        
        // Fill the table model with room data
        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getAvailability(),
                    room.getStatus(),
                    room.getPrice(),
                    room.getBedType()
            });
        }

        // Create the table with the model
        JTable roomTable = new JTable(tableModel);
        roomTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(roomTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create a back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the RoomsPage
            }
        });

        // Add the back button to the bottom of the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Sample room data
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", true, true, 2000, "Single"));
        rooms.add(new Room("102", false, false, 2500, "Double"));
        rooms.add(new Room("103", true, false, 3000, "Suite"));
        rooms.add(new Room("104", false, true, 3500, "Deluxe"));
        rooms.add(new Room("105", true, true, 4000, "Executive"));

        // Display the RoomsPage with sample data
        SwingUtilities.invokeLater(() -> new Roompage(rooms));
    }

    public String[] getAvailableRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableRooms'");
    }
}
