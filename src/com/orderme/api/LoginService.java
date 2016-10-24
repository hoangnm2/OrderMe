package com.orderme.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {
	
	@GET
	public Response getMsg() {

		String output = "Jersey say : Hoang";

		return Response.status(200).entity(output).build();

	}
}
