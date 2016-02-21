package com.test.nav.util;

import java.sql.Connection;

import org.javalite.activejdbc.Base;

public class DbUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/navjeevan";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static Connection getConnection() {
		Base.open(DRIVER, URL, USER, PASSWORD);
		return Base.connection();
	}
}
