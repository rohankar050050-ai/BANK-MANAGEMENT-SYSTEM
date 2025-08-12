package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class fastcash extends JFrame implements ActionListener { 
    JButton deposit, withdrawl, fastcash, mini, PIN, bal, EXIT;
    String pinnumber;

    fastcash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        deposit = new JButton("Rs 100");
        deposit.setBounds(160, 390, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdrawl = new JButton("Rs 500");
        withdrawl.setBounds(355, 390, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);
        
        fastcash = new JButton("Rs 1000");
        fastcash.setBounds(160, 425, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        mini = new JButton("Rs 2000");
        mini.setBounds(355, 425, 150, 30);
        mini.addActionListener(this);
        image.add(mini);
        
        PIN = new JButton("Rs 5000");
        PIN.setBounds(160, 460, 150, 30);
        PIN.addActionListener(this);
        image.add(PIN);
        
        bal = new JButton("Rs 10000");
        bal.setBounds(355, 460, 150, 30);
        bal.addActionListener(this);
        image.add(bal);
        
        EXIT = new JButton("BACK");
        EXIT.setBounds(355, 495, 150, 30);
        EXIT.addActionListener(this);
        image.add(EXIT);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == EXIT) {
            setVisible(false);
            new transaction(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3); // "Rs 500" → "500"
            conn c = new conn();
            try {
                // FIXED: Correct column name 'pin'
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                // FIXED: Correct column name 'pin'
                String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" 
                               + pinnumber + "', '" + date + "', 'withdrawal', '" + amount + "')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");
                setVisible(false);
                new transaction(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new fastcash("");
    }
}
