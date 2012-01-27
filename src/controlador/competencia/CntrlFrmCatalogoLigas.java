package controlador.competencia;

import java.util.List;

import modelo.Divisa;
import modelo.Liga;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.implementacion.ServicioLiga;

public class CntrlFrmCatalogoLigas extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component catalogo;
	ServicioLiga servicioLiga;
	//Objeto Lista de lisgas que se mostraran en el catalogo...
	List<Liga> ligas;
	Listbox lsbxLigas;
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		
		//Se listan las ligas activas y se cargan mediante databinding (ver zul)
		ligas = servicioLiga.listarActivos();
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
//				if (lsbxLigas.getItems().size() != 0){
//					lsbxLigas.setSelectedIndex(0);
//				}

	}
	
	public void onClick$btnAceptar() throws InterruptedException  {
		
		//Se comprueba que se haya seleccionado un elemento de la lista
				if (lsbxLigas.getSelectedIndex() != -1) {
					//se obtiene la liga seleccionada
					Liga l = ligas.get(lsbxLigas.getSelectedIndex());
					//se obtiene la referencia del formulario
					Component formulario = (Component) catalogo.getVariable("formulario",false);
		            //se le asigna el objeto liga al formulario
					formulario.setVariable("liga", l,false);
					//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
					Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
					//se cierra el catalogo
					catalogo.detach();
					
				} else {
						Messagebox.show("Seleccione una liga", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

				}
	}
	
	
	public List<Liga> getLigas() {
		return ligas;
	}

	public void setLigas(List<Liga> ligas) {
		this.ligas = ligas;
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}
}
