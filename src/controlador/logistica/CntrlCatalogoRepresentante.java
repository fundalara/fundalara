package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.FamiliarComisionEquipo;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioFamiliarComisionEquipo;
import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

public class CntrlCatalogoRepresentante extends GenericForwardComposer {
	
	Component catalogoRepresentante;
	Component frmPlanificarActividad;
    IServicioPersona servicioPersona;
    IServicioFamiliarComisionEquipo servicioFamiliarComisionEquipo;
	BeanFactory beanFactory;
	DatoBasico tipoPersona = new DatoBasico();
    List<PersonaNatural> listaPersonaNatural;
    PersonaNatural personaNatural = new PersonaNatural();
    FamiliarComisionEquipo familiarComision;
    List<FamiliarComisionEquipo> listaFamiliarComision;
    
    Listbox lboxPersona;
	
//	public void onCreate$frmCatRepresentante(){
//		DatoBasico comision = (DatoBasico) frmPlanificarActividad.getVariable("comision",false);
//		listaFamiliarComision =  servicioFamiliarComisionEquipo.listarPorComision(comision);
//		
//		familiarComision.getFamiliarJugador().getFamiliar().getPersonaNatural();
//	}
	
	public void agregarRepresentantes(){
		
	}
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);
		catalogoRepresentante=comp;
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");		
	  
		}
		
//	public void onClick$btnGuardar() throws InterruptedException{
//		
//		//Se comprueba que se haya seleccionado un elemento de la lista
//		if (lboxPersona.getSelectedIndex() != -1) {
//			
//			//se obtiene la plantilla seleccionada
//			personaNatural = familiarComision.getFamiliarJugador().getFamiliar().getPersonaNatural();
//		
//			
//			//se obtiene la referencia del formulario
//			Component frmPlanificarMantenimiento = (Component) catalogoRepresentante.getVariable("frmPlanificarActividad",false);
//            
//			
//			//se le asigna el objeto plantilla al formulario
//			frmPlanificarActividad.setVariable("personaNatural", personaNatural,false);
//			
//			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
//			Events.sendEvent(new Event("onCatalogoRepresentanteCerrado",frmPlanificarActividad));          
//				
//			//se cierra el catalogo	
//			catalogoRepresentante.detach();
//
//		} else {
//				Messagebox.show("Seleccione un representante", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);
//
//		}
//
//	}
	
	
	public void onClick$btnGuardar() throws InterruptedException{
		  
		//System.out.println(persona.getPersonaNatural().getPrimerNombre());
		
		System.out.println("aquiiiiiiiiiiiiiiiiii");
		
		//Quitar esto luego
		Component frmPlanificarActividad = (Component) catalogoRepresentante.getVariable("frmPlanificarActividad",false);
		((Row) frmPlanificarActividad.getFellow("fila2")).setVisible(false);
		((Row) frmPlanificarActividad.getFellow("filaInvisible")).setVisible(true);
		
		
		catalogoRepresentante.detach();
		//--
		

	}

	public List<PersonaNatural> getListaPersonaNatural() {
		return listaPersonaNatural;
	}

	public void setListaPersonaNatural(List<PersonaNatural> listaPersonaNatural) {
		this.listaPersonaNatural = listaPersonaNatural;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}


	

}

