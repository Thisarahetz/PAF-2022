package com.billManagement;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    //A common method to connect to the DB
	    private Connection connect() 
	        { 
                Connection con = null; 
                try
	        { 
	            Class.forName("com.mysql.jdbc.Driver"); 
	    
	    //Provide the correct details: DBServer/DBName, username, password 
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogred", "root", "12345"); 
    } 
    catch (Exception e) 
	        {e.printStackTrace();} 
    return con; 
    } 
    /**
     * add bill
     * @param accountId
     * @param joinDate
     * @param meterReadingBeforeDate
     * @param meterReadingBefore
     * @param meterReadingNowDate
     * @param meterReadingNow
     * @param noOfUntitsConsumed
     * @param chargeforelectricityConsume
     * @param adjustments
     * @param totalAmountDue
     * @param billDate
     * @return
     */
    public String addBill(String accountId, String joinDate, String meterReadingBeforeDate,
    String meterReadingBefore, String meterReadingNowDate, String meterReadingNow, String noOfUntitsConsumed, String chargeforelectricityConsume,
            String adjustments, Double totalAmountDue, String billDate ) 
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
                String query = "insert into bill(`billId`,`accountId`,`joinDate`,`meterReadingBeforeDate`,`meterReadingBefore`,`meterReadingNowDate`,`meterReadingNow`,`noOfUntitsConsumed`,`chargeforelectricityConsume`,`adjustments`,`totalAmountDue`,`billDate`)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setString(1, "BI"+datetime); 
                preparedStmt.setString(2, accountId); 
                preparedStmt.setString(3, joinDate); 
                preparedStmt.setString(4, meterReadingBeforeDate);  
                preparedStmt.setString(5, meterReadingBefore); 
                preparedStmt.setString(6, meterReadingNowDate); 
                preparedStmt.setString(7, meterReadingNow);  
                preparedStmt.setString(8, noOfUntitsConsumed); 
                preparedStmt.setString(9, chargeforelectricityConsume); 
                preparedStmt.setString(10, adjustments);  
                preparedStmt.setDouble(11, totalAmountDue);
                preparedStmt.setString(12, billDate); 

                // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                output = "New bill Create successfully"; 
            } 
            catch (Exception e) 
            { 
            output = "Error while creating the bill"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 
    /**
     * Get data User and bill database
     * @return
     */
    public String readBill() 
    { 
    String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
        {return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th>Bill ID</th>"
        		+ "<th>Account ID</th>" +
                "<th>Join Date</th>" + 
                "<th>Meter Reding Before Date</th>"+
                "<th>Meter Reding Before</th>" + 
                "<th>Meter ReadingNowDate</th>" +
                "<th>Meter ReadingNow</th>" + 
                "<th>No Of UntitsConsumed</th>"+
                "<th>Charge for electricity Consum</th>" + 
                "<th>adjustments</th>" +
                "<th>totalAmountDue</th>" + 
                "<th>billDate</th>"+
                "<th>Customer name</th></tr>" ; 
        
        /**Use join query connect tree table */
        String query = "SELECT * FROM bill INNER JOIN users ON users.accountId = bill.accountId"; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String billId = rs.getString("billId"); 
            String accountId = rs.getString("accountId"); 
            String joinDate = rs.getString("joinDate"); 
            String meterReadingBeforeDate = rs.getString("meterReadingBeforeDate"); 
            String meterReadingBefore = rs.getString("meterReadingBefore"); 
            String meterReadingNowDate = rs.getString("meterReadingNowDate"); 
            String meterReadingNow = rs.getString("meterReadingNow"); 
            String noOfUntitsConsumed = rs.getString("noOfUntitsConsumed"); 
            String chargeforelectricityConsume = rs.getString("chargeforelectricityConsume");
            String adjustments  = rs.getString("adjustments"); 
            String totalAmountDue  = rs.getString("totalAmountDue"); 
            String billDate  = rs.getString("billDate"); 
            String CoustomerName  = rs.getString("firstName"); 
            // Add into the html table
            output += "<tr><td>" + billId + "</td>"; 
            output += "<td>" + accountId + "</td>"; 
            output += "<td>" + joinDate + "</td>"; 
            output += "<td>" + meterReadingBeforeDate + "</td>"; 
            output += "<td>" + meterReadingBefore + "</td>"; 
            output += "<td>" + meterReadingNowDate + "</td>"; 
            output += "<td>" + meterReadingNow + "</td>"; 
            output += "<td>" + noOfUntitsConsumed + "</td>";
            output += "<td>" + chargeforelectricityConsume + "</td>"; 
            output += "<td>" + adjustments + "</td>"; 
            output += "<td>" + totalAmountDue + "</td>"; 
            output += "<td>" + billDate + "</td>"; 
            output += "<td>" + CoustomerName + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the Bills."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
   
    /**
     * Update Bill Information
     * @param BillId
     * @param accountId
     * @param joinDate
     * @param meterReadingBeforeDate
     * @param meterReadingBefore
     * @param meterReadingNowDate
     * @param meterReadingNow
     * @param noOfUntitsConsumed
     * @param chargeforelectricityConsume
     * @param adjustments
     * @param totalAmountDue
     * @param billDate
     * @return
     */
    public String updateBill(String billId, String accountId, String joinDate, String meterReadingBeforeDate,
    String meterReadingBefore, String meterReadingNowDate, String meterReadingNow, String noOfUntitsConsumed, String chargeforelectricityConsume,
            String adjustments, Double totalAmountDue, String billDate )  
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE bill SET billId=?,accountId=?,joinDate=?,meterReadingBeforeDate=?"
    		+ ",meterReadingBefore=?,meterReadingNowDate=?,meterReadingNow=?,noOfUntitsConsumed=?,"
    		+ "chargeforelectricityConsume=? ,adjustments=?,totalAmountDue=?,billDate=? WHERE billId=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
        preparedStmt.setString(1, billId); 
        preparedStmt.setString(2, accountId); 
        preparedStmt.setString(3, joinDate); 
        preparedStmt.setString(4, meterReadingBeforeDate);
        preparedStmt.setString(5, meterReadingBefore); 
        preparedStmt.setString(6, meterReadingNowDate);
        preparedStmt.setString(7, meterReadingNow); 
        preparedStmt.setString(8, noOfUntitsConsumed);
        preparedStmt.setString(9, chargeforelectricityConsume); 
        preparedStmt.setString(10, adjustments);
        preparedStmt.setDouble(11, totalAmountDue );
        preparedStmt.setString(12, billDate ); 
        preparedStmt.setString(13, billId);
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    output = "Updated successfully"; 
    } 
        catch (Exception e) 
    { 
        output = "Error while updating the Bill."; 
        System.err.println(e.getMessage()); 
    } 
        return output; 
    } 

    
    /**
     * remove the Bill details from the table
     * @param BillId
     * @return
     */
    public String deleteBill(String BillId) 
    { 
	    String output = ""; 
	    try
	    { 
	    	Connection con = connect(); 
		    if (con == null) 
		    	{return "Error while connecting to the database for deleting."; } 
			    // create a prepared statement
			    String query = "delete from bill where BillId=?"; 
			    PreparedStatement preparedStmt = con.prepareStatement(query); 
			    // binding values
			    preparedStmt.setString(1, BillId); 
			    // execute the statement
			    preparedStmt.execute(); 
			    con.close(); 
			    output = "Deleted successfully"; 
		    } 
	    catch (Exception e) 
	    { 
		    output = "Error while deleting the Bill."; 
		    System.err.println(e.getMessage()); 
	    } 
	    return output; 
	    }

}

