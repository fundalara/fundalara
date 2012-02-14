package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.TipoIngreso;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import comun.ConeccionBD;

import servicio.implementacion.ServicioTipoIngreso;

public class CntrlReportesIngresos extends GenericForwardComposer {
	Component component;
	Window winIngresos;
	DatoBasico db;
	Datebox dtbFechaInicio,dtbFechaFin;
	Button btnImprimir, btnCancelar;
	Map parameters = new HashMap();
	Combobox cmbIngresos;
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReport;
	TipoIngreso tipoIngreso;
	ServicioTipoIngreso servicioTipoIngreso = new ServicioTipoIngreso();
	private List<TipoIngreso> tiposIngresos, aux;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrlRepingresos", this, true);
		component = comp;
		aux = servicioTipoIngreso.listarActivos();
		aux.add(new TipoIngreso(20,db,"TODOS",'A',false));
		tiposIngresos = aux;
		System.out.println(tiposIngresos);
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
		final AMedia amedia = new AMedia("Reporte de Ingresos.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReport.setContent(amedia);
	}
	
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (cmbIngresos.getSelectedItem().getLabel().equals("TODOS")){
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ingresosnoparams.jrxml");
		}else {
			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ingresos.jrxml");
		}
		
		con = ConeccionBD.getCon("postgres","postgres","123456");
		parameters.put("fecha_Inicio",dtbFechaInicio.getText());
		parameters.put("fecha_Fin",dtbFechaFin.getText());
		parameters.put("tipo_Ingreso",cmbIngresos.getSelectedItem().getLabel());
		
		showReportfromJrxml();
	}

	public List<TipoIngreso> getTiposIngresos() {
		return tiposIngresos;
	}

	public void setTiposIngresos(List<TipoIngreso> tiposIngresos) {
		this.tiposIngresos = tiposIngresos;
	}
	
	


}
