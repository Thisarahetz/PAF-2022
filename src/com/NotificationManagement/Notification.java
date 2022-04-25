package com.NotificationManagement;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Notification {
    //A common method to connect to the DB
    private Connection connect() 
        { 
        	Connection con = null; 
        try
        { 
        	Class.forName("com.mysql.jdbc.Driver"); 
    
    //Provide the correct details: DBServer/DBName, username, password 
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogred", "root", "Shashini@1996"); 
    } 
    catch (Exception e) 
    {e.printStackTrace();
    } 
    return con; 
    } 
    /**
     * 
     * @param accountId
     * @param billId
     * @param amountToBePaiddouble
     * @param email
     * @param mobileNumber
     * @param subject
     * @param massage
     * @param dateNotify
     * @return
     */
    public String insertNotification(String accountId, String billId, Double amountToBePaiddouble,
           String email, String mobileNumber, String subject, String massage, String dateNotify
            )
    { 
        String output = ""; 
       
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                {
                	return "Error while connecting to the database for inserting.";
                } 
                
                //crate uuid for id
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                String datetime = ft.format(dNow);
            
                // create a prepared statement
                String query = " insert into notification (`notificationId`,`accountId`,`billId`,`amountToBePaid`,`email`,`mobileNumber`,`subject`,`massage`,`dateNotify`)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setString(1, "NI"+datetime); 
                preparedStmt.setString(2, accountId); 
                preparedStmt.setString(3, billId); 
                preparedStmt.setDouble(4, amountToBePaiddouble);  
                preparedStmt.setString(5, email); 
                preparedStmt.setString(6, mobileNumber); 
                preparedStmt.setString(7, subject);  
                preparedStmt.setString(8, massage); 
                preparedStmt.setString(9, dateNotify); 

                // execute the statement
                preparedStmt.execute();  
                con.close(); 
                output = "Notification insert successfully"; 
            } 
            catch (Exception e) 
            { 
            output = "Error while inserting the Notification"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 

}

