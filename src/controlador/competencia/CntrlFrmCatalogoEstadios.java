package controlador.competencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Estadio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.ConeccionBD;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioEstadio;

/**
 * Controlador para el archivo 'FrmCatalogoEstadios.zul'
 * 
 * @author Alix Villanueva
 * @version 1.0
 */

public class CntrlFrmCatalogoEstadios extends GenericForwardComposer{
	AnnotateDataBinder binder;
	ServicioEstadio servicioEstadio;
	List<Estadio> estadios;
	Listbox lsbxEstadios;
	Component catalogo;
	Textbox txtFiltro;
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();

	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las divisas activas y se cargan mediante databinding (ver zul)
		estadios = servicioEstadio.listarActivos();
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxEstadios.getItems().size() != 0){
			lsbxEstadios.setSelectedIndex(0);
		}

	}
	
		public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();

		estadios = servicioEstadio.listarEstadiosPorFiltro(dato);
		  binder.loadAll();
	}
		
		public void onClick$btnImprimir()throws JRException, IOException, InterruptedException, SQLException{			
			con = ConeccionBD.getCon("postgres", "postgres", "123456");
			parameters.put("codigoEstadio", 1);
			parameters.put("codigoDatoBasico", 2);
			String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/reportEstadio.jrxml");
			JasperReport report = JasperCompileManager.compileReport(rutaReporte);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

			byte[] archivo = JasperExportManager.exportReportToPdf(print);

			final AMedia amedia = new AMedia("estadios.pdf", "pdf", "application/pdf", archivo);

			 Component visor = Executions.createComponents("/General/"
						+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);

		}


	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxEstadios.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Estadio e = estadios.get(lsbxEstadios.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto estadio al formulario
			formulario.setVariable("estadio", e,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione un estadio", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Estadio> getEstadios() {
		return estadios;
	}

	public void setEstadios(List<Estadio> estadios) {
		this.estadios = estadios;
	}

}
