package controlador.general;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;

import comun.ConeccionBD;
import comun.Validacion;

public class ManejadorJasper {
	
	
	public static AMedia showReportfromJrxml(String jrxmlSrc, Map parameters, Connection con, String nombre) throws JRException, IOException{
		
		File archivo = new File(jrxmlSrc);
		AMedia amedia = null;
		try {
			amedia = new AMedia(null, null, null, archivo, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Component visor = Executions.createComponents("/General/"
				+"frmVisorDocumento.zul", null, null);

		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameters(parameters);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT ,jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
		exporter.exportReport();
		arrayOutputStream.close();
	    amedia = new AMedia(nombre+".pdf","pdf","application/pdf", arrayOutputStream.toByteArray());
		visor.setVariable("archivo", amedia, false);
		return amedia;
		
	}

	
//	public void onClick$btnImprimir() throws WrongValueException,
//	ParseException, JRException, IOException, SQLException {
////if (Validacion.validarFecha(dtbFechaInicio, dtbFechaFin)) {
////	con = ConeccionBD.getCon("postgres", "postgres", "123456");
////	DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //hacerlo en mi controlador
////	Date startDate = (Date) format.parse(dtbFechaInicio.getText());
////	Date endDate = (Date) format.parse(dtbFechaFin.getText());
////	jrxmlSrc = Sessions.getCurrent().getWebApp()
////			.getRealPath("/WEB-INF/reportes/Ingresos_Egresos.jrxml");
////	parameters.put("Inicio", startDate);
////	parameters.put("Fin", endDate);
//	File archivo = new File(jrxmlSrc);
//	AMedia amedia = null;
//	try {
//		amedia = new AMedia(null, null, null, archivo, true);
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	}
//
//	Component visor = Executions.createComponents("Administracion/Reportes/"
//			+"frmVisorDocumento.zul", null, null);
//
//	JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
//	JasperPrint jaspPrint = JasperFillManager.fillReport(jasp,
//			parameters, con);
//	ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//	JRExporter exporter = new JRPdfExporter();
//	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jaspPrint);
//	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
//			arrayOutputStream);
//	exporter.exportReport();
//	arrayOutputStream.close();
//	amedia = new AMedia("Balance Ingresos y Egresos.pdf", "pdf",
//			"application/pdf", arrayOutputStream.toByteArray());
//
//	visor.setVariable("archivo", amedia, false);
//}
//}

}
