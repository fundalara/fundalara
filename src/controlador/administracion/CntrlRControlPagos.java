package controlador.administracion;

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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import comun.ConeccionBD;

public class CntrlRControlPagos extends GenericForwardComposer {
	Datebox dtbFechaIni, dtbfechaFin;
	Button btnImprimir, btnCancelar;
	Window winEstadoFinanciero;
	Component componente;
	Iframe ifReport;
	private String jrxmlSrc;
	private Connection con;
	private Map parameters = new HashMap();
	
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		componente = comp;
		
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
		final AMedia amedia = new AMedia("Listado de Pagos.pdf","pdf","application/pdf", arrayOutputStream.toByteArray());
		ifReport.setContent(amedia);
	}
	
	public void onClick$btnImprimir() throws WrongValueException, ParseException, JRException, IOException, SQLException{
		con = ConeccionBD.getCon("postgres","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/Control de Pagos.jrxml");
		parameters.put("fechvendes", dtbFechaIni.getText());
		parameters.put("fechvenchas", dtbfechaFin.getText());
		showReportfromJrxml();
	}

}
