package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.nav.model.AJBill;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOBill;
import com.test.nav.model.DTOBillPrint;
import com.test.nav.model.DTOBillReceipt;
import com.test.nav.util.NumberToWord;

public class BillTransformer {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<DTOBill> transformList(List<AJBill> ajBills) {
		List<DTOBill> dtoBills = new ArrayList<>();
		for(AJBill ajBill : ajBills) {
			DTOBill dtoBill = tranform(ajBill);
			dtoBills.add(dtoBill);
		}
		return dtoBills;
	}
	
	public DTOBill tranform(AJBill ajBill) {
		DTOBill dtoBill = new DTOBill();
		dtoBill.setId(ajBill.getInteger(AJBill.ID));
		dtoBill.setIndoorRegisterId(ajBill.getInteger(AJBill.INDOOR_REGISTER_ID));
		dtoBill.setSerialNo(ajBill.getLong(AJBill.SERIAL_NO));
		dtoBill.setIndoorCharges(ajBill.getInteger(AJBill.INDOOR_HOSPITAL_CHARGES));
		dtoBill.setSonography(ajBill.getInteger(AJBill.SONOGRAPHY));
		dtoBill.setConsultantCharges(ajBill.getInteger(AJBill.CONSULTANT_CHARGES));
		dtoBill.setBloodTransmissionCharges(ajBill.getInteger(AJBill.BLOOD_TRANSFUSION_CHARGES));
		dtoBill.setProcedureCharges(ajBill.getInteger(AJBill.PROCEDURE_CHARGES));
		dtoBill.setOperationCharges(ajBill.getInteger(AJBill.OPERATION_CHARGES));
		dtoBill.setEpisiotomyCharges(ajBill.getInteger(AJBill.EPISIOTOMY_CHARGES));
		dtoBill.setNursingCharges(ajBill.getInteger(AJBill.NURSING_CHARGES));
		dtoBill.setOtCharges(ajBill.getInteger(AJBill.OT_CHARGES));
		dtoBill.setOtherCharges(ajBill.getInteger(AJBill.OTHER_CHARGES));
		dtoBill.setBillType(ajBill.getString(AJBill.BILL_TYPE));
		dtoBill.setChequeNo(ajBill.getString(AJBill.CHEQUE_NO));
		dtoBill.setTotal(calculateTotal(dtoBill));
		return dtoBill;
	}

	private int calculateTotal(DTOBill dtoBill) {
		return dtoBill.getIndoorCharges() + dtoBill.getSonography() + dtoBill.getConsultantCharges()
				+ dtoBill.getBloodTransmissionCharges() + dtoBill.getProcedureCharges() + dtoBill.getOperationCharges()
				+ dtoBill.getEpisiotomyCharges() + dtoBill.getNursingCharges() + dtoBill.getOtCharges()
				+ dtoBill.getOtherCharges();
	}

	public DTOBillPrint tranformBillToPrint(AJBill ajBill, AJIndoorRegister ajIndoorRegister, Date operationDate) {
		DTOBillPrint dtoBillPrint = new DTOBillPrint();
		dtoBillPrint.setBillNo(ajBill.getLong(AJBill.SERIAL_NO));
		dtoBillPrint.setBillDate(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE));
		
		dtoBillPrint.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME).trim());
		dtoBillPrint.setSex(ajIndoorRegister.getString(AJIndoorRegister.GENDER).toUpperCase());
		dtoBillPrint.setAge(ajIndoorRegister.getInteger(AJIndoorRegister.AGE));
		dtoBillPrint.setDiagnosis(ajIndoorRegister.getString(AJIndoorRegister.DIAGNOSIS));
		dtoBillPrint.setAdmitDate(ajIndoorRegister.getDate(AJIndoorRegister.ADMIT_DATE));
		dtoBillPrint.setOperationDate(operationDate);
		dtoBillPrint.setDischargeDate(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE));
		
		DTOBill dtoBill = tranform(ajBill);
		dtoBillPrint.setIndoorCharges(dtoBill.getIndoorCharges());
		dtoBillPrint.setSonography(dtoBill.getSonography());
		dtoBillPrint.setConsultantCharges(dtoBill.getConsultantCharges());
		dtoBillPrint.setBloodTransmissionCharges(dtoBill.getBloodTransmissionCharges());
		dtoBillPrint.setProcedureCharges(dtoBill.getProcedureCharges());
		dtoBillPrint.setOperationCharges(dtoBill.getOperationCharges());
		dtoBillPrint.setEpisiotomyCharges(dtoBill.getEpisiotomyCharges());
		dtoBillPrint.setNursingCharges(dtoBill.getNursingCharges());
		dtoBillPrint.setOtCharges(dtoBill.getOtCharges());
		dtoBillPrint.setOtherCharges(dtoBill.getOtherCharges());
		dtoBillPrint.setBillType(dtoBill.getBillType());
		dtoBillPrint.setChequeNo(dtoBill.getChequeNo());
		int total = dtoBill.getTotal();
		dtoBillPrint.setTotal(total);
		dtoBillPrint.setInWords(NumberToWord.convert(total).toUpperCase());
		
		return dtoBillPrint;
	}

	public DTOBillReceipt tranformBillForReceipt(AJBill ajBill, AJIndoorRegister ajIndoorRegister) {
		DTOBillReceipt billReceipt = new DTOBillReceipt();
		DTOBill dtoBill = tranform(ajBill);
		int total = dtoBill.getTotal();
		billReceipt.setTotalBill(total);
		billReceipt.setReceiptNo(ajBill.getInteger(AJBill.SERIAL_NO));
		billReceipt.setInWords(NumberToWord.convert(total).toUpperCase());
		billReceipt.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME));
		billReceipt.setReceiptDate(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE));
		return billReceipt;
	}
}
