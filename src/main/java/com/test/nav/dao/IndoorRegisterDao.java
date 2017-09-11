package com.test.nav.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJDeliveryRegister;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.AJOTRegister;
import com.test.nav.model.DTODeliveryRegister;
import com.test.nav.model.DTOIndoorRegister;
import com.test.nav.model.DTOMtpRegister;
import com.test.nav.model.DTOOTRegister;
import com.test.nav.transformer.IndoorRegisterTransformer;
import com.test.nav.util.DbUtil;

public class IndoorRegisterDao {

	public void deleteIndoorRegister(int id) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJIndoorRegister irToDelete = AJIndoorRegister.findById(id);
			if(irToDelete != null) {
				Integer mtpRegId = irToDelete.getInteger(AJIndoorRegister.MTP_REGISTER_ID);
				if(mtpRegId != null && mtpRegId > 0) {
					System.out.println("deleting mtp register with id:" + mtpRegId);
					AJMtpRegister.delete("id = ?", mtpRegId);
				}
				
				Integer deliveryRegId = irToDelete.getInteger(AJIndoorRegister.DELIVERY_REGISTER_ID);
				if(deliveryRegId != null && deliveryRegId > 0) {
					System.out.println("deleting delivery register with id:" + deliveryRegId);
					AJDeliveryRegister.delete("id = ?", deliveryRegId);
				}
				
				Integer otRegId = irToDelete.getInteger(AJIndoorRegister.OT_REGISTER_ID);
				if(otRegId !=  null && otRegId > 0) {
					System.out.println("deleting ot register with id:" + otRegId);
					AJOTRegister.delete("id = ?", otRegId);
				}
				irToDelete.delete();
			}
			Base.commitTransaction();
		} catch (SQLException e) {
			Base.rollbackTransaction();
			e.printStackTrace();
		} finally {
			Base.close();
		}
	}

	public List<DTOIndoorRegister> getIndoorRegistersByMonth(String month, String year, String dr) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			LazyList<AJIndoorRegister> ajIndoorRegisters = null;
			System.out.println("dr = " + dr);
			boolean drFilter = false;
			int drId = 0;
			if (dr != null && !dr.isEmpty()) {
				try {
					drId = Integer.parseInt(dr);
					drFilter = true;
				} catch (NumberFormatException nfe) {
					drFilter = false;
				}
			}
			
			if (drFilter) {
				ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_BY_MONTH_DR, month, year, drId);
			} else {
				ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_BY_MONTH, month, year);
			}

			System.out.println("Number of records found:" + ajIndoorRegisters.size());
			return new IndoorRegisterTransformer().transformList(ajIndoorRegisters, true);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
	
	public void update(DTOIndoorRegister register) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJIndoorRegister irToUpdate = new AJIndoorRegister();
			irToUpdate.setId(register.getId());
			irToUpdate.setDate(AJIndoorRegister.ADMIT_DATE, register.getAdmitDate());
			irToUpdate.setDate(AJIndoorRegister.DISCHARGE_DATE, register.getDischargeDate());
			irToUpdate.setString(AJIndoorRegister.PATIENT_NAME, register.getPatientName());
			irToUpdate.setString(AJIndoorRegister.PATIENT_ADDRESS, register.getPatientAddress());
			irToUpdate.setInteger(AJIndoorRegister.AGE, register.getAge());
			irToUpdate.setString(AJIndoorRegister.GENDER, register.getGender());
			irToUpdate.setString(AJIndoorRegister.DIAGNOSIS, register.getDiagnosis());
			irToUpdate.setDouble(AJIndoorRegister.FEES, register.getFees());
			irToUpdate.setString(AJIndoorRegister.REMARKS, register.getRemarks());
			irToUpdate.setInteger(AJIndoorRegister.DR_ID, register.getDrId());
			
			irToUpdate.save();
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void assign(int id, int drId) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			AJIndoorRegister irToAssign = AJIndoorRegister.findById(id);
			if(irToAssign != null) {
				irToAssign.setInteger(AJIndoorRegister.DR_ID, drId);
				irToAssign.save();
			}
			Base.commitTransaction();
		} catch (SQLException e) {
			Base.rollbackTransaction();
			e.printStackTrace();
		} finally {
			Base.close();
		}
	}
	
	public void insertWithMtpAndOTRegister(DTOIndoorRegister dtoIndoorRegister, DTOMtpRegister dtoMtpRegister, DTOOTRegister dtootRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			MTPRegisterDao mtpRegisterDao = new MTPRegisterDao();
			
			int mtpId = mtpRegisterDao.insert(dtoMtpRegister);
			dtoIndoorRegister.setMtpRegisterId(mtpId);
			System.out.println("MTP register id inserted:" + mtpId);
			
			OTRegisterDao otRegisterDao = new OTRegisterDao();
			dtootRegister.setOperationDate(dtoMtpRegister.getOperationDate());
			int otId = otRegisterDao.insert(dtootRegister);
			System.out.println("OT register id inserted:" + otId);
			dtoIndoorRegister.setOtRegisterId(otId);
			
			insert(dtoIndoorRegister);
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void insertWithOTRegister(DTOIndoorRegister dtoIndoorRegister, DTOOTRegister dtootRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			OTRegisterDao otRegisterDao = new OTRegisterDao();
			int otId = otRegisterDao.insert(dtootRegister);
			System.out.println("OT register id inserted:" + otId);
			dtoIndoorRegister.setOtRegisterId(otId);
			insert(dtoIndoorRegister);
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void insertWithDeliveryAndOTRegister(DTOIndoorRegister dtoIndoorRegister, DTODeliveryRegister deliveryRegister, DTOOTRegister dtootRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			DeliveryRegisterDao deliveryRegisterDao = new DeliveryRegisterDao();
			int id = deliveryRegisterDao.insert(deliveryRegister);
			dtoIndoorRegister.setDeliveryRegisterId(id);
			System.out.println("Delivery register id inserted:" + id);
			
			OTRegisterDao otRegisterDao = new OTRegisterDao();
			dtootRegister.setOperationDate(deliveryRegister.getDeliveryDate());
			int otId = otRegisterDao.insert(dtootRegister);
			System.out.println("OT register id inserted:" + otId);
			dtoIndoorRegister.setOtRegisterId(otId);
			insert(dtoIndoorRegister);
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void insertWithDeliveryRegister(DTOIndoorRegister dtoIndoorRegister, DTODeliveryRegister deliveryRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			DeliveryRegisterDao deliveryRegisterDao = new DeliveryRegisterDao();
			int id = deliveryRegisterDao.insert(deliveryRegister);
			dtoIndoorRegister.setDeliveryRegisterId(id);
			System.out.println("Delivery register id inserted:" + id);
			insert(dtoIndoorRegister);
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void insertWithMtpRegister(DTOIndoorRegister dtoIndoorRegister, DTOMtpRegister dtoMtpRegister) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			MTPRegisterDao mtpRegisterDao = new MTPRegisterDao();
			int id = mtpRegisterDao.insert(dtoMtpRegister);
			System.out.println("MTP register id inserted:" + id);
			dtoIndoorRegister.setMtpRegisterId(id);
			insert(dtoIndoorRegister);
			Base.commitTransaction();
		} catch (Throwable t) {
			t.printStackTrace();
			Base.rollbackTransaction();
		} finally {
			Base.close();
		}
	}
	
	public void insert(DTOIndoorRegister dtoIndoorRegister) {
			AJIndoorRegister irToInsert = new AJIndoorRegister();
			irToInsert.setDate(AJIndoorRegister.ADMIT_DATE, dtoIndoorRegister.getAdmitDate());
			irToInsert.setDate(AJIndoorRegister.DISCHARGE_DATE, dtoIndoorRegister.getDischargeDate());
			irToInsert.setString(AJIndoorRegister.PATIENT_NAME, dtoIndoorRegister.getPatientName());
			irToInsert.setString(AJIndoorRegister.PATIENT_ADDRESS, dtoIndoorRegister.getPatientAddress());
			irToInsert.setInteger(AJIndoorRegister.AGE, dtoIndoorRegister.getAge());
			irToInsert.setString(AJIndoorRegister.GENDER, dtoIndoorRegister.getGender());
			irToInsert.setString(AJIndoorRegister.DIAGNOSIS, dtoIndoorRegister.getDiagnosis());
			irToInsert.setString(AJIndoorRegister.TREATMENT, dtoIndoorRegister.getTreatment());
			irToInsert.setDouble(AJIndoorRegister.FEES, dtoIndoorRegister.getFees());
			irToInsert.setString(AJIndoorRegister.REMARKS, dtoIndoorRegister.getRemarks());
			
			irToInsert.setInteger(AJIndoorRegister.DELIVERY_REGISTER_ID, dtoIndoorRegister.getDeliveryRegisterId());
			if(dtoIndoorRegister.getOtRegisterId() > 0) {
				irToInsert.setInteger(AJIndoorRegister.OT_REGISTER_ID, dtoIndoorRegister.getOtRegisterId());
			}
			irToInsert.setInteger(AJIndoorRegister.MTP_REGISTER_ID, dtoIndoorRegister.getMtpRegisterId());
			
			irToInsert.save();
			
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
	
	public AJIndoorRegister getIndoorRegisterForDeliveryReport(int deliveryRegisterId) {
		LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister
				.findBySQL(AJIndoorRegister.SELECT_FOR_DELIVERY_REPORT,
						deliveryRegisterId);
		if (!ajIndoorRegisters.isEmpty()) {
			return ajIndoorRegisters.get(0);
		}

		return null;
	}
	
	public AJIndoorRegister getIndoorRegisterForMtpReport(int mtpRegisterId) {
		LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_FOR_MTP_REPORT, mtpRegisterId);
		if(!ajIndoorRegisters.isEmpty()) {
			return ajIndoorRegisters.get(0);
		}

		return null;
	}
	
	public AJIndoorRegister getIndoorRegisterForOtReport(int otRegisterId) {
		LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_FOR_OT_REPORT, otRegisterId);
		if(!ajIndoorRegisters.isEmpty()) {
			return ajIndoorRegisters.get(0);
		}
		return null;
	}

	public List<DTOIndoorRegister> getIncompleteIndoorRegister() {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(true);
			Base.openTransaction();

			System.out.println("request to fetch incomplete indoor report");
			LazyList<AJIndoorRegister> ajIndoorRegisters = AJIndoorRegister.findBySQL(AJIndoorRegister.SELECT_INCOMPLETE);
			System.out.println("Number of records found:" + ajIndoorRegisters.size());
			return new IndoorRegisterTransformer().transformList(ajIndoorRegisters, true);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			Base.close();
		}

		return null;
	}
}

