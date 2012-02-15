package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.PlanificacionActividad;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioPlanificacionActividad;

import comun.MensajeMostrar;

public class CntrlFrmCatalogoPlantilla extends GenericForwardComposer {

	Component catalogoPlantilla;
	AnnotateDataBinder binder;
	CntrlFrmPlanificarMantenimiento cntrl;
	List<PlanificacionActividad> listaPlantilla;
	IServicioPlanificacionActividad servicioPlanificacionActividad;
	PlanificacionActividad plantilla = new PlanificacionActividad();
	// Componentes
	Window frmCatPlantilla;
	Listbox lboxPlantilla;
	DatoBasico tipo = new DatoBasico();

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

		catalogoPlantilla = comp;
		listaPlantilla = servicioPlanificacionActividad.listarPlantilla();
	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona una
	 * plantilla retorna la plantilla al formulario que lo ha llamado
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxPlantilla.getSelectedIndex() != -1) {

			// se obtiene la plantilla seleccionada
			plantilla = listaPlantilla.get(lboxPlantilla.getSelectedIndex());

			// se obtiene la referencia del formulario
			Component frmPlanificarMantenimiento = (Component) catalogoPlantilla.getVariable("frmPlanificarMantenimiento", false);

			// se le asigna el objeto plantilla al formulario
			frmPlanificarMantenimiento.setVariable("plantilla", plantilla, false);

			// se le envia una señal al formulario indicado que el formulario se
			// cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", frmPlanificarMantenimiento));

			// se cierra el catalogo
			catalogoPlantilla.detach();

		} else {
			Messagebox.show("Seleccione una plantilla ", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
	public void onClick$btnSalir() {
		catalogoPlantilla.detach();
	}

	public CntrlFrmPlanificarMantenimiento getCntrl() {
		return cntrl;
	}

	public void setCntrl(CntrlFrmPlanificarMantenimiento cntrl) {
		this.cntrl = cntrl;
	}

	public Window getFrmCatPlantilla() {
		return frmCatPlantilla;
	}

	public void setFrmCatPlantilla(Window frmCatPlantilla) {
		this.frmCatPlantilla = frmCatPlantilla;
	}

	public PlanificacionActividad getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlanificacionActividad plantilla) {
		this.plantilla = plantilla;
	}

	public List<PlanificacionActividad> getListaPlantilla() {
		return listaPlantilla;
	}

	public void setListaPlantilla(List<PlanificacionActividad> listaPlantilla) {
		this.listaPlantilla = listaPlantilla;
	}

}
