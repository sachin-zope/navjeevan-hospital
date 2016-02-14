package com.test.nav.model;

import java.util.Date;

public class DTOOTRegister {

	private int id;
	private String nameOfSurgeon;
	private String assistant;
	private String anaesthetist;
	private Date operationDate;
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

	public String getNameOfSurgeon() {
		return nameOfSurgeon;
	}
	
	public void setNameOfSurgeon(String nameOfSurgeon) {
		this.nameOfSurgeon = nameOfSurgeon;
	}
	
	public String getAssistant() {
		return assistant;
	}
	
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	
	public String getAnaesthetist() {
		return anaesthetist;
	}
	
	public void setAnaesthetist(String anaesthetist) {
		this.anaesthetist = anaesthetist;
	}
	
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
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
		return "DTOOTRegister [id=" + id + ", nameOfSurgeon=" + nameOfSurgeon + ", assistant=" + assistant
				+ ", anaesthetist=" + anaesthetist + ", operationDate=" + operationDate + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", patientName=" + patientName + ", gender=" + gender + ", patientAddress="
				+ patientAddress + ", age=" + age + ", diagnosis=" + diagnosis + ", treatment=" + treatment + "]";
	}

}
