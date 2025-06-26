import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRoomPage extends JFrame {
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> bedTypeComboBox;
    private JCheckBox availableOnlyCheckBox;
    private List<Room> rooms;

    public SearchRoomPage() {
        System.out.println("Initializing SearchRoomPage...");

        setTitle("Search Room");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Dummy room data
        rooms = new ArrayList<>();
        rooms.add(new Room("101", true, false, 2000, "Single"));
        rooms.add(new Room("102", false, true, 2500, "Double"));
        rooms.add(new Room("103", true, false, 3000, "Suite"));
        rooms.add(new Room("104", false, true, 3500, "Deluxe"));
        rooms.add(new Room("105", true, false, 4000, "Executive"));

        // Filter Panel
        JPanel filterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(new JLabel("Bed Type:"), gbc);

        bedTypeComboBox = new JComboBox<>(new String[]{"All", "Single", "Double", "Suite", "Deluxe", "Executive"});
        gbc.gridx = 1;
        filterPanel.add(bedTypeComboBox, gbc);

        availableOnlyCheckBox = new JCheckBox("Only Available");
        gbc.gridx = 2;
        filterPanel.add(availableOnlyCheckBox, gbc);

        JButton searchButton = new JButton("Search");
        gbc.gridx = 3;
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> filterRooms());
        filterPanel.add(searchButton, gbc);

        // Table for displaying rooms
        String[] columnNames = {"Room Number", "Availability", "Status", "Price", "Bed Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        roomTable.setRowHeight(25);
        updateTable(rooms);

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(roomTable), BorderLayout.CENTER);

        setVisible(true); // Ensure this is at the end
        System.out.println("SearchRoomPage is now visible.");
    }

    private void filterRooms() {
        String selectedBedType = (String) bedTypeComboBox.getSelectedItem();
        boolean onlyAvailable = availableOnlyCheckBox.isSelected();

        List<Room> filteredRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean matchesBedType = selectedBedType.equals("All") || room.getBedType().equals(selectedBedType);
            boolean matchesAvailability = !onlyAvailable || room.isAvailable();

            if (matchesBedType && matchesAvailability) {
                filteredRooms.add(room);
            }
        }

        updateTable(filteredRooms);
    }

    private void updateTable(List<Room> roomList) {
        tableModel.setRowCount(0); // Clear table
        for (Room room : roomList) {
            tableModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.isAvailable() ? "Available" : "Not Available",
                room.getStatus() ? "Occupied" : "Vacant",
                room.getPrice(),
                room.getBedType()
            });
        }
    }

    public static void main(String[] args) {
        new SearchRoomPage();
    }
}

// Room Class
class Room {
    private String roomNumber;
    private boolean available;
    private boolean status; // true: Occupied, false: Vacant
    private int price;
    private String bedType;

    public Room(String roomNumber, boolean available, boolean status, int price, String bedType) {
        this.roomNumber = roomNumber;
        this.available = available;
        this.status = status;
        this.price = price;
        this.bedType = bedType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean getStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    public String getBedType() {
        return bedType;
    }
}
