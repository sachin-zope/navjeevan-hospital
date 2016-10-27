package com.test.nav.model;

public class DTOBill {

	private int id;
	private int indoorRegisterId;
	private Long serialNo;
	private String roomType;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndoorRegisterId() {
		return indoorRegisterId;
	}

	public void setIndoorRegisterId(int indoorRegisterId) {
		this.indoorRegisterId = indoorRegisterId;
	}

	public Long getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
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
	
}
