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

	//Get Bill details 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String getBillDetails() 
	{ 
		return bill.readBill(); 
	}
	
	//update bill information
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBill(String billData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
	//Read the values from the JSON object
	  
	 String billId   = billObject.get("billId").getAsString();
	 String accountId  = billObject.get("accountId").getAsString(); 
	 String joinDate  = billObject.get("joinDate").getAsString(); 
	 String meterReadingBeforeDate  = billObject.get("meterReadingBeforeDate").getAsString(); 
	 String meterReadingBefore  = billObject.get("meterReadingBefore").getAsString(); 
	 String meterReadingNowDate  = billObject.get("meterReadingNowDate").getAsString(); 
	 String meterReadingNow  = billObject.get("meterReadingNow").getAsString(); 
	 String noOfUntitsConsumed  = billObject.get("noOfUntitsConsumed").getAsString(); 
	 String chargeforelectricityConsume   = billObject.get("chargeforelectricityConsume").getAsString(); 
	 String adjustments  = billObject.get("adjustments").getAsString(); 
	 Double totalAmountDue  = billObject.get("totalAmountDue").getAsDouble(); 
	 String billDate  = billObject.get("billDate").getAsString(); 
	 
	 String output = bill.updateBill(billId, accountId, joinDate, meterReadingBeforeDate, meterReadingBefore, meterReadingNowDate, meterReadingNow, noOfUntitsConsumed ,chargeforelectricityConsume, adjustments, totalAmountDue, billDate);
	return output; 
	
	}
	
	//Remove the Bill
	@DELETE
	@Path("/Delete/{billId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(@PathParam("billId") String billId) {
		String response = bill.deleteBill(billId);
		return response;
	}

}
