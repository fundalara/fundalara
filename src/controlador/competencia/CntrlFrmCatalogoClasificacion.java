package controlador.competencia;

import java.util.List;

import modelo.ClasificacionCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioClasificacionCompetencia;

public class CntrlFrmCatalogoClasificacion extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	List<ClasificacionCompetencia> clasificacionCompetencias;
	Listbox lsbxClasificacion;
	Component catalogo;
	Textbox txtFiltro;
	Combobox cmbTipoCompetencia;

	// public void onCreate$frm(){
	// //OBTIENE LA VARIABLE DEL FORMULARIO ANTERIOR EN EL CMB
	// TipoCompetencia tc = (TipoCompetencia) catalogo.getVariable("tc",false);
	// modalidades = servicioModalidadCompetencia.listarModalidad(tc);
	// binder.loadAll();
	//
	// }

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

	public void onCtrlKey$txtFiltro() {
		System.out.println("changing...");
	}

	public void onChanging$txtFiltro() {
		// divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

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