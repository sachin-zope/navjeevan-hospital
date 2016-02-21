package com.test.nav.transformer;

import java.util.ArrayList;
import java.util.List;

import org.javalite.activejdbc.LazyList;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.dao.MTPRegisterDao;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.model.AJMtpRegister;
import com.test.nav.model.AJMtpRegisterDetails;
import com.test.nav.model.DTOMtpRegister;

public class MtpRegisterTransformer {

	public List<DTOMtpRegister> transformList(LazyList<AJMtpRegister> ajMtpRegisters) {
		IndoorRegisterDao irDao = new IndoorRegisterDao();
		List<DTOMtpRegister> mtpRegisters = new ArrayList<DTOMtpRegister>();
		int monthlySerialNo = 0;
		for (AJMtpRegister ajMtpRegister : ajMtpRegisters) {
			DTOMtpRegister mtpRegister = transform(ajMtpRegister);
			AJIndoorRegister ajIndoorRegister = irDao.getIndoorRegisterForMtpReport(mtpRegister.getId());
			if(ajIndoorRegister != null) {
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
			} else {
				MTPRegisterDao mtpRegisterDao = new MTPRegisterDao();
				AJMtpRegisterDetails ajMtpRegisterDetails = mtpRegisterDao.getMtpRegisterDetailsForMtpReport(mtpRegister.getId());
				if (ajMtpRegisterDetails != null) {
					mtpRegister.setPatientName(ajMtpRegisterDetails.getString(AJMtpRegisterDetails.PATIENT_NAME));
					mtpRegister.setPatientAddress(ajMtpRegisterDetails.getString(AJMtpRegisterDetails.ADDRESS));
					mtpRegister.setAge(ajMtpRegisterDetails.getInteger(AJMtpRegisterDetails.AGE));
					if (ajMtpRegisterDetails.getString(AJMtpRegisterDetails.GENDER).equalsIgnoreCase("male")) {
						mtpRegister.setGender("M");
					} else if (ajMtpRegisterDetails.getString(AJMtpRegisterDetails.GENDER).equalsIgnoreCase("female")) {
						mtpRegister.setGender("F");
					} else {
						mtpRegister.setGender("");
					}
				}
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
		dtoMtpRegister.setProcedure(ajMtpRegister.getString(AJMtpRegister.MTP_PROCEDURE));
		dtoMtpRegister.setAlongWith(ajMtpRegister.getString(AJMtpRegister.ALONG_WITH));
		dtoMtpRegister.setDurationOfPregnancy(ajMtpRegister.getInteger(AJMtpRegister.DURATION_OF_PREGNANCY));
		dtoMtpRegister.setmChildrens(ajMtpRegister.getInteger(AJMtpRegister.MALE_CHILDRENS));
		dtoMtpRegister.setfChildrens(ajMtpRegister.getInteger(AJMtpRegister.FEMALE_CHILDRENS));
		dtoMtpRegister.setOperationDate(ajMtpRegister.getDate(AJMtpRegister.OPERATION_DATE));
		dtoMtpRegister.setDoneByDr(ajMtpRegister.getString(AJMtpRegister.DONE_BY_DR));
		dtoMtpRegister.setOpinionGivenBy(ajMtpRegister.getString(AJMtpRegister.OPINION_GIVEN_BY));
		return dtoMtpRegister;
	}

	public DTOMtpRegister transformForEdit(AJMtpRegister ajMtpRegister) {
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
		dtoMtpRegister.setReligion(ajMtpRegister.getString(AJMtpRegister.RELIGION));
		dtoMtpRegister.setMarried(ajMtpRegister.getString(AJMtpRegister.MARRIED));
		dtoMtpRegister.setProcedure(ajMtpRegister.getString(AJMtpRegister.MTP_PROCEDURE));
		dtoMtpRegister.setAlongWith(ajMtpRegister.getString(AJMtpRegister.ALONG_WITH));
		System.out.println(dtoMtpRegister.toString());
		return dtoMtpRegister;
	}
	
}
