
package com.nkl.nmu.erp.supplies.controller;
import static com.nkl.mcot.erp.controller.Consql.getCon;
import java.io.File;
import java.io.IOException; 
import java.util.HashMap;
import java.util.Map; 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;
import net.sf.jasperreports.engine.JasperPrint;
import static net.sf.jasperreports.engine.JasperRunManager.runReportToPdf;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author eakibanez
 */
public class reportSpEmp {

    public reportSpEmp(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {


		String getReportType = request.getParameter("getReportType");
		String jasperName = "report3";  //request.getParameter("jasperName"); 
                String dc_department_id = request.getParameter("i_department");
                String i_level = request.getParameter("i_level");
                String i_enable = request.getParameter("i_enable"); 
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("i_level", i_level);
		parameters.put("i_enable", i_enable);
		parameters.put("dc_department_id", 2);
			ServletOutputStream servletOutputStream = response.getOutputStream(); 
			File reportFile = new File("D:\\ERP\\_reports\\supplies\\"+jasperName+".jasper"); //Drive Server

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
	 }

 

    }
    
