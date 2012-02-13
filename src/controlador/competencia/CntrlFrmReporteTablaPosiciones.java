package controlador.competencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Estadio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;

import comun.ConeccionBD;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import org.zkoss.zul.Messagebox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.EquipoCompetencia;
import modelo.FamiliarJugador;
import modelo.Persona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Comboitem;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipoCompetencia;

import comun.ConeccionBD;
import comun.EstadoCompetencia;


import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;

public class CntrlFrmReporteTablaPosiciones extends GenericForwardComposer {
	
	Component formulario;
	AnnotateDataBinder binder;
	
	ServicioCompetencia servicioCompetencia;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	Competencia competencia;
	CategoriaCompetencia categoriaCompetencia;
	List<Competencia> competencias;
	List<CategoriaCompetencia> categoriasCompetencia;
	
	Combobox cmbCompetencia;
	Combobox cmbCategoria;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();
		
	}
	
	public void restaurar(){
		competencia = new Competencia(); 
		competencias = servicioCompetencia.listarAperturadasClausurada();
		categoriasCompetencia = new ArrayList<CategoriaCompetencia>();
		cmbCompetencia.setText("----Seleccione----");
		cmbCategoria.setText("----Seleccione----");
		
	}
	
	public void onSelect$cmbCompetencia() {
		competencia = (Competencia) cmbCompetencia.getSelectedItem().getValue();
		categoriasCompetencia = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia
						.getCodigoCompetencia());
		binder.loadAll();
	}
	
	public void onSelect$cmbCategoria() {
		categoriaCompetencia = (CategoriaCompetencia) cmbCategoria.getSelectedItem().getValue();
		binder.loadAll();
	}
	
	
	public CntrlFrmReporteTablaPosiciones() throws SQLException {
		super();
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
//		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/anuario.pdf");

	}
	public void onClick$btnGenerar()throws JRException, IOException, InterruptedException{
		if (cmbCompetencia.getText().equalsIgnoreCase("----Seleccione----")) { 
			throw new WrongValueException(cmbCompetencia, "Debe seleccionar una competencia");
		} else if (cmbCategoria.getText().equalsIgnoreCase("----Seleccione----")) { 
			throw new WrongValueException(cmbCategoria, "Debe seleccionar una categoria");	
		}else {
//			showReportfromJrxml();
			parameters.put("CodCompetencia", competencia.getCodigoCompetencia());
			parameters.put("codCategoria", categoriaCompetencia.getCategoria().getCodigoCategoria());
//			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("tabladeposicionesnormal.pdf", "pdf", "application/pdf", archivo);

			 Component visor = Executions.createComponents("/General/"
						+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);

		}
		
		
	}
	public void showReportfromJrxml() throws JRException, IOException {
		
		parameters.put("CodCompetencia", competencia.getCodigoCompetencia());
		parameters.put("codCategoria", categoriaCompetencia.getCategoria().getCodigoCategoria());
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters,
				con);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameters(parameters);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				arrayOutputStream);
		exporter.exportReport();
		arrayOutputStream.close();
		final AMedia amedia = new AMedia("tabladeposicionesnormal.jxrml", "jxrml",
				"jxrml/application", arrayOutputStream.toByteArray());
//		ifReporte.setContent(amedia);
		//jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Competencias/tabladeposicionesnormal.jrxml");
		
		
		File archivo = new File(jrxmlSrc);
		AMedia amedias=null;
		try {
			 amedias = new AMedia(null,null,null,archivo,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 Component visor = Executions.createComponents("/WebContent/General/frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedias, false);			
			visor.setVariable("orientacion", "horizontal",false );
			
		
	}

	public void onClick$btnCancelar(){
		restaurar();
		binder.loadAll();
	}
	
	public void onClick$btnSalir() throws InterruptedException{
		if (Messagebox.show("¿Desea salir?", "Mensaje",	Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
				formulario.detach();
		
	}
	
	//Getters y Setters 
	public List<Competencia> getCompetencias() {
		return competencias;
	}
	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}
	public List<CategoriaCompetencia> getCategoriasCompetencia() {
		return categoriasCompetencia;
	}
	public void setCategoriasCompetencia(
			List<CategoriaCompetencia> categoriasCompetencia) {
		this.categoriasCompetencia = categoriasCompetencia;
	}
	

}
