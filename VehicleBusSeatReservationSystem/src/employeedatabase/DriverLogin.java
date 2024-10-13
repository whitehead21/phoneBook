package employeedatabase;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * DriverLogin class for handling driver login functionality.
 */
public class DriverLogin extends JFrame {
	 protected static final String driverID = null;
		protected static final String Password = null;
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
					DriverLogin frame = new DriverLogin();
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
	public DriverLogin() {
		setTitle(" login page");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MGECW\\Documents\\VAJAA NUST DOCUMENTS\\project\\icon2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Driver Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Brush Script MT", Font.BOLD, 31));
		lblNewLabel.setBounds(171, 11, 315, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("driverID");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(326, 175, 115, 22);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(441, 176, 169, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(339, 246, 102, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driverID = textField.getText();
                String Password = new String(passwordField.getPassword());
                String query = "SELECT * FROM driversinfo WHERE driverID = ? AND Password = ?";
             // Use a try-with-resources statement to ensure the connection and statement are closed automatically
                try (Connection connection = MysqlConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(query)) {
                	// Set the parameters for the prepared statement 
                	statement.setString(1, driverID);
                     statement.setString(2, Password);
                     
                     // Execute the query and get the result set
                     ResultSet resultSet = statement.executeQuery();
                     // Check if a matching record was found
                     if (resultSet.next()) {
                    	// Display a success message if login is successful
                         JOptionPane.showMessageDialog(contentPane, "Login successful!");
                      }
                     else {
                    	// Display an error message if login fails
                         JOptionPane.showMessageDialog(contentPane, "Login failed!");
                    }
                 } catch (SQLException ex) {
                	// Print the stack trace for debugging purposes
                     ex.printStackTrace();
                     // Display an error message if an exception occurs during the login process
                     JOptionPane.showMessageDialog(contentPane, "An error occurred during login.");
                        }
		
					
				}
                 
		});
		btnNewButton.setFont(new Font("Californian FB", Font.PLAIN, 18));
		btnNewButton.setBounds(352, 372, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(441, 246, 169, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("");
		 Image img = new ImageIcon(this.getClass().getResource("/bus4.png")).getImage();
	     lblNewLabel_3.setIcon(new ImageIcon(img));
		lblNewLabel_3.setBounds(31, 69, 285, 276);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                 Login mainPage=new Login();
                  mainPage.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(64, 374, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
