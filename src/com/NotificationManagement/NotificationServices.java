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

	//Get Notification details 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String getBillDetails() 
		{ 
			return notification.readNotification(); 
		}
		
		//update Notification information
		@PUT
		@Path("/Update") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateNotice(String notice) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject noticObject = new JsonParser().parse(notice).getAsJsonObject(); 
		//Read the values from the JSON object
		  
		 String notificationId   = noticObject.get("notificationId").getAsString();
		 String accountId  = noticObject.get("accountId").getAsString(); 
		 String billId   = noticObject.get("billId").getAsString(); 
		 Double amountToBePaid   = noticObject.get("amountToBePaid").getAsDouble(); 
		 String email   = noticObject.get("email").getAsString(); 
		 String mobileNumber   = noticObject.get("mobileNumber").getAsString(); 
		 String subject  = noticObject.get("subject").getAsString(); 
		 String massage   = noticObject.get("massage").getAsString(); 
		 String dateNotify    = noticObject.get("dateNotify").getAsString(); 
		
		 String output = notification.updateNotification(notificationId, accountId, billId, amountToBePaid, email, mobileNumber, subject, massage, dateNotify);
		return output; 
		
		}
		
		//Remove the Notification Id
		@DELETE
		@Path("/Delete/{noticeId}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteNotice(@PathParam("noticeId") String noticeId) {
			String response = notification.deleteNotice(noticeId);
			return response;
		}
}