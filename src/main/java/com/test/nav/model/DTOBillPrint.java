package com.test.nav.model;

import java.util.Date;

public class DTOBillPrint {

	private long billNo;
	private Date billDate;
	private String patientName;
	private String sex;
	private int age;
	private String diagnosis;
	private Date admitDate;
	private Date operationDate;
	private Date dischargeDate;

	private int indoorCharges;
	private int sonography;
	private int consultantCharges;
	private int bloodTransmissionCharges;
	private int procedureCharges;
	private int operationCharges;
	private int episiotomyCharges;
	private int nursingCharges;
	private int otCharges;
	private int otherCharges;
	private String billType;
	private String chequeNo;

	private int total;
	private String inWords;

	public long getBillNo() {
		return billNo;
	}

	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public int getIndoorCharges() {
		return indoorCharges;
	}

	public void setIndoorCharges(int indoorCharges) {
		this.indoorCharges = indoorCharges;
	}

	public int getSonography() {
		return sonography;
	}

	public void setSonography(int sonography) {
		this.sonography = sonography;
	}

	public int getConsultantCharges() {
		return consultantCharges;
	}

	public void setConsultantCharges(int consultantCharges) {
		this.consultantCharges = consultantCharges;
	}

	public int getBloodTransmissionCharges() {
		return bloodTransmissionCharges;
	}

	public void setBloodTransmissionCharges(int bloodTransmissionCharges) {
		this.bloodTransmissionCharges = bloodTransmissionCharges;
	}

	public int getProcedureCharges() {
		return procedureCharges;
	}

	public void setProcedureCharges(int procedureCharges) {
		this.procedureCharges = procedureCharges;
	}

	public int getOperationCharges() {
		return operationCharges;
	}

	public void setOperationCharges(int operationCharges) {
		this.operationCharges = operationCharges;
	}

	public int getEpisiotomyCharges() {
		return episiotomyCharges;
	}

	public void setEpisiotomyCharges(int episiotomyCharges) {
		this.episiotomyCharges = episiotomyCharges;
	}

	public int getNursingCharges() {
		return nursingCharges;
	}

	public void setNursingCharges(int nursingCharges) {
		this.nursingCharges = nursingCharges;
	}

	public int getOtCharges() {
		return otCharges;
	}

	public void setOtCharges(int otCharges) {
		this.otCharges = otCharges;
	}

	public int getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(int otherCharges) {
		this.otherCharges = otherCharges;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getInWords() {
		return inWords;
	}

	public void setInWords(String inWords) {
		this.inWords = inWords;
	}
}
