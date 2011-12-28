package controlador.competencia;

import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Divisa;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;



/**
 * Controlador para el archivo 'FrmCatalogoCompetencia.zul'
 * 
 * @author Eduardo Ochoa
 * @version 1.0
 */
public class CntrlFrmCatalogoCompetencia extends GenericForwardComposer {
    
	
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	List<Competencia> competencias;
	Listbox lsbxCompetencias;
	Component catalogo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	List<CategoriaCompetencia> categoria;

	public void onCreate$FrmCatalogoC(){
	    int estatus = (Integer) catalogo.getVariable("estatus",false);	
	    competencias = servicioCompetencia.listarPorEstatus(estatus);
	    determinarTitulo(estatus);
	    binder.loadAll();
	}
	
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
		catalogo = c;
	}

	public void onClick$btnAceptar() throws InterruptedException {
		if (lsbxCompetencias.getSelectedIndex() != -1) {
		   Competencia c = competencias.get(lsbxCompetencias.getSelectedIndex());
		   Component formulario = (Component) catalogo.getVariable("formulario",false);
           formulario.setVariable("competencia", c,false);
		   Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
		   catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una divisa", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

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
