package controlador.jugador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import modelo.Institucion;
import modelo.DatoBasico;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.TipoDatoBasico;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioInstitucion;



/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar a una institucion dentro de un equipo devuelve
 * al catalogo que lo llamo la institucion seleccionada
 * 
 * @author Miguel B
 * 
 * @version 1.0 29/11/2011
 * 
 * */


public class CntrlBuscarInstitucion extends GenericForwardComposer {
	ServicioInstitucion servicioInstitucion;
	ServicioDatoBasico servicioDatoBasico;
	
	private Institucion institucion = new Institucion();
	private DatoBasico tipo = new DatoBasico();
	Window win;
	
	List<Institucion> instituciones = new ArrayList<Institucion>();
	List<DatoBasico> tipos = new ArrayList<DatoBasico>();

	
	Textbox filter1;
	Listbox listinstitucion;
	
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
		//tipos=servicioDatoBasico.buscar(TipoDatoBasico.INSTITUCION);
		
	}

	public void onClick$btnSeleccionar() throws InterruptedException{
	//Se comprueba que se haya seleccionado un elemento de la lista

	if (listinstitucion.getSelectedIndex() != -1) {
		//se obtiene la divisa seleccionada
		Institucion d = instituciones.get(listinstitucion.getSelectedIndex());
		//se obtiene la referencia del formulario
		Component formulario = (Component) catalogo.getVariable("formulario",false);
        //se le asigna el objeto divisa al formulario
		formulario.setVariable("institucion", d,false);
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
			
	public  List<DatoBasico> getTipos() {
		return servicioDatoBasico.buscar(TipoDatoBasico.INSTITUCION);
		
	}
	
	public  List<Institucion> getInstituciones() {
		instituciones=servicioInstitucion.buscarInstitucionTipo(tipo);
		return servicioInstitucion.buscarInstitucionTipo(tipo);
		
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public DatoBasico getTipo() {
		return tipo;
	}

	public void setTipo(DatoBasico tipo) {
		this.tipo = tipo;
	}
	
		
}


