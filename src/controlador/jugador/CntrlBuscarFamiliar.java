package controlador.jugador;

import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import modelo.Familiar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioFamiliar;

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
	private List<Familiar> familiares;
	
	private Button btnSeleccionar;

    private Window winBuscarfamiliar;
    private Textbox filter2;
    private Textbox filter1;
    private Textbox filter3;
    private Listbox listFamiliar;

	public int estatus;
	private Component catalogo;
	private AnnotateDataBinder binder;

	public void onBlur$filter2(){
		binder.loadAll();
	}
	
	public void onBlur$filter1(){
		binder.loadAll();
	}
	
	public void onBlur$filter3(){
		binder.loadAll();
	}

	public void onCreate$winBuscarfamiliar(){
		estatus = (Integer) catalogo.getVariable("estatus",false);
		if(estatus==1){
			familiares=servicioFamiliar.filtrar(1,filter1.getValue().toString().toUpperCase(), filter2.getValue().toString().toUpperCase(), filter3.getValue().toString().toUpperCase());
		}
		else{
			familiares=servicioFamiliar.filtrar(2,filter1.getValue().toString().toUpperCase(), filter2.getValue().toString().toUpperCase(), filter3.getValue().toString().toUpperCase());
		}
		determinarTitulo(estatus);
	    binder.loadAll();
	}
	
	public void determinarTitulo(int estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {
		    
		case 1:
			w.setTitle("Catálogo Representantes");
			break;
		case 2:
			w.setTitle("Catálogo Familiares");
			break;
		}
	}
	
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
	}

	public void onClick$btnSalir(){
		winBuscarfamiliar.detach();
	}
	
	public void onClick$btnSeleccionar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista

		if (listFamiliar.getSelectedIndex() != -1) {
			Familiar d = familiares.get(listFamiliar.getSelectedIndex());
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			formulario.setVariable("familiar", d, false);
			Events.sendEvent(new Event("onCatalogoBuscarFamiliarCerrado",
					formulario));
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione un Familiar", "Mensaje",
					Messagebox.YES, Messagebox.INFORMATION);
		}
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public List<Familiar> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<Familiar> familiares) {
		this.familiares = familiares;
	}
	
}