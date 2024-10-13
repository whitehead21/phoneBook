package employeedatabase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeatReservation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField seatNumberField;
    private JLabel messageLabel;
    private JComboBox<String> vehicleTypeComboBox;
    private JComboBox<String> specificVehicleComboBox;
    private Map<String, Map<Integer, Boolean>> bookingData = new HashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SeatReservation frame = new SeatReservation();
                frame.setVisible(true);
            } catch (Exception e) {
                // Print stack trace if an exception occurs
                e.printStackTrace();
            }
        });
    }

    public SeatReservation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 510);
        contentPane = new JPanel();
        contentPane.setBackground(Color.CYAN);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblVehicleType = new JLabel("Choose vehicle type:");
        lblVehicleType.setBounds(205, 118, 150, 14);
        contentPane.add(lblVehicleType);

        vehicleTypeComboBox = new JComboBox<>(new String[]{"Car", "Bus"});
        vehicleTypeComboBox.setBounds(423, 115, 150, 20);
        contentPane.add(vehicleTypeComboBox);

        JLabel lblSpecificVehicle = new JLabel("Choose specific vehicle:");
        lblSpecificVehicle.setBounds(205, 172, 150, 14);
        contentPane.add(lblSpecificVehicle);

        specificVehicleComboBox = new JComboBox<>();
        specificVehicleComboBox.setBounds(372, 169, 200, 20);
        contentPane.add(specificVehicleComboBox);
        vehicleTypeComboBox.addActionListener(e -> populateSpecificVehicleOptions());

        JLabel lblEnterSeatNumber = new JLabel("Enter seat number (or 0 for full ride):");
        lblEnterSeatNumber.setBounds(205, 226, 250, 14);
        contentPane.add(lblEnterSeatNumber);

        seatNumberField = new JTextField();
        seatNumberField.setBounds(487, 223, 86, 20);
        contentPane.add(seatNumberField);
        seatNumberField.setColumns(10);

        JButton bookButton = new JButton("Book");
        bookButton.setBounds(145, 279, 100, 23);
        contentPane.add(bookButton);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.setBounds(462, 279, 130, 23);
        contentPane.add(cancelButton);

        JButton viewBookedSeatsButton = new JButton("View Booked Seats");
        viewBookedSeatsButton.setBounds(462, 356, 150, 23);
        contentPane.add(viewBookedSeatsButton);

        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setBounds(123, 356, 150, 23);
        contentPane.add(checkAvailabilityButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(295, 409, 150, 23);
        contentPane.add(exitButton);

        messageLabel = new JLabel(""); // Corrected here
        messageLabel.setBounds(10, 301, 400, 53);
        contentPane.add(messageLabel);

        JLabel lblNewLabel = new JLabel("");
        Image img = new ImageIcon(this.getClass().getResource("/busss.png")).getImage();
        lblNewLabel.setIcon(new ImageIcon(img));
        lblNewLabel.setBounds(10, 30, 170, 156);
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
        btnNewButton.setBounds(10, 421, 86, 23);
        contentPane.add(btnNewButton);

        bookButton.addActionListener(e -> bookSeats());
        cancelButton.addActionListener(e -> cancelBooking());
        viewBookedSeatsButton.addActionListener(e -> viewBookedSeats());
        checkAvailabilityButton.addActionListener(e -> checkAvailability());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void populateSpecificVehicleOptions() {
        // Get the selected vehicle type

        String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
        specificVehicleComboBox.removeAllItems();
     // Populate specific vehicle options based on the selected vehicle type

        if (vehicleType.equals("Car")) {
            specificVehicleComboBox.addItem("Sedan (4 seats)");
            specificVehicleComboBox.addItem("7-seat SUV (6 seats)");
        } else if (vehicleType.equals("Bus")) {
            specificVehicleComboBox.addItem("Standard Coach Bus (55 seats)");
            specificVehicleComboBox.addItem("Mini Bus (30 seats)");
            specificVehicleComboBox.addItem("Shuttle Bus (30 seats)");
            specificVehicleComboBox.addItem("School Bus (72 seats)");
        }

        // Clear previous booking data when vehicle type changes
        bookingData.clear();
    }

    private void initializeBookingData() {
    	// Get the selected specific vehicle
        String specificVehicle = (String) specificVehicleComboBox.getSelectedItem();
        if (specificVehicle != null) {
        	  // Extract vehicle key and total seats from the selected specific vehicle
            String vehicleKey = specificVehicle.split(" \\(")[0];
            int totalSeats = Integer.parseInt(specificVehicle.replaceAll("[^0-9]", ""));
         // Initialize booking data for the selected vehicle
            bookingData.putIfAbsent(vehicleKey, new HashMap<>());
            Map<Integer, Boolean> seats = bookingData.get(vehicleKey);
         // Mark all seats as available
            for (int i = 1; i <= totalSeats; i++) {
                seats.putIfAbsent(i, false);
            }
        }
    }

    private void bookSeats() {
        String specificVehicle = (String) specificVehicleComboBox.getSelectedItem();
        if (specificVehicle == null) {
            messageLabel.setText("Please select a vehicle first.");
            return;
        }

        initializeBookingData(); // Call this here to ensure booking data is initialized

        String vehicleKey = specificVehicle.split(" \\(")[0];
        Map<Integer, Boolean> seats = bookingData.get(vehicleKey);
        if (seats == null) {
            messageLabel.setText("No booking data found for this vehicle.");
            return;
        }
        try {
            int seatNumber = Integer.parseInt(seatNumberField.getText());

            if (seatNumber == 0) {
            	// Book all seats if the input is 0
                for (int seat : seats.keySet()) {
                    seats.put(seat, true);
                }
             // Display a success message for booking all seats
                messageLabel.setText("All " + seats.size() + " seats have been successfully booked.");
            } else if (seatNumber < 1 || seatNumber > seats.size()) {
                messageLabel.setText("Invalid seat number! Choose between 1 and " + seats.size());
            } else if (seats.get(seatNumber)) {
                messageLabel.setText("Seat " + seatNumber + " is already booked.");
            } else {
                seats.put(seatNumber, true);
                messageLabel.setText("Seat " + seatNumber + " has been successfully booked.");
            }
        } catch (NumberFormatException e) {
        	// Display an error message if the input is not a valid numbers
            messageLabel.setText("Please enter a valid seat number.");
        }
    }

    private void cancelBooking() {
        String specificVehicle = (String) specificVehicleComboBox.getSelectedItem();
        if (specificVehicle == null) {
            messageLabel.setText("Please select a vehicle first.");
            return;
        }

        String vehicleKey = specificVehicle.split(" \\(")[0];
        Map<Integer, Boolean> seats = bookingData.get(vehicleKey);
        if (seats == null) {
            messageLabel.setText("No booking data found for this vehicle.");
            return;
        }

        try {
            int seatNumber = Integer.parseInt(seatNumberField.getText());

            if (seatNumber < 1 || seatNumber > seats.size()) {
                messageLabel.setText("Invalid seat number! Enter between 1 and " + seats.size() + ".");
            } else if (!seats.get(seatNumber)) {
                messageLabel.setText("Seat " + seatNumber + " is not booked.");
            } else {
                seats.put(seatNumber, false);
                messageLabel.setText("Booking for Seat " + seatNumber + " has been successfully cancelled.");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid seat number.");
        }
    }

    private void viewBookedSeats() {
        // Logic to display booked seats goes here
        messageLabel.setText("Viewing booked seats feature is not implemented yet.");
    }

    private void checkAvailability() {
        // Logic to check seat availability goes here
        messageLabel.setText("Checking availability feature is not implemented yet.");
    }
}
 