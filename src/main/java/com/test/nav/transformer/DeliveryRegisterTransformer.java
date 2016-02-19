package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.LazyList;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.AJDeliveryRegister;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.DTODeliveryRegister;

public class DeliveryRegisterTransformer {

	public List<DTODeliveryRegister> transformList(LazyList<AJDeliveryRegister> ajDeliveryRegisters, boolean b) {
		IndoorRegisterDao irDao = new IndoorRegisterDao();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		Map<String, List<Long>> serialNoList = new HashMap<String, List<Long>>();
		List<DTODeliveryRegister> deliveryRegisters = new ArrayList<DTODeliveryRegister>();
		for (AJDeliveryRegister ajDeliveryRegister : ajDeliveryRegisters) {
			DTODeliveryRegister deliveryRegister = transform(ajDeliveryRegister);
			Long serialNo;
			String key = dateFormatter.format(ajDeliveryRegister.getDate(AJDeliveryRegister.DELIVERY_DATE));
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
				serialNoList.put(key, newIds);
			}
			deliveryRegister.setSerialNo(serialNo);
			AJIndoorRegister ajIndoorRegister = irDao.getIndoorRegisterForDeliveryReport(deliveryRegister.getId());
			deliveryRegister.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME));
			deliveryRegister.setPatientAddress(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_ADDRESS));
			deliveryRegister.setAge(ajIndoorRegister.getInteger(AJIndoorRegister.AGE));
			deliveryRegister.setDiagnosis(ajIndoorRegister.getString(AJIndoorRegister.DIAGNOSIS));
			deliveryRegister.setTreatment(ajIndoorRegister.getString(AJIndoorRegister.TREATMENT));
			if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("male")) {
				deliveryRegister.setGender("M");
			} else if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("female")) {
				deliveryRegister.setGender("F");
			} else {
				deliveryRegister.setGender("");
			}
			
			deliveryRegisters.add(deliveryRegister);
		}
		return deliveryRegisters;
	}
	
	public DTODeliveryRegister transform(AJDeliveryRegister ajDeliveryRegister) {
		DTODeliveryRegister dtoDeliveryRegister = new DTODeliveryRegister();
		dtoDeliveryRegister.setId(ajDeliveryRegister.getInteger(AJDeliveryRegister.ID));
		dtoDeliveryRegister.setDeliveryDate(ajDeliveryRegister.getDate(AJDeliveryRegister.DELIVERY_DATE));
		dtoDeliveryRegister.setEpisiotomy(ajDeliveryRegister.getString(AJDeliveryRegister.EPISIOTOMY));
		dtoDeliveryRegister.setDeliveryType(ajDeliveryRegister.getString(AJDeliveryRegister.DELIVERY_TYPE));
		dtoDeliveryRegister.setSexOfChild(ajDeliveryRegister.getString(AJDeliveryRegister.SEX_OF_CHILD));
		dtoDeliveryRegister.setBirthWeight(ajDeliveryRegister.getDouble(AJDeliveryRegister.BIRTH_WEIGHT));
		dtoDeliveryRegister.setBirthTime(ajDeliveryRegister.getString(AJDeliveryRegister.BIRTH_TIME));
		dtoDeliveryRegister.setIndication(ajDeliveryRegister.getString(AJDeliveryRegister.INDICATION));
		dtoDeliveryRegister.setDeliveryRemarks(ajDeliveryRegister.getString(AJDeliveryRegister.REMARKS));
		
		return dtoDeliveryRegister;
	}

}
