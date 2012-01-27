package controlador.competencia;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import java.util.List;

import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioConstanteCategoria;

public class CntrlFrmCatalogoConstante extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	ServicioConstante servicioConstante;
	ServicioConstanteCategoria servicioConstanteCategoria;
	List<Constante> constantes;
	//List<Categoria> constanteCategoria;
	Listbox lsbxConstantes;
	Component catalogo;
	//ConstanteCategoria constantesCategorias;
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan las constates activas y se cargan mediante databinding (ver zul)
		constantes = servicioConstante.listarConstantesActivos();
		
		//constantescategoria = servicioConstanteCategoria.listarActivos();
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxConstantes.getItems().size() != 0){
			lsbxConstantes.setSelectedIndex(0);
		}
	}
	
	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxConstantes.getSelectedIndex() != -1) {
			//se obtiene la constante seleccionada
			Constante c = constantes.get(lsbxConstantes.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto Constante al formulario
			formulario.setVariable("constante",c,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione una constante", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onChanging$txtFiltro(){
		
		
		//divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Constante> getConstantes() {
		return constantes;
	}

	public void setConstantes(List<Constante> constantes) {
		this.constantes = constantes;
	}

	public ServicioConstanteCategoria getServicioConstanteCategoria() {
		return servicioConstanteCategoria;
	}

	public void setServicioConstanteCategoria(
			ServicioConstanteCategoria servicioConstanteCategoria) {
		this.servicioConstanteCategoria = servicioConstanteCategoria;
	}
	
	

}
