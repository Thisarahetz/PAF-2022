package com.UserManagement;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/users") 
public class UserServices {
	//create user object
	Users users =  new Users();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String registerUser(@FormParam("isAdmin") boolean isAdmin, 
	@FormParam("firstName") String firstName, 
	@FormParam("lastName") String lastName,
	@FormParam("nic") String nic, 
	@FormParam("permanantAddress") String permanantAddress, 
	@FormParam("mobileNumber") String mobileNumber ,
	@FormParam("landNumber") String landNumber , 
	@FormParam("email") String email, 
	@FormParam("userPassword") String userPassword ,
	@FormParam("areaoffice") String areaoffice , 
	@FormParam("joinDate") String joinDate)
	{ 
	 
	 String output = users.registerUser(isAdmin, firstName, lastName, nic, permanantAddress, mobileNumber, landNumber, email, userPassword, areaoffice, joinDate);
	return output; 
	}

	//Read User Details details 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String getUserDetails() 
	{ 
		return users.readUsers(); 
	}
	
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	  
	 String accountId  = paymentObject.get("accountId").getAsString();
	 String isAdmin = paymentObject.get("isAdmin").getAsString(); 
	 String firstName = paymentObject.get("firstName").getAsString(); 
	 String lastName = paymentObject.get("lastName").getAsString(); 
	 String nic = paymentObject.get("nic").getAsString(); 
	 String permanantAddress = paymentObject.get("permanantAddress").getAsString(); 
	 String mobileNumber = paymentObject.get("mobileNumber").getAsString(); 
	 String landNumber = paymentObject.get("landNumber").getAsString(); 
	 String email = paymentObject.get("email").getAsString(); 
	 String userPassword = paymentObject.get("userPassword").getAsString(); 
	 String areaoffice = paymentObject.get("areaoffice").getAsString(); 
	 String joinDate = paymentObject.get("joinDate").getAsString();  
	 
	 String output = users.updateUser(accountId, isAdmin, firstName, lastName, nic, permanantAddress, mobileNumber, landNumber, email, userPassword, areaoffice, joinDate);
	return output; 
	
	}
	
	//Remove the payment
			@DELETE
			@Path("/Delete/{accountId}")
			@Produces(MediaType.TEXT_PLAIN)
			public String deletePayment(@PathParam("accountId") String accountId) {
				String response = users.deleteUser(accountId);
				return response;
			}

}
