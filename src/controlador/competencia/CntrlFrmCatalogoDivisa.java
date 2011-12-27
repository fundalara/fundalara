package controlador.competencia;

import java.util.List;

import modelo.Divisa;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioDivisa;

public class CntrlFrmCatalogoDivisa extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioDivisa servicioDivisa;
	List<Divisa> divisas;
	Listbox lsbxDivisas;
	Component catalogo;
	Textbox txtFiltro;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las divisas activas y se cargan mediante databinding (ver zul)
		divisas = servicioDivisa.listarActivos();
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxDivisas.getItems().size() != 0){
			lsbxDivisas.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxDivisas.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Divisa d = divisas.get(lsbxDivisas.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto divisa al formulario
			formulario.setVariable("divisa", d,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una divisa", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onChanging$txtFiltro(){
		
		
		divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Divisa> getDivisas() {
		return divisas;
	}

	public void setDivisas(List<Divisa> divisas) {
		this.divisas = divisas;
	}

}
