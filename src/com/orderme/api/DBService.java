package com.orderme.api;

import java.util.logging.Logger;

public class DBService {

	private static final Logger logger = Logger.getLogger("DBService");
	
	static {
		try {
			// This is ada
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.severe("Cant load driver for JDBC");
		}
	}
}
