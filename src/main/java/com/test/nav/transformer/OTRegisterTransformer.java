package com.test.nav.transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.LazyList;

import com.test.nav.model.AJOTRegister;
import com.test.nav.model.DTOOTRegister;

public class OTRegisterTransformer {

	public List<DTOOTRegister> transformList(LazyList<AJOTRegister> ajotRegisters, boolean b) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		List<DTOOTRegister> otrs = new ArrayList<DTOOTRegister>();
		Map<String, List<Long>> serialNoList = new HashMap<String, List<Long>>();
		for (AJOTRegister ajotRegister : ajotRegisters) {
			DTOOTRegister dtootRegister = transform(ajotRegister);
		}
		return otrs;
	}

	private DTOOTRegister transform(AJOTRegister ajotRegister) {
		DTOOTRegister dtootRegister = new DTOOTRegister();
		dtootRegister.setId(ajotRegister.getInteger(AJOTRegister.ID));
		dtootRegister.setNameOfSurgeon(ajotRegister.getString(AJOTRegister.NAME_OF_SURGEON));
		dtootRegister.setAnaesthetist(ajotRegister.getString(AJOTRegister.ANAESTHETIST));
		dtootRegister.setAssistant(ajotRegister.getString(AJOTRegister.ASSISTANT));
		dtootRegister.setOperationDate(ajotRegister.getDate(AJOTRegister.OPERATION_DATE));
		return dtootRegister;
	}

}
