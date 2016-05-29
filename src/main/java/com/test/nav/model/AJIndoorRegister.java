package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("indoor_register")
public class AJIndoorRegister extends Model{
	
	public static final String ID = "id";
	public static final String IPD_NO = "ipd_no";
	public static final String SERIAL_NO = "serial_no";
	public static final String ADMIT_DATE = "admit_date";
	public static final String DISCHARGE_DATE = "discharge_date";
	public static final String PATIENT_NAME = "patient_name";
	public static final String GENDER = "gender";
	public static final String PATIENT_ADDRESS = "address";
	public static final String AGE = "age";
	public static final String DIAGNOSIS = "diagnosis";
	public static final String TREATMENT = "treatment";
	public static final String REMARKS = "remarks";
	public static final String FEES = "fees";
	public static final String IS_BILL_GENERATED = "is_bill_generated";
	public static final String DELIVERY_REGISTER_ID = "delivery_register_id";
	public static final String MTP_REGISTER_ID = "mtp_register_id";
	public static final String OT_REGISTER_ID = "ot_register_id";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";
	
	public static String SELECT_BY_MONTH = "SELECT id, admit_date, discharge_date, patient_name, "
			+ "gender, address, age, diagnosis, treatment, fees, is_bill_generated, remarks, delivery_register_id, ot_register_id, "
			+ "mtp_register_id FROM indoor_register WHERE date_format(discharge_date, '%b') = ? and "
			+ "date_format(discharge_date, '%Y') = ? order by discharge_date asc";
	
	public static String SELECT_INCOMPLETE = "SELECT id, admit_date, discharge_date, patient_name, "
			+ "gender, address, age, diagnosis, treatment, fees, is_bill_generated, remarks, delivery_register_id, ot_register_id, "
			+ "mtp_register_id FROM indoor_register WHERE discharge_date IS null";
	
	public static String SELECT_FOR_DELIVERY_REPORT = "SELECT patient_name, gender, address, age, diagnosis, "
			+ "treatment FROM indoor_register WHERE delivery_register_id = ?";
	
	public static String SELECT_FOR_MTP_REPORT = "SELECT admit_date, discharge_date, patient_name, gender, address, age, diagnosis, "
			+ "treatment FROM indoor_register WHERE mtp_register_id = ?";
	
	public static String SELECT_FOR_OT_REPORT = "SELECT patient_name, gender, address, age, diagnosis, "
			+ "treatment, mtp_register_id FROM indoor_register WHERE ot_register_id = ?";

}
