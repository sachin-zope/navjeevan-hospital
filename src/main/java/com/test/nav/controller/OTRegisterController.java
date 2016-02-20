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

import com.test.nav.dao.OTRegisterDao;
import com.test.nav.model.DTOOTRegister;
import com.test.nav.util.AppUtil;

/**
 * Servlet implementation class OTRegisterController
 */
public class OTRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OTRegisterDao otRegisterDao;
	private static final String EDIT = "edit_ot_register.jsp";
	private static final String REPORT = "ot_register_report.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OTRegisterController() {
		super();
		otRegisterDao = new OTRegisterDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
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
			forward = REPORT;
			request.setAttribute("otrs", otRegisterDao.getOTRegisterByMonth(month, year));
		} else if (action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("request to edit ot register for id:" + id);
			forward = EDIT;
			request.setAttribute("otr", otRegisterDao.getOTRegisterById(id));
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
			System.out.println("editing ot register for id:" + id);
			DTOOTRegister dtootRegister = generateOTRegister(request);
			dtootRegister.setId(id);
			otRegisterDao.update(dtootRegister);
			HttpSession session = request.getSession();
			String month = session.getAttribute("REPORT_MONTH").toString();
			String year = session.getAttribute("REPORT_YEAR").toString();
			forward = REPORT;
			request.setAttribute("otrs", otRegisterDao.getOTRegisterByMonth(month, year));
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	private DTOOTRegister generateOTRegister(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTOOTRegister otRegister = new DTOOTRegister();
		otRegister.setNameOfSurgeon(request.getParameter("NameOfSurgeon"));
		otRegister.setAssistant(request.getParameter("assistant"));
		otRegister.setAnaesthetist(request.getParameter("anaesthetist"));
		String strOperationDate = request.getParameter("operationDate");
		Date operationDate;
		try {
			operationDate = dateFormatter.parse(strOperationDate);
			otRegister.setOperationDate(operationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("formed ot register from request:" + otRegister.toString());
		return otRegister;
	}

}
