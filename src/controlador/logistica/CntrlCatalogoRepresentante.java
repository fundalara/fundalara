package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.PersonaNatural;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioPersona;


public class CntrlCatalogoRepresentante extends GenericForwardComposer {
	
	BeanFactory beanFactory;
	
	Component catalogoRepresentante;
	Component frmPadre;

	
    IServicioPersona servicioPersona;
    IServicioComisionFamiliar servicioComisionFamiliar;
    
	DatoBasico tipoPersona = new DatoBasico();
	DatoBasico comision = new DatoBasico();
	
    PersonaNatural personaNatural = new PersonaNatural();
    ComisionFamiliar comisionFamiliar = new ComisionFamiliar();
    
    List<ComisionFamiliar> listaComisionFamiliar = new ArrayList<ComisionFamiliar>();
    List<PersonaNatural> listaPersonaNatural;
    
    Listbox lboxRepresentante;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);
		catalogoRepresentante=comp;		    
		}
	
	public void onCreate$frmCatRepresentante(){
		DatoBasico comision = (DatoBasico) catalogoRepresentante.getVariable("comision",false);
		System.out.println(comision);
	    listaComisionFamiliar = servicioComisionFamiliar.listarRepresentantesPorComision(comision);
	}
		
	public void onClick$btnGuardar() throws InterruptedException{
		
		//Se comprueba que se haya seleccionado un elemento de la lista
		if (lboxRepresentante.getSelectedIndex() != -1) {
			
			//se obtiene la plantilla seleccionada
			personaNatural = comisionFamiliar.getFamiliarJugador().getFamiliar().getPersonaNatural();
		

			//se obtiene la referencia del formulario
			Component frmPadre = (Component) catalogoRepresentante.getVariable("frmPadre",false);
            
			System.out.println(personaNatural.getCedulaRif());
			//se le asigna el objeto plantilla al formulario
			frmPadre.setVariable("personaNatural", personaNatural,false);
			
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoRepresentanteCerrado",frmPadre));          
				
			//se cierra el catalogo	
			catalogoRepresentante.detach();

		} else {
				Messagebox.show("Seleccione un representante", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}

	public void onClick$btnSalir(){
		catalogoRepresentante.detach();
	}
	public ComisionFamiliar getComisionFamiliar() {
		return comisionFamiliar;
	}

	public void setComisionFamiliar(ComisionFamiliar comisionFamiliar) {
		this.comisionFamiliar = comisionFamiliar;
	}

	public List<ComisionFamiliar> getListaComisionFamiliar() {
		return listaComisionFamiliar;
	}

	public void setListaComisionFamiliar(
			List<ComisionFamiliar> listaComisionFamiliar) {
		this.listaComisionFamiliar = listaComisionFamiliar;
	}



	public DatoBasico getComision() {
		return comision;
	}

	public void setComision(DatoBasico comision) {
		this.comision = comision;
	}


}

