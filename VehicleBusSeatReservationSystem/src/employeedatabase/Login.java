package employeedatabase;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Login {

    protected static final String UserID = null;
	protected static final String Password = null;
	private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        frame.setBounds(100, 100, 768, 482);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("UsersID");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(50, 108, 78, 22);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(50, 154, 80, 22);
        frame.getContentPane().add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(157, 109, 150, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(157, 155, 150, 20);
        frame.getContentPane().add(passwordField);
        
        JLabel lblNewLabel_2 = new JLabel("");
        Image img = new ImageIcon(this.getClass().getResource("/bus4.png")).getImage();
        lblNewLabel_2.setIcon(new ImageIcon(img));
        lblNewLabel_2.setBounds(464, 60, 278, 293);
        frame.getContentPane().add(lblNewLabel_2);
        
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();        // Retrieve the text from the username text field

                String password = new String(passwordField.getPassword()); // Retrieve the password from the password field and convert it to a String
                
                // SQL query to select user information based on the provided username and password
                String query = "SELECT * FROM usersinfo WHERE UserID = ? AND Password = ?";
                // SQL query to select user information based on the provided username and password
                      try (Connection connection = MysqlConnector.getConnection();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                 // Execute the query and get the result set
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                     JOptionPane.showMessageDialog(frame, "Login successful!");
                  // Show a success message
                        frame.dispose(); 
                        // Close the login frame
                       SeatReservation userPage=new SeatReservation(); // Open the SeatReservation page
                        userPage.setVisible(true);
                        }
                        
                    else {
                        JOptionPane.showMessageDialog(frame, "Login failed!");// Show a failure message if no matching user was found

                    }
                } catch (SQLException ex) {
                	// Print the stack trace for the exception
                    ex.printStackTrace();
                 // Show an error message if an exception occurs
                    JOptionPane.showMessageDialog(frame, "An error occurred during login.");
                }
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnNewButton.setBounds(185, 220, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Driver");
     // Add an ActionListener to the button to handle click events
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	frame.dispose(); 
        	DriverLogin driverlogin= new DriverLogin();
             driverlogin.setVisible(true);
        	}
        });
        btnNewButton_1.setBounds(25, 330, 89, 23);
        frame.getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Admin");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
        		AdminLogin adminlogin= new AdminLogin();
                adminlogin.setVisible(true);
        	}
        });
        btnNewButton_2.setBounds(25, 389, 89, 23);
        frame.getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Create an Account");
        btnNewButton_3.setFont(new Font("Arial Nova", Font.ITALIC, 11));
        btnNewButton_3.setBackground(Color.CYAN);
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose(); 
        		NewRegistration newRegristration= new NewRegistration();
                newRegristration.setVisible(true);
        		}
        });
        btnNewButton_3.setBounds(270, 186, 150, 23);
        frame.getContentPane().add(btnNewButton_3);
        
    
        JLabel lblNewLabel_3 = new JLabel("Vehicle/Bus Seat \r\nReservation System");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Brush Script MT", Font.BOLD, 35));
        lblNewLabel_3.setBounds(67, 11, 600, 38);
        frame.getContentPane().add(lblNewLabel_3);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
    	   // Schedule a job for the event-dispatching thread
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	 // Create a new instance of the Login class
                    Login window = new Login();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                	 // Print the stack trace for the exception
                    e.printStackTrace();
                }
            }
        });
    }

	public void setVisible(boolean b) {
		 // Create a new instance of the Login class
		 Login window = new Login();
         window.getFrame().setVisible(true);
		
	}
}
