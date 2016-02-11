package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOIndoorRegister;

public class IndoorRegisterTransformer {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<DTOIndoorRegister> transformList(LazyList<AJIndoorRegister> ajIndoorRegisters) {
		List<DTOIndoorRegister> irs = new ArrayList<DTOIndoorRegister>();
		for (AJIndoorRegister ajRegister : ajIndoorRegisters) {
			irs.add(transform(ajRegister));
		}
		return irs;
	}
	
	public DTOIndoorRegister transform(AJIndoorRegister ajIndoorRegister) {
		DTOIndoorRegister register = new DTOIndoorRegister();
		register.setId(ajIndoorRegister.getInteger(AJIndoorRegister.ID));
		register.setIpdNo(ajIndoorRegister.getLong(AJIndoorRegister.IPD_NO));
		register.setSerialNo(ajIndoorRegister.getInteger(AJIndoorRegister.SERIAL_NO));
		
		/*register.setAdmitDate(sdf.format(ajIndoorRegister.getDate(AJIndoorRegister.ADMIT_DATE)));
		if (ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE) != null) {
			register.setDischargeDate(sdf.format(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE)));
		} else {
			register.setDischargeDate("");
		}*/
		register.setAdmitDate(ajIndoorRegister.getDate(AJIndoorRegister.ADMIT_DATE));
		register.setDischargeDate(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE));
		register.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME));
		
		if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("male")) {
			register.setGender("M");
		} else if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("female")) {
			register.setGender("F");
		} else {
			register.setGender("");
		}
		
		register.setPatientAddress(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_ADDRESS));
		register.setAge(ajIndoorRegister.getInteger(AJIndoorRegister.AGE));
		register.setDiagnosis(ajIndoorRegister.getString(AJIndoorRegister.DIAGNOSIS));
		register.setTreatment(ajIndoorRegister.getString(AJIndoorRegister.TREATMENT));
		register.setRemarks(ajIndoorRegister.getString(AJIndoorRegister.REMARKS));
		register.setFees(ajIndoorRegister.getDouble(AJIndoorRegister.FEES));
		register.setCreateDate(ajIndoorRegister.getDate(AJIndoorRegister.CREATE_DATE));
		register.setUpdateDate(ajIndoorRegister.getDate(AJIndoorRegister.UPDATE_DATE));
		return register;
	}
}
