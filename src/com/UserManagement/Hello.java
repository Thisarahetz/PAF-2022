package com.UserManagement; 
import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
@Path("/Lakshan") 
public class Hello 
{ 
@GET
@Path("/") 
@Produces(MediaType.TEXT_PLAIN) 
public String hello() 
 { 
 return "Hello world. 2022"; 
 } 
}