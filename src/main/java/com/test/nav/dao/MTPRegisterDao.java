package com.test.nav.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.AJMtpRegisterDetails;
import com.test.nav.model.DTOMtpRegister;
import com.test.nav.model.DTOMtpRegisterDetails;
import com.test.nav.transformer.MtpRegisterTransformer;
import com.test.nav.util.AppUtil;
import com.test.nav.util.DbUtil;

public class MTPRegisterDao {

	public int getMtpSerialNo(int mtpRegisterId) {
		int serialNo = 0;
		LazyList<AJMtpRegister> ajMtpRegisters = AJMtpRegister.findBySQL(AJMtpRegister.SELECT_SERIAL_NO, mtpRegisterId);
		if (!ajMtpRegisters.isEmpty()) {
			serialNo = ajMtpRegisters.get(0).getInteger(AJMtpRegister.MTP_SERIAL_NO);
		}
		return serialNo;
	}
	
	public void insertWithDetails(DTOMtpRegister dtoMtpRegister, DTOMtpRegisterDetails dtoMtpRegisterDetails) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			int id = insert(dtoMtpRegister);
			System.out.println("inserted mtp register id:" + id);
			AJMtpRegisterDetails ajMtpRegisterDetails = new AJMtpRegisterDetails();
			ajMtpRegisterDetails.setInteger(AJMtpRegisterDetails.MTP_REGISTER_ID, id);
			ajMtpRegisterDetails.setString(AJMtpRegisterDetails.PATIENT_NAME, dtoMtpRegisterDetails.getpName());
			ajMtpRegisterDetails.setString(AJMtpRegisterDetails.ADDRESS, dtoMtpRegisterDetails.getpAddress());
			ajMtpRegisterDetails.setString(AJMtpRegisterDetails.GENDER, dtoMtpRegisterDetails.getGender());
			ajMtpRegisterDetails.setInteger(AJMtpRegisterDetails.AGE, dtoMtpRegisterDetails.getAge());
			ajMtpRegisterDetails.setDouble(AJMtpRegisterDetails.FEES, dtoMtpRegisterDetails.getFees());
			ajMtpRegisterDetails.setString(AJMtpRegisterDetails.REMARKS, dtoMtpRegisterDetails.getRemarks());
			ajMtpRegisterDetails.save();
			Base.commitTransaction();
			System.out.println("saved details: " + ajMtpRegisterDetails.getId());
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}
	
	public int insert(DTOMtpRegister dtoMtpRegister) throws Exception {
		try {
			AJMtpRegister ajMtpRegister = new AJMtpRegister();
			ajMtpRegister.setInteger(AJMtpRegister.MTP_SERIAL_NO, generateSerialNo(dtoMtpRegister.getOperationDate()));
			ajMtpRegister.setString(AJMtpRegister.RELIGION, dtoMtpRegister.getReligion());
			ajMtpRegister.setString(AJMtpRegister.INDICATION, dtoMtpRegister.getMindication());
			ajMtpRegister.setString(AJMtpRegister.MTP_PROCEDURE, dtoMtpRegister.getProcedure());
			ajMtpRegister.setInteger(AJMtpRegister.DURATION_OF_PREGNANCY, dtoMtpRegister.getDurationOfPregnancy());
			ajMtpRegister.setString(AJMtpRegister.ALONG_WITH, dtoMtpRegister.getAlongWith());
			ajMtpRegister.setInteger(AJMtpRegister.MALE_CHILDRENS, dtoMtpRegister.getmChildrens());
			ajMtpRegister.setInteger(AJMtpRegister.FEMALE_CHILDRENS, dtoMtpRegister.getfChildrens());
			ajMtpRegister.setDate(AJMtpRegister.OPERATION_DATE, dtoMtpRegister.getOperationDate());
			ajMtpRegister.setString(AJMtpRegister.DONE_BY_DR, dtoMtpRegister.getDoneByDr());
			ajMtpRegister.setString(AJMtpRegister.MARRIED, dtoMtpRegister.getMarried());
			ajMtpRegister.setString(AJMtpRegister.OPINION_GIVEN_BY, dtoMtpRegister.getOpinionGivenBy());
			ajMtpRegister.setString(AJMtpRegister.BATCH_NO, dtoMtpRegister.getBatchNo());
			ajMtpRegister.save();
			return Integer.parseInt(ajMtpRegister.getId().toString());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void update(DTOMtpRegister dtoMtpRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJMtpRegister ajMtpRegister = new AJMtpRegister();
			ajMtpRegister.setId(dtoMtpRegister.getId());
			ajMtpRegister.setString(AJMtpRegister.RELIGION, dtoMtpRegister.getReligion());
			ajMtpRegister.setString(AJMtpRegister.INDICATION, dtoMtpRegister.getMindication());
			ajMtpRegister.setString(AJMtpRegister.MTP_PROCEDURE, dtoMtpRegister.getProcedure());
			ajMtpRegister.setInteger(AJMtpRegister.DURATION_OF_PREGNANCY, dtoMtpRegister.getDurationOfPregnancy());
			ajMtpRegister.setString(AJMtpRegister.ALONG_WITH, dtoMtpRegister.getAlongWith());
			ajMtpRegister.setInteger(AJMtpRegister.MALE_CHILDRENS, dtoMtpRegister.getmChildrens());
			ajMtpRegister.setInteger(AJMtpRegister.FEMALE_CHILDRENS, dtoMtpRegister.getfChildrens());
			ajMtpRegister.setDate(AJMtpRegister.OPERATION_DATE, dtoMtpRegister.getOperationDate());
			ajMtpRegister.setString(AJMtpRegister.DONE_BY_DR, dtoMtpRegister.getDoneByDr());
			ajMtpRegister.setString(AJMtpRegister.MARRIED, dtoMtpRegister.getMarried());
			ajMtpRegister.setString(AJMtpRegister.OPINION_GIVEN_BY, dtoMtpRegister.getOpinionGivenBy());
			ajMtpRegister.setString(AJMtpRegister.BATCH_NO, dtoMtpRegister.getBatchNo());
			ajMtpRegister.save();
			Base.commitTransaction();
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}
	
	public Integer generateSerialNo(Date operationDate) throws Exception {
		Integer serialNo = 0;
		//this date will be used while updating serialno
		Date originalOperationDate = operationDate;
		
		if(operationDate != null) {
			Date resetDate = AppUtil.getMTPResetDate(operationDate);
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
			boolean doUpdate = false;
			
			//Check how many record exists for current year.
			int recordCount = checkForEmpty(currentYear);
			System.out.println("number of records in mtp_register after:" + new SimpleDateFormat("dd/MM/yyyy").format(resetDate) + " are " + recordCount);
			if(recordCount > 0) {
				Calendar newOperationDate = Calendar.getInstance();
				do {
					newOperationDate.setTime(operationDate);
					serialNo = getLastSerialNo(newOperationDate.getTime());
					System.out.println("got serial no:" + serialNo + " for operation date:" + operationDate);
					newOperationDate.add(Calendar.DATE, -1);
					operationDate = newOperationDate.getTime();
				} while(serialNo == 0 && operationDate.compareTo(resetDate) >= 0);
				
				//if serialNo is still zero, operation date is earlier than all present in table
				//so setting it to CURRENT_YEAR + 001
				if(serialNo == 0) {
					serialNo = new Integer(currentYear.toString() + "001");
				} else {
					serialNo = serialNo + 1;
				}
				doUpdate = true;				
				
				//if recordCount is zero then no record present in table
				//so set it to CURRENT_YEAR + 001
			} else if(recordCount == 0) {
				serialNo = new Integer(currentYear.toString() + "001");
			}
			
			System.out.println("generated serial no:" + serialNo);
			if(doUpdate) {
				updateSerialNoGreaterThanOperationDate(originalOperationDate);
				System.out.println("updated all serialNo which is having operation date greater than :" + operationDate);
			}
		}
		//if still serialNo is zero, then something went wrong. Check can be placed while calling this method.
		return serialNo;
	}
	
	private Integer getLastSerialNo(Date operationDate) throws Exception{
		Integer lastSerialNo = 0;
		Object maxSerialNo = Base.firstCell("SELECT max(mtp_serial_no) from mtp_register where operation_date = ?", new SimpleDateFormat("yyyy-MM-dd").format(operationDate));
		if (maxSerialNo != null) {
			lastSerialNo = Integer.parseInt(maxSerialNo.toString());
		}
		return lastSerialNo;
	}
	
	private int checkForEmpty(int currentYear) {
		Long resultCount = null;
		resultCount = Base.count("mtp_register", "date_format(operation_date, '%Y') = ?", currentYear);
		return resultCount != null ? resultCount.intValue() : 0;
	}
	
	private void updateSerialNoGreaterThanOperationDate(Date operationDate) {
		AJMtpRegister.update("mtp_serial_no = mtp_serial_no + 1", "operation_date > ?", operationDate);
	}

	public List<DTOMtpRegister> getMtpRegisterByMonth(String month, String year) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			System.out.println("request to fetch mtp register report");
			LazyList<AJMtpRegister> ajMtpRegisters = AJMtpRegister.findBySQL(AJMtpRegister.SELECT_BY_MONTH, month,
					year);
			System.out.println("Number of records found:" + ajMtpRegisters.size());
			return new MtpRegisterTransformer().transformList(ajMtpRegisters);
		} catch (Throwable t) {
			t.printStackTrace(); 
		} finally {
			Base.close();
		}

		return null;
	}
	
	public AJMtpRegisterDetails getMtpRegisterDetailsForMtpReport(int id) {
		LazyList<AJMtpRegisterDetails> details = AJMtpRegisterDetails.findBySQL(AJMtpRegisterDetails.SELECT, id);
		if(!details.isEmpty()) {
			return details.get(0);
		}
		
		return null;
	}

	public DTOMtpRegister getMtpRegisterById(int id) {
		Connection conn = DbUtil.getConnection();
		try {
			conn.setReadOnly(true);
			Base.openTransaction();
			return new MtpRegisterTransformer().transformForEdit((AJMtpRegister) AJMtpRegister.findById(id));
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}		
}