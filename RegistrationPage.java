import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public RegistrationPage() {
        // Set the title of the registration window
        setTitle("Registration Page");

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
        panel.setLayout(null); // Set null layout for absolute positioning

        // Create and set up username label and field
        JLabel usernameLabel = new JLabel("New Username:");
        usernameLabel.setBounds(50, 50, 120, 30);
        usernameLabel.setForeground(Color.BLUE);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 50, 150, 30);
        panel.add(usernameField);

        // Create and set up password label and field
        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setBounds(50, 100, 120, 30);
        passwordLabel.setForeground(Color.BLUE);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 100, 150, 30);
        panel.add(passwordField);

        // Create and set up register button
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 150, 100, 40);
        registerButton.setBackground(Color.DARK_GRAY);
        registerButton.setForeground(Color.WHITE);
        panel.add(registerButton);

        // Add action listener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate inputs
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Save registered username and password in LoginPage
                    LoginPage.registerUser(username, password);
                    JOptionPane.showMessageDialog(null, "Registration Successful! You can now log in with your credentials.");
                    new LoginPage();
                    dispose(); // Close the registration page
                }
            }
        });

        // Create and set up back button
        backButton = new JButton("Back");
        backButton.setBounds(50, 150, 80, 40); // Position to the left of the register button
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        // Add buttons to the panel
        panel.add(backButton);
        add(panel);

        // Make frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationPage(); // Create an instance of RegistrationPage
    }
}
