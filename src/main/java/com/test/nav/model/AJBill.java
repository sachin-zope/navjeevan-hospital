package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("bill")
public class AJBill extends Model{
	  
	public static final String ID = "id";
	public static final String INDOOR_REGISTER_ID = "indoor_register_id";
	public static final String SERIAL_NO = "serial_no";
	public static final String ROOM_TYPE = "room_type";
	public static final String INDOOR_HOSPITAL_CHARGES = "indoor_hospital_charges";
	public static final String SONOGRAPHY = "sonography";
	public static final String CONSULTANT_CHARGES = "consultant_charges";
	public static final String BLOOD_TRANSFUSION_CHARGES = "blood_transfusion_charges";
	public static final String PROCEDURE_CHARGES = "procedure_charges";
	public static final String OPERATION_CHARGES = "operation_charges";
	public static final String EPISIOTOMY_CHARGES = "episiotomy_charges";
	public static final String NURSING_CHARGES = "nursing_charges";
	public static final String OT_CHARGES = "ot_charges";
	public static final String OTHER_CHARGES = "other_charges";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";
	public static final String BILL_TYPE = "bill_type";
	public static final String CHEQUE_NO = "cheque_no";
}
