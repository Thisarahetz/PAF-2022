package com.billManagement;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/bills") 
public class BillServices {
	//create user object
	Bill bill =  new Bill();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addBill(
			@FormParam("accountId") String accountId, 
			@FormParam("joinDate") String joinDate, 
			@FormParam("meterReadingBeforeDate") String meterReadingBeforeDate,
			@FormParam("meterReadingBefore") String meterReadingBefore, 
			@FormParam("meterReadingNowDate") String meterReadingNowDate, 
			@FormParam("meterReadingNow") String meterReadingNow ,
			@FormParam("noOfUntitsConsumed") String noOfUntitsConsumed , 
			@FormParam("chargeforelectricityConsume") String chargeforelectricityConsume, 
			@FormParam("adjustments") String adjustments ,
			@FormParam("totalAmountDue") Double totalAmountDue , 
			@FormParam("billDate") String billDate)
	{ 
	 
	 String output = bill.addBill(accountId, joinDate,meterReadingBeforeDate, meterReadingBefore, meterReadingNowDate, meterReadingNow, noOfUntitsConsumed, chargeforelectricityConsume, adjustments, totalAmountDue, billDate);
	return output; 
	}

}
