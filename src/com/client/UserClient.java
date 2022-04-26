package com.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
 
import org.glassfish.jersey.client.ClientConfig;
 

public class UserClient {
	 private static String baseURI = "http://localhost:8080/Puf-2022/electrogred/users";
	 
	 static WebTarget getWebTarget() {
	        ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	         
	        return client.target(baseURI);     
	    }
	 //testing users microservies is working
	 static void testPayment() {
		    WebTarget target = getWebTarget();
		     
		    String response = target.request().accept("text/html").get(String.class);
		     
		    System.out.println(response);      
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Output from Server ....OK... \n");
		testPayment();
		
	}	
}
