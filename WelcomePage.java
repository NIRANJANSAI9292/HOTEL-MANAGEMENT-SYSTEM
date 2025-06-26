import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JButton nextButton;

    public WelcomePage() {
        // Set the title of the window
        setTitle("Welcome Page");

        // Set the size of the window
        setSize(800, 600);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background image
                ImageIcon background = new ImageIcon("first.png"); // Change to your image path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null); // Set null layout for absolute positioning

        // Create a welcome label at the top
        JLabel welcomeLabel = new JLabel("WELCOME TO TAJ HOTEL");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE); // Set text color
        welcomeLabel.setBounds(200, 20, 400, 50); // Set position and size (x, y, width, height)

        // Create a next button
        nextButton = new JButton("Next");
        nextButton.setBounds(350, 400, 100, 40); // Set position and size

        // Add action listener to the button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page when the next button is clicked
                new LoginPage();
                dispose(); // Close the welcome page
            }
        });

        panel.add(welcomeLabel);
        panel.add(nextButton);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomePage(); // Create an instance of WelcomePage
    }
}
