package com.test.nav.model;

import java.util.Date;

public class OTRegister {

	private int id;
	private Long serialNo;
	private String nameOfSurgeon;
	private String assistant;
	private String anaesthetist;
	private Date operationDate;
	private Date createDate;
	private Date updateDate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
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
		return "OTRegister [id=" + id + ", serialNo= " + serialNo + ", nameOfSurgeon=" + nameOfSurgeon
				+ ", assistant=" + assistant + ", anaesthetist=" + anaesthetist
				+ ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
}
