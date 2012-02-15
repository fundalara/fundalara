package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.PersonaNatural;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioPersona;

import comun.MensajeMostrar;

public class CntrlCatalogoRepresentante extends GenericForwardComposer {

	Component catalogoRepresentante;
	Component frmPadre;

	IServicioPersona servicioPersona;
	IServicioComisionFamiliar servicioComisionFamiliar;

	DatoBasico tipoPersona = new DatoBasico();
	DatoBasico comision = new DatoBasico();

	PersonaNatural personaNatural = new PersonaNatural();
	ComisionFamiliar comisionFamiliar = new ComisionFamiliar();

	List<ComisionFamiliar> listaComisionFamiliar = new ArrayList<ComisionFamiliar>();
	List<PersonaNatural> listaPersonaNatural;

	Listbox lboxRepresentante;
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
		catalogoRepresentante = comp;
	}

	/**
	 * El metodo onCreate$frmCatRepresentante() se ejecuta cuando se esta
	 * creando el formulario, el carga la lista con los representante de la
	 * comision que se ha sido enviada del formulario padre.
	 */

	public void onCreate$frmCatRepresentante() {
		DatoBasico comision = (DatoBasico) catalogoRepresentante.getVariable("comision", false);
		listaComisionFamiliar = servicioComisionFamiliar.listarRepresentantesPorComision(comision);
	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona un
	 * material con su respectiva cantidad, retorna el material al formulario
	 * que lo ha llamado
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxRepresentante.getSelectedIndex() != -1) {

			// se obtiene la plantilla seleccionada
			personaNatural = listaComisionFamiliar.get(lboxRepresentante.getSelectedIndex()).getFamiliarJugador().getFamiliar().getPersonaNatural();

			// se obtiene la referencia del formulario
			Component frmPadre = (Component) catalogoRepresentante.getVariable("frmPadre", false);

			// se le asigna el objeto plantilla al formulario
			frmPadre.setVariable("personaNatural", personaNatural, false);

			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoRepresentanteCerrado", frmPadre));

			// se cierra el catalogo
			catalogoRepresentante.detach();

		} else {
			Messagebox.show("Seleccione un representante", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
	public void onClick$btnSalir() {
		catalogoRepresentante.detach();
	}

	public ComisionFamiliar getComisionFamiliar() {
		return comisionFamiliar;
	}

	public void setComisionFamiliar(ComisionFamiliar comisionFamiliar) {
		this.comisionFamiliar = comisionFamiliar;
	}

	public List<ComisionFamiliar> getListaComisionFamiliar() {
		return listaComisionFamiliar;
	}

	public void setListaComisionFamiliar(List<ComisionFamiliar> listaComisionFamiliar) {
		this.listaComisionFamiliar = listaComisionFamiliar;
	}

	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}

	public void onChanging$txtNombre(Event event) throws InterruptedException {
		lboxRepresentante.setModel(new BindingListModelList(this.filtrar2(txtNombre.getText()), false));
	}

	public void onChanging$txtApellido(Event event) throws InterruptedException {
		lboxRepresentante.setModel(new BindingListModelList(this.filtrar3(txtApellido.getText()), false));
	}

	public List<ComisionFamiliar> filtrar2(String descripcion) {
		List<ComisionFamiliar> filtrados = new ArrayList<ComisionFamiliar>();
		for (Iterator<ComisionFamiliar> i = listaComisionFamiliar.iterator(); i.hasNext();) {
			ComisionFamiliar tmp = i.next();
			if (tmp.getFamiliarJugador().getFamiliar().getPersonaNatural().getPrimerNombre().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}

	public List<ComisionFamiliar> filtrar3(String descripcion) {
		List<ComisionFamiliar> filtrados = new ArrayList<ComisionFamiliar>();
		for (Iterator<ComisionFamiliar> i = listaComisionFamiliar.iterator(); i.hasNext();) {
			ComisionFamiliar tmp = i.next();
			if (tmp.getFamiliarJugador().getFamiliar().getPersonaNatural().getPrimerApellido().toLowerCase()
					.indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}

}
