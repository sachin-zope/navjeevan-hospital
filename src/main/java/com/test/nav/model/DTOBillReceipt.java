package com.test.nav.model;

import java.util.Date;

public class DTOBillReceipt {

	private int receiptNo;
	private Date receiptDate;
	private String patientName;
	private int totalBill;
	private String inWords;

	public int getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}

	public String getInWords() {
		return inWords;
	}

	public void setInWords(String inWords) {
		this.inWords = inWords;
	}

}
