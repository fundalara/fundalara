package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import modelo.Categoria;

import modelo.Equipo;
import modelo.Familiar;
import modelo.Jugador;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar a un jugador dentro de un equipo devuelve al
 * catalogo que lo llamo el jugador seleccionado
 * 
 * @author Miguel B
 * 
 * @version 1.0 29/12/2011
 * 
 * */

public class CntrlBuscarFamiliar extends GenericForwardComposer {
	
	ServicioFamiliar servicioFamiliar;


	private Familiar familiar = new Familiar();
	List<Familiar> familiares=new ArrayList<Familiar>();
	
    Button btnSeleccionar;

	Textbox filter2;
	Textbox filter1;
	Textbox filter3;
	Listbox listFamiliar;

	Component catalogo;
	private AnnotateDataBinder binder;


	public void onChanging$filter2(){
		binder.loadAll();
		
	}
	
	public void onChanging$filter1(){
		binder.loadAll();
		
	}
	
	public void onChanging$filter3(){
		binder.loadAll();
		
	}

	
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
		
		
	}

	
	public void onClick$btnSeleccionar() throws InterruptedException{
		//Se comprueba que se haya seleccionado un elemento de la lista

		if (listFamiliar.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Familiar d = (Familiar) listFamiliar.getSelectedItem().getValue();
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
	        //se le asigna el objeto divisa al formulario
			formulario.setVariable("familiar", d,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
			Messagebox.show("Seleccione un Familiar", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);

		}
		
		}

	
	
	public List<Familiar> getFamiliares() {
		familiares=servicioFamiliar.listar();
		return servicioFamiliar.listar();
	}

	

}
