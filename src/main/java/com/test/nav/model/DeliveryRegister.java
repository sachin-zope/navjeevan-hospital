package com.test.nav.model;

import java.util.Date;

public class DeliveryRegister {
	
	private int id;
	private Long serialNo;
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

	@Override
	public String toString() {
		return "DeliveryRegister [id=" + id + ", deliveryDate=" + deliveryDate
				+ ", episiotomy=" + episiotomy + ", deliveryType="
				+ deliveryType + ", sexOfChild=" + sexOfChild
				+ ", birthWeight=" + birthWeight + ", birthTime=" + birthTime
				+ ", indication=" + indication + ", deliveryRemarks="
				+ deliveryRemarks + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
