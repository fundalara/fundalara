package controlador.logistica;

import java.util.List;


import modelo.DatoBasico;
import modelo.Persona;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;


import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividadPlanificada;


public class CntrlFrmCatalogoPersonalA extends GenericForwardComposer {
	
	Component catalogoPersonal;
	Component frmPlanificarActividad;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
IServicioPersona servicioPersona;
	BeanFactory beanFactory;
	DatoBasico tipoPersonal = new DatoBasico();
    List<Persona> listaPersona;
    Persona persona = new Persona();
    Listbox lboxPersonal;
	
	public void onCreate$frmCatPersonal(){
	//	DatoBasico tipoPersonal = (DatoBasico) frmPlanificarMantenimiento.getVariable("tipoPersonal",false);
	//	listaPersonal= servicioPersonalActividadPlanificada.listarPersonalMant(tipoPersonal);
	//	System.out.print(tipoPersonal.getDescripcion());
	}
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);
		catalogoPersonal=comp;
		tipoPersonal.setDescripcion("MANTENIMIENTO");
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");		
	
	    listaPersona = servicioPersona.listarPorTipos("MANTENIMIENTO");

		}
	
	public void onClick$btnGuardar() throws InterruptedException{
		  
		
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxPersonal.getSelectedIndex() != -1) {
			
			//se obtiene la plantilla seleccionada
			persona = listaPersona.get(lboxPersonal.getSelectedIndex());
			
			//se obtiene la referencia del formulario
			Component frmPlanificarActividad= (Component) catalogoPersonal.getVariable("frmPlanificarActividad",false);
            
			//se le asigna el objeto plantilla al formulario
			frmPlanificarActividad.setVariable("persona", persona,false);
			
			System.out.println(persona.getPersonaNatural().getPrimerNombre());
			
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoPersonalA",frmPlanificarActividad));  
			
			System.out.println("ya la envie senal");
				
			//se cierra el catalogo	
			catalogoPersonal.detach();

		} else {
				Messagebox.show("Seleccione una plantilla ", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}
	
	
	
}
