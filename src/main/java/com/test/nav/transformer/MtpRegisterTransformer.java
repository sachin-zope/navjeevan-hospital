package com.test.nav.transformer;

import java.util.ArrayList;
import java.util.List;

import org.javalite.activejdbc.LazyList;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.DTOMtpRegister;

public class MtpRegisterTransformer {

	public List<DTOMtpRegister> transformList(LazyList<AJMtpRegister> ajMtpRegisters) {
		IndoorRegisterDao irDao = new IndoorRegisterDao();
		List<DTOMtpRegister> mtpRegisters = new ArrayList<DTOMtpRegister>();
		int monthlySerialNo = 0;
		for (AJMtpRegister ajMtpRegister : ajMtpRegisters) {
			DTOMtpRegister mtpRegister = transform(ajMtpRegister);
			AJIndoorRegister ajIndoorRegister = irDao.getIndoorRegisterForMtpReport(mtpRegister.getId());
			mtpRegister.setPatientName(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_NAME));
			mtpRegister.setPatientAddress(ajIndoorRegister.getString(AJIndoorRegister.PATIENT_ADDRESS));
			mtpRegister.setAge(ajIndoorRegister.getInteger(AJIndoorRegister.AGE));
			mtpRegister.setDiagnosis(ajIndoorRegister.getString(AJIndoorRegister.DIAGNOSIS));
			mtpRegister.setTreatment(ajIndoorRegister.getString(AJIndoorRegister.TREATMENT));
			mtpRegister.setAdmitDate(ajIndoorRegister.getDate(AJIndoorRegister.ADMIT_DATE));
			mtpRegister.setDischargeDate(ajIndoorRegister.getDate(AJIndoorRegister.DISCHARGE_DATE));
			if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("male")) {
				mtpRegister.setGender("M");
			} else if (ajIndoorRegister.getString(AJIndoorRegister.GENDER).equalsIgnoreCase("female")) {
				mtpRegister.setGender("F");
			} else {
				mtpRegister.setGender("");
			}
			monthlySerialNo = monthlySerialNo + 1;
			mtpRegister.setMonthlySerialNo(monthlySerialNo);
			mtpRegisters.add(mtpRegister);
		}
		return mtpRegisters;
	}
	
	public DTOMtpRegister transform(AJMtpRegister ajMtpRegister) {
		DTOMtpRegister dtoMtpRegister = new DTOMtpRegister();
		dtoMtpRegister.setId(ajMtpRegister.getInteger(AJMtpRegister.ID));
		dtoMtpRegister.setMtpSerialNo(ajMtpRegister.getInteger(AJMtpRegister.MTP_SERIAL_NO));
		dtoMtpRegister.setMindication(ajMtpRegister.getString(AJMtpRegister.INDICATION));
		dtoMtpRegister.setBatchNo(ajMtpRegister.getString(AJMtpRegister.BATCH_NO));
		dtoMtpRegister.setDurationOfPregnancy(ajMtpRegister.getInteger(AJMtpRegister.DURATION_OF_PREGNANCY));
		dtoMtpRegister.setmChildrens(ajMtpRegister.getInteger(AJMtpRegister.MALE_CHILDRENS));
		dtoMtpRegister.setfChildrens(ajMtpRegister.getInteger(AJMtpRegister.FEMALE_CHILDRENS));
		dtoMtpRegister.setOperationDate(ajMtpRegister.getDate(AJMtpRegister.OPERATION_DATE));
		dtoMtpRegister.setDoneByDr(ajMtpRegister.getString(AJMtpRegister.DONE_BY_DR));
		dtoMtpRegister.setOpinionGivenBy(ajMtpRegister.getString(AJMtpRegister.OPINION_GIVEN_BY));
		
		return dtoMtpRegister;
	}

	
}
