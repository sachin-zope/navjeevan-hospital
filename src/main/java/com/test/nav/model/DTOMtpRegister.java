package com.test.nav.model;

import java.util.Date;

public class DTOMtpRegister {

	private int id;
	private int mtpSerialNo;
	private int monthlySerialNo;
	private String religion;
	private String married;
	private String mindication;
	private String procedure;
	private String batchNo;
	private int durationOfPregnancy;
	private String alongWith;
	private int mChildrens;
	private int fChildrens;
	private Date operationDate;
	private String doneByDr;
	private String opinionGivenBy;
	private Date createDate;
	private Date updateDate;
	
	private Date admitDate;
	private Date dischargeDate;
	private String patientName;
	private String gender;
	private String patientAddress;
	private int age;
	private String diagnosis;
	private String treatment;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMtpSerialNo() {
		return mtpSerialNo;
	}

	public void setMtpSerialNo(int mtpSerialNo) {
		this.mtpSerialNo = mtpSerialNo;
	}
	
	public int getMonthlySerialNo() {
		return monthlySerialNo;
	}

	public void setMonthlySerialNo(int monthlySerialNo) {
		this.monthlySerialNo = monthlySerialNo;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getMindication() {
		return mindication;
	}

	public void setMindication(String mindication) {
		this.mindication = mindication;
	}
	
	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getDurationOfPregnancy() {
		return durationOfPregnancy;
	}

	public void setDurationOfPregnancy(int durationOfPregnancy) {
		this.durationOfPregnancy = durationOfPregnancy;
	}

	public String getAlongWith() {
		return alongWith;
	}

	public void setAlongWith(String alongWith) {
		this.alongWith = alongWith;
	}

	public int getmChildrens() {
		return mChildrens;
	}

	public void setmChildrens(int mChildrens) {
		this.mChildrens = mChildrens;
	}

	public int getfChildrens() {
		return fChildrens;
	}

	public void setfChildrens(int fChildrens) {
		this.fChildrens = fChildrens;
	}
	
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	public String getDoneByDr() {
		return doneByDr;
	}

	public void setDoneByDr(String doneByDr) {
		this.doneByDr = doneByDr;
	}

	public String getOpinionGivenBy() {
		return opinionGivenBy;
	}

	public void setOpinionGivenBy(String opinionGivenBy) {
		this.opinionGivenBy = opinionGivenBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return "DTOMtpRegister [id=" + id + ", mtpSerialNo=" + mtpSerialNo + ", religion=" + religion + ", married="
				+ married + ", mindication=" + mindication + ", procedure=" + procedure + ", batchNo=" + batchNo
				+ ", durationOfPregnancy=" + durationOfPregnancy + ", alongWith=" + alongWith + ", mChildrens=" + mChildrens
				+ ", fChildrens=" + fChildrens + ", operationDate=" + operationDate + ", doneByDr=" + doneByDr
				+ ", opinionGivenBy=" + opinionGivenBy + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", patientName=" + patientName + ", gender=" + gender + ", patientAddress=" + patientAddress + ", age=" + age
				+ ", diagnosis=" + diagnosis + ", treatment=" + treatment + "]";
	}
}