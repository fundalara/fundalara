package controlador.competencia;

import java.util.List;

import modelo.Estadio;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioEstadio;

/**
 * Controlador para el archivo 'FrmCatalogoEstadios.zul'
 * 
 * @author Alix Villanueva
 * @version 1.0
 */

public class CntrlFrmCatalogoEstadios extends GenericForwardComposer{
	AnnotateDataBinder binder;
	ServicioEstadio servicioEstadio;
	List<Estadio> estadios;
	Listbox lsbxEstadios;
	Component catalogo;
	Textbox txtFiltro;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las divisas activas y se cargan mediante databinding (ver zul)
		estadios = servicioEstadio.listarActivos();
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxEstadios.getItems().size() != 0){
			lsbxEstadios.setSelectedIndex(0);
		}

	}
	
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onClick$btnBuscar(){
		estadios = servicioEstadio.filtrar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	
		/*public void onChanging$txtFiltro(){
		
		estadios = servicioEstadio.filtrar(txtFiltro.getText()+"%");
		binder.loadAll();
	}*/

	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxEstadios.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Estadio e = estadios.get(lsbxEstadios.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto estadio al formulario
			formulario.setVariable("estadio", e,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione un estadio", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Estadio> getEstadios() {
		return estadios;
	}

	public void setEstadios(List<Estadio> estadios) {
		this.estadios = estadios;
	}

}
