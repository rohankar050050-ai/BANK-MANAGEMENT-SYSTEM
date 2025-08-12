package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatementt extends JFrame {

    String pinnumber;

    MiniStatementt(String pinnumber) {
        this.pinnumber = pinnumber;

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bankName = new JLabel("INDIAN BANK");
        bankName.setBounds(150, 20, 200, 20);
        bankName.setFont(new Font("System", Font.BOLD, 16));
        add(bankName);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 300, 20);
        add(card);

        JLabel mini = new JLabel();
        mini.setBounds(20, 100, 400, 200);
        add(mini);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 320, 300, 20);
        add(balanceLabel);

        try {
            conn c = new conn();

            // Get card number from login table
            ResultSet rs = c.s.executeQuery("SELECT card_number FROM login WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                String cardNumber = rs.getString("card_number");
                // Mask card number except first 4 and last 4 digits
                card.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }

            // Fetch transactions
            int balance = 0;
            ResultSet rs2 = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            StringBuilder sb = new StringBuilder("<html>");
            while (rs2.next()) {
                sb.append(rs2.getString("date"))
                  .append("&nbsp;&nbsp;&nbsp;")
                  .append(rs2.getString("type"))
                  .append("&nbsp;&nbsp;&nbsp;")
                  .append(rs2.getString("amount"))
                  .append("<br>");

                if (rs2.getString("type").equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(rs2.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs2.getString("amount"));
                }
            }
            sb.append("</html>");
            mini.setText(sb.toString());

            balanceLabel.setText("Your Current Account Balance is Rs " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 400);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatementt("");
    }
}
