package employeedatabase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		setTitle("login page");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MGECW\\Documents\\VAJAA NUST DOCUMENTS\\project\\icon2.png"));
		// Set the default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("Brush Script MT", Font.BOLD, 31));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(209, 25, 232, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("AdminID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(313, 179, 110, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(313, 250, 110, 26);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(429, 179, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(429, 256, 158, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Retrieve the AdminID and Password from the text fields
			String AdminID = textField.getText();
            String Password = new String(passwordField.getPassword());
            
            // SQL query to select admin information based on the provided AdminID and Password
            String query = "SELECT * FROM asmininfo WHERE AdminID = ? AND Password = ?";
           
            // Try-with-resources statement to ensure the connection and statement are closed automatically
                  try (Connection connection = MysqlConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                	// Set the AdminID parameter in the SQL query
                statement.setString(1, AdminID);
             // Set the Password parameter in the SQL query
                statement.setString(2, Password);
                
                // Execute the query and get the result set
                ResultSet resultSet = statement.executeQuery();
             // Check if a matching admin was found
                if (resultSet.next()) {
                	  // Show a success message
                    JOptionPane.showMessageDialog(contentPane, "Login successful!");
                    // Close the current frame
                    dispose(); 
                 // Open the AdminPage
            		AdminPage adminpage= new AdminPage();
                    adminpage.setVisible(true);
                  
                 }
                    
                else {
                	 // Show a failure message if no matching admin was found
                    JOptionPane.showMessageDialog(contentPane, "Login failed!");
                }
            } catch (SQLException ex) {
            	// Print the stack trace for the exception
                ex.printStackTrace();
             // Show an error message if an exception occurs
                JOptionPane.showMessageDialog(contentPane, "An error occurred during login.");
            }
        }
			
		});
		btnNewButton.setFont(new Font("Californian FB", Font.PLAIN, 18));
		btnNewButton.setBounds(352, 386, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bus4.png")).getImage();
        lblNewLabel_3.setIcon(new ImageIcon(img));
		lblNewLabel_3.setBounds(10, 106, 273, 253);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Login mainPage=new Login();
                 mainPage.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(28, 388, 89, 23);
		contentPane.add(btnNewButton_1);
		
	
	}
}
