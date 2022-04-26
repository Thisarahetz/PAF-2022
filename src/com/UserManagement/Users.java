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

    /**
     * Read read users
     * @return users table
     */ 
    public String readUsers() 
    { 
        String output = ""; 
        try
    { 
        Connection con = connect(); 
        if (con == null) 
        {return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th>Account ID</th>"
        		+ "<th>is Admin</th>" +
                "<th>First Name</th>" + 
                "<th>Last Name</th>"+
                "<th>Nic</th>" + 
                "<th>Permanent Address</th>" +
                "<th>MobileNumber</th>" + 
                "<th>LandNumber</th>"+
                "<th>Email</th>"+
                "<th>User Password</th>" + 
                "<th>Are Office</th>"+
                "<th>Join Date</th></tr>"; 
        
        //query
        String query = "SELECT * FROM users"; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String accountId  = rs.getString("accountId"); 
            String isAdmin  = rs.getString("isAdmin"); 
            String firstName = rs.getString("firstName"); 
            String lastName = rs.getString("lastName"); 
            String nic = rs.getString("nic"); 
            String permanantAddress  = rs.getString("permanantAddress"); 
            String mobileNumber  = rs.getString("mobileNumber");
            String landNumber  = rs.getString("landNumber"); 
            String email  = rs.getString("email"); 
            String userPassword = rs.getString("userPassword"); 
            String areaoffice = rs.getString("areaoffice"); 
            String joinDate  = rs.getString("joinDate"); 
            
            
            // Add into the html table
            output += "<tr><td>" + accountId + "</td>"; 
            output += "<td>" + isAdmin + "</td>"; 
            output += "<td>" + firstName + "</td>"; 
            output += "<td>" + lastName + "</td>"; 
            output += "<td>" + nic + "</td>"; 
            output += "<td>" + permanantAddress + "</td>";
            output += "<td>" + mobileNumber + "</td>";
            output += "<td>" + landNumber + "</td>"; 
            output += "<td>" + email + "</td>"; 
            output += "<td>" + userPassword + "</td>"; 
            output += "<td>" + areaoffice + "</td>";
            output += "<td>" + joinDate + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the userss."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
    
    public String updateUser(String accountId, String isAdmin, String firstName, String lastName, String nic, String permanantAddress, String mobileNumber,String landNumber,String email,String userPassword,String areaoffice,String joinDate) 
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE users SET accountId=?,isAdmin=?,firstName=?,lastName=?,nic=?,permanantAddress=?,mobileNumber=?,landNumber=?,email=?,userPassword=?,areaoffice=?,joinDate=? WHERE accountId=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
        preparedStmt.setString(1, accountId); 
        preparedStmt.setString(2, isAdmin); 
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
        preparedStmt.setString(13, accountId);
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    output = "Updated successfully"; 
    } 
        catch (Exception e) 
    { 
        output = "Error while updating the users."; 
        System.err.println(e.getMessage()); 
    } 
        return output; 
    } 

    public String deleteUsers(String usersID) 
    { 
	    String output = ""; 
	    try
    { 
	    Connection con = connect(); 
    if (con == null) 
    {	return "Error while connecting to the database for deleting."; } 
    	// create a prepared statement
	    String query = "delete from userss where usersID=?"; 
	    PreparedStatement preparedStmt = con.prepareStatement(query); 
	    // binding values
	    preparedStmt.setInt(1, Integer.parseInt(usersID)); 
	    // execute the statement
	    preparedStmt.execute(); 
	    con.close(); 
	    output = "Deleted successfully"; 
    } 
    catch (Exception e) 
    { 
	    output = "Error while deleting the users."; 
	    System.err.println(e.getMessage()); 
    } 
	    return output; 
    } 
    
    /**
     * remove the users details from the table
     * @param usersId
     * @return
     */
    public String deleteUser(String accountId) 
    { 
	    String output = ""; 
	    try
	    { 
	    	Connection con = connect(); 
		    if (con == null) 
		    	{return "Error while connecting to the database for deleting."; } 
			    // create a prepared statement
			    String query = "delete from users where accountId=?"; 
			    PreparedStatement preparedStmt = con.prepareStatement(query); 
			    // binding values
			    preparedStmt.setString(1, accountId); 
			    // execute the statement
			    preparedStmt.execute(); 
			    con.close(); 
			    output = "Deleted successfully"; 
		    } 
	    catch (Exception e) 
	    { 
		    output = "Error while deleting the users."; 
		    System.err.println(e.getMessage()); 
	    } 
	    return output; 
	    }
   }


