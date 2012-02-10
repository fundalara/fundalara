package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import modelo.CategoriaCompetencia;
import modelo.Competencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import comun.EstadoCompetencia;
import diamondedge.util.HashList;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;

public class CntrlFrmSelectorCompetencia extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	List<Competencia> competencias;
	Listbox lsbxCompetencias;
	Component catalogo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	List<CategoriaCompetencia> categoria;

	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.APERTURADA);
		catalogo = c;
	}

	public void onClick$btnAceptar() throws InterruptedException {
		Competencia competencia;
		if (lsbxCompetencias.getSelectedItems().size() > 0) {
		   
		    competencia = competencias.get(lsbxCompetencias.getSelectedIndex());
		    Component formulario = (Component) catalogo.getVariable("formulario",false);
		    formulario.setVariable("competencia",competencia, false);
		    Events.postEvent(new Event("onCatalogoCerrado", formulario));
		    catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una competencia", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

}
