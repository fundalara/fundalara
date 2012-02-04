package controlador.logistica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.util.media.AMedia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;

import comun.ConeccionBD;
import comun.TipoDatoBasico;

public class CntrlReporteEstadoActividad extends GenericForwardComposer{
	
	Datebox dboxFechaInicio;
	Datebox dboxFechaFin;
	private Connection con;
	private String jrxmlSrc;
	Map parameters = new HashMap();
	Iframe ifReport;
	
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);		
		comp.setVariable("cntrl", this, false);
		
		}
	
	
	
	public void showReportfromJrxml() throws JRException, IOException{
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameters(parameters);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT ,jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
		exporter.exportReport();
		arrayOutputStream.close();
		final AMedia amedia = new AMedia("ReporteEstadistico.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReport.setContent(amedia);
		    		    
//		JasperExportManager.exportReportToPdfFile(jaspPrint,"C:\\Users\\Reinaldo López\\Documents\\repositorio\\fundalara\\WebContent\\Logistica\\Reportes\\ReporteEstadistico.pdf");
		    
		//Para visualizar el pdf directamente desde java
		JasperViewer.viewReport(jaspPrint, false);
	}
	
	public void onClick$btnImprimir() throws SQLException, JRException, IOException, ParseException {
		con = ConeccionBD.getCon("postgres","postgres","admin");
		//jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/Logistica/Reportes/ReporteEstadistico.jrxml");
		jrxmlSrc = "C:\\Users\\Reinaldo López\\Documents\\repositorio\\fundalara\\WebContent\\Logistica\\Reportes\\ReporteEstadistico.jrxml";				
				
		Date fi = new Date(dboxFechaInicio.getValue().getYear(), dboxFechaInicio.getValue().getMonth(), dboxFechaInicio.getValue().getDay());
		
		parameters.put("FechaInicio", fi);
	    parameters.put("FechaFin", dboxFechaFin.getValue());
		
		showReportfromJrxml();
	}
	
	
}
