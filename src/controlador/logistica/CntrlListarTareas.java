package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.interfaz.IServicioDatoBasico;

import comun.MensajeMostrar;

public class CntrlListarTareas extends GenericForwardComposer {

	List<DatoBasico> listaTareas;

	IServicioDatoBasico servicioDatoBasico;

	DatoBasico tipoTarea = new DatoBasico();
	DatoBasico tareaSeleccionada;

	Component frmTarea;

	Listbox lboxTarea;
	AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);

		frmTarea = comp;

		tipoTarea = servicioDatoBasico.buscarPorCodigo(224);

		listaTareas = servicioDatoBasico.buscarDatosPorRelacion(tipoTarea);
		// listaTareas = (List<DatoBasico>) frmTarea.getVariable("listaTareas",
		// false);

		// Component frmPlanificarActividad = (Component)
		// frmTarea.getVariable("frmPlanificarActividad", false);
		// List<DatoBasico> tareas = (List<DatoBasico>)
		// frmTarea.getVariable("tareas", false);
		// List<DatoBasico> tareas = (List<DatoBasico>)
		// frmTarea.getAttribute("tareasCargadas");

	}

	public void onClick$btnAgregar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxTarea.getSelectedIndex() != -1) {

			// se obtiene la tarea seleccionada
			tareaSeleccionada = listaTareas.get(lboxTarea.getSelectedIndex());

			// se obtiene la referencia del formulario
			Component frmPlanificarActividad = (Component) frmTarea.getVariable("General", false);

			// se le asigna el objeto al formulario
			frmPlanificarActividad.setVariable("tareaSeleccionada", tareaSeleccionada, false);

			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", frmPlanificarActividad));

			// listaTareas.remove(tareaSeleccionada);
			// lboxTarea.removeItemAt(lboxTarea.getSelectedIndex());
			// binder.loadAll();

			// se cierra el catalogo
			frmTarea.detach();

		} else {
			Messagebox.show("Seleccione una tarea ", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);

		}
	}

	public List<DatoBasico> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<DatoBasico> listaTareas) {
		this.listaTareas = listaTareas;

	}

	public DatoBasico getTareaSeleccionada() {
		return tareaSeleccionada;
	}

	public void setTareaSeleccionada(DatoBasico tareaSeleccionada) {
		this.tareaSeleccionada = tareaSeleccionada;
	}

}
