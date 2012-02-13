package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioDatoBasico;

public class CntrlFrmCatalogoTarea extends GenericForwardComposer {

	AnnotateDataBinder binder;
	IServicioDatoBasico servicioDatoBasico;
	List<DatoBasico> listaTarea = new ArrayList<DatoBasico>();
	List<DatoBasico> auxListaTarea = new ArrayList<DatoBasico>();
	List<DatoBasico> listadoTarea = new ArrayList<DatoBasico>();
	List<DatoBasico> todas = new ArrayList<DatoBasico>();
	DatoBasico tarea;
	DatoBasico tipoTarea;
	BeanFactory beanFactory;
	Component catalogoTarea;
	Component frmPlanificarMantenimiento;
	Listbox lboxTarea;
	Window frmCatTarea;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);

		catalogoTarea = comp;

	}

	public void onCreate$frmCatTarea() {
		TipoDato td = new TipoDato();
		td.setCodigoTipoDato(110);
		todas = servicioDatoBasico.buscarPorTipoDato(td);

		List<DatoBasico> variable = (List<DatoBasico>) catalogoTarea.getVariable("tarea", true);
		auxListaTarea = variable;

		if (auxListaTarea != null) {
			for (DatoBasico datoBasico : todas) {
				if (!auxListaTarea.contains(datoBasico)) {
					listaTarea.add(datoBasico);
				}
			}
		} else {
			listaTarea = todas;

		}
		binder.loadAll();
	}

	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxTarea.getSelectedIndex() != -1) {

			// se obtiene la referencia del formulario
			Component frmPadre = (Component) catalogoTarea.getVariable("frmPadre", false);

			DatoBasico aux = new DatoBasico();
			for (int i = 0; i < listaTarea.size(); i++) {
				if (lboxTarea.getItemAtIndex(i).isSelected()) {
					aux = (DatoBasico) lboxTarea.getItemAtIndex(i).getValue();
					listadoTarea.add(aux);
				}
			}
			// se le asigna el objeto tarea al formulario
			frmPadre.setVariable("tarea", listadoTarea, false);

			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoTareaCerrado", frmPadre));

			// se cierra el catalogo
			catalogoTarea.detach();

		} else {
			Messagebox.show("Seleccione una tarea ", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnSalir() {
		catalogoTarea.detach();
	}

	public List<DatoBasico> getListaTarea() {
		return listaTarea;
	}

	public void setListaTarea(List<DatoBasico> listaTarea) {
		this.listaTarea = listaTarea;
	}

	public DatoBasico getTarea() {
		return tarea;
	}

	public void setTarea(DatoBasico tarea) {
		this.tarea = tarea;
	}

}
