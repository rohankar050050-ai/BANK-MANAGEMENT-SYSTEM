
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;




    public class signup1 extends JFrame implements ActionListener{
    long random;
    JTextField nameTextField,fnameTextField,dobTextField,emailTextField,addressTextField,CityTextField,StateTextField,PincodeTextField;
    JButton next;
    JRadioButton male,female,married,unmarried;
    JDateChooser DateChooser;
    
    signup1()
    {
        setLayout(null);
        
        Random ran = new Random();// to get random number for application number
         random =(Math.abs(ran.nextLong() % 9000L) + 1000L);//to get big random no
        
        JLabel formno = new JLabel("APPLICATION FORM NO "+ random );//ctrating the labels
        formno.setFont(new Font("Railway", Font.BOLD,38));
        formno.setBounds(140, 20,600,40);
        add(formno);
        
        JLabel personaldetails = new JLabel("Page 1 : Personal Details" );
        personaldetails.setFont(new Font("Railway", Font.BOLD,22));
        personaldetails.setBounds(290, 80,400,30);
        add(personaldetails);
        
        JLabel name = new JLabel("Name : " );
        name.setFont(new Font("Railway", Font.BOLD,20));
        name.setBounds(100, 140,100,30);
        add(name);
        
         nameTextField = new JTextField();//creating text fields
        nameTextField.setFont(new Font("Railway", Font.BOLD,14));
        nameTextField.setBounds(300,140,400,30);
        add(nameTextField);
        
        JLabel fname = new JLabel("Father 's Name : " );
        fname.setFont(new Font("Railway", Font.BOLD,20));
        fname.setBounds(100, 190,200,30);
        add(fname);
        
         fnameTextField = new JTextField();//creating text fields
        fnameTextField.setFont(new Font("Railway", Font.BOLD,14));
        fnameTextField.setBounds(300,190,400,30);
        add(fnameTextField);
        
        JLabel dob = new JLabel("Date of Birth : " );
        dob.setFont(new Font("Railway", Font.BOLD,20));
        dob.setBounds(100, 240,200,30);
        add(dob);
        
         DateChooser = new JDateChooser();
        DateChooser.setBounds(300,240,200,30);
        add(DateChooser);
        
       
        
         JLabel gender = new JLabel("Gender : " );
        gender.setFont(new Font("Railway", Font.BOLD,20));
        gender.setBounds(100, 290,200,30);
        add(gender);
        
         male = new JRadioButton("MALE");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);
        
        
        
          female = new JRadioButton("FEMALE");
         female.setBounds(450, 290, 80, 30);
          female.setBackground(Color.WHITE);
        add(female);
        
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
         gendergroup.add(female);
//to prevent male female selectiong both together
        
        
        
        
         JLabel email = new JLabel("Email Address : " );
        email.setFont(new Font("Railway", Font.BOLD,20));
        email.setBounds(100, 340,200,30);
        add(email);
        
         emailTextField = new JTextField();//creating text fields
        emailTextField.setFont(new Font("Railway", Font.BOLD,14));
        emailTextField.setBounds(300,340,400,30);
        add(emailTextField);
        
        JLabel marital = new JLabel("Marital Status : " );
        marital.setFont(new Font("Railway", Font.BOLD,20));
        marital.setBounds(100, 390,200,30);
        add(marital);
        
       
        
        
         married = new JRadioButton("MARRIED");
married.setBounds(300, 390, 100, 30);
married.setBackground(Color.WHITE);
add(married);

 unmarried = new JRadioButton("UNMARRIED");
unmarried.setBounds(450, 390, 120, 30);
unmarried.setBackground(Color.WHITE);
add(unmarried);

// Group them
ButtonGroup marryGroup = new ButtonGroup();
marryGroup.add(married);
marryGroup.add(unmarried);
         
//to prevent male female selectiong both together
        
        
        JLabel address = new JLabel("Address : " );
        address.setFont(new Font("Railway", Font.BOLD,20));
        address.setBounds(100, 440,200,30);
        add(address);
        
         addressTextField = new JTextField();//creating text fields
        addressTextField.setFont(new Font("Railway", Font.BOLD,14));
        addressTextField.setBounds(300,440,400,30);
        add(addressTextField);
        
        JLabel City  = new JLabel("City : " );
        City .setFont(new Font("Railway", Font.BOLD,20));
        City .setBounds(100, 490,200,30);
        add(City );
        
         CityTextField = new JTextField();//creating text fields
        CityTextField.setFont(new Font("Railway", Font.BOLD,14));
        CityTextField.setBounds(300,490,400,30);
        add(CityTextField);
        
        JLabel State  = new JLabel("State : " );
        State .setFont(new Font("Railway", Font.BOLD,20));
        State.setBounds(100, 540,200,30);
        add(State );
        
         StateTextField = new JTextField();//creating text fields
        StateTextField.setFont(new Font("Railway", Font.BOLD,14));
        StateTextField.setBounds(300,540,400,30);
        add(StateTextField);
        
         JLabel Pincode  = new JLabel("Pincode : " );
        Pincode .setFont(new Font("Railway", Font.BOLD,20));
        Pincode.setBounds(100, 590,200,30);
        add(Pincode );
        
         PincodeTextField = new JTextField();//creating text fields
        PincodeTextField.setFont(new Font("Railway", Font.BOLD,14));
        PincodeTextField.setBounds(300,590,400,30);
        add(PincodeTextField);
        next = new JButton("Next");
next.setBackground(Color.BLACK);
next.setForeground(Color.WHITE);
next.setFont(new Font("Raleway", Font.BOLD, 14));
next.setBounds(620, 660, 80, 30);
next.addActionListener(this);
add(next);

        
  
  
        getContentPane().setBackground(Color.white);//change color of the entire page
       
        setSize(850 ,800);
        setLocation(350,10);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
     String formno = "" + random;
     String name = nameTextField.getText();//get vales from textfield to databases
     String fname = fnameTextField.getText();
     String dob= ((JTextField)DateChooser.getDateEditor().getUiComponent()).getText();
     String gender= null;
     if(male.isSelected())
     {
         gender = "Male";
     }
     else if(female.isSelected())
     {
         gender= "Female";
         
     }
     String email=emailTextField.getText();
     String marital = null;
     if(married.isSelected())
     {
         marital = "Married";
     }
     else if(unmarried.isSelected())
     {
         marital= "Unmarried";
         
     }
     String address=addressTextField.getText();
     String city=CityTextField.getText();
     String state=StateTextField.getText();
     String pin=PincodeTextField.getText();
     
     try {
         if(name.equals("")){
             JOptionPane.showMessageDialog(null, "Name Is Required");
         }
         else{
             conn c= new conn();// to run the query in mysql
            
            String query = "insert into signup values('"+formno+"', '"+name+"','"+fname+"', '"+dob+"', '"+gender+"', '"+email+"', '"+marital+"', '"+address+"', '"+city+"', '"+pin+"', '"+state+"')";

             c.s.executeUpdate(query);
             
             setVisible(false);
             new signup2(formno).setVisible(true);

         }
         
     } catch (Exception e) {
         System.out.println(e);
     }
     
    }
    

   
    public static void main(String args[]) 
    {
       new signup1();  
    }
}
