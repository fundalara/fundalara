package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.Persona;

import org.springframework.beans.factory.BeanFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

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

	public void onCreate$frmCatPersonal() {

		int aux = (Integer) catalogoPersonal.getVariable("aux", false);

		if (aux == 1) {
			listaPersona = servicioPersona.listarTrabajadoresMantenimiento();
		} else {
			listaPersona = servicioPersona.listarTrabajadores();
		}
		binder.loadAll();
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		catalogoPersonal = comp;
		listaPersona = servicioPersona.listarTrabajadores();
	}

	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxPersonal.getSelectedIndex() != -1) {

			// se obtiene la plantilla seleccionada
			persona = listaPersona.get(lboxPersonal.getSelectedIndex());

			System.out.println("persona del selectItem: " + persona.getCedulaRif());
			// se obtiene la referencia del formulario

			frmPadre = (Component) catalogoPersonal.getVariable("frmPadre", false);

			// se le asigna el objeto plantilla al formulario
			frmPadre.setVariable("persona", persona, false);

			numero = (Integer) catalogoPersonal.getVariable("numero", true);
			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			System.out.println(numero);
			if (numero == 1) {
				Events.sendEvent(new Event("onCatalogoCerradoResponsable", frmPadre));
			} else {
				Events.sendEvent(new Event("onCatalogoCerradoPersonal", frmPadre));
			}
			// se cierra el catalogo
			catalogoPersonal.detach();

		} else {
			Messagebox.show("Seleccione una plantilla ", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

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

}
