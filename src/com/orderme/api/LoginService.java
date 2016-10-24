package com.orderme.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.orderme.entity.User;

@Path("/login")
public class LoginService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getMsg() {

		String output = "Jersey say : Hoang";
		User u = new User();
		u.setName("name");
		u.setPassword("pass");
		return u;

	}
}
