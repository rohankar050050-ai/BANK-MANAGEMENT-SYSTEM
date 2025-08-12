package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pinnumber;
    JButton back;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel();
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 35);
        image.add(text);

        int balance = 0;
        try {
            conn c = new conn();
            String query = "SELECT * FROM bank WHERE pin = '" + pinnumber + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                String type = rs.getString("type").trim();
                int amount = Integer.parseInt(rs.getString("amount").trim());

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("Withdrawl") 
                        || type.equalsIgnoreCase("Withdrawal") 
                        || type.equalsIgnoreCase("Fast Cash")) {
                    balance -= amount;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        text.setText("Your Current Account Balance is Rs " + balance);

        back = new JButton("BACK");
        back.setBounds(355, 495, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new transaction(pinnumber).setVisible(true);
    }

    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}
