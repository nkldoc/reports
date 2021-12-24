package com.nkl.mcot.erp.controller;
 
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import static java.lang.Class.forName;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection; ;
import java.util.HashMap;
import java.util.Map;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;
import net.sf.jasperreports.engine.JasperPrint;
import static net.sf.jasperreports.engine.JasperRunManager.runReportToPdf;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		// Do required initialization
		//Connection con = new getCon();

	}


	public static Connection getConNMU() {
		Connection con = null;
		try {
			forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=NMU_ERP";
			con = getConnection(url, "sa", "nklV1");

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}
		return con;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
//@TODO Controller Servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		try {
			switch (action) {
				/*	case "/reportImc001":
					reportImc001(request, response);
					break;
				case "/reportImc003":
					reportImc003(request, response);
					break;
				case "/getRepImc002":
					reportImc002(request, response);
					break;
				case "/reportImc0011":
					reportImc0011(request, response);
					break;
				case "/getReport":
					reportBook(request, response);
					break;*/
				case "/session":
					sessionReq(request, response);
					break;
				case "/servlet2":
					sessionRes(request, response);
					break;
				case "/LoginServlet":
					LoginServlet(request, response);
					break;
				case "/LogoutServlet":
					LogoutServlet(request, response);

					break;
				case "/ProfileServlet":
					ProfileServlet(request, response);
					break;
				case "/reportSpEmp":
					reportSpEmp(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
//@TODO PHP	
	private void LogoutServlet(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.php").include(request, response);
		HttpSession session = request.getSession();
		session.invalidate();
		out.print("You are successfully logged out!");
		out.close();

	}
	
	private void ProfileServlet(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		request.setAttribute("message", "ส่งข้อมูลไปแสดงที่ JSP massage setAttribute."); // Store error message in request scope.
		request.getRequestDispatcher("/test.jsp").forward(request, response);

	}

	private void LoginServlet(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.php").include(request, response);

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (password.equals("admin123")) {
			out.print("Welcome, " + name);
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
		} else {
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("login.php").include(request, response);
		}
		out.close();

	}

	private void sessionRes(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession(false);
			String n = (String) session.getAttribute("uname");
			out.print("Hello " + n);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void sessionReq(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String n = request.getParameter("userName");
			out.print("Welcome " + n);

			HttpSession session = request.getSession();
			session.setAttribute("uname", n);

			out.print("<a href='./servlet2'>visit</a>");

			out.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}
//@TODO REPORT

	/**
	 * **
	 *	private void reportImc002(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		String getReportType = request.getParameter("getReportType");
		String jasperName = request.getParameter("jasperName");
		String getRpt = jasperName + ".jasper";

		int c_month1 = Integer.parseInt(request.getParameter("c_month"));
		int c_month2 = Integer.parseInt(request.getParameter("c_month2"));

		String c_yyyy_mm = request.getParameter("c_year") + ((c_month1 < 10) ? "0" + request.getParameter("c_month") : request.getParameter("c_month"));
		String c_yyyy_mm2 = request.getParameter("c_year2") + ((c_month2 < 10) ? "0" + request.getParameter("c_month2") : request.getParameter("c_month2"));

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("c_yyyy_mm", c_yyyy_mm);
		parameters.put("c_yyyy_mm2", c_yyyy_mm2);
		parameters.put("dis_year2", request.getParameter("dis_year2"));
		parameters.put("dis_month2", request.getParameter("dis_month2"));
		parameters.put("dis_year", request.getParameter("dis_year"));
		parameters.put("dis_month", request.getParameter("dis_month"));

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/" + getRpt));
			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getConNMU());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getConNMU());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HHmmss");
					Date date = new Date();
					System.out.println(formatter.format(date));
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + formatter.format(date) + ".pdf");
					exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.html");
					html.exportReport();
					break;
				default:

					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}

	private void reportImc003(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {

//		String getReportType = request.getParameter("getReportType");
//		String jasperName = request.getParameter("jasperName");
//
//		int c_month1 = Integer.parseInt(request.getParameter("c_month"));
//		int c_month2 = Integer.parseInt(request.getParameter("c_month2"));
//
//		String c_yyyy_mm = request.getParameter("c_year") + ((c_month1 < 10) ? "0" + request.getParameter("c_month") : request.getParameter("c_month"));
//		String c_yyyy_mm2 = request.getParameter("c_year2") + ((c_month2 < 10) ? "0" + request.getParameter("c_month2") : request.getParameter("c_month2"));
//
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("c_yyyy_mm", c_yyyy_mm);
//		parameters.put("c_yyyy_mm2", c_yyyy_mm2);
//		parameters.put("dis_year2", request.getParameter("dis_year2"));
//		parameters.put("dis_month2", request.getParameter("dis_month2"));
//		parameters.put("dis_year", request.getParameter("dis_year"));
//		parameters.put("dis_month", request.getParameter("dis_month"));
		String getReportType = request.getParameter("getReportType");
		String jasperName = request.getParameter("jasperName");

		int c_month1 = Integer.parseInt(request.getParameter("c_month"));

		String c_month = (c_month1 < 10) ? "0" + request.getParameter("c_month") : request.getParameter("c_month");
		String c_yyyy_mm = request.getParameter("c_year") + c_month;
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("c_yyyy_mm", c_yyyy_mm);
		parameters.put("dis_year", request.getParameter("dis_year"));
		parameters.put("dis_month", request.getParameter("dis_month"));

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/imc003.jasper"));
			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getConNMU());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getConNMU());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".pdf");
					exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.html");
					html.exportReport();
					break;
				default:
					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}

	private void reportBook(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {

//		int id = Integer.parseInt(request.getParameter("id"));
//		String title = request.getParameter("title");
//		String author = request.getParameter("author");
//		float price = Float.parseFloat(request.getParameter("price"));
		String getReportType = request.getParameter("getReportType");//"pdf";
		String jasperName = "report1";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("tor_type_id", 3);
		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/report2.jasper"));
			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getCon());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getCon());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.pdf");
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.html");
					html.exportReport();
					break;
				default:
					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}

	private void reportImc001(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {

//		int id = Integer.parseInt(request.getParameter("id"));
//		String title = request.getParameter("title");
//		String author = request.getParameter("author");
//		float price = Float.parseFloat(request.getParameter("price"));
		String getReportType = request.getParameter("getReportType");//"pdf";
		String jasperName = "imc001";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("tor_type_id", 3);
		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/imc001.jasper"));
			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getConNMU());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getConNMU());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".pdf");
					exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.html");
					html.exportReport();
					break;
				default:
					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}

	private void reportImc0011(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {

		String getReportType = request.getParameter("getReportType");
		String jasperName = request.getParameter("jasperName");

		int c_month1 = Integer.parseInt(request.getParameter("c_month"));
		int c_month2 = Integer.parseInt(request.getParameter("c_month2"));

		String c_yyyy_mm = request.getParameter("c_year") + ((c_month1 < 10) ? "0" + request.getParameter("c_month") : request.getParameter("c_month"));
		String c_yyyy_mm2 = request.getParameter("c_year2") + ((c_month2 < 10) ? "0" + request.getParameter("c_month2") : request.getParameter("c_month2"));

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("c_yyyy_mm", c_yyyy_mm);
		parameters.put("c_yyyy_mm2", c_yyyy_mm2);
		parameters.put("dis_year2", request.getParameter("dis_year2"));
		parameters.put("dis_month2", request.getParameter("dis_month2"));
		parameters.put("dis_year", request.getParameter("dis_year"));
		parameters.put("dis_month", request.getParameter("dis_month"));

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/imc0011.jasper"));
			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getConNMU());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getConNMU());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".pdf");
					exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + "your.html");
					html.exportReport();
					break;
				default:
					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}
*/
	private void reportSpEmp(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {

		String getReportType = request.getParameter("getReportType");
		String jasperName = "Rep_sp_emp";  //request.getParameter("jasperName");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("i_level", 1);
		parameters.put("i_enable", 1);
		parameters.put("dc_department_id", 1);


		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();
			//new File(getServletConfig().getServletContext().getRealPath("/reports/report3.jasper"));
			File reportFile = new File("D:\\ERP\\_reports\\supplies\\report3.jasper");

			JasperPrint jasperPrint = fillReport(reportFile.getPath(), parameters, getConNMU());
			String strPahtfile = "D:\\ERP\\ExportFile\\";

			switch (getReportType) {
				case "pdf":
					byte[] bytes = runReportToPdf(reportFile.getPath(), parameters, getConNMU());
//----------------------------------------
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
//----------------------------------------
					servletOutputStream.write(bytes, 0, bytes.length);
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "excel":
//----------------------------------------
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + jasperName + ".xlsx" + "\"");
//---------------------------------------
					JRXlsxExporter exporterXls = new JRXlsxExporter();
					exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
					exporterXls.exportReport();
					servletOutputStream.flush();
					servletOutputStream.close();
					break;
				case "exp2pdf":
					System.out.println(" --> Exporting to PDF");
					//----------------------------------------
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".pdf");
					//SET PASSWORD
					exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "hi");
					exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
					//END
					exporter.exportReport();
					break;
				case "exp2xlsx":
					System.out.println(" --> Exporting to Excel");
					//----------------------------------------
					JRXlsxExporter excel = new JRXlsxExporter();
					excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					excel.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".xlsx");
					excel.exportReport();
					break;
				case "exp2html":
					System.out.println(" --> Exporting to HTML");
					//----------------------------------------
					JRHtmlExporter html = new JRHtmlExporter();
					html.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					html.setParameter(JRExporterParameter.START_PAGE_INDEX, 0);
					html.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strPahtfile + jasperName + ".html");
					html.exportReport();
					break;
				default:
					break;
			}
		} catch (JRException ex) {
			getLogger(ControllerServlet.class
				.getName()).log(SEVERE, null, ex);
		}
//		response.sendRedirect("list");
	}


}
