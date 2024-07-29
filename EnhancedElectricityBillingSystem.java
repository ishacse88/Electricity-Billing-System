import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EnhancedElectricityBillingSystem extends JFrame {
    private JTextField customerIDField;
    private JTextField customerNameField;
    private JTextField unitField;
    private JTextArea billArea;

    private HashMap<String, Customer> customers;

    public EnhancedElectricityBillingSystem() {
        customers = new HashMap<>();

        setTitle("Electricity Billing System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // background image
        

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(204, 229, 255));

        // Create a panel for content
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(10, 10, 465, 340);
        contentPane.setBackground(new Color(255, 255, 255, 150));
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        add(contentPane);

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDLabel.setBounds(30, 30, 100, 30);
        contentPane.add(customerIDLabel);

        customerIDField = new JTextField();
        customerIDField.setBounds(150, 30, 280, 30);
        contentPane.add(customerIDField);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setBounds(30, 70, 100, 30);
        contentPane.add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(150, 70, 280, 30);
        contentPane.add(customerNameField);

        JLabel unitLabel = new JLabel("Units:");
        unitLabel.setBounds(30, 110, 100, 30);
        contentPane.add(unitLabel);

        unitField = new JTextField();
        unitField.setBounds(150, 110, 280, 30);
        contentPane.add(unitField);

        JButton okButton = new JButton("Generate Bill");
        okButton.setBounds(150, 150, 130, 30);
        okButton.setBackground(new Color(0, 153, 76));
        okButton.setForeground(Color.WHITE);
        okButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 51), 2));
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        contentPane.add(okButton);

        JButton printButton = new JButton("Print");
        printButton.setBounds(300, 150, 130, 30);
        printButton.setBackground(new Color(0, 153, 76));
        printButton.setForeground(Color.WHITE);
        printButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 51), 2));
        printButton.setFocusPainted(false);
        contentPane.add(printButton);

        billArea = new JTextArea();
        billArea.setBounds(30, 200, 400, 120);
        billArea.setEditable(false);
        contentPane.add(billArea);
    }

    private void generateBill() {
        try {
            String customerID = customerIDField.getText();
            String customerName = customerNameField.getText();
            int units = Integer.parseInt(unitField.getText());

            if (customerID.isEmpty() || customerName.isEmpty() || units < 0) {
                JOptionPane.showMessageDialog(this, "Please fill all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = calculateBill(units);

            Customer customer = new Customer(customerID, customerName, units, amount);
            customers.put(customerID, customer);

            billArea.setText("Electricity Billing System\n"
                    + "Bill No : " + customerID + "\n"
                    + "Customer : " + customerName + "\n"
                    + "Unit : " + units + "\n"
                    + "Amount : " + amount + "\n"
                    + "Thank you, Come Again");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for units.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateBill(int units) {
        double rate = 2.8;  // Example rate per unit
        return units * rate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EnhancedElectricityBillingSystem().setVisible(true);
        });
    }
}


   