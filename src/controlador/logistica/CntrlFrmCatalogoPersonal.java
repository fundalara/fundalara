package controlador.logistica;

import java.util.List;

import javassist.expr.NewArray;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Window;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioPersona;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;

public class CntrlFrmCatalogoPersonal extends GenericForwardComposer {
	
	Component catalogoPersonal;
	Component formularioGeneral;
	IServicioPersonalActividadPlanificada servicioPersonalActividadPlanificada;
	IServicioPersona servicioPersona;
	BeanFactory beanFactory;
	DatoBasico tipoPersonal = new DatoBasico();
    List<Persona> listaPersona;
    Persona persona = new Persona();
    Listbox lboxPersonal;
    
    Window frmCatPersonal;
	
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
		//servicioPersonalActividadPlanificada =  (IServicioPersonalActividadPlanificada) beanFactory.getBean("servicioPersonalActividadPlanificada");
	    
		servicioPersona= (IServicioPersona) beanFactory.getBean("servicioPersona");
		// HAY QUE ARREGLAR EL DAO DE PERSONA PARA BUSCARLO POR EL CODIGO
	    listaPersona = servicioPersona.listarPorTipos("MANTENIMIENTO");
	    
	 
		//listaPersonal= servicioPersonalActividadPlanificada.listarPersonalMant(tipoPersonal);

	}

	
	public void onClick$btnGuardar() throws InterruptedException{
		
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxPersonal.getSelectedIndex() != -1) {
			
			//se obtiene la plantilla seleccionada
			persona = listaPersona.get(lboxPersonal.getSelectedIndex());
		 
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogoPersonal.getVariable("General",false);
            
			
			//se le asigna el objeto plantilla al formulario
			formulario.setVariable("persona", persona,false);
			
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoPersonalCerrado",formulario));          
				
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
	
	
	public void onClick$btnSalir(){
		this.frmCatPersonal.detach();
	}
	
	
}
