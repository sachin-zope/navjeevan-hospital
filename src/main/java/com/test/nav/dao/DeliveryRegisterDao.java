package com.test.nav.dao;

import java.sql.Connection;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJDeliveryRegister;
import com.test.nav.model.DTODeliveryRegister;
import com.test.nav.transformer.DeliveryRegisterTransformer;
import com.test.nav.util.DbUtil;

public class DeliveryRegisterDao {

	public int insert(DTODeliveryRegister dtoDeliveryRegister) {
		AJDeliveryRegister ajDeliveryRegister = new AJDeliveryRegister();
		ajDeliveryRegister.setDate(AJDeliveryRegister.DELIVERY_DATE, dtoDeliveryRegister.getDeliveryDate());
		ajDeliveryRegister.setString(AJDeliveryRegister.EPISIOTOMY, dtoDeliveryRegister.getEpisiotomy());
		ajDeliveryRegister.setString(AJDeliveryRegister.DELIVERY_TYPE, dtoDeliveryRegister.getDeliveryType());
		ajDeliveryRegister.setString(AJDeliveryRegister.SEX_OF_CHILD, dtoDeliveryRegister.getSexOfChild());
		ajDeliveryRegister.setString(AJDeliveryRegister.BIRTH_WEIGHT, dtoDeliveryRegister.getBirthWeight());
		ajDeliveryRegister.setString(AJDeliveryRegister.BIRTH_TIME, dtoDeliveryRegister.getBirthTime());
		ajDeliveryRegister.setString(AJDeliveryRegister.INDICATION, dtoDeliveryRegister.getIndication());
		ajDeliveryRegister.setString(AJDeliveryRegister.REMARKS, dtoDeliveryRegister.getDeliveryRemarks());
		ajDeliveryRegister.save();
		return Integer.parseInt(ajDeliveryRegister.getId().toString());
	}
	
	public void update(DTODeliveryRegister dtoDeliveryRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJDeliveryRegister ajDeliveryRegister = new AJDeliveryRegister();
			ajDeliveryRegister.setId(dtoDeliveryRegister.getId());
			ajDeliveryRegister.setDate(AJDeliveryRegister.DELIVERY_DATE, dtoDeliveryRegister.getDeliveryDate());
			ajDeliveryRegister.setString(AJDeliveryRegister.EPISIOTOMY, dtoDeliveryRegister.getEpisiotomy());
			ajDeliveryRegister.setString(AJDeliveryRegister.DELIVERY_TYPE, dtoDeliveryRegister.getDeliveryType());
			ajDeliveryRegister.setString(AJDeliveryRegister.SEX_OF_CHILD, dtoDeliveryRegister.getSexOfChild());
			ajDeliveryRegister.setString(AJDeliveryRegister.BIRTH_WEIGHT, dtoDeliveryRegister.getBirthWeight());
			ajDeliveryRegister.setString(AJDeliveryRegister.BIRTH_TIME, dtoDeliveryRegister.getBirthTime());
			ajDeliveryRegister.setString(AJDeliveryRegister.INDICATION, dtoDeliveryRegister.getIndication());
			ajDeliveryRegister.setString(AJDeliveryRegister.REMARKS, dtoDeliveryRegister.getDeliveryRemarks());
			ajDeliveryRegister.save();
			Base.commitTransaction();
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}

	public List<DTODeliveryRegister> getDeliveryRegisterByMonth(String month, String year) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			System.out.println("request to fetch delivery register report");
			LazyList<AJDeliveryRegister> ajDeliveryRegisters = AJDeliveryRegister.findBySQL(AJDeliveryRegister.SELECT_BY_MONTH, month,
					year);
			System.out.println("Number of records found:" + ajDeliveryRegisters.size());
			return new DeliveryRegisterTransformer().transformList(ajDeliveryRegisters, true);
		} catch (Throwable t) {
			t.printStackTrace(); 
		} finally {
			Base.close();
		}

		return null;
	}

	public DTODeliveryRegister getDeliveryRegisterById(int id) {
		Connection conn = DbUtil.getConnection();
		try {
			conn.setReadOnly(true);
			Base.openTransaction();
			return new DeliveryRegisterTransformer().transformForEdit((AJDeliveryRegister) AJDeliveryRegister.findById(id));
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
}
