package com.orderme.api;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.orderme.entity.Role;
import com.orderme.entity.User;



@Path("/user")
public class LoginService extends DBService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(User user) throws ClassNotFoundException {
		
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("testjpa");
	    EntityManager em = entityManagerFactory.createEntityManager();
	    EntityTransaction userTransaction = em.getTransaction();
	    
	    String select = "SELECT us FROM orderme_user us WHERE us.email=:email and us.password=:password";
	    Query query = em.createQuery(select);
	    query.setParameter("userName", user.getEmail());
	    query.setParameter("password", user.getPassword());
	    List<User> users = query.getResultList();
	    
	    userTransaction.commit();
	    em.close();
	    entityManagerFactory.close();
		return "call me hehe";

	}
	
	@POST
	//@PathParam()
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public int register() throws ClassNotFoundException {
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
		return 1;

	}
}
