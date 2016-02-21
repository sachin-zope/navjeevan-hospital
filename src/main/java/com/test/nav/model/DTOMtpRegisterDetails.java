package com.test.nav.model;

import java.util.Date;

public class DTOMtpRegisterDetails {
	private int id;
	private String pName;
	private String gender;
	private String pAddress;
	private int age;
	private String remarks;
	private double fees;
	private int mtpRegisterId;
	private Date createDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
	
	public int getMtpRegisterId() {
		return mtpRegisterId;
	}

	public void setMtpRegisterId(int mtpRegisterId) {
		this.mtpRegisterId = mtpRegisterId;
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
		return "MTPRegisterDetails [id=" + id + ", pName=" + pName
				+ ", gender=" + gender + ", pAddress=" + pAddress + ", age="
				+ age + ", remarks=" + remarks + ", fees=" + fees
				+ ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}

}
