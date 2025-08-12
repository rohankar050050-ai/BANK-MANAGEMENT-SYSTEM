
 
package bank.management.system;//5 steps in java database connectivity 1-register the driver 2-create connection 3-create statement 4-execute query 5-close connectiom

import java.sql.*;


public class conn {//main pupose of conn class is to establish connection with database
    
    Connection c;
    Statement s;
    public conn()//how to create connection
    {
        try //we have to do exception handling as mysql is an external entity therefore chances of getting errors runtime error asbe
        {
           
           c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "tiger");//create connection
 
            s= c.createStatement();
            
            
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

    