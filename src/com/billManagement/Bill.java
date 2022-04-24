package com.billManagement;

import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
@Path("/Asanka") 
public class Bill 
{ 
@GET
@Path("/") 
@Produces(MediaType.TEXT_PLAIN) 
public String hello() 
 { 
 return "Hello world."; 
 } 
} 

