package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class pinchange extends JFrame implements ActionListener {

    JPasswordField oldPinTextField, newPinTextField, rePinTextField;
    JButton change, back;
    String pinnumber;

    pinchange(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(250, 250, 500, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        JLabel oldpintext = new JLabel("Current PIN:");
        oldpintext.setBounds(165, 290, 180, 25);
        oldpintext.setForeground(Color.WHITE);
        oldpintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(oldpintext);

        oldPinTextField = new JPasswordField();
        oldPinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        oldPinTextField.setBounds(330, 290, 180, 25);
        image.add(oldPinTextField);

        JLabel newpintext = new JLabel("New PIN:");
        newpintext.setBounds(165, 330, 180, 25);
        newpintext.setForeground(Color.WHITE);
        newpintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(newpintext);

        newPinTextField = new JPasswordField();
        newPinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        newPinTextField.setBounds(330, 330, 180, 25);
        image.add(newPinTextField);

        JLabel repintext = new JLabel("Re-enter New PIN:");
        repintext.setBounds(165, 370, 180, 25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);

        rePinTextField = new JPasswordField();
        rePinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        rePinTextField.setBounds(330, 370, 180, 25);
        image.add(rePinTextField);

        change = new JButton("CHANGE");
        change.setBounds(355, 410, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 445, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String oldpin = oldPinTextField.getText();
                String newpin = newPinTextField.getText();
                String repin = rePinTextField.getText();

                if (oldpin.equals("") || newpin.equals("") || repin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                if (!newpin.equals(repin)) {
                    JOptionPane.showMessageDialog(null, "New PIN entries do not match");
                    return;
                }

                conn c = new conn();

                // Verify old PIN
                String checkQuery = "SELECT * FROM login WHERE pin = '" + oldpin + "'";
                ResultSet rs = c.s.executeQuery(checkQuery);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Current PIN is incorrect");
                    return;
                }

                // Update PIN in all relevant tables
                c.s.executeUpdate("UPDATE bank SET pin = '" + newpin + "' WHERE pin = '" + oldpin + "'");
                c.s.executeUpdate("UPDATE login SET pin = '" + newpin + "' WHERE pin = '" + oldpin + "'");
                c.s.executeUpdate("UPDATE signup3 SET pin = '" + newpin + "' WHERE pin = '" + oldpin + "'");

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new transaction(newpin).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new pinchange("").setVisible(true);
    }
}
