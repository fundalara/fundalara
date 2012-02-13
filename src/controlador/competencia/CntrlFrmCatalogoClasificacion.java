package controlador.competencia;

import java.util.List;

import modelo.ClasificacionCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioClasificacionCompetencia;

/**
 * @author Merielen Gaspar
 * @author Diana Santiago
 * 
 */

public class CntrlFrmCatalogoClasificacion extends GenericForwardComposer {

	/**
	 * @version 2.0 01/02/2012
	 * Clase CntrlFrmCatalogoClasificacion para acceso/manejo de la interfaz (vista) de los tipos
	 * de competencias (FrmCatalogoClasificacion.zul) 
	 * Utiliza los Servicios ServicioClasificacionCompetencia
	 */
	
	
	/** Atributos */
	List<ClasificacionCompetencia> clasificacionCompetencias;
	AnnotateDataBinder binder;
	Component catalogo;
	
	
	/** Servicios utilizados */
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	
	
	/** Vistas */
	Combobox cmbTipoCompetencia;
	Listbox lsbxClasificacion;
	Textbox txtFiltro;


	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		clasificacionCompetencias = servicioClasificacionCompetencia
				.listarActivos();
		catalogo = c;
		if (lsbxClasificacion.getItems().size() != 0) {
			lsbxClasificacion.setSelectedIndex(0);
		}
	}


	/** Envia el tipo de competencia seleccionado a la pantalla princiapal (FrmClasificacion.zul) */
	public void onClick$btnAceptar() throws Exception {
		if (lsbxClasificacion.getSelectedIndex() != -1) {
			ClasificacionCompetencia m = clasificacionCompetencias
					.get(lsbxClasificacion.getSelectedIndex());
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			formulario.setVariable("clasificacion", m, false);
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			catalogo.detach();
		} else {
			Messagebox.show("Seleccione una Clasificación", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);
		}
	}


	/** Salir de la pantalla actual */
	public void onClick$btnSalir() throws InterruptedException {
		if (Messagebox.show("                  ¿Desea salir?", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
			catalogo.detach();

	}


	public void onCtrlKey$txtFiltro(){

	}
		public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();

		clasificacionCompetencias = servicioClasificacionCompetencia.listarClasificacionPorFiltro(dato);
		  binder.loadAll();
	}
	
	
	/** Getters and Setters */
	public List<ClasificacionCompetencia> getClasificacionCompetencias() {
		return clasificacionCompetencias;
	}

	public void setClasificacionCompetencias(
			List<ClasificacionCompetencia> clasificacionCompetencias) {
		this.clasificacionCompetencias = clasificacionCompetencias;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public ServicioClasificacionCompetencia getServicioClasificacionCompetencia() {
		return servicioClasificacionCompetencia;
	}

	public void setServicioClasificacionCompetencia(
			ServicioClasificacionCompetencia servicioClasificacionCompetencia) {
		this.servicioClasificacionCompetencia = servicioClasificacionCompetencia;
	}

	public Listbox getLsbxClasificacion() {
		return lsbxClasificacion;
	}

	public void setLsbxClasificacion(Listbox lsbxClasificacion) {
		this.lsbxClasificacion = lsbxClasificacion;
	}

	public Component getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Component catalogo) {
		this.catalogo = catalogo;
	}

	public Combobox getCmbTipoCompetencia() {
		return cmbTipoCompetencia;
	}

	public void setCmbTipoCompetencia(Combobox cmbTipoCompetencia) {
		this.cmbTipoCompetencia = cmbTipoCompetencia;
	}

}