package com.test.nav.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.AJIndoorRegister;
import com.test.nav.util.AppUtil;

/**
 * Servlet implementation class IndoorRegisterController
 */
public class IndoorRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/indoor_register.jsp";
	private static String INDOOR_REPORT = "/indoor_register_report.jsp";
	private IndoorRegisterDao indoorRegisterDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndoorRegisterController() {
		super();
		indoorRegisterDao = new IndoorRegisterDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		
		if (action != null && !action.isEmpty()) {
			if (action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("Request to delete indoor register for id:" + id);
				//indoorRegisterDao.deleteIndoorRegister(id);
				forward = INDOOR_REPORT;
				// TODO: Fetch month and year from session
				HttpSession session = request.getSession();
				String month = session.getAttribute("REPORT_MONTH").toString();
				String year = session.getAttribute("REPORT_YEAR").toString();
				request.setAttribute("irs", indoorRegisterDao
						.getAllIndoorRegisters(month, year));
			} else if (action.equalsIgnoreCase("edit")) {
				forward = INSERT_OR_EDIT;
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("request to edit indoor register for id:" + id);
				AJIndoorRegister ir = indoorRegisterDao.getIndoorRegisterById(id);
				request.setAttribute("indoor_register", ir);
			} else if (action.equalsIgnoreCase("report")) {
				forward = INDOOR_REPORT;
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
				request.setAttribute("irs", indoorRegisterDao.getAllIndoorRegisters(month, year));
			} else {
				forward = INSERT_OR_EDIT;
			}
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
