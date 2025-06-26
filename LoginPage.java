import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    // Store registered users in a HashMap (for demonstration purposes)
    private static HashMap<String, String> registeredUsers = new HashMap<>();

    public LoginPage() {
        // Set the title of the login window
        setTitle("Login Page");

        // Set the size of the window
        setSize(400, 300);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image
                ImageIcon background = new ImageIcon("third.png"); // Change to your image path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); 
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        usernameLabel.setForeground(Color.BLUE); // Set text color to blue
        panel.add(usernameLabel);  usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setForeground(Color.BLUE); // Set text color to blue
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        panel.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 40);
        loginButton.setBackground(Color.DARK_GRAY); // Set dark background color
        loginButton.setForeground(Color.WHITE); // Set text color to white
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    openReceptionPage(); // Open the Reception Page
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create and set up register button
        registerButton = new JButton("Register");
        registerButton.setBounds(50, 150, 100, 40); // Position to the left of the login button
        registerButton.setBackground(Color.DARK_GRAY); // Set dark background color
        registerButton.setForeground(Color.WHITE); // Set text color to white
        panel.add(registerButton);

        // Add action listener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration page when the register button is clicked
                new RegistrationPage();
                dispose(); // Close the login page
            }
        });
        add(panel);
        setVisible(true);
    }
    public static void registerUser(String username, String password) {
        registeredUsers.put(username, password);
    }

    private void openReceptionPage() {
        new ReceptionPage(); // Instantiate the ReceptionPage
        dispose(); // Close the Login Page
    }
    public static void main(String[] args) {
        // Add some example users for testing
        registerUser("user1", "pass1");
        registerUser("user2", "pass2");
        new LoginPage(); // Create an instance of LoginPage
    }
}
