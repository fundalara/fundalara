package controlador.jugador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import modelo.Medico;
import modelo.DatoBasico;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioMedico;



/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar a un Medico dentro de un equipo devuelve
 * al catalogo que lo llamo el Medico seleccionado
 * 
 * @author Miguel B
 * 
 * @version 1.0 29/11/2011
 * 
 * */


public class CntrlBuscarMedico extends GenericForwardComposer {
	ServicioMedico servicioMedico;
	
	private Medico medico = new Medico();
	Window win;
	
	List<Medico> medicos = new ArrayList<Medico>();

	
	Textbox filter2;
	Textbox filter1;
	Textbox filter3;
	Textbox filter4;
	Listbox listmedico;
	
	Component catalogo;
	private AnnotateDataBinder binder ;
	
	Combobox cmbEquipo, cmbCategoria;
	 
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("controller", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		
		medicos=servicioMedico.listar();
		
	}

	public void onClick$btnSeleccionar() throws InterruptedException{
	//Se comprueba que se haya seleccionado un elemento de la lista

	if (listmedico.getSelectedIndex() != -1) {
		//se obtiene la divisa seleccionada
		Medico d = medicos.get(listmedico.getSelectedIndex());
		//se obtiene la referencia del formulario
		Component formulario = (Component) catalogo.getVariable("formulario",false);
		
        //se le asigna el objeto divisa al formulario
		formulario.setVariable("medico", d,false);
		//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
		Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
		//se cierra el catalogo
		catalogo.detach();
		
	} else {
			Messagebox.show("Seleccione un Medico", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

	}
	
	}
	
	public void onClick$btnSalir(){
		win.detach();
	}
			
	public  List<Medico> getMedicos() {
		return servicioMedico.listar();
		
	}
	
		
}


