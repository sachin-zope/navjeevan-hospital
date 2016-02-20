package com.test.nav.dao;

import java.sql.Connection;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJOTRegister;
import com.test.nav.model.DTOOTRegister;
import com.test.nav.transformer.OTRegisterTransformer;
import com.test.nav.util.DbUtil;

public class OTRegisterDao {
	
	public int insert (DTOOTRegister dtootRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJOTRegister ajotRegister = new AJOTRegister();
			ajotRegister.setString(AJOTRegister.NAME_OF_SURGEON, dtootRegister.getNameOfSurgeon());
			ajotRegister.setString(AJOTRegister.ASSISTANT, dtootRegister.getAssistant());
			ajotRegister.setString(AJOTRegister.ANAESTHETIST, dtootRegister.getAnaesthetist());
			ajotRegister.setDate(AJOTRegister.OPERATION_DATE, dtootRegister.getOperationDate());
			ajotRegister.save();
			Base.commitTransaction();
			return Integer.parseInt(ajotRegister.getId().toString());
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
		return 0;
	}
	
	public void update (DTOOTRegister dtootRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJOTRegister ajotRegister = new AJOTRegister();
			ajotRegister.setId(dtootRegister.getId());
			ajotRegister.setString(AJOTRegister.NAME_OF_SURGEON, dtootRegister.getNameOfSurgeon());
			ajotRegister.setString(AJOTRegister.ASSISTANT, dtootRegister.getAssistant());
			ajotRegister.setString(AJOTRegister.ANAESTHETIST, dtootRegister.getAnaesthetist());
			ajotRegister.setDate(AJOTRegister.OPERATION_DATE, dtootRegister.getOperationDate());
			ajotRegister.save();
			Base.commitTransaction();
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}

	public List<DTOOTRegister> getOTRegisterByMonth(String month, String year) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			System.out.println("request to fetch ot register report");
			LazyList<AJOTRegister> ajotRegisters = AJOTRegister.findBySQL(AJOTRegister.SELECT_BY_MONTH, month,
					year);
			System.out.println("Number of records found:" + ajotRegisters.size());
			return new OTRegisterTransformer().transformList(ajotRegisters, true);
		} catch (Throwable t) {
			t.printStackTrace(); 
		} finally {
			Base.close();
		}

		return null;
	}

	public DTOOTRegister getOTRegisterById(int id) {
		Connection conn = DbUtil.getConnection();
		try {
			conn.setReadOnly(true);
			Base.openTransaction();
			return new OTRegisterTransformer().transform((AJOTRegister) AJOTRegister.findById(id));
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
}