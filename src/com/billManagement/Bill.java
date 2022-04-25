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

}

