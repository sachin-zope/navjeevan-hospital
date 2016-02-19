package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("mtp_register")
public class AJMtpRegister extends Model {

	public static final String ID = "id";
	public static final String MTP_SERIAL_NO = "mtp_serial_no";
	public static final String RELIGION = "religion";
	public static final String INDICATION = "indication";
	public static final String MTP_PROCEDURE = "mtp_procedure";
	public static final String DURATION_OF_PREGNANCY = "duration_of_pregnancy";
	public static final String ALONG_WITH = "along_with";
	public static final String MALE_CHILDRENS = "male_childrens";
	public static final String FEMALE_CHILDRENS = "female_childrens";
	public static final String OPERATION_DATE = "operation_date";
	public static final String DONE_BY_DR = "done_by_dr";
	public static final String MARRIED = "married";
	public static final String OPINION_GIVEN_BY = "opinion_given_by";
	public static final String BATCH_NO = "batch_no";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";
	
	public static final String SELECT_SERIAL_NO = "SELECT mtp_serial_no FROM mtp_register WHERE id = ?";
	public static final String SELECT_BY_MONTH = "SELECT id, mtp_serial_no, operation_date, duration_of_pregnancy, "
			+ "indication, male_childrens, female_childrens, done_by_dr, opinion_given_by FROM mtp_register WHERE "
			+ "date_format(operation_date, '%b') = ? AND date_format(operation_date, '%Y') = ? order by operation_date asc";

}