package com.ashish.webservices.client;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.ashish.webservices.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class UserJerseyClient {
		 
		    public static void main(String[] args) {
		        Client client = Client.create();
		        WebResource resource = client.resource("http://localhost:7001/RestfullSample/rest/UserService");
		 
		        // Get response as String
		        String string = resource.path("users")
		            .accept(MediaType.APPLICATION_XML)
		                .get(String.class);
		        System.out.println(string);

		        string = resource.path("userstext")
			            .accept(MediaType.TEXT_XML)
			                .get(String.class);
			    System.out.println(string);
			    
		        // Get response as User
//		        User customer = resource.path("users")
//		            .accept(MediaType.APPLICATION_XML)
//		                .get(User.class);
//		        System.out.println(customer.getName() + ", "+ customer.getProfession());
		 
		        // Get response as List<Customer>
		        List<User> customers = resource.path("users")
		            .accept(MediaType.APPLICATION_XML)
		                .get(new GenericType<List<User>>(){});
		        for (Iterator<User> iterator = customers.iterator(); iterator.hasNext();) {
					User user = iterator.next();
					System.out.println("User = " + user.getId() + " , " + user.getName() + " , " + user.getProfession());
				}
		        System.out.println("Users Size = " + customers.size());
		    }
		 
		}