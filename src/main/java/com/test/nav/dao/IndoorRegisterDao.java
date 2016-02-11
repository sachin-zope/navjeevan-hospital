package com.test.nav.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOIndoorRegister;
import com.test.nav.transformer.IndoorRegisterTransformer;
import com.test.nav.util.DbUtil;

public class IndoorRegisterDao {

	public void deleteIndoorRegister(int id) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJIndoorRegister.delete("id = ?", id);
			Base.commitTransaction();
		} catch (SQLException e) {
			Base.rollbackTransaction();
			e.printStackTrace();
		} finally {
			Base.close();
		}
	}

	public List<DTOIndoorRegister> getAllIndoorRegisters(String month, String year) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			System.out.println("request to fetch report");
			LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_BY_MONTH, month,
					year);
			System.out.println("Number of records found:" + ajIndoorRegisters.size());
			return new IndoorRegisterTransformer().transformList(ajIndoorRegisters);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
	
	public void updateIndoorRegister(DTOIndoorRegister register) {
		Connection conn = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			
			AJIndoorRegister ajIndoorRegister = AJIndoorRegister.findById(register.getId());
			//if admit date is changed, generate ipdno again
			if(!ajIndoorRegister.getDate(AJIndoorRegister.ADMIT_DATE).equals(register.getAdmitDate())) {
				ajIndoorRegister.set(AJIndoorRegister.IPD_NO, generateIPDNo(register.getAdmitDate()));
			}
			
			ajIndoorRegister.set(AJIndoorRegister.PATIENT_NAME, register.getPatientName());
			ajIndoorRegister.set(AJIndoorRegister.PATIENT_ADDRESS, register.getPatientAddress());
			ajIndoorRegister.set(AJIndoorRegister.AGE, register.getAge());
			ajIndoorRegister.set(AJIndoorRegister.GENDER, register.getGender());
			ajIndoorRegister.set(AJIndoorRegister.FEES, register.getFees());
			ajIndoorRegister.set(AJIndoorRegister.REMARKS, register.getRemarks());
			
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}

	public DTOIndoorRegister getIndoorRegisterById(int id) {
		Connection conn = DbUtil.getConnection();
		try {
			conn.setReadOnly(true);
			Base.openTransaction();
			return new IndoorRegisterTransformer().transform((AJIndoorRegister) AJIndoorRegister.findById(id));
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
	
	public long generateIPDNo(Date admitDate) throws Exception {
		SimpleDateFormat ipdNoFormatter = new SimpleDateFormat("yyyyMMdd");
		Long ipdNo = getLastIPDNo(admitDate);
		if(ipdNo != null) {
			System.out.println("database returned IPD no = " + ipdNo);
			ipdNo = ipdNo + 1;
		} else if (ipdNo == null) {
			System.out.println("database returned null IPD no, generating new");
			String strIPDNo = ipdNoFormatter.format(admitDate) + "01";
			ipdNo = new Long(strIPDNo);
		}
		
		System.out.println("setting Serial No:" + ipdNo);
		return ipdNo;
	}
	
	public Long getLastIPDNo(Date admitDate) throws Exception{
		Long lastIPDNo = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();
			
			Object maxIpdNo = Base.firstCell("SELECT max(ipd_no) from indoor_register where admit_date = STR_TO_DATE(?, '%d-%m-%Y)", admitDate);
			lastIPDNo = Long.parseLong(maxIpdNo.toString());	
		} catch(Exception e) {
			throw e;
		} finally {
			Base.close();
		}

		return lastIPDNo;
	}
}

