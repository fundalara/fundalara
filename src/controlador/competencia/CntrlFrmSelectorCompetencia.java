<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
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

	public void determinarTitulo(int estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {
		    
		case 6:
			w.setTitle("Competencias Registradas");
			break;
		case 7:
			w.setTitle("Competencias Aperturadas");
			break;
		case 8:
			w.setTitle("Competencias Eliminadas");
			break;
		case 9:
			w.setTitle("Competencias Clausuradas");
			break;
		   
		}
	}
	
	
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
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

	public void determinarTitulo(int estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {
		    
		case 6:
			w.setTitle("Competencias Registradas");
			break;
		case 7:
			w.setTitle("Competencias Aperturadas");
			break;
		case 8:
			w.setTitle("Competencias Eliminadas");
			break;
		case 9:
			w.setTitle("Competencias Clausuradas");
			break;
		   
		}
	}
	
	
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
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
