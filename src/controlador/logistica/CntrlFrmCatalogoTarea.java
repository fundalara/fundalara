package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;

import org.springframework.beans.factory.BeanFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioDatoBasico;

import comun.MensajeMostrar;

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
	Textbox txtNombre;

	/**
	 * El metodo doAfterCompose se encarga de enviar las acciones,metodos y
	 * eventos desde el controlador java hasta el componente Zk
	 * 
	 * @param comp
	 * @exception super
	 *                .doAfterCompose(comp)
	 */
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);

		catalogoTarea = comp;
	}

	/**
	 * El metodo onCreate$frmCatTarea(), este metodo se activa cuando se esta
	 * creando la ventana, el llena la lista de las tareas para mostrar
	 * dependiendo si son de mantenimiento o para las actividades
	 * complementarias.
	 */
	public void onCreate$frmCatTarea() {

		int tipo = (Integer) catalogoTarea.getVariable("tipoTarea", false);

		if (tipo != 1) {
			DatoBasico datoBasico = new DatoBasico();
			datoBasico.setCodigoDatoBasico(224);
			datoBasico.setDatoBasico(datoBasico);
			todas = servicioDatoBasico.buscarDatosPorRelacion(datoBasico);

			List<DatoBasico> todas2 = new ArrayList<DatoBasico>();
			DatoBasico datoBasico2 = new DatoBasico();
			datoBasico2.setCodigoDatoBasico(223);
			datoBasico2.setDatoBasico(datoBasico);
			todas2 = servicioDatoBasico.buscarDatosPorRelacion(datoBasico2);

			for (DatoBasico datoBasico3 : todas2) {
				todas.add(datoBasico3);
			}
		} else {
			DatoBasico datoBasico = new DatoBasico();
			datoBasico.setCodigoDatoBasico(223);
			datoBasico.setDatoBasico(datoBasico);
			todas = servicioDatoBasico.buscarDatosPorRelacion(datoBasico);
		}

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

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona una o
	 * varias tareas y retorna las tareas seleccionadas al formulario que la
	 * haya llamado
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxTarea.getSelectedIndex() != -1) {

			// se obtiene la referencia del formulario
			Component frmPadre = (Component) catalogoTarea.getVariable("frmPadre", false);

			DatoBasico aux = new DatoBasico();
			for (int i = 0; i < lboxTarea.getModel().getSize(); i++) {
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
			Messagebox.show("Seleccione una tarea ", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
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

	public void onChanging$txtNombre(Event event) throws InterruptedException {
		lboxTarea.setModel(new BindingListModelList(this.filtrar2(txtNombre.getText()), false));
	}

	public List<DatoBasico> filtrar2(String descripcion) {
		List<DatoBasico> filtrados = new ArrayList<DatoBasico>();
		for (Iterator<DatoBasico> i = listaTarea.iterator(); i.hasNext();) {
			DatoBasico tmp = i.next();
			if (tmp.getNombre().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}
}
