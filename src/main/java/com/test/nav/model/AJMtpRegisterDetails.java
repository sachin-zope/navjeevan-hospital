package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("mtp_register_details")
public class AJMtpRegisterDetails extends Model{
	
	public static final String ID = "id";
	public static final String MTP_REGISTER_ID = "mtp_register_id";
	public static final String PATIENT_NAME = "patient_name";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String AGE = "age";
	public static final String FEES = "fees";
	public static final String REMARKS = "remarks";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";

}