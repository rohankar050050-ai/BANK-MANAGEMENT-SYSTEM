package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class transaction extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fastcash, mini, PIN, bal, EXIT;
    String pinnumber;

    transaction(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(160, 390, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("CASH WITHDRAWL");
        withdrawl.setBounds(355, 390, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("FAST CASH");
        fastcash.setBounds(160, 425, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        mini = new JButton("MINI STATEMENT");
        mini.setBounds(355, 425, 150, 30);
        mini.addActionListener(this);
        image.add(mini);

        PIN = new JButton("PIN CHANGE");
        PIN.setBounds(160, 460, 150, 30);
        PIN.addActionListener(this);
        image.add(PIN);

        bal = new JButton("BALANCE ENQUIRY");
        bal.setBounds(355, 460, 150, 30);
        bal.addActionListener(this);
        image.add(bal);

        EXIT = new JButton("EXIT");
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
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new depositt(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new withdrawl(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new fastcash(pinnumber).setVisible(true);
        } else if (ae.getSource() == PIN) {
            setVisible(false);
            new pinchange(pinnumber).setVisible(true);
        } else if (ae.getSource() == mini) {
            new MiniStatementt(pinnumber).setVisible(true);
        } else if (ae.getSource() == bal) {  // ✅ Added BALANCE ENQUIRY
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new transaction("");
    }
}
