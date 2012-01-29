package controlador.administracion;

import java.util.ArrayList;
import java.util.List;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;

public class CntrlRCatalogoBusquedaPersona extends GenericForwardComposer {
	
	ServicioCuentaPagar servicioCuentaPagar;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioPersona servicioPersona;
	CuentaPagar cuentaPagar;
	PersonaJuridica personaJuridica;
	Persona persona;
	PersonaNatural personaNatural;
	
	List<CuentaPagar> facturas;
	List<PersonaJuridica> proveedores;
	List<Persona> personas = new ArrayList<Persona>();;
	
	AnnotateDataBinder binder;
	Window CatRepresentantes;
	
	
	Listbox lbxPersonas;
	Button btnSalir,btnAceptar;
	Combobox cbProveedor;
	Component CatRepre;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		CatRepre = comp;
//		cuentaPagar = new CuentaPagar();
//		personaJuridica = new PersonaJuridica();
		//facturas = servicioCuentaPagar.listarActivos();
		//proveedores = servicioPersonaJuridica.listar();
		persona = new Persona();
		personas = servicioPersona.listarActivos();
		System.out.println(personas);
		personaNatural = new PersonaNatural();
//		persona.getPersonaNatural().getPrimerApellido();
		
	}
	
	public void onClick$btnAceptar(){
		
		if (lbxPersonas.getSelectedIndex() != -1) {
					
					Persona pers = personas.get(lbxPersonas.getSelectedIndex());
				
					Component formulario = (Component) CatRepre.getVariable("formulario",false);

					formulario.setVariable("persona",pers,false);
					
					Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
					
					CatRepre.detach();
		} else {
			
		}
	}
	
	public ServicioCuentaPagar getServicioCuentaPagar() {
		return servicioCuentaPagar;
	}

	public void setServicioCuentaPagar(ServicioCuentaPagar servicioCuentaPagar) {
		this.servicioCuentaPagar = servicioCuentaPagar;
	}

	public ServicioPersonaJuridica getServicioPersonaJuridica() {
		return servicioPersonaJuridica;
	}

	public void setServicioPersonaJuridica(
			ServicioPersonaJuridica servicioPersonaJuridica) {
		this.servicioPersonaJuridica = servicioPersonaJuridica;
	}

	public ServicioPersona getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersona servicioPersona) {
		this.servicioPersona = servicioPersona;
	}

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public List<CuentaPagar> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<CuentaPagar> facturas) {
		this.facturas = facturas;
	}

	public List<PersonaJuridica> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<PersonaJuridica> proveedores) {
		this.proveedores = proveedores;
	}

	

	public Listbox getLbxPersonas() {
		return lbxPersonas;
	}

	public void setLbxPersonas(Listbox lbxPersonas) {
		this.lbxPersonas = lbxPersonas;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	



}
