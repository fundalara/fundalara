package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioActividad;

public class CntrlFrmCatalogoActividad extends GenericForwardComposer {

	Component frmCatActPla;
	Component frmPrestamoDevolucion;
	IServicioActividad servicioActividad;
	BeanFactory beanFactory;
	List<Actividad> listaActividad;
	Actividad actividad = new Actividad();
	Listbox lboxActividad;
	Textbox txtNombre;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		frmCatActPla = comp;
		listaActividad = servicioActividad.listarActivos();

	}

	public void onClick$btnSalir() {
		frmCatActPla.detach();
	}

	public void onClick$btnGuardar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxActividad.getSelectedIndex() != -1) {

			// se obtiene la plantilla seleccionada
			actividad = listaActividad.get(lboxActividad.getSelectedIndex());

			Component frmPrestamoDevolucion = (Component) frmCatActPla.getVariable("frmPrestamoDevolucion", false);
			
			Integer numero = (Integer) frmCatActPla.getVariable("numero", false);

			// se le asigna el objeto plantilla al formulario
			frmPrestamoDevolucion.setVariable("actividad", actividad, false);

			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			if (numero == 1) {
				Events.sendEvent(new Event("onCatalogoActividadCerradoP", frmPrestamoDevolucion));
			} else if (numero == 2) {
				Events.sendEvent(new Event("onCatalogoActividadCerradoD", frmPrestamoDevolucion));
			}

			// se cierra el catalogo
			frmCatActPla.detach();

		} else {
			Messagebox.show("Seleccione una Actividad", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public Component getCatalogoActividad() {
		return frmCatActPla;
	}

	public void setCatalogoActividad(Component catalogoActividad) {
		this.frmCatActPla = catalogoActividad;
	}

	public Component getFrmPrestamoDevolucion() {
		return frmPrestamoDevolucion;
	}

	public void setFrmPrestamoDevolucion(Component frmPrestamoDevolucion) {
		this.frmPrestamoDevolucion = frmPrestamoDevolucion;
	}

	public IServicioActividad getServicioActividad() {
		return servicioActividad;
	}

	public void setServicioActividad(IServicioActividad servicioActividad) {
		this.servicioActividad = servicioActividad;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public List<Actividad> getListaActividad() {
		return listaActividad;
	}

	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Listbox getLboxActividad() {
		return lboxActividad;
	}

	public void setLboxActividad(Listbox lboxActividad) {
		this.lboxActividad = lboxActividad;
	}
	public void onChanging$txtNombre(Event event) throws InterruptedException {
		lboxActividad.setModel(new BindingListModelList(this.filtrar2(txtNombre.getText()), false));
	}

	public List<Actividad> filtrar2(String descripcion) {
		List<Actividad> filtrados = new ArrayList<Actividad>();
		for (Iterator<Actividad> i = listaActividad.iterator(); i.hasNext();) {
			Actividad tmp = i.next();
			if (tmp.getPlanificacionActividad().getDescripcion().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}
}