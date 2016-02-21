package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.LazyList;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.AJOTRegister;
import com.test.nav.model.DTOOTRegister;

public class OTRegisterTransformer {

	public List<DTOOTRegister> transformList(LazyList<AJOTRegister> ajotRegisters, boolean b) {
		IndoorRegisterDao irDao = new IndoorRegisterDao();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		List<DTOOTRegister> otrs = new ArrayList<DTOOTRegister>();
		Map<String, List<Long>> serialNoList = new HashMap<String, List<Long>>();
		for (AJOTRegister ajotRegister : ajotRegisters) {
			DTOOTRegister dtootRegister = transform(ajotRegister);
			Long serialNo;
			String key = dateFormatter.format(ajotRegister.getDate(AJOTRegister.OPERATION_DATE));
			if(serialNoList.containsKey(key)) {
				List<Long> existingIds = serialNoList.get(key);
				serialNo = existingIds.get(existingIds.size() - 1) + 1;
				existingIds.add(serialNo);
				Collections.sort(existingIds);
				serialNoList.put(key, existingIds);
			} else {
				serialNo = Long.parseLong(key + "01");
				List<Long> newIds = new ArrayList<Long>();
				newIds.add(serialNo);
				//Collections.sort(newIds);
				serialNoList.put(key, newIds);
			}
			dtootRegister.setSerialNo(serialNo);
			AJIndoorRegister ajIndoorRegister = irDao.getIndoorRegisterForOtReport(dtootRegister.getId());
			if(ajIndoorRegister != null) {
				dtootRegister.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME));
				dtootRegister.setPatientAddress(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_ADDRESS));
				dtootRegister.setAge(ajIndoorRegister.getInteger(AJIndoorRegister.AGE));
				dtootRegister.setDiagnosis(ajIndoorRegister.getString(AJIndoorRegister.DIAGNOSIS));
				String treatment = ajIndoorRegister.getString(AJIndoorRegister.TREATMENT);
				dtootRegister.setTreatment(treatment);
				if (treatment.contains("MTP")) {
					int mtpRegisterId = ajIndoorRegister.getInteger(AJIndoorRegister.MTP_REGISTER_ID);
					LazyList<AJMtpRegister> mtpRegister = AJMtpRegister.findBySQL("select mtp_serial_no from mtp_register where id = ?", mtpRegisterId);
					if(!mtpRegister.isEmpty()) {
						dtootRegister.setMtpSerialNo(""+mtpRegister.get(0).getInteger(AJMtpRegister.MTP_SERIAL_NO));
					}
				}
				
				if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("male")) {
					dtootRegister.setGender("M");
				} else if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("female")) {
					dtootRegister.setGender("F");
				} else {
					dtootRegister.setGender("");
				}
				otrs.add(dtootRegister);
			}
		}
		return otrs;
	}

	public DTOOTRegister transform(AJOTRegister ajotRegister) {
		DTOOTRegister dtootRegister = new DTOOTRegister();
		dtootRegister.setId(ajotRegister.getInteger(AJOTRegister.ID));
		dtootRegister.setNameOfSurgeon(ajotRegister.getString(AJOTRegister.NAME_OF_SURGEON));
		dtootRegister.setAnaesthetist(ajotRegister.getString(AJOTRegister.ANAESTHETIST));
		dtootRegister.setAssistant(ajotRegister.getString(AJOTRegister.ASSISTANT));
		dtootRegister.setOperationDate(ajotRegister.getDate(AJOTRegister.OPERATION_DATE));
		System.out.println(dtootRegister.toString());
		return dtootRegister;
	}

}
