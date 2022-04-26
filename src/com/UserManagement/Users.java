package com.UserManagement;
import java.sql.*;
public class Users {
    //A common method to connect to the DB
    private Connection connect() 
        { 
        Connection con = null; 
        try
        { 
        Class.forName("com.mysql.jdbc.Driver"); 
    
    //Provide the correct details: DBServer/DBName, username, password 
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogred", "root", "1234"); 
    } 
    catch (Exception e) 
    {e.printStackTrace();} 
    return con; 
    } 
   
    public String registerUser(boolean isAdmin, String firstName, String lastName, String nic,
           String permanantAddress, String mobileNumber, String landNumber, String email, String userPassword,
            String areaoffice, String joinDate)
    { 
        String output = ""; 
       
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                {return "Error while connecting to the database for inserting."; } 
                // create a prepared statement
                String query = " insert into users (`accountID`,`isAdmin`,`firstName`,`lastName`,`nic`,`permanantAddress`,`mobileNumber`,`landNumber`,`email`,`userPassword`,`areaoffice`,`joinDate`)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setString(1, "AN"+nic.substring(0,6)); 
                preparedStmt.setBoolean(2, isAdmin); 
                preparedStmt.setString(3, firstName); 
                preparedStmt.setString(4, lastName);  
                preparedStmt.setString(5, nic); 
                preparedStmt.setString(6, permanantAddress); 
                preparedStmt.setString(7, mobileNumber);  
                preparedStmt.setString(8, landNumber); 
                preparedStmt.setString(9, email); 
                preparedStmt.setString(10, userPassword);  
                preparedStmt.setString(11, areaoffice);
                preparedStmt.setString(12, joinDate); 

                // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                output = "User Register successfully"; 
            } 
            catch (Exception e) 
            { 
            output = "Error while inserting the user"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 

}

