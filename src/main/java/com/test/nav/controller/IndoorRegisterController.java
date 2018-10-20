package com.test.nav.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.nav.dao.IndoorRegisterDao;
import com.test.nav.model.DTODeliveryRegister;
import com.test.nav.model.DTOIndoorRegister;
import com.test.nav.model.DTOMtpRegister;
import com.test.nav.model.DTOOTRegister;
import com.test.nav.util.AppUtil;
import com.test.nav.util.ReportWritingUtils;

/**
 * Servlet implementation class IndoorRegisterController
 */
public class IndoorRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/indoor_register.jsp";
	private static String EDIT = "/edit_indoor_register.jsp";
	private static String INDOOR_REPORT = "/indoor_register_report.jsp";
	private static String INCOMPLETE_INDOOR_REPORT = "/incomplete_indoor_register_report.jsp";
	private static String PRINT_INDOOR_REGISTER = "/print_indoor_register.jsp";
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		boolean isForward = true;
		String action = request.getParameter("action");
		System.out.println("get action = " + action);

		if (action != null && !action.isEmpty()) {
			if (action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("Request to delete indoor register for id:" + id);
				indoorRegisterDao.deleteIndoorRegister(id);
				forward = INDOOR_REPORT;
				HttpSession session = request.getSession();
				String month = session.getAttribute("REPORT_MONTH").toString();
				String year = session.getAttribute("REPORT_YEAR").toString();
				String dr = session.getAttribute("REPORT_DR").toString();
				request.setAttribute("irs", indoorRegisterDao.getIndoorRegistersByMonth(month, year, dr));
				request.setAttribute("drName", getDrName(dr));
			} else if (action.equalsIgnoreCase("edit")) {
				forward = EDIT;
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("request to edit indoor register for id:" + id);
				DTOIndoorRegister ir = indoorRegisterDao.getIndoorRegisterById(id);
				HttpSession session = request.getSession();
				session.setAttribute("FROM", request.getParameter("from"));
				request.setAttribute("ir", ir);
			} else if (action.equalsIgnoreCase("assign")) {
				forward = INDOOR_REPORT;
				int id = Integer.parseInt(request.getParameter("id"));
				int drId = Integer.parseInt(request.getParameter("dr"));
				System.out.println("Request to assign indoor register of id:" + id + " to Dr:" + drId);
				indoorRegisterDao.assign(id, drId);
				
				HttpSession session = request.getSession();
				String month = session.getAttribute("REPORT_MONTH").toString();
				String year = session.getAttribute("REPORT_YEAR").toString();
				String dr = session.getAttribute("REPORT_DR").toString();
				request.setAttribute("irs", indoorRegisterDao.getIndoorRegistersByMonth(month, year, dr));
				request.setAttribute("drName", getDrName(dr));
			} else if (action.equalsIgnoreCase("report")) {

				String type = request.getParameter("type");
				List<DTOIndoorRegister> indoorList = null;
				if (type != null && !type.isEmpty()) {
					if (type.equalsIgnoreCase("complete")) {
						String month = request.getParameter("month");
						String year = request.getParameter("year");
						String dr = request.getParameter("dr");
						
						if (month == null || month.isEmpty()) {
							month = AppUtil.getCurrentMonth();
						}

						if (year == null || year.isEmpty()) {
							year = AppUtil.getCurrentYear().toString();
						}
						System.out.println("Month:" + month + " Year: " + year + " Dr: " + dr);
						HttpSession session = request.getSession();
						session.removeAttribute("REPORT_DR");
						
						session.setAttribute("REPORT_MONTH", month);
						session.setAttribute("REPORT_YEAR", year);
						session.setAttribute("REPORT_DR", dr);
						forward = INDOOR_REPORT;
						indoorList = indoorRegisterDao.getIndoorRegistersByMonth(month, year, dr);
						session.setAttribute("INDOOR_LIST", indoorList);
						request.setAttribute("drName", getDrName(dr));
					} else if (type.equalsIgnoreCase("incomplete")) {
						indoorList = indoorRegisterDao.getIncompleteIndoorRegister();
						forward = INCOMPLETE_INDOOR_REPORT;
					}
				}

				request.setAttribute("irs", indoorList);
				
			} else if (action.equalsIgnoreCase("print")) { 
				forward = PRINT_INDOOR_REGISTER;
				HttpSession session = request.getSession();
				List<DTOIndoorRegister> indoorList = (List<DTOIndoorRegister>) session.getAttribute("INDOOR_LIST");
				request.setAttribute("irs", indoorList);
			} else if (action.equalsIgnoreCase("download")) {
				HttpSession session = request.getSession();
				ServletContext context = getServletContext();
				List<DTOIndoorRegister> indoorList = (List<DTOIndoorRegister>) session.getAttribute("INDOOR_LIST");
				String month = session.getAttribute("REPORT_MONTH").toString();
				String year = session.getAttribute("REPORT_YEAR").toString();
				
				// write to file
				String fileDir = context.getRealPath("/downloads");
				String fileName = "IndoorRegister_" + month + year + ".xlsx";
				// For windows the path should be "\" 
				String filePath = fileDir + "\\" + fileName;
		        System.out.println("writing file  = " + filePath);
		        
		        new ReportWritingUtils().write(filePath, indoorList);
		        
		        File downloadFile = new File(filePath);
		        FileInputStream inStream = new FileInputStream(downloadFile);
		        String mimeType = context.getMimeType(filePath);
		        if (mimeType == null) {        
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		        System.out.println("MIME type: " + mimeType);
		         
		        // modifies response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		        
		        // forces download
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		         
		        // obtains response's output stream
		        OutputStream outStream = response.getOutputStream();
		         
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		         
		        inStream.close();
		        outStream.close();     
		        isForward = false;
			} else {
				forward = INSERT;
			}
		} else {
			forward = INSERT;
		}

		if (isForward) {
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String forward = "";
			String action = request.getParameter("action");
			System.out.println("action = " + action);

			DTOIndoorRegister ir = generateIndoorRegister(request);

			if (action.equalsIgnoreCase("add")) {
				String treatment = request.getParameter("treatment");
				if (treatment.equalsIgnoreCase("other")) {
					ir.setTreatment(request.getParameter("OtherTreatment"));
				} else {
					ir.setTreatment(treatment);
				}

				if (treatment.equalsIgnoreCase("mtp")) {
					DTOMtpRegister mtpRegister = generateMTPRegister(request);
					indoorRegisterDao.insertWithMtpRegister(ir, mtpRegister);
				} else if (treatment.equalsIgnoreCase("mtp + abdominal tubectomy")
						|| treatment.equalsIgnoreCase("mtp + laparoscopic tubectomy")) {
					DTOMtpRegister mtpRegister = generateMTPRegisterForTubectomy(request);
					DTOOTRegister otRegister = generateOTRegisterForTubectomy(request);
					indoorRegisterDao.insertWithMtpAndOTRegister(ir, mtpRegister, otRegister);
				} else if (treatment.equalsIgnoreCase("delivery")) {
					DTODeliveryRegister deliveryRegister = generateDeliveryRegister(request);
					indoorRegisterDao.insertWithDeliveryRegister(ir, deliveryRegister);
				} else if (treatment.equalsIgnoreCase("lscs") || treatment.equalsIgnoreCase("LSCS with Tubectomy")) {
					DTODeliveryRegister deliveryRegister = generateDeliveryRegisterForLSCS(request);
					DTOOTRegister otRegister = generateOTRegisterForLSCS(request);
					indoorRegisterDao.insertWithDeliveryAndOTRegister(ir, deliveryRegister, otRegister);
				} else if (treatment.equalsIgnoreCase("other")) {
					DTOOTRegister dtootRegister = generateOTRegister(request);
					indoorRegisterDao.insertWithOTRegister(ir, dtootRegister);
				} else {
					DTOOTRegister dtootRegister = generateOTRegister(request);
					indoorRegisterDao.insertWithOTRegister(ir, dtootRegister);
				}
				forward = INSERT;
				request.setAttribute("RESP", "success");
			} else if (action.equalsIgnoreCase("edit")) {
				indoorRegisterDao.update(ir);
				HttpSession session = request.getSession();
				String month = session.getAttribute("REPORT_MONTH").toString();
				String year = session.getAttribute("REPORT_YEAR").toString();
				String dr = session.getAttribute("REPORT_DR").toString();
				
				String from = session.getAttribute("FROM") != null ? session.getAttribute("FROM").toString() : null;
				if(from == null) {
					forward = INDOOR_REPORT;
					request.setAttribute("irs", indoorRegisterDao.getIndoorRegistersByMonth(month, year, dr));
				} else if (from.equalsIgnoreCase("complete")) {
					forward = INDOOR_REPORT;
					request.setAttribute("irs", indoorRegisterDao.getIndoorRegistersByMonth(month, year, dr));
					request.setAttribute("drName", getDrName(dr));
				} else {
					forward = INCOMPLETE_INDOOR_REPORT;
					request.setAttribute("irs", indoorRegisterDao.getIncompleteIndoorRegister());
				}
				System.out.println("From: " + from);
			}

			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		} catch (Throwable t) {
			t.printStackTrace();
			request.setAttribute("RESP", "error");
			request.setAttribute("ERROR", "Record Not Inserted, please try again!");
			RequestDispatcher view = request.getRequestDispatcher(INSERT);
			view.forward(request, response);
		}
	}

	private DTOOTRegister generateOTRegisterForLSCS(HttpServletRequest request) {
		DTOOTRegister otRegister = new DTOOTRegister();
		otRegister.setNameOfSurgeon(request.getParameter("NameOfSurgeonForLSCS"));
		otRegister.setAssistant(request.getParameter("assistantForLSCS"));
		otRegister.setAnaesthetist(request.getParameter("anaesthetistForLSCS"));
		return otRegister;
	}

	private DTODeliveryRegister generateDeliveryRegisterForLSCS(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTODeliveryRegister deliveryRegister = new DTODeliveryRegister();

		try {
			String deliveryDate = request.getParameter("deliveryDateForLSCS");
			if (deliveryDate != null && !deliveryDate.isEmpty()) {
				deliveryRegister.setDeliveryDate(dateFormatter.parse(deliveryDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String episiotomy = (request.getParameter("episiotomyForLSCS") != null) ? "Given" : null;
		deliveryRegister.setEpisiotomy(episiotomy);
		deliveryRegister.setDeliveryType(request.getParameter("deliveryTypeForLSCS"));
		deliveryRegister.setSexOfChild(request.getParameter("sexOfChildForLSCS"));
		deliveryRegister.setBirthWeight(Double.valueOf(request.getParameter("birthWeightForLSCS")));
		deliveryRegister.setBirthTime(request.getParameter("birthTimeForLSCS"));
		String indication = request.getParameter("indicationForLSCS");
		if (indication != null && indication.equalsIgnoreCase("other")) {
			deliveryRegister.setIndication(request.getParameter("otherIndicationForLSCS"));
		} else {
			deliveryRegister.setIndication(indication);
		}
		deliveryRegister.setDeliveryRemarks(request.getParameter("deliveryRemarksForLSCS"));
		return deliveryRegister;
	}

	private DTODeliveryRegister generateDeliveryRegister(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTODeliveryRegister deliveryRegister = new DTODeliveryRegister();

		try {
			String deliveryDate = request.getParameter("deliveryDate");
			if (deliveryDate != null && !deliveryDate.isEmpty())
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
		if (indication != null && indication.equalsIgnoreCase("other")) {
			deliveryRegister.setIndication(request.getParameter("otherIndication"));
		} else {
			deliveryRegister.setIndication(indication);
		}
		deliveryRegister.setDeliveryRemarks(request.getParameter("deliveryRemarks"));

		return deliveryRegister;
	}

	private DTOMtpRegister generateMTPRegisterForTubectomy(HttpServletRequest request) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DTOMtpRegister mtpRegister = new DTOMtpRegister();
		mtpRegister.setDurationOfPregnancy(Integer.parseInt(request.getParameter("MTdurationOfPregnancy")));
		String strMtpSerialNo = request.getParameter("MTmtpSerialNo");
		if (strMtpSerialNo != null && !strMtpSerialNo.isEmpty()) {
			try {
				mtpRegister.setMtpSerialNo(Integer.parseInt(strMtpSerialNo));
			} catch (NumberFormatException nfe) {
				mtpRegister.setMtpSerialNo(0);
			}
		}
		
		mtpRegister.setReligion(request.getParameter("MTreligion"));
		mtpRegister.setMarried(request.getParameter("MTmarried"));
		mtpRegister.setMindication(request.getParameter("MTmindication"));
		mtpRegister.setProcedure(request.getParameter("MTprocedure"));
		mtpRegister.setAlongWith(request.getParameter("MTalongWith"));
		String strMChildrens = request.getParameter("MTmChildrens");
		int mChildrens = (strMChildrens != null && strMChildrens.length() > 0) ? Integer.parseInt(strMChildrens) : 0;
		mtpRegister.setmChildrens(mChildrens);

		String strFChildrens = request.getParameter("MTfChildrens");
		int fChildrens = (strFChildrens != null && strFChildrens.length() > 0 ? Integer.parseInt(strFChildrens) : 0);
		mtpRegister.setfChildrens(fChildrens);

		mtpRegister.setDoneByDr(request.getParameter("MTdoneby"));
		mtpRegister.setOpinionGivenBy(request.getParameter("MTopinionby"));

		Date operationDate;
		try {
			operationDate = dateFormatter.parse(request.getParameter("MTmtpOperationDate"));
			mtpRegister.setOperationDate(operationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mtpRegister;
	}

	private DTOOTRegister generateOTRegisterForTubectomy(HttpServletRequest request) {
		DTOOTRegister otRegister = new DTOOTRegister();
		otRegister.setNameOfSurgeon(request.getParameter("MTdoneby"));
		otRegister.setAssistant(request.getParameter("assistantForTubectomy"));
		otRegister.setAnaesthetist(request.getParameter("anaesthetistForTubectomy"));
		return otRegister;
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
		int mChildrens = (strMChildrens != null && strMChildrens.length() > 0) ? Integer.parseInt(strMChildrens) : 0;
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

		return otRegister;
	}

	private DTOIndoorRegister generateIndoorRegister(HttpServletRequest request) {
		DTOIndoorRegister ir = new DTOIndoorRegister();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			ir.setId(Integer.parseInt(id));
		}
		try {
			ir.setAdmitDate(dateFormatter.parse(request.getParameter("admitDate")));
			String strDischargeDate = request.getParameter("dischargeDate");
			if (strDischargeDate != null && !strDischargeDate.isEmpty()) {
				ir.setDischargeDate(dateFormatter.parse(strDischargeDate));
			}
		} catch (ParseException pe) {
			System.out.println("error in parsing discharge date");
		}
		ir.setPatientName(request.getParameter("pName"));
		ir.setGender(request.getParameter("gender"));
		ir.setPatientAddress(request.getParameter("pAddress"));
		ir.setAge(Integer.parseInt(request.getParameter("age")));

		String diagnosis = request.getParameter("diagnosis");
		if (diagnosis.equalsIgnoreCase("other")) {
			ir.setDiagnosis(request.getParameter("OtherDiagnosis"));
		} else {
			ir.setDiagnosis(diagnosis);
		}
		
		String drId = request.getParameter("drId");
		if (drId != null) {
			try {
				int intId = Integer.parseInt(drId);
				if (intId > 0) {
					ir.setDrId(intId);
				}
			} catch (NumberFormatException nfe) {
				System.out.println("invalid doctor id");
			}
		}

		ir.setRemarks(request.getParameter("remarks"));

		String fees = request.getParameter("fees");
		if (fees != null && fees.length() > 0) {
			ir.setFees(Double.valueOf(fees));
		}

		return ir;
	}
	
	private String getDrName(String dr) {
		try {
			if (dr.equalsIgnoreCase("1")) {
				return "Dr. Narendra";
			} else if (dr.equalsIgnoreCase("2")) {
				return "Dr. Smita";
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
}
