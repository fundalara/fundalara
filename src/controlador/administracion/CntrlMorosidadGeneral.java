package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import modelo.DocumentoAcreedor;
import modelo.FamiliarJugador;
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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;

import comun.ConeccionBD;

public class CntrlMorosidadGeneral extends GenericForwardComposer {
	Component component;
	Datebox dtbFechaVenDesde, dtbFechaVenHasta, dtbFechaEmiHasta, dtbFechaEmiDesde;
	Button btnCancelar, btnImprimir;
	Iframe ifReport;
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		component = comp;
	}
	
	// ---------------------------------------------------------------------------------------------------
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
		final AMedia amedia = new AMedia("controlMorosidad.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReport.setContent(amedia);
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {
		con = ConeccionBD.getCon("sol","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteGeneralMorosidad.jrxml");
		parameters.put("fechemides_1",dtbFechaEmiDesde.getText());
		parameters.put("fechemihas_1",dtbFechaEmiHasta.getText() );
		parameters.put("fechvendes_1", dtbFechaVenDesde.getText());
		parameters.put("fechvenchas_1", dtbFechaVenHasta.getText());
		showReportfromJrxml();
	}
	
		

}
