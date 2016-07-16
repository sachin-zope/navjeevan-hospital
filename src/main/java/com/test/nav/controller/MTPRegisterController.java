package com.test.nav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.MTPRegisterDao;
import com.test.nav.model.DTOMtpRegister;
import com.test.nav.model.DTOMtpRegisterDetails;
import com.test.nav.util.AppUtil;

/**
 * Servlet implementation class MTPRegisterController
 */
public class MTPRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MTPRegisterDao mtpRegisterDao;
	private static final String INSERT = "mtp_register.jsp";
	private static final String EDIT = "edit_mtp_register.jsp";
	private static final String REPORT = "mtp_register_report.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MTPRegisterController() {
		super();
		mtpRegisterDao = new MTPRegisterDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = REPORT;
		String action = request.getParameter("action");
		System.out.println("action = " + action);

		if (action.equalsIgnoreCase("report")) {
			String month = request.getParameter("month");
			String year = request.getParameter("year");

			if (month == null || month.isEmpty()) {
				month = AppUtil.getCurrentMonth();
			}

			if (year == null || year.isEmpty()) {
				year = AppUtil.getCurrentYear().toString();
			}
			System.out.println("Month:" + month + " Year: " + year);
			HttpSession session = request.getSession();
			session.setAttribute("REPORT_MONTH", month);
			session.setAttribute("REPORT_YEAR", year);
			request.setAttribute("mtprs", mtpRegisterDao.getMtpRegisterByMonth(month, year));
		} else if (action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("request to edit ot register for id:" + id);
			forward = EDIT;
			request.setAttribute("mtpr", mtpRegisterDao.getMtpRegisterById(id));
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		System.out.println("action = " + action);

		if(action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("editing mtp register for id:" + id);
			DTOMtpRegister mtpRegister = generateMTPRegister(request);
			mtpRegister.setId(id);
			mtpRegisterDao.update(mtpRegister);
			HttpSession session = request.getSession();
			String month = session.getAttribute("REPORT_MONTH").toString();
			String year = session.getAttribute("REPORT_YEAR").toString();
			forward = REPORT;
			request.setAttribute("mtprs", mtpRegisterDao.getMtpRegisterByMonth(month, year));
		} else if(action.equalsIgnoreCase("add")) {
			DTOMtpRegisterDetails dtoMtpRegisterDetails = generateMTPRegisterDetails(request);
			DTOMtpRegister dtoMtpRegister = generateMTPRegisterForDetails(request);
			mtpRegisterDao.insertWithDetails(dtoMtpRegister, dtoMtpRegisterDetails);
			forward = INSERT;
			request.setAttribute("RESP", "success");
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	private DTOMtpRegisterDetails generateMTPRegisterDetails(HttpServletRequest request) {
		DTOMtpRegisterDetails details = new DTOMtpRegisterDetails();
		details.setpName(request.getParameter("pName"));
		details.setGender(request.getParameter("gender"));
		details.setpAddress(request.getParameter("pAddress"));
		details.setAge(Integer.parseInt(request.getParameter("age")));
		details.setRemarks(request.getParameter("remarks"));
		
		String fees = request.getParameter("fees");
		if(fees != null && fees.length() > 0) {
			details.setFees(Double.valueOf(fees));
		}
		
		return details;
	}
	
	private DTOMtpRegister generateMTPRegisterForDetails(HttpServletRequest request) { 
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTOMtpRegister mtpRegister = new DTOMtpRegister();
		mtpRegister.setDurationOfPregnancy(Integer.parseInt(request.getParameter("durationOfPregnancy")));
		String strMtpSerialNo = request.getParameter("mtpSerialNo");
		if (strMtpSerialNo != null && !strMtpSerialNo.isEmpty()) {
			try {
				mtpRegister.setMtpSerialNo(Integer.parseInt(strMtpSerialNo));
			} catch (NumberFormatException nfe) {
				mtpRegister.setMtpSerialNo(0);
			}
		}
		mtpRegister.setReligion(request.getParameter("religion"));
		mtpRegister.setMarried(request.getParameter("married"));
		mtpRegister.setMindication(request.getParameter("mindication"));
		
		String procedure = request.getParameter("procedure");
		mtpRegister.setProcedure(procedure);
		if(procedure.equalsIgnoreCase("Medication abortion")) {
			mtpRegister.setBatchNo(request.getParameter("batchNo"));
		} 
		mtpRegister.setAlongWith(request.getParameter("alongWith"));
		String strMChildrens = request.getParameter("mChildrens"); 
		int mChildrens =  (strMChildrens != null && strMChildrens.length() > 0 ) ? Integer.parseInt(strMChildrens) : 0;  
		mtpRegister.setmChildrens(mChildrens);
		
		String strFChildrens = request.getParameter("fChildrens");
		int fChildrens = (strFChildrens != null && strFChildrens.length() > 0 ? Integer.parseInt(strFChildrens) : 0);
		mtpRegister.setfChildrens(fChildrens);
		
		mtpRegister.setDoneByDr(request.getParameter("doneby"));
		mtpRegister.setOpinionGivenBy(request.getParameter("opinionby"));
		
		Date opdDate;
		try {
			opdDate = dateFormatter.parse(request.getParameter("opdDate"));
			mtpRegister.setOperationDate(opdDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return mtpRegister;
	}
	
	private DTOMtpRegister generateMTPRegister(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTOMtpRegister mtpRegister = new DTOMtpRegister();
		mtpRegister.setDurationOfPregnancy(Integer.parseInt(request.getParameter("durationOfPregnancy")));
		String strMtpSerialNo = request.getParameter("mtpSerialNo");
		if (strMtpSerialNo != null && !strMtpSerialNo.isEmpty()) {
			try {
				mtpRegister.setMtpSerialNo(Integer.parseInt(strMtpSerialNo));
			} catch (NumberFormatException nfe) {
				mtpRegister.setMtpSerialNo(0);
			}
		}
		mtpRegister.setReligion(request.getParameter("religion"));
		mtpRegister.setMarried(request.getParameter("married"));
		mtpRegister.setMindication(request.getParameter("mindication"));
		mtpRegister.setProcedure(request.getParameter("procedure"));
		mtpRegister.setAlongWith(request.getParameter("alongWith"));
		String strMChildrens = request.getParameter("mChildrens"); 
		int mChildrens =  (strMChildrens != null && strMChildrens.length() > 0 ) ? Integer.parseInt(strMChildrens) : 0;  
		mtpRegister.setmChildrens(mChildrens);
		
		String strFChildrens = request.getParameter("fChildrens");
		int fChildrens = (strFChildrens != null && strFChildrens.length() > 0 ? Integer.parseInt(strFChildrens) : 0);
		mtpRegister.setfChildrens(fChildrens);
		
		mtpRegister.setDoneByDr(request.getParameter("doneby"));
		mtpRegister.setOpinionGivenBy(request.getParameter("opinionby"));
		
		Date operationDate;
		try {
			operationDate = dateFormatter.parse(request.getParameter("mtpOperationDate"));
			mtpRegister.setOperationDate(operationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return mtpRegister;
	}

}
