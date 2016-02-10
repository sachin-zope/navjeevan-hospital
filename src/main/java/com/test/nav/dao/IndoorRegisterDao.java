package com.test.nav.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOIndoorRegister;
import com.test.nav.transformer.IndoorRegisterTransformer;
import com.test.nav.util.DbUtil;

public class IndoorRegisterDao {

	public void deleteIndoorRegister(int id) {
		try {
			Connection conn = DbUtil.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("delete from indoor_register where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DTOIndoorRegister> getAllIndoorRegisters(String month, String year) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();
			
			System.out.println("request to fetch report");
			//List<IndoorRegister> irs = IndoorRegister.findBySQL(IndoorRegister.SELECT_BY_MONTH, month, year);
			LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_BY_MONTH, month, year);
			System.out.println("Number of records found:" + ajIndoorRegisters.size());
			return new IndoorRegisterTransformer().transform(ajIndoorRegisters);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.setReadOnly(false);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	public AJIndoorRegister getIndoorRegisterById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
