package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.LazyList;

import com.test.nav.dao.MTPRegisterDao;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTOIndoorRegister;

public class IndoorRegisterTransformer {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<DTOIndoorRegister> transformList(LazyList<AJIndoorRegister> ajIndoorRegisters, boolean generateSerial) {
		SimpleDateFormat ipdFormatter = new SimpleDateFormat("yyyyMMdd");
		List<DTOIndoorRegister> irs = new ArrayList<DTOIndoorRegister>();
		Map<String, List<Long>> ipdList = new HashMap<String, List<Long>>();
		MTPRegisterDao mtpRegisterDao = new MTPRegisterDao();
		int serialNo = 1;
		for (AJIndoorRegister ajRegister : ajIndoorRegisters) {
			DTOIndoorRegister register = transform(ajRegister); 
			register.setIpdNo(1l);
			register.setSerialNo(serialNo++);
			Long ipdNo;
			String key = ipdFormatter.format(ajRegister.getDate(AJIndoorRegister.ADMIT_DATE));
			if(ipdList.containsKey(key)) {
				List<Long> existingIds = ipdList.get(key);
				ipdNo = existingIds.get(existingIds.size() - 1) + 1;
				existingIds.add(ipdNo);
				Collections.sort(existingIds);
				ipdList.put(key, existingIds);
			} else {
				ipdNo = Long.parseLong(key + "01");
				List<Long> newIds = new ArrayList<Long>();
				newIds.add(ipdNo);
				//Collections.sort(newIds);
				ipdList.put(key, newIds);
			}
			register.setIpdNo(ipdNo);
			System.out.println(register.toString());
			Integer mtpRegId = ajRegister.getInteger(AJIndoorRegister.MTP_REGISTER_ID);
			if(mtpRegId != null) {
				register.setMtpSerialNo(mtpRegisterDao.getMtpSerialNo(mtpRegId));
			}
			irs.add(register);
		}
		Collections.sort(irs);
		return irs;
	}
	
	public DTOIndoorRegister transform(AJIndoorRegister ajIndoorRegister) {
		DTOIndoorRegister register = new DTOIndoorRegister();
		register.setId(ajIndoorRegister.getInteger(AJIndoorRegister.ID));
		//register.setIpdNo(ajIndoorRegister.getLong(AJIndoorRegister.IPD_NO));
		//register.setSerialNo(ajIndoorRegister.getInteger(AJIndoorRegister.SERIAL_NO));
		
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
