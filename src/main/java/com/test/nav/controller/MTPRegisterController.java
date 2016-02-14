package com.test.nav.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.MTPRegisterDao;
import com.test.nav.util.AppUtil;

/**
 * Servlet implementation class MTPRegisterController
 */
public class MTPRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MTPRegisterDao mtpRegisterDao;

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
			request.setAttribute("mtprs", mtpRegisterDao.getMtpRegisterByMonth(month, year));
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
