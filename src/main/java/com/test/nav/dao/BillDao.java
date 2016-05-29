package com.test.nav.dao;

import java.sql.Connection;
import java.util.ResourceBundle;

import org.javalite.activejdbc.Base;

import com.test.nav.model.AJBill;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOBill;
import com.test.nav.model.DTODeliveryRegister;
import com.test.nav.model.DTOIndoorRegister;
import com.test.nav.util.DbUtil;

public class BillDao {
	
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("charges");

	public void save(DTOBill dtoBill) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setReadOnly(false);
			Base.openTransaction();
			
			AJBill ajBill = new AJBill();
			ajBill.setInteger(AJBill.INDOOR_REGISTER_ID, dtoBill.getIndoorRegisterId());
			ajBill.setLong(AJBill.SERIAL_NO, dtoBill.getSerialNo());
			ajBill.setString(AJBill.ROOM_TYPE, dtoBill.getRoomType());
			ajBill.setInteger(AJBill.INDOOR_HOSPITAL_CHARGES, dtoBill.getIndoorCharges());
			ajBill.setInteger(AJBill.SONOGRAPHY, dtoBill.getSonography());
			ajBill.setInteger(AJBill.CONSULTANT_CHARGES, dtoBill.getConsultantCharges());
			ajBill.setInteger(AJBill.BLOOD_TRANSFUSION_CHARGES, dtoBill.getBloodTransmissionCharges());
			ajBill.setInteger(AJBill.PROCEDURE_CHARGES, dtoBill.getProcedureCharges());
			ajBill.setInteger(AJBill.OPERATION_CHARGES, dtoBill.getOperationCharges());
			ajBill.setInteger(AJBill.EPISIOTOMY_CHARGES, dtoBill.getEpisiotomyCharges());
			ajBill.setInteger(AJBill.NURSING_CHARGES, dtoBill.getNursingCharges());
			ajBill.setInteger(AJBill.OT_CHARGES, dtoBill.getOtCharges());
			ajBill.setInteger(AJBill.OTHER_CHARGES, dtoBill.getOtherCharges());
			
			double total = dtoBill.getIndoorCharges() + dtoBill.getSonography() + dtoBill.getConsultantCharges()
					+ dtoBill.getBloodTransmissionCharges() + dtoBill.getProcedureCharges()
					+ dtoBill.getOperationCharges() + dtoBill.getEpisiotomyCharges() + dtoBill.getNursingCharges()
					+ dtoBill.getOtCharges() + dtoBill.getOtherCharges();
			if (ajBill.save()) {
				AJIndoorRegister ajIndoorRegister = new AJIndoorRegister();
				ajIndoorRegister.setId(dtoBill.getIndoorRegisterId());
				ajIndoorRegister.setBoolean(AJIndoorRegister.IS_BILL_GENERATED, true);
				ajIndoorRegister.setDouble(AJIndoorRegister.FEES, total);
				ajIndoorRegister.save();
			}
			
			Base.commitTransaction();
		} catch (Throwable t) {
			Base.rollbackTransaction();
			t.printStackTrace();
		} finally {
			Base.close();
		}
	}
	
	public DTOBill generateBill(String roomType, DTOIndoorRegister indoorRegister) {
		DTOBill dtoBill = new DTOBill();
		long diff = indoorRegister.getDischargeDate().getTime() - indoorRegister.getAdmitDate().getTime();
		int totalDays = (int) diff / (24 * 60 * 60 * 1000);
		int fees = (int) indoorRegister.getFees();
		
		int roomCharges;
		switch (roomType) {
		case "general":
			roomCharges = getCharges("general_room_charges");
			break;
		case "semi-special":
			roomCharges = getCharges("semi_special_room_charges");
			break;

		default:
			roomCharges = 500;
			break;
		}
		
		int indoorCharges = totalDays * roomCharges;
		int consultantCharges = totalDays * getCharges("consultant_charges");
		int nursingCharges = totalDays * getCharges("nursing_charges");
		
		DeliveryRegisterDao deliveryRegisterDao = new DeliveryRegisterDao();
		System.out.println("check#1:" + indoorRegister.getDeliveryRegisterId());
		DTODeliveryRegister dtoDeliveryRegister = deliveryRegisterDao.getDeliveryRegisterById(indoorRegister.getDeliveryRegisterId());
		
		int episiotomyCharges = 0;
		if (dtoDeliveryRegister.getEpisiotomy() != null && dtoDeliveryRegister.getEpisiotomy().equals("Given")) {
			episiotomyCharges = getCharges("episiotomy_charges");
		}
		
		int procedureCharges = 0;
		int otCharges = 0;
		int otherCharges = 0;
		switch (indoorRegister.getTreatment()) {
		case "Delivery":
			procedureCharges = fees - (indoorCharges + consultantCharges + nursingCharges + episiotomyCharges);
			break;

		case "LSCS":
			otCharges = getCharges("lscs_ot_charges");
			break;
		case "LSCS with Tubectomy":
			otCharges = getCharges("lscs_tubectomy_ot_charges");
			break;
		case "MTP":
			otCharges = getCharges("mtp_ot_charges");
			break;
		case "MTP + Abdominal Tubectomy":
			otCharges = getCharges("mtp_abdominal_tubectomy_ot_charges");
			break;
		case "MTP + Laparoscopic Tubectomy":
			otCharges = getCharges("mtp_laparoscopic_tubectomy_ot_charges");
			break;
		case "Tubectomy":
			otCharges = getCharges("tubectomy_ot_charges");
			break;
		case "Abdominal Tubectomy":
			otCharges = getCharges("abdominal_tubectomy_ot_charges");
			break;
		case "Laparoscopic Tubectomy":
			otCharges = getCharges("laparoscopic_tubectomy_ot_charges");
			break;
		case "Abdominal Hysterectomy":
			otCharges = getCharges("abdominal_hysterectomy_ot_charges");
			break;
		case "Vaginal Hysterectomy":
			otCharges = getCharges("vaginal_hysterectomy_ot_charges");
			break;
		case "Diagnostic Laparohysteroscopy":
			otCharges = getCharges("diagnostic_laparohysteroscopy_ot_charges");
			break;
		case "Laparoscopic Hysterectomy":
			otCharges = getCharges("laparoscopic_hysterectomy_ot_charges");
			break;
		case "D and E":
			otCharges = getCharges("de_ot_charges");
			break;
		case "Cervical Encirclage":
			otCharges = getCharges("cervical_encirclage_ot_charges");
			break;
		
		default:
			otCharges = getCharges("other_charges");
			break;
		}
		
		int operationCharges = fees - (indoorCharges + consultantCharges + nursingCharges + otCharges); 
				
		dtoBill.setRoomType(roomType);
		dtoBill.setIndoorRegisterId(indoorRegister.getId());
		dtoBill.setSerialNo(indoorRegister.getIpdNo());
		dtoBill.setIndoorCharges(indoorCharges);
		dtoBill.setConsultantCharges(consultantCharges);
		dtoBill.setProcedureCharges(procedureCharges);
		dtoBill.setOperationCharges(operationCharges);
		dtoBill.setNursingCharges(nursingCharges);
		dtoBill.setEpisiotomyCharges(episiotomyCharges);
		dtoBill.setOtCharges(otCharges);
		dtoBill.setOtherCharges(otherCharges);
		int total = indoorCharges + consultantCharges + procedureCharges + operationCharges + nursingCharges + episiotomyCharges + otCharges;
		dtoBill.setTotal(total);
		return dtoBill;
	}
	
	private int getCharges(String key) {
		String charge = resourceBundle.getString(key);
		try {
			return Integer.parseInt(charge);
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
