package controlador.competencia;

import java.util.List;

import modelo.Competencia;
import modelo.Liga;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCompetencia;

public class CntrlFrmCatalogoEstatusCompetencia extends GenericForwardComposer {
	AnnotateDataBinder binder;
	Component catalogo;
	ServicioCompetencia servicioCompetencia;
	Competencia competencia;
	List<Competencia> competencias;
	
	// Vista....
	Listbox lsbxCompetencias;
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		catalogo = c;

		competencias = servicioCompetencia.listarRegistradasAperturadas();

	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}


	public ServicioCompetencia getServicioCompetencia() {
		return servicioCompetencia;
	}

	public void setServicioCompetencia(ServicioCompetencia servicioCompetencia) {
		this.servicioCompetencia = servicioCompetencia;
	}

	public void onClick$btnAceptar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxCompetencias.getSelectedIndex() != -1) {
			// se obtiene la competencia seleccionada
			Competencia c = competencias.get(lsbxCompetencias.getSelectedIndex());
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			// se le asigna el objeto competencia al formulario
			formulario.setVariable("competencia", c, false);
			// se le envia una señal al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			// se cierra el catalogo
			catalogo.detach();
		} else {
			Messagebox.show("Seleccione una competencia", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);
		}
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}
}
