package controlador.logistica;

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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;

import comun.ConeccionBD;

/**
 * Clase controladora del formulario a partir del cual se selecciona el rango de
 * fechas generar el reporte de estados de las actividades
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlReporteEstadoActividad extends GenericForwardComposer {

	private Datebox dboxFechaInicio;
	private Datebox dboxFechaFin;
	private Connection con;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

	}

	public void onClick$btnImprimir() throws SQLException, JRException, IOException, ParseException {
		con = ConeccionBD.getCon("postgres", "postgres", "admin");

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		parameters.put("FECHA", formato.format(fecha));
		parameters.put("FechaInicio", formato.parse(formato.format(dboxFechaInicio.getValue())));
		parameters.put("FechaFin", formato.parse(formato.format(dboxFechaFin.getValue())));
		formato.applyPattern("hh:mm");
		parameters.put("HORA", formato.format(fecha));

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteEstadistico.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

		// Exporta el informe a PDF
		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteEstadistico.pdf");
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el pdf con el visor de Jasper
		JasperViewer.viewReport(print, false);
	}

}
