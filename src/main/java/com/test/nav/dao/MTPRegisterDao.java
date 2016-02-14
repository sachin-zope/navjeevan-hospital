package com.test.nav.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.DTOMtpRegister;
import com.test.nav.util.AppUtil;
import com.test.nav.util.DbUtil;

public class MTPRegisterDao {

	public int getMtpSerialNo(int mtpRegisterId) {
		Connection conn = null;
		int serialNo = 0;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();
			LazyList<AJMtpRegister> ajMtpRegisters = AJMtpRegister.findBySQL(AJMtpRegister.SELECT_SERIAL_NO, mtpRegisterId);
			if (!ajMtpRegisters.isEmpty()) {
				serialNo = ajMtpRegisters.get(0).getInteger(AJMtpRegister.MTP_SERIAL_NO);
			}
			Base.commitTransaction();
		} catch (SQLException e) {
			Base.rollbackTransaction();
			e.printStackTrace();
		} finally {
			Base.close();
		}
		return serialNo;
	}
	
	public int insert(DTOMtpRegister dtoMtpRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJMtpRegister ajMtpRegister = new AJMtpRegister();
			ajMtpRegister.setInteger(AJMtpRegister.MTP_SERIAL_NO, dtoMtpRegister.getMtpSerialNo());
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
			return Integer.parseInt(ajMtpRegister.getId().toString());
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
		return 0;
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
		Integer lastSerialNo = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();
			
			Object maxSerialNo = Base.firstCell("SELECT max(mtp_serial_no) from mtp_register where operation_date = STR_TO_DATE(?, '%d-%m-%Y)", operationDate);
			lastSerialNo = Integer.parseInt(maxSerialNo.toString());
		} catch(Exception e) {
			throw e;
		} finally {
			Base.close();
		}

		return lastSerialNo;
	}
	
	public int checkForEmpty(int currentYear) {
		Long resultCount = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();
			resultCount = Base.count("mtp_register", "date_format(operationDate, '%Y') = :?", currentYear);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Base.close();
		}
		
		return resultCount != null ? resultCount.intValue() : 0;
	}
	
	private void updateSerialNoGreaterThanOperationDate(Date operationDate) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJMtpRegister.update("mtp_serial_no = mtp_serial_no + 1", "operation_date > ?", operationDate);
			Base.commitTransaction();
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}

	public List<DTOMtpRegister> getMtpRegisterByMonth(String month, String year) {
		// TODO Auto-generated method stub
		return null;
	}		
}