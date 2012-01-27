package controlador.competencia;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.JugadorForaneo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;



import servicio.implementacion.ServicioEquipoCompetencia;

public class CntrlFrmCatalogoEquipoCompetencia extends GenericForwardComposer {
	//Servicios utilizados
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	List<EquipoCompetencia> equipoCompetencia;
	
	//Atributos
	AnnotateDataBinder binder;
	Listbox lsbxEquipoCompetencia;
	Component catalogo;
	Textbox txtFiltro;
	Equipo equipo;
	int id=0;

	
	  //SETTERS Y GETTERS...
	
	public List<EquipoCompetencia> getEquipoCompetencia() {
		return equipoCompetencia ;
	}

	public void setEquipoCompetencia(List<EquipoCompetencia> equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Listbox getLsbxEquipoCompetencia() {
		return lsbxEquipoCompetencia;
	}

	public void setLsbxEquipoCompetencia(Listbox lsbxEquipoCompetencia) {
		this.lsbxEquipoCompetencia = lsbxEquipoCompetencia;
	}

	
	public void onCreate$frmCatalogoEC(){
		Competencia competencia = (Competencia) catalogo.getVariable("competencia", false);
		equipoCompetencia = ConvertirConjuntoALista(competencia.getEquipoCompetencias());
		id = (Integer) catalogo.getVariable("id",false);
		EquipoCompetencia aux = (EquipoCompetencia) catalogo.getVariable("equipoCompetencia",false);
		if (aux.getCodigoEquipoCompetencia() != 0){
			sacar(aux);
		}
		binder.loadAll();
	}
	
	public void sacar(EquipoCompetencia aux){
		System.out.println("sacar");
		for (int i=0;i<equipoCompetencia.size();i++){
			if (aux.getCodigoEquipoCompetencia() == equipoCompetencia.get(i).getCodigoEquipoCompetencia()){
				equipoCompetencia.remove(i);
				System.out.println("consiguio");
			}
		}
	}
	
	//METODO PARA TRABAJAR EL CATALOGO EQUIPO
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		//se guarda la referencia al catalogo
		catalogo = c;
		//Se listan los equipos activos y se cargan mediante databinding (ver zul)
		
		//equipoCompetencia = servicioEquipoCompetencia.listarActivos();
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxEquipoCompetencia.getItems().size() != 0){
			lsbxEquipoCompetencia.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxEquipoCompetencia.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			EquipoCompetencia ec = equipoCompetencia.get(lsbxEquipoCompetencia.getSelectedIndex());
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
            //se le asigna el objeto divisa al formulario
			formulario.setVariable("equipoCompetencia", ec,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			if (id==1)
				Events.postEvent(new Event("onCatalogoCerrado1",formulario)); 
			else 
				Events.postEvent(new Event("onCatalogoCerrado2",formulario)); 
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
				Messagebox.show("Seleccione un Equipo", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
   
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onChanging$txtFiltro(){
		
		
		binder.loadAll();
	}
	
	
	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	
	
	
	
	

}
