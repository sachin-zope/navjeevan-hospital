package com.test.nav.model;

import java.util.Date;

public class DTOIndoorRegister {

	private int id;
	private Long ipdNo;
	private int serialNo;
	private Date admitDate;
	private Date dischargeDate;
	private String patientName;
	private String gender;
	private String patientAddress;
	private int age;
	private String diagnosis;
	private String treatment;
	private String remarks;
	private double fees;
	private DeliveryRegister deliveryRegister;
	private MTPRegister mtpRegister;
	private OTRegister otRegister;
	private Date createDate;
	private Date updateDate;

	public DTOIndoorRegister() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Long getIpdNo() {
		return ipdNo;
	}

	public void setIpdNo(Long ipdNo) {
		this.ipdNo = ipdNo;
	}
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}
	
	public DeliveryRegister getDeliveryRegister() {
		return deliveryRegister;
	}

	public void setDeliveryRegister(DeliveryRegister deliveryRegister) {
		this.deliveryRegister = deliveryRegister;
	}

	public MTPRegister getMtpRegister() {
		return mtpRegister;
	}

	public void setMtpRegister(MTPRegister mtpRegister) {
		this.mtpRegister = mtpRegister;
	}
	
	public OTRegister getOtRegister() {
		return otRegister;
	}

	public void setOtRegister(OTRegister otRegister) {
		this.otRegister = otRegister;
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

	@Override
	public String toString() {
		return "IndoorRegister [id=" + id + ", ipdno= " + ipdNo + ", admitDate=" + admitDate
				+ ", dischargeDate=" + dischargeDate + ", patientName=" + patientName
				+ ", gender=" + gender + ", patientAddress=" + patientAddress
				+ ", diagnosis=" + diagnosis + ", treatment=" + treatment + ", remarks="
				+ remarks + ", fees=" + fees
				+ "]";
	}
}
