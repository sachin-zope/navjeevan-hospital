package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("delivery_register")
public class AJDeliveryRegister extends Model {

	public static final String ID = "id";
	public static final String DELIVERY_DATE = "delivery_date";
	public static final String EPISIOTOMY = "episiotomy";
	public static final String DELIVERY_TYPE = "delivery_type";
	public static final String SEX_OF_CHILD = "sex_of_child";
	public static final String BIRTH_WEIGHT = "birth_weight";
	public static final String BIRTH_TIME = "birth_time";
	public static final String INDICATION = "indication";
	public static final String REMARKS = "remarks";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";

	public static final String SELECT_BY_MONTH = "SELECT id, delivery_date, episiotomy, delivery_type, "
			+ "sex_of_child, birth_weight, birth_time, indication, remarks FROM delivery_register WHERE "
			+ "date_format(delivery_date, '%b') = ? AND date_format(delivery_date, '%Y') = ? order by delivery_date asc";
}