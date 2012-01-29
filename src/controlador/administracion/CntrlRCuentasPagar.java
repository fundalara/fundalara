package controlador.administracion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.FamiliarJugador;
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
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

//import com.sun.org.apache.bcel.internal.generic.NEW;
import comun.ConeccionBD;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFamiliarJugador;

public class CntrlRCuentasPagar extends GenericForwardComposer {

	DatoBasico datoBasico = new DatoBasico();
	Window winRCuentasPagar;
	Combobox cmbDescripcion;
	Datebox dtFEmiDesde,dtFEmiHasta,dtFVenDesde,dtFVenHasta;
	Button btnGenerar, btnImprimir, btnSalir;
	Iframe ifReporte;
	Component componente;
	ServicioDatoBasico servicioDatoBasico;
	ServicioFamiliarJugador servicioFamiliarJugador;

	Persona persona;
	FamiliarJugador familiarJugador;
	Component formulario1,formulario2;
	Textbox txtCedulaDesde, txtCedulaHasta;
	Listbox lbxJudadores;
	AnnotateDataBinder binder;
	//ServicioDatoBasico servicioDatoBasico;// = new ServicioDatoBasico();
	
	List<DatoBasico> tipoEgreso = new ArrayList<DatoBasico>();
	List<FamiliarJugador> listFamiliarJugador = new ArrayList<FamiliarJugador>();
	
	private Date fecha;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		componente = comp;
		formulario1 = comp;
		formulario2 = comp;
		fecha = new Date();
		tipoEgreso = servicioDatoBasico.listarPorTipoDato("TIPO EGRESO");
		dtFEmiDesde.setValue(fecha);
		dtFVenDesde.setValue(fecha);
	}

	public void onClick$btnBuscar1() {
		
		persona = new Persona();
		Component catalogo1 = Executions.createComponents(
				"/Administracion/Reportes/FrmCatalogo.zul", null, null);

		catalogo1.setVariable("formulario", formulario1, false);
		formulario1.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario1.getVariable("persona", false);
				txtCedulaDesde.setText(persona.getCedulaRif());
			}
		});
		
	}
	
	public void onClick$btnBuscar2() {
		
		persona = new Persona();
		Component catalogo2 = Executions.createComponents(
				"/Administracion/Reportes/FrmCatalogo.zul", null, null);

		catalogo2.setVariable("formulario", formulario2, false);
		formulario2.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				persona = (Persona) formulario2.getVariable("persona", false);
				txtCedulaHasta.setText(persona.getCedulaRif());
			}
		});
		
	}
	
	public CntrlRCuentasPagar() throws SQLException {
		super();
		con = ConeccionBD.getCon("postgres","postgres","12345");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/Cuentas Por Pagar.jrxml");

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
		final AMedia amedia = new AMedia("reporteEstadistico.pdf","pdf","pdf/application", arrayOutputStream.toByteArray());
		ifReporte.setContent(amedia);
		System.out.println("Entrando");
	}

	
	
	
	
	public void onClick$btnGenerar() throws JRException, IOException {
		String var="";
		if (dtFEmiDesde.getText().equals("")) {
			dtFEmiDesde.setText("01/01/2000");
		}
		if (dtFVenDesde.getText().equals("")) {
			dtFVenDesde.setText("01/01/2000");
		}
		if (dtFEmiHasta.getText().equals("")) {
			dtFEmiHasta.setText("01/01/2020");
		}
		if (dtFVenHasta.getText().equals("")) {
			dtFVenHasta.setText("01/01/2020");
		}
		if (txtCedulaDesde.getText().equals("")){
			txtCedulaDesde.setText("J-123456789");
		}
		if (txtCedulaHasta.getText().equals("")){
			txtCedulaHasta.setText("J-307071583");
		}
		if (cmbDescripcion.getSelectedIndex() == -1){
			var = "ADQUISICION";
		} else {
			var = cmbDescripcion.getSelectedItem().getLabel();
		}
		parameters.put("fechemides", dtFEmiDesde.getText());
		parameters.put("fechemihas", dtFEmiHasta.getText());
		parameters.put("fechvendes", dtFVenDesde.getText());
		parameters.put("fechvenchas", dtFVenHasta.getText());
		parameters.put("cedrifdes", txtCedulaDesde.getText());
		parameters.put("cedrifhas", txtCedulaHasta.getText());
		parameters.put("nomb", var);
		showReportfromJrxml();
		
		
	}
	
	public void onClick$btnCancelar(){
		dtFEmiDesde.setText("");
		dtFEmiHasta.setText("");
		dtFVenDesde.setText("");
		dtFVenHasta.setText("");
		cmbDescripcion.setValue("");
		txtCedulaDesde.setText("");
		txtCedulaHasta.setText("");
	}
	
	public void onClick$btnSalir(){
		winRCuentasPagar.detach();
		
	}


	public List<DatoBasico> getTipoEgreso() {
		return tipoEgreso;
	}

	public void setTipoEgreso(List<DatoBasico> tipoEgreso) {
		this.tipoEgreso = tipoEgreso;
	}

}
