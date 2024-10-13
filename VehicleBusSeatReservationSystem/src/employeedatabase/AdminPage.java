package employeedatabase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.SwingConstants;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 495);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("welcome\r\nAdmin!!!");
		lblNewLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 36));
		lblNewLabel.setBounds(27, 11, 215, 74);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Login mainPage=new Login();
                 mainPage.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBounds(27, 409, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Create a driver account!");
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(37, 65, 186, 48);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(250, 119, 342, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("driverID");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_2.setBounds(175, 113, 67, 28);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(250, 175, 342, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(250, 225, 342, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(250, 326, 139, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(169, 175, 73, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Surname");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(167, 224, 73, 17);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(153, 275, 95, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(175, 322, 67, 23);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "INSERT INTO driversinfo VALUES(?,?,?,?,?)";
				try (Connection connection = MysqlConnector.getConnection();
				     PreparedStatement statement = connection.prepareStatement(query)) {
					  statement.setString(1, textField.getText());
					    statement.setString(2, textField_1.getText());
					    statement.setString(3, textField_2.getText());
					    statement.setString(4, passwordField.getText()); // Added missing parenthesis

					    statement.setFloat(5, Integer.parseInt(textField_4.getText())); // Convert text to float
					    // Execute the update
						   int i = statement.executeUpdate();
						  JOptionPane.showConfirmDialog(btnNewButton_1, i +"Record has been added successfully");
						  statement.close();
						  connection.close();
						} catch (SQLException e1) {
						    e1.printStackTrace();
						}

					}

				
			
		});
		btnNewButton_1.setBounds(503, 409, 89, 23);
		contentPane.add(btnNewButton_1);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 279, 342, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_4.setText("");
				passwordField.setText("");
				buttonGroup.clearSelection();
			}
		});
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.setBounds(503, 325, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
