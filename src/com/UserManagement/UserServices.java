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

}
