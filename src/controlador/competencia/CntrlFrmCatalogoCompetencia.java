package controlador.competencia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.ClasificacionCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.PersonaNatural;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import servicio.implementacion.ServicioCompetencia;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Label;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import comun.ConeccionBD;
import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioPersonaNatural;



/**
 * Controlador para el archivo 'FrmCatalogoCompetencia.zul'
 * 
 * @author Eduardo Ochoa
 * @version 1.0
 */
public class CntrlFrmCatalogoCompetencia extends GenericForwardComposer {
    
	
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	List<Competencia> competencias;
	Listbox lsbxCompetencias;
	Textbox txtfiltro;
	Component catalogo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	List<CategoriaCompetencia> categoria;	

	Label lblMostrar;
	Combobox cmbEstadoCompetencia;
	
	int estadoComp;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	

	public void onCreate$FrmCatalogoC(){
		int estado_comp = (Integer) catalogo.getVariable("estado_comp",false);		

	    if (estado_comp == EstadoCompetencia.REGISTRADA + EstadoCompetencia.APERTURADA){
	    
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.REGISTRADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.REGISTRADA;
	    	lblMostrar.setVisible(true);
	    	cmbEstadoCompetencia.setVisible(true);
	    	
	    }else{
	    	competencias = servicioCompetencia.listarPorEstatus(estado_comp);
	    	ordenarCompetencia(competencias);
	    	estadoComp = estado_comp;
	    }	
	    determinarTitulo(estado_comp);
	    binder.loadAll();
	}
	
	
	
	public void determinarTitulo(int estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {
		    
		case 6:
			w.setTitle("Competencias Registradas");
			break;
		case 7:
			w.setTitle("Competencias Aperturadas");
			break;
		case 8:
			w.setTitle("Competencias Eliminadas");
			break;
		case 9:
			w.setTitle("Competencias Clausuradas");
			break;
		   
		}
	}
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		catalogo = c;
	}
	
	
	public void onChange$cmbEstadoCompetencia() {
				
		if (cmbEstadoCompetencia.getText().equals("REGISTRADA")) {
	
			txtfiltro.setText("");
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.REGISTRADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.REGISTRADA;
			binder.loadAll();
			
			
		} else if (cmbEstadoCompetencia.getText().equals("APERTURADA")) {

			txtfiltro.setText("");
	    	competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.APERTURADA);
	    	ordenarCompetencia(competencias);
	    	estadoComp = EstadoCompetencia.APERTURADA;
			binder.loadAll();
			
		}
			
	}
	
	
	public void onClick$btnAceptar() throws InterruptedException {
		if (lsbxCompetencias.getSelectedIndex() != -1) {
		   Competencia c = competencias.get(lsbxCompetencias.getSelectedIndex());
		   Component formulario = (Component) catalogo.getVariable("formulario",false);
           formulario.setVariable("competencia", c,false);
		   Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
		   catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una Competencia", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}
	}
	
	
	public void ordenarCompetencia(List<Competencia> complista){
		
		Collections.sort(complista, new Comparator() {

		public int compare(Object o1, Object o2) {  
			Competencia compt1 = (Competencia) o1;  
			Competencia compt2 = (Competencia) o2;
		    return compt1.getNombre().compareToIgnoreCase(compt2.getNombre());  
		}
		});  		
	}
	
	
	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public int getEstadoComp() {
		return estadoComp;
	}

	public void setEstadoComp(int estadoComp) {
		this.estadoComp = estadoComp;
	}

	public void onChanging$txtfiltro(InputEvent event){
		
		String dato = event.getValue().toUpperCase();
		
		competencias = servicioCompetencia.listarPorfiltro(dato,estadoComp);
    	ordenarCompetencia(competencias);		
		binder.loadAll();
	}
	
	

	
	public void onClick$btnImprimir()throws JRException, IOException, InterruptedException, SQLException{
		
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			parameters.put("CodCompetencia", 1);
			parameters.put("codCategoria", 2);
//			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/competencia.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("competencia.pdf", "pdf", "application/pdf", archivo);

			 Component visor = Executions.createComponents("/General/"
						+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);

		}
		
		

	public void showReportfromJrxml() throws JRException, IOException {
		
		parameters.put("CodCompetencia", 1);
		parameters.put("codCategoria", 2);
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
		final AMedia amedia = new AMedia("competencia.jxrml", "jxrml",
				"jxrml/application", arrayOutputStream.toByteArray());
//		ifReporte.setContent(amedia);
		//jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Competencias/competencia.jrxml");
		
		
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

	
}