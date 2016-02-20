package com.test.nav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.DeliveryRegisterDao;
import com.test.nav.model.DTODeliveryRegister;
import com.test.nav.util.AppUtil;

/**
 * Servlet implementation class DeliveryRegisterController
 */
public class DeliveryRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeliveryRegisterDao deliveryRegisterDao;
	private static final String EDIT = "edit_delivery_register.jsp";
	private static final String REPORT = "delivery_register_report.jsp";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliveryRegisterController() {
		super();
		deliveryRegisterDao = new DeliveryRegisterDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = REPORT;
		String action = request.getParameter("action");
		System.out.println("DeliveryRegisterController : action = " + action);

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
			request.setAttribute("drs", deliveryRegisterDao.getDeliveryRegisterByMonth(month, year));
		} else if (action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("request to edit delivery register for id:" + id);
			forward = EDIT;
			request.setAttribute("dr", deliveryRegisterDao.getDeliveryRegisterById(id));
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
			System.out.println("editing delivery register for id:" + id);
			DTODeliveryRegister deliveryRegister = generateDeliveryRegister(request);
			deliveryRegister.setId(id);
			deliveryRegisterDao.update(deliveryRegister);
			HttpSession session = request.getSession();
			String month = session.getAttribute("REPORT_MONTH").toString();
			String year = session.getAttribute("REPORT_YEAR").toString();
			forward = REPORT;
			request.setAttribute("drs", deliveryRegisterDao.getDeliveryRegisterByMonth(month, year));
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	private DTODeliveryRegister generateDeliveryRegister(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTODeliveryRegister deliveryRegister = new DTODeliveryRegister(); 
		
		try {
			String deliveryDate = request.getParameter("deliveryDate");
			if(deliveryDate != null && !deliveryDate.isEmpty())
			deliveryRegister.setDeliveryDate(dateFormatter.parse(deliveryDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String episiotomy = (request.getParameter("episiotomy") != null) ? "Given" : null;
		deliveryRegister.setEpisiotomy(episiotomy);
		deliveryRegister.setDeliveryType(request.getParameter("deliveryType"));
		deliveryRegister.setSexOfChild(request.getParameter("sexOfChild"));
		deliveryRegister.setBirthWeight(Double.valueOf(request.getParameter("birthWeight")));
		deliveryRegister.setBirthTime(request.getParameter("birthTime"));
		String indication = request.getParameter("indication");
		if(indication != null && indication.equalsIgnoreCase("other")) {
			deliveryRegister.setIndication(request.getParameter("otherIndication"));
		} else {
			deliveryRegister.setIndication(indication);
		}
		deliveryRegister.setDeliveryRemarks(request.getParameter("deliveryRemarks"));
		
		return deliveryRegister;
	}

}
