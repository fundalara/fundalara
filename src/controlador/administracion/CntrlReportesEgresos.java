package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.TipoDato;
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
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;

import comun.ConeccionBD;

public class CntrlReportesEgresos extends GenericForwardComposer {
	Component component;
	Window winEgresos;
	Combobox cmbEgresos;
	Datebox dtbFechaInicio, dtbFechaFin;
	Iframe ifReport;
	Button btnImprimir, btnCancelar;
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	private DatoBasico egreso;
	private List<DatoBasico> tiposEgresos,aux;
	ServicioDatoBasico servicioDatoBasico;
	TipoDato td;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrlRepegresos", this, true);
		component = comp;
		aux = servicioDatoBasico.listarPorTipoDato("TIPO EGRESO");
		aux.add(new DatoBasico(99, td, "TODOS", 'A'));
		tiposEgresos = aux;
		
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
		final AMedia amedia = new AMedia("Reporte de Egresos.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReport.setContent(amedia);
	}
	
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		con = ConeccionBD.getCon("postgres","postgres","123456");
		if (cmbEgresos.getSelectedItem().getLabel().equals("TODOS")){
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/egresosnoparams.jrxml");
		}else {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/egresos.jrxml");
		}
		parameters.put("fecha_Inicio",dtbFechaInicio.getText());
		parameters.put("fecha_Fin",dtbFechaFin.getText());
		parameters.put("tipo_Egreso",cmbEgresos.getSelectedItem().getLabel());
		
		showReportfromJrxml();
	}

	public List<DatoBasico> getTiposEgresos() {
		return tiposEgresos;
	}

	public void setTiposEgresos(List<DatoBasico> tiposEgresos) {
		this.tiposEgresos = tiposEgresos;
	}
	
	
	

}
