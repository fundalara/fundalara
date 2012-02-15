package controlador.competencia;

import java.util.List;



import modelo.JugadorForaneo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioJugadorForaneo;

public class CntrlFrmCatalogoJugadorForaneo extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioJugadorForaneo servicioJugadorForaneo;
	List<JugadorForaneo> jugadorForaneo;
	Listbox lsbxJugadorForaneo;
	Component catalogo;
	Textbox txtFiltro;
	
	int codCat;
	
	
	public void onCreate$frmCatalogoJF(){
		codCat = (Integer) catalogo.getVariable("categoria",false);	
		//System.out.print("Codigo=");
		//System.out.println(codCat);
		jugadorForaneo = servicioJugadorForaneo.listarJugadorForaneoPorCategoria(codCat);
		
		binder.loadAll();
	}
	
	
	public void onCtrlKey$txtFiltro(){

	}
	
	
	
		public void onChanging$txtFiltro(InputEvent event ){
		
		String dato = event.getValue().toUpperCase();

		jugadorForaneo = servicioJugadorForaneo.listarJugadorForaneoPorFiltro(dato);
		  binder.loadAll();
	}
		
		
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		
		
		jugadorForaneo = servicioJugadorForaneo.listarActivos();
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxJugadorForaneo.getItems().size() != 0){
			lsbxJugadorForaneo.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxJugadorForaneo.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			JugadorForaneo jf = jugadorForaneo.get(lsbxJugadorForaneo.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto divisa al formulario
			formulario.setVariable("jugadorForaneo", jf,false);
			//se le envia una seÃ±al al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado2",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione un jugador", "Olimpo - Información",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	
	
	public void onChanging$txtFiltro(){
		
		
		//divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<JugadorForaneo> getJugadorForaneo() {
		return jugadorForaneo;
	}

	public void setJugadorForaneo(List<JugadorForaneo> jugadorForaneo) {
		this.jugadorForaneo = jugadorForaneo;
	}

	public ServicioJugadorForaneo getServicioJugadorForaneo() {
		return servicioJugadorForaneo;
	}

	public void setServicioJugadorForaneo(
			ServicioJugadorForaneo servicioJugadorForaneo) {
		this.servicioJugadorForaneo = servicioJugadorForaneo;
	}

	public Listbox getLsbxJugadorForaneo() {
		return lsbxJugadorForaneo;
	}

	public void setLsbxJugadorForaneo(Listbox lsbxJugadorForaneo) {
		this.lsbxJugadorForaneo = lsbxJugadorForaneo;
	}



	
}


