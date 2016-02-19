package com.test.nav.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.OTRegisterDao;
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
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
