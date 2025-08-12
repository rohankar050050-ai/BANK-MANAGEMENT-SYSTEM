
package bank.management.system;

import javax.swing.*;//to cretae the frame we use swing and we are importing the swing package
import java.awt.*;
import java.awt.event.*;//to implement actionlistener
import java.sql.*;

public class Login extends JFrame implements ActionListener// action listner tell you that yes button is clicked signin button signup buttonjframe is a class o swing which we need to import from swing package
{
    JButton login,signup,clear;//globally defining
    JTextField cardTextField;
    JPasswordField pinTextField;//password filed is used to hide the pin from user
    Login() {
        setTitle("AUTOMATED TELLER MACHINE");//USED TO SET TITLE
        
        setLayout(null);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));//used to load the image from its location
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);//used to scale the image small
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10,100, 100);//used to set the location of the image
        add(label);//used to place the image in the frame
        
        JLabel text = new JLabel("WELCOME TO ATM");//add text to the frame
        text.setFont(new Font ("osward",Font.BOLD,38));//change the font
        text.setBounds(200,40,400,40);
        add(text);
        
        
        
        
        JLabel cardno = new JLabel("CARD NO:");//add text to the frame
        cardno.setFont(new Font ("Railway",Font.BOLD,28));//change the font
        cardno.setBounds(120,150,150,30);
        add(cardno);
        
         cardTextField = new JTextField();//used to set box beside cardno
        cardTextField.setBounds(300, 150, 250, 30);
        cardTextField .setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);
        
        JLabel pin = new JLabel("PIN:");//add text to the frame
        pin.setFont(new Font ("osward",Font.BOLD,38));//change the font
        pin.setBounds(120,220,230,40);
        add(pin);
        
         pinTextField = new JPasswordField();//used to set box beside cardno
        pinTextField.setBounds(300, 220, 250, 30);
        pinTextField .setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);
        
         login = new JButton ("SIGN IN");//use to create sign in button
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);//this function tell that yes login button is clicked
        add(login);
        
         clear = new JButton ("CLEAR");//use to create sign in button
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);//this function tell that yes login button is clicked
        add(clear);
        
         signup = new JButton ("SIGN IN");//use to create sign in button
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);//this function tell that yes login button is clicked
        add(signup);
        
        
        getContentPane().setBackground(Color.white);//set the color of the frame
        
        
        setSize(800,480);//function use to set the size of frame
        setVisible(true);
        setLocation(350,200);//location change of the frame
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource()== clear)//check which button is clicked
        {
          cardTextField.setText("");
          pinTextField.setText("");//the textfield willget empty on clicking clear button
        }
        else if (ae.getSource()== login)
                {
                    conn conn = new conn();
                    String cardnumber= cardTextField.getText();
                    String pinnumber=pinTextField.getText();
                    String query = " select * from  login where card_number= '"+cardnumber+"'and  pin = '"+pinnumber+"'";
                    try{
                        ResultSet rs= conn.s.executeQuery(query);
                        if(rs.next()){
                            setVisible(false);
                            new transaction(pinnumber).setVisible(true);
                            
                        }else{
                            JOptionPane.showMessageDialog(null,"INCORRECT CARD NUMBER OR PIN");
                        }
                    }catch (Exception e) {
                        System.out.print(e);
                    }
                }
        else if (ae.getSource()== signup)
        {
            setVisible(false);
            new signup1().setVisible(true);
            
        }
                
    }

    
    public static void main(String args[]) {
        new Login();
    }
}
