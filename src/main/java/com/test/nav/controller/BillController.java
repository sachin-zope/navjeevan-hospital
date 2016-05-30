package com.test.nav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.nav.dao.BillDao;
import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.DTOBill;
import com.test.nav.model.DTOBillPrint;
import com.test.nav.model.DTOBillReceipt;
import com.test.nav.model.DTOIndoorRegister;

/**
 * Servlet implementation class BillController
 */
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String BILL_FINAL = "/bill_final.jsp";
	private static final String BILLS_BY_MONTH = "/bills_by_month.jsp";
	private static final String BILL_RECEIPT = "/bill_receipt.jsp";
	private static final String BILL_PRINT = "/bill_print.jsp";
    
	private IndoorRegisterDao indoorRegisterDao;
	private BillDao billDao;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BillController() {
        super();
        indoorRegisterDao = new IndoorRegisterDao();
        billDao = new BillDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		System.out.println("get action = " + action);

		if (action != null && !action.isEmpty()) {
			if (action.equalsIgnoreCase("bills_by_month")) {
				forward = BILLS_BY_MONTH;
				List<DTOBill> listBills = billDao.getBillsByMonth();
				request.setAttribute("bills", listBills);
			} else if(action.equalsIgnoreCase("view")) {
				
			} else if(action.equalsIgnoreCase("print")) {
				forward = BILL_PRINT;
				int id = Integer.parseInt(request.getParameter("id"));
				DTOBillPrint dtoBillPrint = billDao.getBillToPrint(id);
				request.setAttribute("print", dtoBillPrint);
			} else if(action.equalsIgnoreCase("receipt")) {
				forward = BILL_RECEIPT;
				int id = Integer.parseInt(request.getParameter("id"));
				DTOBillReceipt dtoBillReceipt = billDao.generateReceipt(id);
				request.setAttribute("receipt", dtoBillReceipt);
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		System.out.println("get action = " + action);

		if (action != null && !action.isEmpty()) {
			if (action.equalsIgnoreCase("generate")) {
				forward = BILL_FINAL;
				String roomType = request.getParameter("room_type");
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("Generating bill for id:" + id + " and RoomType:" + roomType);
				
				DTOIndoorRegister ir = indoorRegisterDao.getIndoorRegisterById(id);
				ir.setIpdNo(Long.parseLong(request.getParameter("ipdno")));
				DTOBill bill = billDao.generateBill(roomType, ir);
				request.setAttribute("bill", bill);
			} else if (action.equalsIgnoreCase("save")) {
				forward = BILLS_BY_MONTH;
				DTOBill dtoBill = populateBillFromRequest(request);
				billDao.save(dtoBill);
			} 
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private DTOBill populateBillFromRequest(HttpServletRequest request) {
		DTOBill dtoBill = new DTOBill();
		String roomType = request.getParameter("room_type");
		long serialNo = Long.parseLong(request.getParameter("serial_no"));
		int indoorRegisterId = getParam(request.getParameter("indoorId"));
		int indoorCharges = getParam(request.getParameter("indoor_hospital_charges"));
		int sonography = getParam(request.getParameter("sonography"));
		int consultantCharges = getParam(request.getParameter("consultant_charges"));
		int bloodTransmissionCharges = getParam(request.getParameter("blood_transfusion_charges"));
		int procedureCharges = getParam(request.getParameter("procedure_charges"));
		int operationCharges = getParam(request.getParameter("operation_charges"));
		int episiotomyCharges = getParam(request.getParameter("episiotomy_charges"));
		int nursingCharges = getParam(request.getParameter("nursing_charges"));
		int otCharges = getParam(request.getParameter("ot_charges"));
		int otherCharges = getParam(request.getParameter("other_charges")); 
		
		dtoBill.setIndoorRegisterId(indoorRegisterId);
		dtoBill.setRoomType(roomType);
		dtoBill.setSerialNo(serialNo);
		dtoBill.setIndoorCharges(indoorCharges);
		dtoBill.setSonography(sonography);
		dtoBill.setConsultantCharges(consultantCharges);
		dtoBill.setBloodTransmissionCharges(bloodTransmissionCharges);
		dtoBill.setProcedureCharges(procedureCharges);
		dtoBill.setOperationCharges(operationCharges);
		dtoBill.setEpisiotomyCharges(episiotomyCharges);
		dtoBill.setNursingCharges(nursingCharges);
		dtoBill.setOtCharges(otCharges);
		dtoBill.setOtherCharges(otherCharges);
		return dtoBill;
	}
	
	private int getParam(String param) {
		try {
			if (param == null || param.isEmpty()) {
				return 0;
			}
			return Integer.parseInt(param);
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
