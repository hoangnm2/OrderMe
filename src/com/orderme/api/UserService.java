package com.orderme.api;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.orderme.entity.Role;
import com.orderme.entity.User;
import com.orderme.utils.Response;

@Path("/user")
public class UserService extends DBService {

	EntityManager em;
	EntityManagerFactory entityManagerFactory;
	//WRF


	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user) throws ClassNotFoundException {
		entityManagerFactory = Persistence.createEntityManagerFactory("testjpa");
		em = entityManagerFactory.createEntityManager();
		
		String select = "SELECT us FROM User us WHERE us.email=:email and us.password=:password";
		Query query = em.createQuery(select);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		List<User> users = query.getResultList();
		if (users.size() != 1) {
			return null;
		}
		//Login success --> close connection
		em.close();
		entityManagerFactory.close();

		return users.get(0);
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int register(User user) throws ClassNotFoundException {
		entityManagerFactory = Persistence.createEntityManagerFactory("testjpa");
		em = entityManagerFactory.createEntityManager();
		
		int flag = checkEmailExists(user);
		if (Response.SUCCESS != flag) {
			em.close();
			entityManagerFactory.close();
			return flag;
		}
		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		user.setCreateDate(new Date());
		user.setRole(Role.ADMIN);

		em.persist(user);
		userTransaction.commit();
		
		em.close();
		entityManagerFactory.close();

		return Response.SUCCESS;
	}

	private int checkEmailExists(User user) {
		String select = "SELECT us FROM User us WHERE us.email=:email and us.password=:password";
		Query query = em.createQuery(select);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return Response.EMAIL_EXIST;
		}
		return Response.SUCCESS;
	}
}
