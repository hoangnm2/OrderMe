package com.orderme.api;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.orderme.entity.Role;
import com.orderme.entity.User;

@Path("/login")
public class LoginService {
	
	@GET
	//@PathParam()
	@Produces(MediaType.APPLICATION_JSON)
	public String login() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("testjpa");
	    EntityManager em = entityManagerFactory.createEntityManager();
	    EntityTransaction userTransaction = em.getTransaction();
	    
	    userTransaction.begin();
	    User user = new User();
	    user.setEmail("minhhoang@gmail.com");
	    user.setPassword("pass1234");
	    user.setName("asd");
	    user.setRole(Role.ADMIN);
	    
	    em.persist(user);
	    userTransaction.commit();
	    em.close();
	    entityManagerFactory.close();
		return "call me hehe";

	}
}
