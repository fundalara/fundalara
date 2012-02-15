package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import modelo.Medico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import comun.Mensaje;

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
	private Window winBuscarMedico;
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private Textbox filter2;
	private Textbox filter1;
	private Textbox filter3;
	private Textbox filter4;
	private Listbox listmedico;
	
	private Component catalogo;
	private AnnotateDataBinder binder ;
	
	private Combobox cmbEquipo;
	private Combobox cmbCategoria;
	
	private void filtrarLista() {
		medicos = servicioMedico.filtrar(filter1.getValue().toString(), filter2
				.getValue().toString(), filter3.getValue().toString()
				.toUpperCase(), filter4.getValue().toString().toUpperCase());
		binder.loadComponent(listmedico);
	}
	 
	public void onBlur$filter2(){
		filtrarLista();
	}
	
	public void onBlur$filter1(){
		filtrarLista();
	}
	
	public void onBlur$filter3(){
		filtrarLista();
	}
	
	public void onBlur$filter4(){
		filtrarLista();
	}
	
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		catalogo = c;
		
		medicos=servicioMedico.filtrar(filter1.getValue()
				.toString().toUpperCase(),filter2.getValue()
				.toString().toUpperCase(), filter3.getValue().toString().toUpperCase(), filter4.getValue()
				.toString().toUpperCase());
	}

	public void onClick$btnSeleccionar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista

		if (listmedico.getSelectedIndex() != -1) {
			// se obtiene la divisa seleccionada
			Medico d = medicos.get(listmedico.getSelectedIndex());
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);

			// se le asigna el objeto divisa al formulario
			formulario.setVariable("medico", d, false);
			// se le envia una se√±al al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			// se cierra el catalogo
			catalogo.detach();

		} else {
			Mensaje.mostrarMensaje("Seleccione un MÈdico", Mensaje.INFORMACION,
					Messagebox.EXCLAMATION);
		}
	}
	
	public void onClick$btnSalir(){
		winBuscarMedico.detach();
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
		
}