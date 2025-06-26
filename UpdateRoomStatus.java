import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UpdateRoomStatus extends JFrame {
    private JTextField roomNumberField;
    private JComboBox<String> statusComboBox;
    private JButton updateButton;
    private HashMap<Integer, String> roomStatus;

    public UpdateRoomStatus() {
        setTitle("Update Room Status");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        
        roomStatus = new HashMap<>(); // Simulating a room status storage
        
        add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        add(roomNumberField);
        
        add(new JLabel("New Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Available", "Occupied", "Cleaning"});
        add(statusComboBox);
        
        updateButton = new JButton("Update Status");
        add(updateButton);
        
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String newStatus = (String) statusComboBox.getSelectedItem();
                updateRoomStatus(roomNumber, newStatus);
            }
        });
        
        setVisible(true);
    }

    public void updateRoomStatus(int roomNumber, String newStatus) {
        roomStatus.put(roomNumber, newStatus);
        JOptionPane.showMessageDialog(null, "Room " + roomNumber + " status updated to: " + newStatus);
    }

    public static void main(String[] args) {
        new UpdateRoomStatus();
    }
}
