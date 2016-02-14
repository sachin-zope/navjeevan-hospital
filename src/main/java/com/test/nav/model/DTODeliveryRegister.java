package com.test.nav.model;

import java.util.Date;

public class DTODeliveryRegister {
	
	private int id;
	private Date deliveryDate;
	private String episiotomy;
	private String deliveryType;
	private String sexOfChild;
	private double birthWeight;
	private String birthTime;
	private String indication;
	private String deliveryRemarks;
	private Date createDate;
	private Date updateDate;
	
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEpisiotomy() {
		return episiotomy;
	}

	public void setEpisiotomy(String episiotomy) {
		this.episiotomy = episiotomy;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getSexOfChild() {
		return sexOfChild;
	}

	public void setSexOfChild(String sexOfChild) {
		this.sexOfChild = sexOfChild;
	}

	public double getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}

	public String getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getDeliveryRemarks() {
		return deliveryRemarks;
	}

	public void setDeliveryRemarks(String deliveryRemarks) {
		this.deliveryRemarks = deliveryRemarks;
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
		return "DTODeliveryRegister [id=" + id + ", deliveryDate=" + deliveryDate + ", episiotomy=" + episiotomy
				+ ", deliveryType=" + deliveryType + ", sexOfChild=" + sexOfChild + ", birthWeight=" + birthWeight
				+ ", birthTime=" + birthTime + ", indication=" + indication + ", deliveryRemarks=" + deliveryRemarks
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", patientName=" + patientName + ", gender="
				+ gender + ", patientAddress=" + patientAddress + ", age=" + age + ", diagnosis=" + diagnosis + ", treatment="
				+ treatment + "]";
	}

}
