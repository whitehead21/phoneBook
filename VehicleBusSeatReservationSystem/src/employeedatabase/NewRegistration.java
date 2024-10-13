package employeedatabase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NewRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 // Create an instance of NewRegistration frame
					NewRegistration frame = new NewRegistration();
					// Make the frame visible
					frame.setVisible(true);
				} catch (Exception e) {
					// Print any exceptions that occur
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewRegistration() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MGECW\\Documents\\VAJAA NUST DOCUMENTS\\project\\icon2.png"));
		setTitle("Registration Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(165, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(165, 116, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(165, 175, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(165, 306, 46, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(165, 245, 85, 14);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(165, 82, 329, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 141, 329, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(165, 211, 329, 23);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(165, 329, 329, 23);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 270, 329, 25);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // SQL query to insert data into the usersinfo table
				String query = "INSERT INTO usersinfo VALUES(?,?,?,?,?)";
				try (Connection connection = MysqlConnector.getConnection();
				     PreparedStatement statement = connection.prepareStatement(query)) {

				    statement.setString(1, textField.getText());
				    statement.setString(2, textField_1.getText());
				    statement.setString(3, textField_2.getText());
				    statement.setString(4, passwordField.getText()); // Added missing parenthesis

				    statement.setFloat(5, Integer.parseInt(textField_3.getText())); // Convert text to float

				    
				    // Execute the update
				   int i = statement.executeUpdate();
				  JOptionPane.showConfirmDialog(btnNewButton, i +"Record has been added successfully");
				  statement.close();
				  connection.close();
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(536, 306, 89, 23);
		contentPane.add(btnNewButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(btnNewButton);
		

		
		JButton btnNewButton_1 = new JButton("Reset");//renaming the button
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				// Clear the text in textField
				textField_1.setText("");
				// Clear the text in textField
				textField_2.setText("");
				textField_3.setText("");
				passwordField.setText("");
				// Clear the selection in buttonGroup
				 buttonGroup.clearSelection();
				

			}
		});
		btnNewButton_1.setBounds(536, 403, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("User Registration");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(256, 11, 172, 34);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/users.png")).getImage();
        lblNewLabel_5.setIcon(new ImageIcon(img));
		lblNewLabel_5.setBounds(554, 11, 140, 108);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Enter the last five (5) digits of your ID:");
		lblNewLabel_7.setBounds(286, 60, 254, 20);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Login mainPage=new Login();
             // Make the Login frame visible
                 mainPage.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.setBounds(35, 403, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
