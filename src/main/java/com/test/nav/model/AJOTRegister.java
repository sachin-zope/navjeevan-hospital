package com.test.nav.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("ot_register")
public class AJOTRegister extends Model{
	
	public static final String ID = "id";
	public static final String NAME_OF_SURGEON = "name_of_surgeon";
	public static final String ASSISTANT = "assistant";
	public static final String ANAESTHETIST = "anaesthetist";
	public static final String OPERATION_DATE = "operation_date";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";

}
