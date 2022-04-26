package com.PaymentManagement;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;
import com.sun.research.ws.wadl.Response;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/payment") 
public class PaymentServices {
	//create user object
	Payment payment =  new Payment();
	
	//add new payment 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addpayment(
			@FormParam("accountId") String accountId, 
			@FormParam("billId") String billId, 
			@FormParam("payeeName") String payeeName,
			@FormParam("paymentType") String paymentType, 
			@FormParam("email") String email, 
			@FormParam("totalAmountBill") Double totalAmountBill ,
			@FormParam("amountToBePaid") Double amountToBePaid , 
			@FormParam("datePayment") String datePayment
			)		
	{
		String output = payment.addPayment(accountId, billId, payeeName, paymentType, email, totalAmountBill, amountToBePaid, datePayment);
		return output; 
	}

	//Get payment details 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String getPaymentDetails() 
	{ 
		return payment.readPayment(); 
	}
	
	//Update Payment Details
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String paymentId  = paymentObject.get("paymentId").getAsString();
	 String accountId = paymentObject.get("accountId").getAsString(); 
	 String billId = paymentObject.get("billId").getAsString(); 
	 String payeeName = paymentObject.get("payeeName").getAsString(); 
	 String paymentType = paymentObject.get("paymentType").getAsString(); 
	 String email = paymentObject.get("email").getAsString(); 
	 Double totalAmountBill = paymentObject.get("totalAmountBill").getAsDouble(); 
	 Double amountToBePaid = paymentObject.get("amountToBePaid").getAsDouble(); 
	 String datePayment = paymentObject.get("datePayment").getAsString();
	 String output = payment.updatePayment(paymentId,accountId, billId, payeeName, paymentType, email, totalAmountBill, amountToBePaid, datePayment);
	return output; 
	}

	//Remove the payment
		@DELETE
		@Path("/Delete/{paymentId}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(@PathParam("paymentId") String paymentId) {
			String response = payment.deletePayment(paymentId);
			return response;
		}


}

