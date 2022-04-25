package com.NotificationManagement;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/notification") 
public class NotificationServices {
	//create user object
	Notification notification  =  new Notification();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String addBill(
			@FormParam("accountId") String accountId, 
			@FormParam("billId") String billId, 
			@FormParam("amountToBePaiddouble") Double amountToBePaiddouble,
			@FormParam("email") String email, 
			@FormParam("mobileNumber") String mobileNumber, 
			@FormParam("subject") String subject ,
			@FormParam("massage") String massage , 
			@FormParam("dateNotify") String dateNotify
			)
	{ 
	 
	 String output = notification.insertNotification(accountId, billId, amountToBePaiddouble, email, mobileNumber, subject, massage, dateNotify);
	return output; 
	}

}

