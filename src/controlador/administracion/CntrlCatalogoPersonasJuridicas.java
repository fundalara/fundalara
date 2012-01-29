package controlador.administracion;

import java.util.ArrayList;
import java.util.List;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioTipoDato;

public class CntrlCatalogoPersonasJuridicas extends GenericForwardComposer {
	Window frmCatalogoJuridica;

	Persona persona;
	PersonaJuridica personaJuridica;
	DatoBasico datoBasico;
	TipoDato tipoDato;

	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioPersona servicioPersona;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;

	List<Persona> personas = new ArrayList<Persona>();
	//List<Persona> listaPersonas = new ArrayList<Persona>();
	List<PersonaJuridica> listaPersonas = new ArrayList<PersonaJuridica>();
	AnnotateDataBinder binder;

	Listbox lbxPersonas;
	Button btnSalir, btnAceptar;
	Textbox txtFiltroCI, txtFiltroRazon;
	Combobox cbProveedor;
	Component catalogo;
	Component formulario;
	DatoBasico tipoPersona;

	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		catalogo = comp;
		String padre = "";
		padre = (String) comp.getParent().getAttribute("padre");
		txtFiltroCI.setText("");
		txtFiltroRazon.setText("");
		if (padre == "CLIENTE") {
			tipoPersona = servicioDatoBasico.buscarPorString("CLIENTE");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					 txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
					 .toString());
		}
		if (padre == "BENEFACTOR JURIDICO") {
			tipoPersona = servicioDatoBasico.buscarPorString("BENEFACTOR JURIDICO");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					 txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
					 .toString());
		}
		if (padre == "PROVEEDOR DE SERVICIO") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PROVEEDOR DE SERVICIO");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					 txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
					 .toString());
		}
		if (padre == "PROVEEDOR DE MATERIALES") {
			tipoPersona = servicioDatoBasico
					.buscarPorString("PROVEEDOR DE MATERIALES");
			listaPersonas = servicioPersonaJuridica.filtarPersonas(tipoPersona,
					 txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
					 .toString());
		}
		if (padre == "PERSONAS") {
			listaPersonas = new ArrayList<PersonaJuridica>();
			listaPersonas = servicioPersonaJuridica.filtarPersonasDistintas(txtFiltroCI.getText().toString(), txtFiltroRazon.getText()
					 .toString());
			//listaPersonas = servicioPersona
		}
		//binder.loadComponent(lbxPersonas);
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		catalogo.detach();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAceptar() {
		Window formularioProv = (Window) catalogo.getVariable("formulario",
				false);
		if (lbxPersonas.getSelectedIndex() != -1) {
			Persona pj = listaPersonas.get(lbxPersonas.getSelectedIndex()).getPersona();
			Component formularioPadre = catalogo.getParent();
			formularioPadre.setVariable("persona", pj, false);
			Events.sendEvent(new Event("onCierreJuridico", formularioPadre));

			catalogo.detach();
		}
		//
		
	}
//	// ------------------------------------------------------------------------------------------------------
//	public void onBlur$txtFiltroCI(){
//		listaPersonas = new ArrayList<Persona>();
//		persona = new Persona();
////		binder.loadComponent(lbxPersonas);
////		listaPersonas = servicioPersona.filtarPersonasJuridicas(tipoPersona, txtFiltroCI
////				.getText().toString().toUpperCase(), txtFiltroRazon.getText()
////				.toString().toUpperCase());
//		binder.loadAll();
//	}
//	
//	public void onBlur$txtFiltroRazon(){
//		listaPersonas = new ArrayList<Persona>();
//		persona = new Persona();
////		binder.loadComponent(lbxPersonas);
////		 listaPersonas = servicioPersona.filtarPersonasJuridicas(tipoPersona,
////		 txtFiltroCI.getText().toString().toUpperCase(), txtFiltroRazon.getText()
////		 .toString().toUpperCase());
//		binder.loadAll();
//	}
	
	
	// --------------- GETTERS AND SETTERS
	// ------------------------------------------------------------------------------------------------------
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Component getCatProv() {
		return catalogo;
	}

	public void setCatProv(Component catProv) {
		catalogo = catProv;
	}

	public List<PersonaJuridica> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaJuridica> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	// ------------------------------------------------------------------------------------------------------
}
