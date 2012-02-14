package controlador.competencia;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;
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
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import comun.ConeccionBD;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioConstanteCategoria;

public class CntrlFrmCatalogoConstante extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	ServicioConstante servicioConstante;
	ServicioConstanteCategoria servicioConstanteCategoria;
	List<Constante> constantes;
	Listbox lsbxConstantes;
	Component catalogo;
	
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las constates activas y se cargan mediante databinding (ver zul)
		constantes = servicioConstante.listarConstantesActivos();
		
		//constantescategoria = servicioConstanteCategoria.listarActivos();
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxConstantes.getItems().size() != 0){
			lsbxConstantes.setSelectedIndex(0);
		}
	}
	
	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxConstantes.getSelectedIndex() != -1) {
			//se obtiene la constante seleccionada
			Constante c = constantes.get(lsbxConstantes.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto Constante al formulario
			formulario.setVariable("constante",c,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una constante", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	public void onCtrlKey$txtFiltro(){
	
	}
	
	
	public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();
		constantes = servicioConstante.listarConstantesPorFiltro(dato);
		binder.loadAll();
	}
	
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Constante> getConstantes() {
		return constantes;
	}

	public void setConstantes(List<Constante> constantes) {
		this.constantes = constantes;
	}

	public ServicioConstanteCategoria getServicioConstanteCategoria() {
		return servicioConstanteCategoria;
	}

	public void setServicioConstanteCategoria(
			ServicioConstanteCategoria servicioConstanteCategoria) {
		this.servicioConstanteCategoria = servicioConstanteCategoria;
	}
	
	
	public void onClick$btnGenerar()throws JRException, IOException, InterruptedException, SQLException {
		    
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			parameters.put("codigoConstante", 1);
//			jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/tabladeposicionesnormal.jrxml");
			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/constanteReport.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("constante.pdf", "pdf", "application/pdf", archivo);

			Component visor = Executions.createComponents("/General/"
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);

		}
		

}
