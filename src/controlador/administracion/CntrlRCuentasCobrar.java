package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import modelo.Persona;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Listbox;

import comun.ConeccionBD;

public class CntrlRCuentasCobrar extends GenericForwardComposer {

	Window winRCuentascobrar;
	Datebox dtEmiDesde,dtEmiHasta;
	Button btnImprimir, btnCancelar;
	Iframe ifReporte;
	private String jrxmlSrc;
	private Connection con;
	private Map parameters = new HashMap();
	private Component componente;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
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
		final AMedia amedia = new AMedia("Cuentas por Cobrar.pdf","pdf","application/pdf", arrayOutputStream.toByteArray());
		ifReporte.setContent(amedia);
	}
	
	public void onClick$btnCancelar(){
		
	}
	
	public void onClick$btnImprimir() throws SQLException, JRException, IOException{
		con = ConeccionBD.getCon("postgres","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteGeneralCcobrar.jrxml");
		parameters.put("fechemides_1", dtEmiDesde.getText());
		parameters.put("fechemihas_1", dtEmiHasta.getText());
		showReportfromJrxml();
	}


	

}
