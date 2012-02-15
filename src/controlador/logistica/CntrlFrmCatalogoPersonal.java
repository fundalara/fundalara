package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;
import modelo.Persona;

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
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

import comun.MensajeMostrar;

public class CntrlFrmCatalogoPersonal extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component catalogoPersonal;
	Component frmPadre;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioPersona servicioPersona;
	BeanFactory beanFactory;
	DatoBasico tipoPersonal = new DatoBasico();
	List<Persona> listaPersona;
	Persona persona = new Persona();
	Listbox lboxPersonal;
	int numero = 0;
	int tipo = 0;
	Window frmCatPersonal;
	Textbox txtNombre;
	Textbox txtApellido;

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
		catalogoPersonal = comp;
		listaPersona = servicioPersona.listarTrabajadores();
	}

	/**
	 * El metodo onCreate$frmCatPersonal() se ejecuta cuando se esta creando el
	 * formulario, el llena la lista del personal dependiendo si son
	 * trabajadores de mantenimiento o todos.
	 */
	public void onCreate$frmCatPersonal() {

		int aux = (Integer) catalogoPersonal.getVariable("aux", false);

		if (aux == 1) {
			listaPersona = servicioPersona.listarTrabajadoresMantenimiento();
		} else {
			listaPersona = servicioPersona.listarTrabajadores();
		}
		binder.loadAll();
	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona un
	 * material con su respectiva cantidad, retorna el material al formulario
	 * que lo ha llamado
	 */

	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxPersonal.getSelectedIndex() != -1) {

			// se obtiene la plantilla seleccionada
			persona = listaPersona.get(lboxPersonal.getSelectedIndex());

			// se obtiene la referencia del formulario

			frmPadre = (Component) catalogoPersonal.getVariable("frmPadre", false);

			// se le asigna el objeto plantilla al formulario
			frmPadre.setVariable("persona", persona, false);

			numero = (Integer) catalogoPersonal.getVariable("numero", true);
			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			if (numero == 1) {
				Events.sendEvent(new Event("onCatalogoCerradoResponsable", frmPadre));
			} else {
				Events.sendEvent(new Event("onCatalogoCerradoPersonal", frmPadre));
			}
			// se cierra el catalogo
			catalogoPersonal.detach();

		} else {
			Messagebox.show("Seleccione una plantilla ", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public void onClick$btnSalir() {
		this.frmCatPersonal.detach();
	}

	public void onChanging$txtNombre(Event event) throws InterruptedException {
		lboxPersonal.setModel(new BindingListModelList(this.filtrar2(txtNombre.getText()), false));
	}

	public void onChanging$txtApellido(Event event) throws InterruptedException {
		lboxPersonal.setModel(new BindingListModelList(this.filtrar3(txtApellido.getText()), false));
	}

	public List<Persona> filtrar2(String descripcion) {
		List<Persona> filtrados = new ArrayList<Persona>();
		for (Iterator<Persona> i = listaPersona.iterator(); i.hasNext();) {
			Persona tmp = i.next();
			if (tmp.getPersonaNatural().getPrimerNombre().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}

	public List<Persona> filtrar3(String descripcion) {
		List<Persona> filtrados = new ArrayList<Persona>();
		for (Iterator<Persona> i = listaPersona.iterator(); i.hasNext();) {
			Persona tmp = i.next();
			if (tmp.getPersonaNatural().getPrimerApellido().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}

}
